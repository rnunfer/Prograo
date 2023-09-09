import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IndexComponent } from './index.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { AnimatedSearchComponent } from './components/animated-search/animated-search.component';
import { PrograoDescriptionComponent } from './components/prograo-description/prograo-description.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    IndexComponent,
    AnimatedSearchComponent,
    PrograoDescriptionComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule
  ],
  exports: []
})
export class IndexModule { }
