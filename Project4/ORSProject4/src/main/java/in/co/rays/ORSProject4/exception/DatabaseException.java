package in.co.rays.ORSProject4.exception;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
public class DatabaseException extends Exception {
	
	public DatabaseException(String msg) {
        super(msg);
        System.out.println("DataBase Exception"+msg);
    }

}
