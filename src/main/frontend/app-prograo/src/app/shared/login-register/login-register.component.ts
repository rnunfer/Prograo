import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { IInputConfiguration } from 'src/app/shared/input/IInputConfiguration';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SecurityService } from 'src/app/security/security.service';
import { ModalService } from '../modal/modal.service';
import { ButtonConfiguration } from '../button/buttonConfiguration';
import { SelectConfiguration } from '../select/SelectConfiguration';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-register',
  templateUrl: './login-register.component.html',
  styleUrls: ['./login-register.component.css']
})
export class LoginRegisterComponent implements OnInit, OnDestroy {

  login: boolean = true;
  register: boolean = false;
  showLoginErrorMessage: boolean = false;
  showRegisterErrorMessage: boolean = false;

  formLogin: FormGroup = this.fb.group({
    email: new FormControl(''),
    password: new FormControl('')
  });

  formRegister: FormGroup = this.fb.group({
    userType: new FormControl('', [ Validators.required ]),
    email: new FormControl('', [ Validators.required, Validators.email ]),
    password1: new FormControl('', [ Validators.required ]),
    password2: new FormControl('', [ Validators.required ])
  });

  buttonLoginConf: ButtonConfiguration = {
    value: 'Iniciar Sesión',
    type: 'primary',
    fontSize: 16,
    full: true
  }

  buttonRegisterConf: ButtonConfiguration = {
    value: 'Registrarse',
    type: 'primary',
    fontSize: 16,
    full: true
  }

  loginEmailConfiguration: IInputConfiguration = {
    placeholder: 'Correo',
    fontSize: 16,
    type: 'text',
    rowsNumberTextarea: 0,
    activateFormControl: false,
    formControl: this.formLogin.controls['email']

  }

  loginPasswordConfiguration: IInputConfiguration = {
    placeholder: 'Contraseña',
    fontSize: 16,
    type: 'password',
    rowsNumberTextarea: 0,
    activateFormControl: false,
    formControl: this.formLogin.controls['password']

  }

  registerUserTypeConfiguration: SelectConfiguration = {
    fontSize: 16,
    activateFormControl: true,
    formControl: this.formRegister.controls['userType'],
    options: [
      {
        value: "freelancer",
        label: "Freelancer"
      },
      {
        value: "seeker",
        label: "Contratador"
      }
    ]
  }

  registerEmailConfiguration: IInputConfiguration = {
    placeholder: 'Correo',
    fontSize: 16,
    type: 'text',
    rowsNumberTextarea: 0,
    activateFormControl: true,
    formControl: this.formRegister.controls['email']

  }

  registerPasswordConfiguration: IInputConfiguration = {
    placeholder: 'Contraseña',
    fontSize: 16,
    type: 'password',
    rowsNumberTextarea: 0,
    activateFormControl: true,
    formControl: this.formRegister.controls['password1']

  }

  registerPassword2Configuration: IInputConfiguration = {
    placeholder: 'Repita Contraseña',
    fontSize: 16,
    type: 'password',
    rowsNumberTextarea: 0,
    activateFormControl: true,
    formControl: this.formRegister.controls['password2']

  }

  constructor ( private fb: FormBuilder, private security: SecurityService, private modalService: ModalService, private router: Router ) {}

  ngOnInit() {
    this.formLogin.reset();
    this.formRegister.reset();
    //this.security.login("correo4@gmail.com", "12345");
  }

  ngOnDestroy() {}

  changeToLogin() {
    this.login = true;
    this.register = false;
  }

  changeToRegister() {
    this.login = false;
    this.register = true;
  }

  async submitLogin() {
    let result: boolean = await this.security.login(this.formLogin.controls['email'].value, this.formLogin.controls['password'].value);
    if (result) {
      this.showLoginErrorMessage = false;
      this.modalService.closeModalAction("loginRegister");
      this.formLogin.reset();
        this.formRegister.reset();
    } else {
      this.showLoginErrorMessage = true;
    }
  }

  async submitRegister() {
    if (this.formRegister.valid && this.formRegister.controls['password1'].value == this.formRegister.controls['password2'].value) {
      let result: boolean = await this.security.register(this.formRegister.controls['email'].value, this.formRegister.controls['password1'].value, this.formRegister.controls['userType'].value);
      if (result) {
        this.formLogin.reset();
        this.formRegister.reset();
        this.showRegisterErrorMessage = false;
        this.modalService.closeModalAction("loginRegister");
        this.router.navigate(['/user/configuration']);
      } else {
        this.showRegisterErrorMessage = true;
      }
    } else {
      this.formRegister.markAllAsTouched();
      this.showRegisterErrorMessage = true;
    }
  }

}
