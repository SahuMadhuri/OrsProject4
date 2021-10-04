package in.co.rays.ORSProject4.bean;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
public class CourseBean extends BaseBean{

	
	private static final long serialVersionUID = 1L;
	/**
	 * Name of Course
	 */
	private String name;
	/**
	 * Duration of Course
	 */
	private String duration;
	/**
	 * Description of project
	 */
	private String description;

	/**
	 * accessor
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey() {
		System.out.println("CollegeBean get id  : "+id);
		return id + "";
	}

	public String getValue() {
		System.out.println("CourseBean getname : "+name);
		return name;
	}

	@Override
	public int compareTo(BaseBean o) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
