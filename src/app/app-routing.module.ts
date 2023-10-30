import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './MyComponents/login/login.component';
import { RegisterComponent } from './MyComponents/register/register.component';
import { WelcomepageComponent } from './MyComponents/welcomepage/welcomepage.component';
import { authGuard } from './guards/trainee/auth.guard';
import { authtrainerGuard } from './guards/trainer/authtrainer.guard';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: RegisterComponent },
  { path: '', component: WelcomepageComponent },

  // { path: '**', redirectTo: '', pathMatch: 'full' },

  { path: 'trainee', canActivate:[authGuard],loadChildren: () => import('./modules/trainee/trainee.module').then((m) => m.TraineeModule) },
  { path: 'trainer',canActivate:[authtrainerGuard], loadChildren: () => import('./modules/trainer/trainer.module').then((m) => m.TrainerModule) },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
