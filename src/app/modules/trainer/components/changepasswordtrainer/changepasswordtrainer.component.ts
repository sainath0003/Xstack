import { Component } from '@angular/core';
import { UserDtoForPasswordChange } from 'src/app/class/UserDtoPasswordChange';
import { DataService } from 'src/app/service/data.service';

@Component({
  selector: 'app-changepasswordtrainer',
  templateUrl: './changepasswordtrainer.component.html',
  styleUrls: ['./changepasswordtrainer.component.css']
})
export class ChangepasswordtrainerComponent {
  currentPassword!: string;
  newPassword!: string;
  confirmPassword!: string;
  userName!: string;
  constructor(private dataservice: DataService) {
    this.userName = localStorage.getItem('trainer-email') || "";
    console.log("hello"+this.userName)
   }
  changePassword() {
    console.log("hello"+this.newPassword)
    console.log("hello"+this.currentPassword)
    
    if (this.newPassword === this.confirmPassword) {
      // Implement password change logic here, e.g., make an HTTP request to your backend
      
      this.dataservice.changepassword(new UserDtoForPasswordChange(this.userName, this.currentPassword, this.newPassword)).subscribe(
        response => {
          console.log(response)
          if (response.status == 200) {
            console.log('Successfully Chnaged the Password.');
          } else {
            console.log('Unable to Change the Password');
          }
        });

    }
  }
}
