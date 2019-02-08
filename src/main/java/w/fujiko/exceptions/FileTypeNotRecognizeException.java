package w.fujiko.exceptions;


public class FileTypeNotRecognizeException extends Exception{

    private static final long serialVersionUID = 1L;

    public FileTypeNotRecognizeException() {
        super();
    }
	public FileTypeNotRecognizeException(String message) {super(message); }
	public FileTypeNotRecognizeException(String message,Throwable cause) { super(message,cause); }
	public FileTypeNotRecognizeException(Throwable cause){ super(cause);	}
}