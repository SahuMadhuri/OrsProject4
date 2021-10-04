package in.co.rays.ORSProject4.exception;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
public class DuplicateRecordException extends Exception {
	/**
	 * @param msg
	 *            error message
	 */
	public DuplicateRecordException(String msg) {
		super(msg);
		System.out.println("DuplicateRecord Exception"+msg);
	}
}
