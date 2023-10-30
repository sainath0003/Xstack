import { Component } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-trainee-navbar',
  templateUrl: './trainee-navbar.component.html',
  styleUrls: ['./trainee-navbar.component.css']
})
export class TraineeNavbarComponent {
constructor(private authservice:AuthService){}

logout(){
  this.authservice.logout();
}
}
