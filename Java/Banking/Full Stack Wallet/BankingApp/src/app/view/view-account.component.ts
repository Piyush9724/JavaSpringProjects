import { Component, OnInit } from '@angular/core';
import { BankService } from '../bank.service';
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Account } from '../../Account';

@Component({
  selector: 'app-view-account',
  templateUrl: './view-account.component.html',
  styleUrls: ['./view-account.component.css']
})
export class ViewAccountComponent implements OnInit {

  ac : Account;
  id: number;
  constructor(private bankService : BankService, private router : Router) { }


  ngOnInit() {
    this.id = this.bankService.getId();
    this.ac = this.bankService.getAccounts().find(acc => acc.accId === this.id);
    console.log(this.ac);
  }
}
