public class Transaction {

    public void performDeposit(BankAccount account,double amount){

        account.deposit(amount);
    }

    public void performWithdraw(BankAccount account, double amount){

        account.withdraw(amount);
    }
}
