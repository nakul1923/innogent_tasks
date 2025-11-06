package customException;

public class InvalidAmountException extends RuntimeException{

    public InvalidAmountException(String err){

        super(err);
    }
}
