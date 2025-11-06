import java.util.InputMismatchException;
import java.util.Scanner;

class Main{

    public static void main(String args[]){


            Scanner sc = new Scanner(System.in);
            AccountRepository accountRepository = new AccountRepository(5);
            Transaction transaction = new Transaction();

            BankAccount savingFirst = new SavingAccount("123456789", "nakul");

            accountRepository.addAccount(savingFirst);

            System.out.println(savingFirst.getAccountHolderName() + "'s Account " + savingFirst.getAccountNumber() + " have " + savingFirst.getBalance() + " balance left");

            while(true) {
                System.out.println("Enter deposit amount: ");

                try {

                    double dep = sc.nextDouble();

                    if (dep < 0 || dep > 100000000) {

                        throw new RuntimeException("Please enter valid amount");

                    }
                    savingFirst.deposit(dep);

                } catch (InputMismatchException ie) {

                    System.err.println("❌ Invalid input! Please enter a valid amount.");


                } catch (RuntimeException re) {

                    System.out.println(re.getMessage());
                    continue;
                } catch (Exception e) {

                    System.out.println("Unexpected error occured. Please try again later");
                    continue;
                }


                System.out.println(savingFirst.getAccountHolderName() + "'s Account " + savingFirst.getAccountNumber() + " have " + savingFirst.getBalance() + " balance left");
                break;
            }

                savingFirst.withdraw(300);

                System.out.println(savingFirst.getAccountHolderName() + "'s Account " + savingFirst.getAccountNumber() + " have " + savingFirst.getBalance() + " balance left");


    }
}