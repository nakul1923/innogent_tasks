public class SavingAccount extends OrgAccount {

    private static final double interest = 0.03;

    SavingAccount(String accountNumber,String accountHolderName){

        super(accountNumber,accountHolderName);
    }

    public void deposit(double amount){

        balance += amount + (amount * interest);
    }

    public void withdraw(double amount){

        if(balance>0){
            if(amount<=balance){

                balance -= amount;
            }
        }else{

            throw new RuntimeException("balance is low");
        }
    }
}
