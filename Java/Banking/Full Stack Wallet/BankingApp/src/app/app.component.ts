import { Component } from '@angular/core';
import { BankService } from './bank.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'BankingApp';

  constructor(private bankService: BankService, private router :Router) {}

  onSubmit(userForm: NgForm){
      console.log(userForm.value);
      this.bankService.setId(userForm.value);
      this.router.navigate(["/viewAcc"]);
   
  }

}
