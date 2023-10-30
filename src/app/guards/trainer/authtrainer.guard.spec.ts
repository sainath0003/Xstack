import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { authtrainerGuard } from './authtrainer.guard';

describe('authtrainerGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => authtrainerGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
