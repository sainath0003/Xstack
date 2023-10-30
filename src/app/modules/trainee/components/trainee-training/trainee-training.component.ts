import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TrainingDto } from 'src/app/class/TrainingDto';
import { TrainingDtoForWrite } from 'src/app/class/TrainingDtoForWrite';
import { DataService } from 'src/app/service/data.service';

@Component({
  selector: 'app-trainee-training',
  templateUrl: './trainee-training.component.html',
  styleUrls: ['./trainee-training.component.css']
})
export class TraineeTrainingComponent implements OnInit{
  training!: TrainingDtoForWrite[];
  traininginput: TrainingDto = new TrainingDto();
  userName!:string;  
constructor(private dataservice:DataService,private router: Router){

  const navigation = this.router.getCurrentNavigation();
  if (navigation && navigation.extras.state) {
    this.userName = navigation.extras.state['userName'];
    console.log(navigation.extras.state['userName'])
  }
  console.log(this.userName)
}






ngOnInit(): void {
 
    this.traininginput = {
      userName: this.userName,
      periodFrom: "string",
      periodTo: "string",
      trainerName: "string",
      trainingType: "string"
    };
    this.dataservice.getTraineeTrainings(this.traininginput).subscribe(
      data=>{
        this.training=data;
      }
    );
    this.dataservice.getTrainerTrainings(this.traininginput).subscribe(
      data=>{
        this.training=data;
      }
    );
    // this.training = this.dataservice.training;
  }

}
