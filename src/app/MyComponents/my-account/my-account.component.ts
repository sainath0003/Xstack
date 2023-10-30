import { Component } from '@angular/core';

@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.css']
})
export class MyAccountComponent {
  editMode = false;

  // Define user data here (e.g., name, email, etc.)
  // Use Angular forms to handle user input and update this data.

  enterEditMode() {
    this.editMode = true;
  }

  saveChanges() {
    // Implement logic to save user data changes
    this.editMode = false;
  }

  cancelEdit() {
    this.editMode = false;
  }
}
