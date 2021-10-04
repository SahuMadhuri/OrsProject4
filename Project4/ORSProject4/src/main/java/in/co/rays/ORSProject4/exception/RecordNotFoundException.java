package in.co.rays.ORSProject4.exception;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
public class RecordNotFoundException extends Exception{
	
	public RecordNotFoundException(String msg) {
        super(msg);
        System.out.println("RecordNotFoundException: "+msg);
    }

}
