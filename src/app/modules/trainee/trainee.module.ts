import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TraineeRoutingModule } from './trainee-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AddtrainingComponent } from './components/addtraining/addtraining.component';
import { TraineeprofileComponent } from './components/traineeprofile/traineeprofile.component';
import { UpdateTraineeComponent } from './components/update-trainee/update-trainee.component';
import { TraineeTrainingComponent } from './components/trainee-training/trainee-training.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { TraineeNavbarComponent } from './components/trainee-navbar/trainee-navbar.component';


@NgModule({
  declarations: [
AddtrainingComponent,
TraineeprofileComponent,
UpdateTraineeComponent,
TraineeTrainingComponent,
ChangePasswordComponent,
TraineeNavbarComponent,


  ],
  imports: [
    CommonModule,
    TraineeRoutingModule,
    FormsModule,HttpClientModule
  ]
})
export class TraineeModule { }
