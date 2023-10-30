import { Component } from '@angular/core';
import { TrainerDto } from 'src/app/class/TrainerDto';
import { TrainerDtoForRead } from 'src/app/class/TrainerDtoForRead';
import { DataService } from 'src/app/service/data.service';

@Component({
  selector: 'app-update-trainer',
  templateUrl: './update-trainer.component.html',
  styleUrls: ['./update-trainer.component.css']
})
export class UpdateTrainerComponent {
email!:string;
  trainer: TrainerDto = {
    firstName: "",
    lastName: "",
    email: "",
    specialization:""
  };
  temp!:TrainerDtoForRead;
  constructor(private dataservice: DataService) { 
this.trainer=dataservice.getTrainer();
  }
  ngOnInit(): void {
    // this.dataservice.gettrainer(this.email).subscribe(
    //   data => {
    //     this.trainer = data;
    //   }
    // );
  }
updatetrainer(){
 
  this.dataservice.setTrainer(this.trainer)
  this.dataservice.updateTrainer(this.trainer).subscribe(
    data=>{
      this.trainer=data;
      console.log(this.trainer);
    }
  );
}

}
