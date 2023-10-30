import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangepasswordtrainerComponent } from './changepasswordtrainer.component';

describe('ChangepasswordtrainerComponent', () => {
  let component: ChangepasswordtrainerComponent;
  let fixture: ComponentFixture<ChangepasswordtrainerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChangepasswordtrainerComponent]
    });
    fixture = TestBed.createComponent(ChangepasswordtrainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
