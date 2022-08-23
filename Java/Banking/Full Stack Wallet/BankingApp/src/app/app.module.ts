import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AddComponent } from './add/add.component';
import { ShowBalanceComponent } from './balance/show-balance.component';
import { DepositComponent } from './deposit/deposit.component';
import { WithdrawComponent } from './withdraw/withdraw.component';
import { FundTransferComponent } from './fundTransfer/fund-transfer.component';
import { PrintTransactionsComponent } from './print/print-transactions.component';
import { BankService } from './bank.service';
import { ShowAccountComponent } from './showAcc/show-account.component';
import { ViewAccountComponent } from './view/view-account.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AddComponent,
    ShowBalanceComponent,
    DepositComponent,
    WithdrawComponent,
    FundTransferComponent,
    PrintTransactionsComponent,
    ShowAccountComponent,
    ViewAccountComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
    
  ],
  providers: [BankService],
  bootstrap: [AppComponent]
})
export class AppModule { }
