import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TraineeTrainingComponent } from './trainee-training.component';

describe('TraineeTrainingComponent', () => {
  let component: TraineeTrainingComponent;
  let fixture: ComponentFixture<TraineeTrainingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TraineeTrainingComponent]
    });
    fixture = TestBed.createComponent(TraineeTrainingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
