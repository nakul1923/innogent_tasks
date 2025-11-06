import java.sql.Array;

public class AccountRepository {

    private BankAccount[] accounts = {};

    private int count = 0;

    AccountRepository(int capacity){

        accounts = new BankAccount[capacity];
    }

    void addAccount(BankAccount account){

        if(count < accounts.length){

            accounts[count++] = account;

        }else{

            System.out.println("you cannot create account! limit exceed");
        }
    }

    BankAccount getAccount(String accountNumber){

        for(int i=0;i<accounts.length;i++){

            if(accounts[i].getAccountNumber()==accountNumber)return accounts[i];

        }

        return null;
    }
}
