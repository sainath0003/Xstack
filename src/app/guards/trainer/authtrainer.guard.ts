
import { Inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';


export const authtrainerGuard: CanActivateFn = (route, state) => {

  if (state.url.includes('trainer') && localStorage.getItem('token') != "null") {
    return true;
  }

  return false;
};
