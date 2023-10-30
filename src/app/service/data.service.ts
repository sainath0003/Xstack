import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserDto } from '../class/UserDto';
import { TraineeDto } from '../class/TraineeDto';
import { TrainerDto } from '../class/TrainerDto';
import { TrainingDto } from '../class/TrainingDto';
import { TrainingDtoForWrite } from '../class/TrainingDtoForWrite';
import { TrainingDtoForNewTraining } from '../class/TrainingDtoForNewTraining';
import { AuthRequest } from '../class/AuthRequest';
import { TraineeDtoForRead } from '../class/TraineeDtoForRead';
import { TrainerDtoForRead } from '../class/TrainerDtoForRead';
import { UserDtoForPasswordChange } from '../class/UserDtoPasswordChange';


@Injectable({
  providedIn: 'root',
})
export class DataService {
  training!:TrainingDtoForWrite[];
  loginsucess:boolean=false;
  constructor(private http: HttpClient) { }
  auth!:AuthRequest;
  userType!:string;

  login(user:UserDto): Observable<any> {
this.auth= new AuthRequest(user.userName,user.password);
     return this.http.post('http://localhost:9000/gymapp/user/login', user);

  }

  changepassword(user:UserDtoForPasswordChange):Observable<any>{
    return this.http.put('http://localhost:9000/gymapp/user/changepassword', user, {  observe: 'response' });
  }

  gettrainer(userName:string):Observable<any>{
    return this.http.get("http://localhost:9000/gymapp/trainer/view?userName="+userName);
  }
  getByTrainingType(trainingType:string):Observable<any>{
    return this.http.get("http://localhost:9000/gymapp/trainer/view/training?trainingType="+trainingType);
  }

  gettrainee(userName:string):Observable<any>{
    return this.http.get("http://localhost:9000/gymapp/trainee/view?userName="+userName);
  }

  
  deletetrainee(userName:string):Observable<any>{
    return this.http.delete("http://localhost:9000/gymapp/trainee/delete?userName="+userName);
  }
  deletetrainer(userName:string):Observable<any>{
    return this.http.delete("http://localhost:9000/gymapp/trainer/delete?userName="+userName);
  }

  registerTrainee(trainee:TraineeDto):Observable<any>{
    return this.http.post('http://localhost:9000/gymapp/trainee/register',trainee);
  }

  registerTrainer(trainer:TrainerDto):Observable<any>{
    return this.http.post('http://localhost:9000/gymapp/trainer/register',trainer);
  }

  getTraineeTrainings(training:TrainingDto):Observable<any>{
    return this.http.post('http://localhost:9000/gymapp/trainee/view/trainingList',training);
  }
  getTrainerTrainings(training:TrainingDto):Observable<any>{
    return this.http.post('http://localhost:9000/gymapp/trainer/view/trainingList',training);
  }
  updateTrainee(trainee:TraineeDto):Observable<any>{
    return this.http.put('http://localhost:9000/gymapp/trainee/update',trainee);
  } 

  updateTrainer(trainer:TrainerDto):Observable<any>{
    return this.http.put('http://localhost:9000/gymapp/trainer/update',trainer);
  }

  addTraining(training:TrainingDtoForNewTraining){
    return this.http.post('http://localhost:9000/gymapp/training/add',training);
  }


  setTrainee(trainee:TraineeDtoForRead){
    localStorage.setItem('trainee-firstName', trainee.firstName);
    localStorage.setItem('trainee-lastName', trainee.lastName);
    localStorage.setItem('trainee-dob', trainee.dob);
    localStorage.setItem('trainee-address', trainee.address);
    localStorage.setItem('trainee-email', trainee.email);
    localStorage.setItem('trainee-active', trainee.isActive +"");

  }

  setTrainer(trainer:TrainerDto){
    localStorage.setItem('trainer-firstName', trainer.firstName);
    localStorage.setItem('trainer-lastName', trainer.lastName);
    localStorage.setItem('trainer-specialization', trainer.specialization);
    localStorage.setItem('trainer-email', trainer.email);
    localStorage.setItem('trainer-active', true+"");
  }

  getTrainer():TrainerDtoForRead{
    return {
      firstName: localStorage.getItem('trainer-firstName') || '',
      lastName: localStorage.getItem('trainer-lastName') || '',
      specialization: localStorage.getItem('trainer-specialization') || '',
      email: localStorage.getItem('trainer-email') || '',
      active: (localStorage.getItem('trainer-active') == "true")
    }
  }

  getTrainee():TraineeDtoForRead{

    return {
      firstName: localStorage.getItem('trainee-firstName') || '',
      lastName: localStorage.getItem('trainee-lastName') || '',
      dob: localStorage.getItem('trainee-dob') || '',
      address: localStorage.getItem('trainee-address') || '',
      email: localStorage.getItem('trainee-email') || '',
      isActive: (localStorage.getItem('trainee-active') == "true")
    }
  }
}
