import { Component, OnInit } from '@angular/core';
import { BankService } from '../bank.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css']
})
export class DepositComponent implements OnInit {

  

  constructor(private bankService : BankService, private router : Router) { }

  ngOnInit() {
  }

  onSubmit(userForm: NgForm){
    console.log(userForm.value.accNo, userForm.value.amount);
    this.bankService.deposit(userForm.value.accNo, userForm.value.amount).subscribe(data => {
      this.router.navigate(['/showAcc']);
    },
      error => {
        console.log(error)
      });
  }

}
