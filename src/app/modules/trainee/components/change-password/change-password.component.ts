import { Component } from '@angular/core';
import { UserDtoForPasswordChange } from 'src/app/class/UserDtoPasswordChange';
import { DataService } from 'src/app/service/data.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent {
  currentPassword!: string;
  newPassword!: string;
  confirmPassword!: string;
  userName!: string;
  constructor(private dataservice: DataService) {
    this.userName = localStorage.getItem("trainee-email") || "";
   }
  changePassword() {
    if (this.newPassword === this.confirmPassword) {
      // Implement password change logic here, e.g., make an HTTP request to your backend
      
      this.dataservice.changepassword(new UserDtoForPasswordChange(this.userName, this.currentPassword, this.newPassword)).subscribe(
        response => {
          console.log(response);
          if (response.status == 200) {
            console.log('Password changed');
          } else {
            console.log('Unable to change Password.');
          }
        });

    }
  }
}
