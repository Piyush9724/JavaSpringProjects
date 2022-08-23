import { Component, OnInit } from '@angular/core';
import { BankService } from '../bank.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-withdraw',
  templateUrl: './withdraw.component.html',
  styleUrls: ['./withdraw.component.css']
})
export class WithdrawComponent implements OnInit {

  constructor(private bankService : BankService, private router : Router) { }

  ngOnInit() {
  }

  onSubmit(userForm: NgForm){
    console.log(userForm.value.accNo, userForm.value.amount);
    this.bankService.withdraw(userForm.value.accNo, userForm.value.amount).subscribe(data => {
      this.router.navigate(['/showAcc']);
    },
      error => {
        console.log(error)
      });
  }

}
