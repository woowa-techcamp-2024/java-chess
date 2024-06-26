package exception;

public class OutOfBoard extends RuntimeException{
    private String message;

    public OutOfBoard(String message){
        super(message);
    }
}
