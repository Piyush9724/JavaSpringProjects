import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AddComponent } from './add/add.component';
import { DepositComponent } from './deposit/deposit.component';
import { WithdrawComponent } from './withdraw/withdraw.component';
import { ShowBalanceComponent } from './balance/show-balance.component';
import { FundTransferComponent } from './fundTransfer/fund-transfer.component';
import { PrintTransactionsComponent } from './print/print-transactions.component';
import { ShowAccountComponent } from './showAcc/show-account.component';
import { ViewAccountComponent } from './view/view-account.component';


const routes: Routes = [
  {path: "home", component: HomeComponent},
  {path: "add", component: AddComponent},
  {path: "deposit", component: DepositComponent},
  {path: "withdraw", component: WithdrawComponent},
  {path: "balance", component: ShowBalanceComponent},
  {path: "fundTransfer", component: FundTransferComponent},
  {path: "print", component: PrintTransactionsComponent},
  {path: "showAcc", component: ShowAccountComponent},
  {path: "viewAcc", component: ViewAccountComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
