import { Injectable } from '@angular/core';
import { HttpClient } from '../../node_modules/@angular/common/http';
import { Observable } from '../../node_modules/rxjs';
import { Account } from '../Account';

@Injectable({
  providedIn: 'root'
})
export class BankService {

  account: Account;
  accounts: Account[];
  id: number;

  constructor(private http: HttpClient) {
    this.populateAccounts().subscribe( data => this.accounts = data, error => console.log(error) );
    
   }

   populateAccounts(): Observable<Account[]> {
    return this.http.get<Account[]>("http://localhost:5400/accounts");
  }

   populateAccount(id: number): Observable<Account> {
     return this.http.get<Account>("http://localhost:5400/accounts/"+id);
   }

   getAccount() : Account {
     return this.account;
   }

   getAccounts(): Account[] {
     return this.accounts;
   }

   addAccount(ac: Account): Observable<Account> {   
     return this.http.post<Account>("http://localhost:5400/accounts/add",ac);
   }

   deposit(id: number, amt: number) {
     this.populateAccount(id);
     return this.http.put<Account>("http://localhost:5400/accounts/dep/"+id+"/"+amt, this.account);
   }

   withdraw(id: number, amt: number) {
    this.populateAccount(id);
    return this.http.put<Account>("http://localhost:5400/accounts/wid/"+id+"/"+amt, this.account);
  }

  fundTransfer(idFrom: number,idTo: number, amt: number) {
    this.populateAccount(idFrom);
    return this.http.put<Account>("http://localhost:5400/accounts/fundtf/"+idFrom+"/"+idTo+"/"+amt, this.account);
  }

  showBalance(id: number) {
    return this.http.get<Account>("http://localhost:5400/accounts/"+id);
  }

  printTranscation(id: number) {
    return this.http.get<Account>("http://localhost:5400/accounts/"+id);
  }

  setId(id: number) {
    this.id = id;
  }

  getId(): number {
    return this.id;
  }
  

}
