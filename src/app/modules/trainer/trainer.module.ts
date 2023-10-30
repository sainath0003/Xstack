import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TrainerRoutingModule } from './trainer-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { TrainerprofileComponent } from './components/trainerprofile/trainerprofile.component';
import { UpdateTrainerComponent } from './components/update-trainer/update-trainer.component';
import { ChangePasswordComponent } from '../trainee/components/change-password/change-password.component';
import { ChangepasswordtrainerComponent } from './components/changepasswordtrainer/changepasswordtrainer.component';
import { TrainerNavbarComponent } from './components/trainer-navbar/trainer-navbar.component';


@NgModule({
  declarations: [
    TrainerprofileComponent,
    UpdateTrainerComponent,
    ChangepasswordtrainerComponent,
    TrainerNavbarComponent,
   
  ],
  imports: [
    CommonModule,
    TrainerRoutingModule,
    FormsModule,HttpClientModule
  ]
})
export class TrainerModule { }
