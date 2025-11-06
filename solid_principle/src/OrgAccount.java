abstract public class OrgAccount implements BankAccount {

    String accountNumber;
    String accountHolderName;
    double balance = 0;

    OrgAccount(String accountNumber,String accountHolderName){

        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
    }

    @Override
    public String getAccountNumber(){

        return accountNumber;
    }

    @Override
    public String getAccountHolderName(){

        return accountHolderName;
    }

    @Override
    public double getBalance() {
        return balance;
    }
}
