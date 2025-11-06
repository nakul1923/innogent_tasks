package customException;

public class NegativeAmountException extends RuntimeException{

    public NegativeAmountException(String err){

        super(err);
    }
}
