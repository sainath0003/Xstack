import { Component, OnInit } from '@angular/core';
import { TraineeDtoForRead } from 'src/app/class/TraineeDtoForRead';
import { TrainerDtoForRead } from 'src/app/class/TrainerDtoForRead';
import { UserDto } from 'src/app/class/UserDto';
import { DataService } from 'src/app/service/data.service';
import { Router, NavigationExtras } from '@angular/router';
import { TokenInterceptor } from 'src/app/service/token-interceptor.service';
import { TokenClass } from 'src/app/class/Token';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  uniquechar!: string;
  checkchar!: string;
  email!: string;
  password!: string;
  userType!: string;
  token!: TokenClass;
  trainee: TraineeDtoForRead = {
    firstName: "John",
    lastName: "Doe",
    email: "johndoe@gmail.com",
    dob: "",
    address: "",
    isActive: false
  };
  trainer: TrainerDtoForRead = {
    firstName: "John",
    lastName: "Doe",
    specialization: "Yoga",
    email: "johndoe@gmail.com",
    active: true,
  };
  navigationExtras: NavigationExtras = {
    state: {

    }
  };

  constructor(private _dataservice: DataService, private router: Router, private tokenservice: TokenInterceptor, private authservice: AuthService) { }
  ngOnInit(): void {
    //localStorage.setItem('token',String(null))
console.log(this.authservice.isLoggedIn())
console.log(localStorage.getItem('token')!="null")

    if (this.authservice.isLoggedIn() && localStorage.getItem('token')!="null") {
      
      this.router.navigate(['../'], { relativeTo: this.router.routerState.root });

      if ( localStorage.getItem('userType') == "Trainee") {
        this.router.navigateByUrl("/trainee")
      }
      if (localStorage.getItem('userType') == "Trainer") {
        this.router.navigateByUrl("/trainer")
      }
    }
    this.generate();

  }
  onUserTypeChange(event: any) {
    this.userType = event.target.value;
  }
  generate() {
    let uniquechar = "";

    const randomchar =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // Generate captcha for length of
    // 5 with random character
    for (let i = 1; i < 5; i++) {
      uniquechar += randomchar.charAt(
        Math.random() * randomchar.length)
    }
    this.uniquechar = uniquechar;
  }

  printmsg() {
    console.log(this.checkchar);
    console.log(this.uniquechar);
    var user: UserDto;
    if (this.checkchar == this.uniquechar) {
      console.log(true);
      user = {
        userName: this.email,
        password: this.password
      }
      console.log("enetering")
      this._dataservice.login(user).subscribe(

        response => {



          this.token = response;
          console.log("enetered");
          console.log("responese value" + this.token);


          if (this.token != null) {
            this.tokenservice.changeToken(response.token);
            this.authservice.setToken(this.token.token);
          
              
            
            localStorage.setItem('userType', this.userType);
            if (this.userType == "Trainee") {
              
              console.log("trainee")
              console.log(user)
              this.gettrainee(user);
            }
            else {
              this.gettrainer(user);
            }
          } else {
            console.log('Status is not 200 OK');
            alert("login fail");
          }
        }
      );


      console.log(this.trainer);
    }

  }

  private gettrainer(user: UserDto) {
    this._dataservice.gettrainer(user.userName).subscribe(
      data => {
        console.log("2" + data);
        if (data != null) {
          console.log("trainer");
          this.trainer = data;
      this._dataservice.setTrainer(this.trainer);
        
          console.log(this.trainer);

          this.router.navigate(['/trainer'], {
            state: {
              trainer: this.trainer
            }
          });

        }
      }
    );
  }

  private gettrainee(user: UserDto) {
    this._dataservice.gettrainee(user.userName).subscribe(
      data => {
        console.log("1" + data);
        if (data != null) {
          console.log("trainee");
          this.trainee = data;
     this._dataservice.setTrainee(this.trainee);
          console.log(this.trainee);
          this.navigationExtras = {
            state: {
              trainee: this.trainee
            }
          };
          this.router.navigate(['/trainee'], {
            state: {
              trainee: this.trainee
            }
          });
        }
      }
    );
  }
}
