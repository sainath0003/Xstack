import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TraineeprofileComponent } from './traineeprofile.component';

describe('TraineeprofileComponent', () => {
  let component: TraineeprofileComponent;
  let fixture: ComponentFixture<TraineeprofileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TraineeprofileComponent]
    });
    fixture = TestBed.createComponent(TraineeprofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
