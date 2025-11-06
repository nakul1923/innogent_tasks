import customException.InsufficientBalanceException;
import customException.InvalidAmountException;
import customException.NegativeAmountException;

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


                try {

                    System.out.println("Enter deposit amount: ");
                    double dep = sc.nextDouble();
                    savingFirst.deposit(dep);

                } catch (InvalidAmountException | NegativeAmountException | InsufficientBalanceException exc) {

                    System.err.println(exc.getMessage());
                }catch (InputMismatchException exc){

                    System.out.println("Please Enter a valid numeric amount");
                }

                System.out.println(savingFirst.getAccountHolderName() + "'s Account " + savingFirst.getAccountNumber() + " have " + savingFirst.getBalance() + " balance left");


                try{
                    System.out.println("Enter withdraw amount: ");
                    double withd = sc.nextDouble();
                    savingFirst.withdraw(withd);
                } catch (InvalidAmountException | NegativeAmountException | InsufficientBalanceException exc) {

                    System.err.println(exc.getMessage());
                } catch (InputMismatchException exc){

                    System.out.println("Please Enter a valid numeric amount");
                }

                System.out.println(savingFirst.getAccountHolderName() + "'s Account " + savingFirst.getAccountNumber() + " have " + savingFirst.getBalance() + " balance left");


    }
}