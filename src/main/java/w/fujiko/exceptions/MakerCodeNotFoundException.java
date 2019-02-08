package w.fujiko.exceptions;


public class MakerCodeNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

    public MakerCodeNotFoundException() {
        super();
    }
	public MakerCodeNotFoundException(String message) {super(message); }
	public MakerCodeNotFoundException(String message,Throwable cause) { super(message,cause); }
	public MakerCodeNotFoundException(Throwable cause){ super(cause);	}
}