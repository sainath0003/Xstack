import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddtrainingComponent } from './addtraining.component';

describe('AddtrainingComponent', () => {
  let component: AddtrainingComponent;
  let fixture: ComponentFixture<AddtrainingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddtrainingComponent]
    });
    fixture = TestBed.createComponent(AddtrainingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
