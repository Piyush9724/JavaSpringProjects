import { Component, OnInit } from '@angular/core';
import { BankService } from '../bank.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-fund-transfer',
  templateUrl: './fund-transfer.component.html',
  styleUrls: ['./fund-transfer.component.css']
})
export class FundTransferComponent implements OnInit {

  constructor(private bankService : BankService, private router : Router) { }

  ngOnInit() {
  }

  onSubmit(userForm: NgForm){
    console.log(userForm.value.accNo, userForm.value.amount);
    this.bankService.fundTransfer(userForm.value.accNoFrom , userForm.value.accNoTo, userForm.value.amount).subscribe(data => {
      this.router.navigate(['/showAcc']);
    },
      error => {
        console.log(error)
      });
  }

}
