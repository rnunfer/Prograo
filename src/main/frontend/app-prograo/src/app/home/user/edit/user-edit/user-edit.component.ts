import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/interfaces/User';
import { SecurityService } from 'src/app/security/security.service';
import { IInputConfiguration } from 'src/app/shared/input/IInputConfiguration';
import { Location } from 'src/app/interfaces/Location';
import { fadeIn, fadeOut } from 'src/app/shared/animation';
import { UserEditService } from '../user-edit.service';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css'],
  animations: [fadeIn, fadeOut]
})
export class UserEditComponent implements OnInit {

  private subscription: Subscription;
  user!: User;
  locationList: Location[] = [];
  filteredLocationList: Location[] = [];

  formUser: FormGroup = this.fb.group({
    name: new FormControl('', [ Validators.required, Validators.pattern('^[a-zA-ZÁáÀàÉéÈèÍíÌìÓóÒòÚúÙùÑñüÜ \-\']+') ]),
    title: new FormControl('', [ Validators.required ]),
    profilePhoto: new FormControl('', [ Validators.required ]),
    city: new FormControl('', [ Validators.required ]),
    country: new FormControl('', [ Validators.required ])
  });

  formLocations: FormGroup = this.fb.group({
    filterSearch: new FormControl(''),
  });

  confName: IInputConfiguration = {
    placeholder: 'Nombre',
    fontSize: 15,
    type: 'text',
    rowsNumberTextarea: 0,
    activateFormControl: true,
    formControl: this.formUser.controls['name']
  };

  confTitle: IInputConfiguration = {
    placeholder: 'Título',
    fontSize: 15,
    type: 'text',
    rowsNumberTextarea: 0,
    activateFormControl: true,
    formControl: this.formUser.controls['title']
  };

  constructor (
    private userEditService: UserEditService,
    private activatedRoute : ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private security: SecurityService
  ) {
    this.subscription = this.security.event$.subscribe((user: User) => {
      this.user = this.security.getUser();
      this.loadData();
    });
  }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.user = this.security.getUser();
    if (this.user != undefined && this.user.userType != 'guest') {
      this.formUser.controls['name'].setValue(this.user.name);
      this.formUser.controls['title'].setValue(this.user.title);
      this.formUser.controls['profilePhoto'].setValue(this.user.profilePhoto);
      this.formUser.controls['city'].setValue(this.user.city);
      this.formUser.controls['country'].setValue(this.user.country);

      this.userEditService.getAllLocation().subscribe((data: Location[]) => {
        this.locationList = data;
        this.filteredLocationList = data;
      });
    }
  }

  searchDataLocation(searchValue: string) {
    this.filteredLocationList = this.locationList.filter((location: Location) => {
      return location.city.toLowerCase().includes(searchValue.toLowerCase());
    })
    this.filteredLocationList = this.filteredLocationList.splice(0, 30);
  }

  async changeProfilePhoto(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      const formData = new FormData();
      formData.append('profilePhoto', file);
      
      this.userEditService.uploadProfilePhoto(formData).subscribe((result: string) => {
        this.formUser.controls['profilePhoto'].setValue(result);
        this.changeField('profilePhoto');
      });
    }
  }

  async changeLocation(location: Location) {
    this.formUser.controls['city'].setValue(location.city);
    this.formUser.controls['country'].setValue(location.country);
    this.formLocations.controls['filterSearch'].setValue("");
    if (!this.formUser.controls['city'].errors && !this.formUser.controls['country'].errors) {
      let userAux: User = { ...this.user };
      userAux['city'] = this.formUser.value['city'];
      userAux['country'] = this.formUser.value['country'];
      let result = await this.userEditService.editUser(userAux);
      if (result) {
        this.user = userAux;
      }
    }
  }

  async changeField(fieldName: string) {
    if (!this.formUser.controls[fieldName].errors) {
      let userAux: User = { ...this.user };
      userAux[fieldName] = this.formUser.value[fieldName];
      await this.userEditService.editUser(userAux);
    }
  }
}
