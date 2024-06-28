package exception;

public class OutOfBoardException extends RuntimeException{
    private String message;

    public OutOfBoardException(String message){
        super(message);
    }
}
