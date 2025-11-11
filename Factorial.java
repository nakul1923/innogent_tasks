import java.util.Scanner;

public  class Factorial {

    static int factorial(int n){          // not handled the case for 1

        if(n==0) return 1;
        int result = 1;
        for(int i=2;i<=n;i++){

            result *= i;
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a Number");
        int num = sc.nextInt();

        if(num<0){

            System.out.println("Invalid input");
        }else{

            System.out.println("Factorial of " + num + " is " + factorial(num));
        }


    }
}