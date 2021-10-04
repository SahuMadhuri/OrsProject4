package in.co.rays.ORSProject4.bean;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
public class RoleBean extends BaseBean{
	private static final long serialVersionUID = 1L;
	/**
	 * Predefined Role constants
	 */
	public static final int ADMIN = 1;
	public static final int STUDENT = 2;
	public static final int COLLEGE = 3;
	public static final int KIOSK = 4;
	public static final int FACULTY = 5;
    private String name;
    private String description;
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey() {
		System.out.println("RoleBean id"+id);
		return id + "";
	}

	public String getValue() {
		System.out.println("RoleBean name"+name);
		return name;
	}

	@Override
	public int compareTo(BaseBean o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
