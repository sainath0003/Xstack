import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TraineeTrainingComponent } from '../trainee/components/trainee-training/trainee-training.component';
import { TrainerprofileComponent } from './components/trainerprofile/trainerprofile.component';
import { UpdateTrainerComponent } from './components/update-trainer/update-trainer.component';
import { ChangepasswordtrainerComponent } from './components/changepasswordtrainer/changepasswordtrainer.component';

const routes: Routes = [

  { path: 'training', component: TraineeTrainingComponent },  
  { path: '', component: TrainerprofileComponent },
  { path: 'updatetrainer', component: UpdateTrainerComponent },
  // { path: '**', component: PageNotFoundComponent },
  { path: 'changepassword', component: ChangepasswordtrainerComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TrainerRoutingModule { }
