package in.co.rays.ORSProject4.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
public class DataValidator {
	
	  /**
     * Checks if value is Null
     *
     * @param val
     * @return
     */
	public static boolean isNull(String val) {
		
		if (val == null || val.trim().length() == 0) {
			return true;
			
		} else {
			return false;
		}
	}

	/**
	 * 
	 * Checks if value is NOT Null
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNotNull(String val) {

		return !isNull(val);
	}

	/**
	 * Checks if value is an Integer
	 * 
	 * @param val
	 * @return
	 */

	public static boolean isInteger(String val) {
		
		if (isNotNull(val)) {
			try {
				Integer.parseInt(val);
				System.out.println("Integer.parseInt(val) "+Integer.parseInt(val));
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Long
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isLong(String val) {
		
		if (isNotNull(val)) {
			try {
				Long.parseLong(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid Email ID
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isEmail(String val) {
		

		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		if (isNotNull(val)) {
			try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid Name
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isName(String val) {
		
		String namereg = "^[^-\\s][\\p{L} .'-]+$";

		if (isNotNull(val)) {
			try {
				return val.matches(namereg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid Roll No
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isRollNo(String val) {
		
		String rollreg = "[a-zA-Z]{2}[0-9]{3}";

		if (isNotNull(val)) {
			try {
				return val.matches(rollreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid standard Password
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isPassword(String val) {
		
		String passreg = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,12}";

		if (isNotNull(val)) {
			try {
				return val.matches(passreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value of Password is in between 8 and 12 characters
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isPasswordLength(String val) {
		
		if (isNotNull(val) && val.length() >= 8 && val.length() <= 12) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid Phone No.
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isPhoneNo(String val) {
		
		String phonereg = "^[6-9][0-9]{9}$";

		if (isNotNull(val)) {
			try {
				return val.matches(phonereg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value of Mobile No is 10
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isPhoneLength(String val) {
		
		if (isNotNull(val) && val.length() == 10) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Date
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isDate(String val) {
		//System.out.println("dataValidator isDate method started"+val);
		Date d = null;
		if (isNotNull(val)) {
			d = DataUtility.getDate(val);
		}
		return d != null;
	}

	/**
	 * Checks if Date is on Sunday
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isSunday(String val) {
		//System.out.println("dataValidator isSunday method started"+val);
		Calendar cal = Calendar.getInstance();
		cal.setTime(DataUtility.getDate(val));
		int i = cal.get(Calendar.DAY_OF_WEEK);

		if (i == Calendar.SUNDAY) {
			return true;
		} else {
			return false;
		}

	}

    public static void main(String[] args) {
    	/*DataValidator d = new DataValidator();
        System.out.println("Not Null 2: " + d.isNotNull("ABC"));
        System.out.println("Not Null 3 :" + d.isNotNull(null));
        System.out.println("Not Null 4 :" + d.isNull("123"));

        System.out.println("Is Int " + d.isInteger(null));
        System.out.println("Is Int " + d.isInteger("ABC1"));
        System.out.println("Is Int " + d.isInteger("123"));
        System.out.println("Is Int " + d.isNotNull("123"));*/
    }


}
