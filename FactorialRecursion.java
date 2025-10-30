import java.util.Scanner;

public class FactorialRecursion{

    static int factorial(int n){

        if(n==0 || n==1) return 1;

        return n * factorial(n-1);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a Number");

        int num = sc.nextInt();

        if(num<0){

            System.out.println("invalid input");
        } else{

            System.out.println("Factorial of " + num + " is " + factorial(num));
        }


    }
}