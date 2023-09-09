import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserBreadcrumbComponent } from './user-breadcrumb/user-breadcrumb.component';
import { UserMenuComponent } from './user-menu/user-menu.component';
import { ModalComponent } from './modal/modal.component';
import { ModalService } from './modal/modal.service';
import { InputComponent } from './input/input.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { LoginRegisterComponent } from './login-register/login-register.component';
import { FooterComponent } from './footer/footer.component';
import { StarsCalificationComponent } from './stars-calification/stars-calification.component';
import { DropdownComponent } from './dropdown/dropdown.component';
import { LocationComponent } from './location/location.component';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { FilterListComponent } from './filter-list/filter-list.component';
import { ButtonComponent } from './button/button.component';
import { SelectComponent } from './select/select.component';

@NgModule({
  declarations: [
    HeaderComponent,
    LoginRegisterComponent,
    FooterComponent,
    StarsCalificationComponent,
    UserBreadcrumbComponent,
    UserMenuComponent,
    ModalComponent,
    InputComponent,
    DropdownComponent,
    LocationComponent,
    SearchBarComponent,
    FilterListComponent,
    ButtonComponent,
    SelectComponent,
    SelectComponent
  ],
  exports: [
    HeaderComponent,
    LoginRegisterComponent,
    FooterComponent,
    StarsCalificationComponent,
    UserBreadcrumbComponent,
    UserMenuComponent,
    ModalComponent,
    InputComponent,
    DropdownComponent,
    LocationComponent,
    SearchBarComponent,
    FilterListComponent,
    ButtonComponent,
    SelectComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule
  ],
  providers: [
    ModalService
  ]
})
export class SharedModule { }
