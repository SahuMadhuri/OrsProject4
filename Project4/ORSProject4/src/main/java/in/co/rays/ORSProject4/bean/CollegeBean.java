package in.co.rays.ORSProject4.bean;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
public class CollegeBean extends BaseBean{
   
	private static final long serialVersionUID = 1L;
	/**
	 * Name of College
	 */
	private String name;
	/**
	 * Address of College
	 */
	private String address;
	/**
	 * State of College
	 */
	private String state;
	/**
	 * City of College
	 */
	private String city;
	/**
	 * Phoneno of College
	 */
	private String phoneNo;

	/**
	 * accessor
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getKey() {
		System.out.println("CollegeBean getid : "+id);
		return id + "";
	}

	public String getValue() {
		System.out.println("CollegeBean getname : "+name);
		return name;
	}

	@Override
	public int compareTo(BaseBean o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
