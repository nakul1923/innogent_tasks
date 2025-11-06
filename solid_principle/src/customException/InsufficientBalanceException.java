package customException;

public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException(String err){

        super(err);
    }
}
