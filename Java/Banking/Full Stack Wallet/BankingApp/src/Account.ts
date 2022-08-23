import { Transaction } from './Transaction';

export interface Account {
    accId: number;
    name: string;
    openingBal: number;
    currentBal: number;
    transactions: Transaction[];
    mobileNo: number;
    Dob: string;
}