package in.co.rays.ORSProject4.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSProject4.bean.TimetableBean;
import in.co.rays.ORSProject4.exception.ApplicationException;
import in.co.rays.ORSProject4.model.TimetableModel;

public class TimetableModelTest {
	public static TimetableModel model = new TimetableModel();

	public static void main(String[] args) throws Exception {
		

		//testAdd();
		// testDelete();
		// testUpdate();
		//testFindByPK();
	testsearch();
	//testList();
		//testfindbycourse();
	}

	public static void testAdd() throws Exception {

		TimetableBean bean = new TimetableBean();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		bean.setCourseId(7);
		bean.setCourseName("B.com2");
		bean.setSubjectId(8);
		bean.setSubjectName("account2");
		bean.setDescription("xyz");
		bean.setExamTime("10 to 1 pm");
		bean.setExamDate(sdf.parse("9/10/2009"));
		bean.setSemester("1st");
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("admin");
		
		model.add(bean);
	}

	public static void testDelete() throws Exception {

		TimetableBean bean = new TimetableBean();

		bean.setId(3);

		model.delete(bean);
	}

	public static void testUpdate() throws Exception {

		TimetableBean bean = new TimetableBean();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		bean.setId(1);

		bean.setCourseId(2);
		bean.setCourseName("Msc");
		bean.setSubjectId(5);
		bean.setSubjectName("ED");
		bean.setExamTime("1 to 2 pm");
		bean.setExamDate(sdf.parse("14/8/2008"));
		bean.setSemester("3rd");
		model.update(bean);
	}
	
	public static void testFindByPK() throws Exception {
	       try {
	           TimetableBean bean = new TimetableBean();
	           //long pk = 2;
	           
	           bean=model.findByPK(2);
	           System.out.println(bean.getId());
	           System.out.println(bean.getCourseName());
	           System.out.println(bean.getSubjectName());
	           System.out.println(bean.getExamDate());
	           
	       } catch (ApplicationException e) {
	           e.printStackTrace();
	       }

	   }
	
	public static void testsearch() throws Exception{
		
		TimetableBean bean = new TimetableBean();
		
		List list =  new ArrayList();
		
		list = model.search(bean,2,2);
		
		Iterator it = list.iterator();
		
		 while(it.hasNext()){
			bean = (TimetableBean)it.next();
			
			System.out.print(bean.getId());
	           System.out.print("\t"+bean.getCourseName());
	           System.out.print("\t"+bean.getSubjectName());
	           System.out.println("\t"+bean.getExamDate());
		}
	}
	
	public static void testList() throws Exception{
		
		TimetableBean bean = new TimetableBean();
		
		List list = new ArrayList();
		
		list = model.list(1,3);
				
		Iterator it = list.iterator();
		while(it.hasNext()){
			bean = (TimetableBean)it.next();
			
			System.out.println(bean.getId());
	           System.out.println(bean.getCourseName());
	           System.out.println(bean.getSubjectName());
	           System.out.println(bean.getExamDate());
		}
	}
	
	
}
