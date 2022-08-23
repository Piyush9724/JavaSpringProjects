import { Component, OnInit } from '@angular/core';
import { BankService } from '../bank.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Transaction } from 'src/Transaction';

@Component({
  selector: 'app-print-transactions',
  templateUrl: './print-transactions.component.html',
  styleUrls: ['./print-transactions.component.css']
})
export class PrintTransactionsComponent implements OnInit {

  Transactions : Transaction[];
  constructor(private bankService: BankService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit(userForm: NgForm){
    console.log(userForm.value.accNo, userForm.value.amount);
     this.bankService.showBalance(userForm.value.accNo).subscribe(data => {
      console.log(data);
      this.Transactions = data.transactions;
    },
      error => {
        console.log(error)
      });
    
  }

}
