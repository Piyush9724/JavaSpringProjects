import { Component, OnInit } from '@angular/core';
import { BankService } from '../bank.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Account } from '../../Account';


@Component({
  selector: 'app-show-balance',
  templateUrl: './show-balance.component.html',
  styleUrls: ['./show-balance.component.css']
})
export class ShowBalanceComponent implements OnInit {

  ac : Account;
  balance: number;
  message : string;
  constructor(private bankService : BankService, private router : Router) { }


  ngOnInit() {
    
  }

  onSubmit(userForm: NgForm){
    console.log(userForm.value.accNo, userForm.value.amount);
     this.bankService.showBalance(userForm.value.accNo).subscribe(data => {
      console.log(data);
      this.ac = data;
      this.balance = data.currentBal;
    },
      error => {
        
        console.log(error);
        
      });
    
  }
}
