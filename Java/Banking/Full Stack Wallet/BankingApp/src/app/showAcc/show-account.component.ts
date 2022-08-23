import { Component, OnInit } from '@angular/core';
import { BankService } from '../bank.service';
import { Router } from '../../../node_modules/@angular/router';
import { Account } from '../../Account';

@Component({
  selector: 'app-show-account',
  templateUrl: './show-account.component.html',
  styleUrls: ['./show-account.component.css']
})
export class ShowAccountComponent implements OnInit {

  accounts : Account[];

  constructor(private bankService: BankService, private router: Router) {

  
   }

  ngOnInit() {

    this.bankService.populateAccounts().subscribe(data=>this.accounts = data);
  }

}
