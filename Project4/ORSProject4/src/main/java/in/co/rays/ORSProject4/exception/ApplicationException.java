package in.co.rays.ORSProject4.exception;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
public class ApplicationException extends Exception {
	
    public ApplicationException(String msg) {
    	
        super(msg);
        System.out.println("Application Exception"+msg);
    }
}
