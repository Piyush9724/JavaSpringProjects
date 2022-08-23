import { Component, OnInit, ViewChild } from '@angular/core';
import { BankService } from '../bank.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Account } from '../../Account';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  @ViewChild('userForm', { static: false })
  public createAccountForm: NgForm;

  constructor(private bankService: BankService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit(userForm) {
    let account: Account = userForm.value;
    console.log(account);
    if (userForm.valid) {
      this.bankService.addAccount(account).subscribe(data => {
        this.router.navigate(['/showAcc']);
      },
        error => {
          console.log(error)
        });

    }

  }

}
