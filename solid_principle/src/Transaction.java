public class Transaction {

    void performDeposit(BankAccount account,double amount){

        account.deposit(amount);
    }

    void performWithdraw(BankAccount account, double amount){

        account.withdraw(amount);
    }
}
