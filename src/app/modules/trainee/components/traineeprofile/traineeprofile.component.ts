import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { TraineeDtoForRead } from 'src/app/class/TraineeDtoForRead';
import { TrainingDto } from 'src/app/class/TrainingDto';
import { TrainingDtoForWrite } from 'src/app/class/TrainingDtoForWrite';
import { DataService } from 'src/app/service/data.service';
import { ActivatedRoute } from '@angular/router';
import { Router, NavigationExtras } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-traineeprofile',
  templateUrl: './traineeprofile.component.html',
  styleUrls: ['./traineeprofile.component.css']
})
export class TraineeprofileComponent implements OnInit {


  trainee: TraineeDtoForRead = new TraineeDtoForRead();
  traininginput: TrainingDto = new TrainingDto();
  training!: TrainingDtoForWrite[];
  email!: string;
  @Output() gettraining: EventEmitter<TrainingDtoForWrite[]> = new EventEmitter();

  constructor(private router: Router,private dataservice:DataService,private authservice:AuthService) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation && navigation.extras.state) {
      this.trainee = navigation.extras.state['trainee'];
      console.log(navigation.extras.state['trainee'])
    }
    console.log(this.trainee)
   
    this.trainee =dataservice.getTrainee();
  
  }
  ngOnInit(): void {

  }

  getTraining() {

    this.router.navigate(['/trainee/training'], {
      state: {
        userName: this.trainee.email

      }
    });
  }
deleteTrainee(){
  console.log(this.trainee.email)
  this.dataservice.deletetrainee(this.trainee.email).subscribe(
    respone=>{
      console.log(respone);
    }
  );
  console.log("over")
  this.authservice.logout();
  
  
}
}


