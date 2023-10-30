import { Component, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';
import { TrainerDtoForRead } from 'src/app/class/TrainerDtoForRead';
import { TrainingDto } from 'src/app/class/TrainingDto';
import { TrainingDtoForWrite } from 'src/app/class/TrainingDtoForWrite';
import { AuthService } from 'src/app/service/auth.service';
import { DataService } from 'src/app/service/data.service';

@Component({
  selector: 'app-trainerprofile',
  templateUrl: './trainerprofile.component.html',
  styleUrls: ['./trainerprofile.component.css']
})
export class TrainerprofileComponent {
 
  trainer: TrainerDtoForRead = new TrainerDtoForRead();
  traininginput: TrainingDto = new TrainingDto();
  training!: TrainingDtoForWrite[];
  email!:string;
  @Output() gettraining: EventEmitter<TrainingDtoForWrite[]> = new EventEmitter();

  constructor(private router: Router,private _dataservice:DataService,private authservice:AuthService) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation && navigation.extras.state) {
      this.trainer = navigation.extras.state['trainer'];
      console.log(navigation.extras.state['trainer'])
    }
    this.trainer=_dataservice.getTrainer();
    console.log(this.trainer)
   }
  ngOnInit(): void {
    // this._dataservice.gettrainer(this.email).subscribe(

    //   data => {
    //     this.trainer = data;
    //     console.log(this.trainer);
    //   }
    // );

    // console.log(this.trainer.email)
    // this.traininginput = {
    //   userName: this.trainer.email,
    //   periodFrom: "string",
    //   periodTo: "string",
    //   trainerName: this.trainer.email,
    //   trainingType: "string"
    // };

    // console.log(this.traininginput);
    // console.log(this.traininginput.userName);


  }

  getTrainingList(){
    this.router.navigate(['/trainer/training'], {
      state:{
        userName:this.trainer.email,
        
       }
     });
  }

  deleteTrainer(){
    this._dataservice.deletetrainer(this.trainer.email).subscribe(
      response=>{
        console.log(response);
      }
    );
    this.authservice.logout();
    
  }
}
