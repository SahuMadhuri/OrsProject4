package in.co.rays.ORSProject4.util;

import java.util.ResourceBundle;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
public class PropertyReader {
	
	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.ORSProject4.bundle.system");

    /**
     * Return value of key
     *
     * @param key
     * @return
     */

    public static String getValue(String key) {
    	//System.out.println("PropertyReader getValue method");

        String val = null;

        try {
            val = rb.getString(key);
          //  System.out.println("PropertyReader val"+val);
        } catch (Exception e) {
            val = key;
        }

        return val;

    }

    /**
     * Gets String after placing param values
     *
     * @param key
     * @param param
     * @return String
     */
    public static String getValue(String key, String param) {
    	//System.out.println("PropertyReader getValue method 2 param");
        String msg = getValue(key);
        msg = msg.replace("{0}", param);
        System.out.println("propetyReader getvalue() msg2"+msg);
        return msg;
    }

    /**
     * Gets String after placing params values
     *
     * @param key
     * @param params
     * @return
     */
    public static String getValue(String key, String[] params) {
    	//System.out.println("PropertyReader getValue Mathod key param 3");
        String msg = getValue(key);
       // System.out.println("PropertyReader msg "+msg);
        for (int i = 0; i < params.length; i++) {
        	System.out.println("PropertyReader params.length "+params.length);
            msg = msg.replace("{" + i + "}", params[i]);
        }
        return msg;
    }

    /**
     * Test method
     *
     * @param args
     */

    public static void main(String[] args) {
        String[] params = { "Roll No" };
       // System.out.println("Property reader "+params);
        //System.out.println(PropertyReader.getValue("error.require", params));
    }


}
