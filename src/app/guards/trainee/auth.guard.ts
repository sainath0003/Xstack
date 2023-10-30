import { Inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
// import { AuthService } from 'src/app/service/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const auth:AuthService=Inject(AuthService);
  const router:Router=Inject(Router);
  if(state.url.includes('trainee') && localStorage.getItem('token')!="null"){
  return true;
  }
  return false;
};
