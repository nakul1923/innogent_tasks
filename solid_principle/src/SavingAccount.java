import customException.InsufficientBalanceException;
import customException.InvalidAmountException;
import customException.NegativeAmountException;

public class SavingAccount extends OrgAccount {

    private static final double interest = 0.03;

    SavingAccount(String accountNumber,String accountHolderName){

        super(accountNumber,accountHolderName);
    }

    public void deposit(double amount){

        if(amount<0){

            throw new NegativeAmountException("Amount cannot be negative! Please enter postive amount");
        }
        if(amount>100000000){

            throw new InvalidAmountException("Please enter amount less than 100000000");
        }
        balance += amount + (amount * interest);
    }

    public void withdraw(double amount){

        if(amount<0){

            throw new NegativeAmountException("Amount cannot be negative! Please enter postive amount");
        } else if (amount>balance) {

            throw new InsufficientBalanceException("Insufficient Balance");
        }
        balance -= amount;
    }
}
