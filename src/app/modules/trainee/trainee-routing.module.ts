import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AddtrainingComponent } from './components/addtraining/addtraining.component';
import { TraineeTrainingComponent } from './components/trainee-training/trainee-training.component';
import { UpdateTraineeComponent } from './components/update-trainee/update-trainee.component';
import { TraineeprofileComponent } from './components/traineeprofile/traineeprofile.component';
import { PageNotFoundComponent } from 'src/app/MyComponents/page-not-found/page-not-found.component';
import { ChangePasswordComponent } from 'src/app/modules/trainee/components/change-password/change-password.component';

const routes: Routes = [

  { path: 'training', component: TraineeTrainingComponent },  
  { path: '', component: TraineeprofileComponent },
  { path: 'updatetrainee', component: UpdateTraineeComponent },
  { path: 'addtraining', component: AddtrainingComponent },
  // { path: '**', component: PageNotFoundComponent },
  { path: 'changepassword', component: ChangePasswordComponent }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TraineeRoutingModule { }
