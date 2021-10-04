package in.co.rays.ORSProject4.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSProject4.bean.FacultyBean;
import in.co.rays.ORSProject4.model.FacultyModel;

public class FacultyModelTest {
	public static FacultyModel model = new FacultyModel();

	public static void main(String[] args) throws ParseException,Exception {
		
		testAdd();
		//testUpdate();
		//testDelete();
		//testfindByPk();
		//testfindByEmail();
		//testSearch();
		//testList();
	}
public static void testfindByPk() throws Exception {
		
		FacultyBean bean = new FacultyBean();
 
		bean= model.findByPK(1);
		System.out.print("\t"+bean.getId());
		System.out.print("\t"+bean.getFirstName());
		System.out.print("\t"+bean.getLastName());
		System.out.print("\t"+bean.getGender());
		
		System.out.print("\t"+bean.getMobileNo());
		System.out.print("\t"+bean.getCollegeId());
		System.out.print("\t"+bean.getCollegeName());
		System.out.print("\t"+bean.getCourseId());
		System.out.print("\t"+bean.getCourseName());
		System.out.print("\t"+bean.getSubjectId());
		System.out.print("\t"+bean.getSubjectName());
		System.out.print("\t"+bean.getCreatedBy());
		System.out.print("\t"+bean.getModifiedBy());
		System.out.print("\t"+bean.getCreatedDatetime());
		System.out.println("\t"+bean.getModifiedDatetime());
	}
public static void testfindByEmail(){
	
	FacultyBean bean = new FacultyBean();
	
	try {
		bean= model.findByEmail("Meena@gmail.com");
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(bean.getFirstName());
	System.out.println(bean.getLastName());
}
public static void testAdd() throws ParseException {
	FacultyBean bean = new FacultyBean();

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	Date d = new Date();
	
	bean.setFirstName("Kanika2");
	bean.setLastName("Viswkarma2");
	bean.setGender("Female");

	bean.setDob(sdf.parse("06/08/2021"));
	
	bean.setEmail("Kanika3@gmail.com");
	bean.setCollegeId(4);
	bean.setCollegeName("scmips");
	bean.setCourseId(4);
	bean.setCourseName("b.ed");
	bean.setSubjectId(2);
	bean.setSubjectName("Finance");
	
    //bean.setDateOfJoining(sdf.parse("10/11/1999"));
	try {
		model.add(bean);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static void testUpdate() throws Exception {
	FacultyBean bean = new FacultyBean();

	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	Date d = new Date();
	
	bean.setId(3);
	bean.setFirstName("gitanshi23");
	bean.setLastName("sahu");
	bean.setEmail("Gitanshi23@yahoo.com");
	bean.setCollegeId(2);
	bean.setCourseId(3);
	bean.setSubjectId(3);
	bean.setDob(sdf.parse("04/04/2002"));

	
	//bean.setMobileNo("987665444");
	
	System.out.println("updated");
	
	model.update(bean);
	
}

public static void testDelete() throws Exception {
	FacultyBean bean = new FacultyBean();

	bean.setId(5);
	
	model.delete(bean);
	System.out.println("record deleted");
}

public static void testSearch() throws Exception{
	
	List list = new ArrayList();
	
	FacultyBean bean=new FacultyBean();
	

	list = model.search(bean,0,0);
	
	bean.setId(1);
	Iterator<FacultyBean> it = list.iterator();
	while(it.hasNext()){
		bean= it.next();
		
		System.out.print(bean.getFirstName());
		System.out.print("\t"+bean.getLastName());
		System.out.println("\t"+bean.getGender());
		
		
	}
}

public static void testList() throws Exception{
	
	List list = new ArrayList();
	
	FacultyBean bean=new FacultyBean();
	

	list = model.list(0,0);
	
	bean.setId(1);
	Iterator<FacultyBean> it = list.iterator();
	while(it.hasNext()){
		bean= it.next();
		
		System.out.print(bean.getFirstName());
		System.out.print("\t"+bean.getLastName());
		System.out.println("\t"+bean.getGender());
		
		
	}
}


}
