import { Component, OnInit } from '@angular/core';
import { TraineeDto } from 'src/app/class/TraineeDto';
import { DataService } from 'src/app/service/data.service';

@Component({
  selector: 'app-update-trainee',
  templateUrl: './update-trainee.component.html',
  styleUrls: ['./update-trainee.component.css']
})
export class UpdateTraineeComponent implements OnInit {
email!:string;
  trainee: TraineeDto = {
    firstName: "",
    lastName: "",
    email: "",
    dob: "",
    address: "",
  };

  constructor(private dataservice: DataService) {

    this.trainee = {
      firstName: localStorage.getItem('trainee-firstName') || '',
      lastName: localStorage.getItem('trainee-lastName') || '',
      dob: localStorage.getItem('trainee-dob') || '',
      address: localStorage.getItem('trainee-address') || '',
      email: localStorage.getItem('trainee-email') || '',
   
    }
    // this.dataservice.gettrainee(this.email).subscribe(
    //   data => {
    //     this.trainee = data;
    //   }
    // );
   }
  ngOnInit(): void {
    
  }
updatetrainee(){
  localStorage.setItem('trainee-firstName', this.trainee.firstName);
  localStorage.setItem('trainee-lastName', this.trainee.lastName);
  localStorage.setItem('trainee-dob', this.trainee.dob);
  localStorage.setItem('trainee-address', this.trainee.address);
  localStorage.setItem('trainee-email', this.trainee.email);
 
  this.dataservice.updateTrainee(this.trainee).subscribe(
    data=>{

      this.trainee=data;
      console.log(this.trainee);
    }
  );
}
}
