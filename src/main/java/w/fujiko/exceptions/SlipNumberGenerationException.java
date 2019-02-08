package w.fujiko.exceptions;


public class SlipNumberGenerationException extends Exception{

    private static final long serialVersionUID = 1L;

    public SlipNumberGenerationException() {
        super();
    }
	public SlipNumberGenerationException(String message) {super(message); }
	public SlipNumberGenerationException(String message,Throwable cause) { super(message,cause); }
	public SlipNumberGenerationException(Throwable cause){ super(cause);	}
}