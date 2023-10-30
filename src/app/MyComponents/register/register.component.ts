import { Component, OnInit } from '@angular/core';
import { TraineeDto } from 'src/app/class/TraineeDto';
import { TrainerDto } from 'src/app/class/TrainerDto';
import { UserDto } from 'src/app/class/UserDto';
import { DataService } from 'src/app/service/data.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  loginForm!: HTMLElement | null;
  signupForm!: HTMLElement | null;
  credentialsModal!: HTMLElement | null;

  current: string='Trainer';

  firstName!:string;
  lastName!:string;
  email!:string;
  active:boolean=true;
  specialization:string="";
  user:UserDto = new UserDto();
  traineeDto:TraineeDto = new TraineeDto();
  trainerDto:TrainerDto = new TrainerDto();
  dob!:Date;

  constructor(private _dataservice: DataService) { }
  ngOnInit(): void {

    this.loginForm = document.querySelector('.login-form');
    this.signupForm = document.querySelector('.signup-form');

   this.credentialsModal = document.getElementById('credentials') as HTMLElement;
  }
  loginFormElement = document.getElementById('login-form') as HTMLFormElement;
  signupFormElement = document.getElementById('signup-form') as HTMLFormElement;



  switch() {

    if (this.loginForm && this.signupForm) {
      if (this.loginForm.style.display === 'block') {
        this.loginForm.style.display = 'none';
        this.signupForm.style.display = 'block';
        this.current = "Trainee"

      } else {
        this.signupForm.style.display = 'none';
        this.loginForm.style.display = 'block';
        this.current = "Trainer"
      }
    }
  }

  signup() {
    if (this.loginForm && this.signupForm) {
      if (this.loginForm.style.display === 'block') {
        this.loginForm.style.display = 'none';
        this.signupForm.style.display = 'block';
        
        // this.loginForm.style.backgroundColor = 'transparent'
        this.current = "Student"

      }
    }
  }

  login() {
    if (this.loginForm && this.signupForm) {
      if (this.signupForm.style.display === 'block') {
        this.signupForm.style.display = 'none';
        this.loginForm.style.display = 'block';
        // this.loginForm.style.backgroundColor = 'white'
        // this.signupForm.style.backgroundColor = 'transparent'
        this.current = "Trainer"
      }

    }
  }

  registerTrainee(){
this.traineeDto.firstName=this.firstName;
this.traineeDto.lastName=this.lastName;
this.traineeDto.email=this.email;
this.traineeDto.address="hello";
this.traineeDto.dob=this.dob+"";

this._dataservice.registerTrainee(this.traineeDto).subscribe(
  data=>{
    console.log(data);
    this.user=data;
  }
);
this.showCredentials();


  }

  registerTrainer(){
    this.trainerDto.firstName=this.firstName;
    this.trainerDto.lastName=this.lastName;
    this.trainerDto.email=this.email;
    this.trainerDto.specialization=this.specialization;
    
    
    this._dataservice.registerTrainer(this.trainerDto).subscribe(
      data=>{
        console.log(data);
        this.user=data;
      }
    );

this.showCredentials();
    
  }

showCredentials(){
  if (this.credentialsModal) {
    if (this.credentialsModal.style.display === 'none') {
      
      this.credentialsModal.style.display = 'block';

    }
  }
}

closeCredentails(){
  if (this.credentialsModal) {
    if (this.credentialsModal.style.display === 'block') {
      
      this.credentialsModal.style.display = 'none';

    }
  }
}
}
