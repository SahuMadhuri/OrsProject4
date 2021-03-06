package in.co.rays.ORSProject4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSProject4.bean.CourseBean;
import in.co.rays.ORSProject4.model.CourseModel;

public class CourseModelTest {
	public static CourseModel model = new CourseModel();
	public static void main(String[] args) throws Exception {
		
		// testAdd();
		// testUpdate();
		//testDelete();
		// testFindByName();
		//testFindByPk();
		testSearch();
		//testList();
	}

	public static void testAdd() throws Exception {

		CourseBean bean = new CourseBean();

		bean.setName("BE");;
		bean.setDuration("4 years");
		bean.setDescription("technical Bachlor degree");
		bean.setCreatedBy("ADMIN");
		bean.setModifiedBy("ADMIN");
	

		model.add(bean);
	}
	public static void testUpdate() throws Exception {

		CourseBean bean = new CourseBean();

		bean.setId(2);
		bean.setName("BE(CS)");
		bean.setDuration("4 years");
		bean.setDescription("Bachlor degree");
		bean.setCreatedBy("Madhuri");
		bean.setModifiedBy("Madhuri");

		model.update(bean);
	}
	public static void testDelete() throws Exception {

		CourseBean bean = new CourseBean();

		bean.setId(8);

		model.delete(bean);
	}
	public static void testFindByName() throws Exception {

		CourseBean bean = new CourseBean();

		bean = model.findByName("b.ed");

		System.out.println(bean.getId());
		System.out.println(bean.getDuration());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getDescription());

	}

	public static void testFindByPk() throws Exception {

		CourseBean bean = new CourseBean();

		bean = model.findByPk(1);

		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDescription());

	}

	public static void testSearch() throws Exception{
		
		CourseBean bean = new CourseBean();
		
		List<CourseBean> list = new ArrayList<CourseBean>();
		
		 list=model.search(bean,1,1);
		 
		 Iterator<CourseBean> it = list.iterator();
		 
		 while(it.hasNext()){
			      bean = it.next();
	 		   System.out.print(bean.getId());
				System.out.print("\t"+bean.getName());
				System.out.print("\t"+bean.getDescription());
				System.out.print("\t"+bean.getDuration());
			 
		 }
	}
	
public static void testList() throws Exception{
		
		CourseBean bean = new CourseBean();
		
		List<CourseBean> list = new ArrayList<CourseBean>();
		
		 list=model.list(1,5);
		 
		 Iterator<CourseBean> it = list.iterator();
		 
		 while(it.hasNext()){
			      bean = it.next();
	 		   System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
				System.out.println(bean.getDuration());
			    System.out.println(bean.getCreatedBy());
		 }
	}



}
