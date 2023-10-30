import { Component } from '@angular/core';
import { TrainerDto } from 'src/app/class/TrainerDto';
import { TrainingDtoForNewTraining } from 'src/app/class/TrainingDtoForNewTraining';
import { DataService } from 'src/app/service/data.service';

@Component({
  selector: 'app-addtraining',
  templateUrl: './addtraining.component.html',
  styleUrls: ['./addtraining.component.css']
})
export class AddtrainingComponent {
  traineeUserName: string = '';
  trainerUserName: string = '';
  trainingName: string = '';
  trainingType: string = '';
  trainingDuration: number = 0;
  trainingDate!:Date;

  trainers!:string[];
constructor(private _dataservice:DataService){
  this.traineeUserName=localStorage.getItem('trainee-email')||"";

}

  addNewTraining() {
    const trainingData: TrainingDtoForNewTraining = {
      traineeUserName: this.traineeUserName,
      trainerUserName: this.trainerUserName,
      trainingName: this.trainingName,
      trainingType: this.trainingType,
      trainingDuration: this.trainingDuration
    };

    this._dataservice.addTraining(trainingData).subscribe(
      respone=>{
        console.log(respone);
      }
    );
  }
getTrainers(){
  this._dataservice.getByTrainingType(this.trainingType).subscribe(
    response=>{
      this.trainers=response;

  });
}

}