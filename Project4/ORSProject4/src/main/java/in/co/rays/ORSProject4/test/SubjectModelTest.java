package in.co.rays.ORSProject4.test;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.sql.Timestamp;

import in.co.rays.ORSProject4.bean.SubjectBean;
import in.co.rays.ORSProject4.model.SubjectModel;

public class SubjectModelTest {
	public static SubjectModel model = new SubjectModel();
	public static void main(String[] args) throws Exception {
		//addTest();
		// testUpdate();
		 testDelete();
		//testfindByName();
		//testfindByPk();
		//testSearch();
		//testList();
		
	}
	public static void addTest() throws Exception {

		SubjectBean bean = new SubjectBean();
		
		bean.setCourseId(1);
		bean.setName("c language");
		bean.setCourseName("B.ed1");
		
		bean.setDescription("for child growing");
		

		model.add(bean);
	}
	public static void testDelete() throws Exception {

		SubjectBean bean = new SubjectBean();

		bean.setId(6);

		model.delete(bean);
	}
	public static void testfindByName() throws Exception {

		SubjectBean bean = new SubjectBean();

		bean = model.findByName("MATHS");
		if (bean == null) {
		    System.out.println("Test Find By Name fail");
		}

		System.out.print(bean.getId());
		System.out.print("\t"+bean.getCourseName());
		System.out.print("\t"+bean.getCourseId());
		System.out.print("\t"+bean.getCreatedBy());
		System.out.println("\t"+bean.getName());
		System.out.println("Test Find By Name success");
		
	}
	public static void testUpdate() throws Exception {

		SubjectBean bean = new SubjectBean();

		bean.setId(5);
		
		bean.setName("C++1");
		bean.setCourseName("Bsc");
		bean.setCourseId(4);
		bean.setDescription("for Finance data");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);
	}
	public static void testSearch() throws Exception {

		SubjectBean bean = new SubjectBean();

		List<SubjectBean> list = new ArrayList<SubjectBean>();

		list = model.search(bean, 0, 0);

		Iterator<SubjectBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();
			System.out.print(bean.getId());
			System.out.print("\t"+bean.getCourseName());
			System.out.println("\t"+bean.getName());
		}
	}
public static void testList() throws Exception{
		
		SubjectBean bean = new SubjectBean();
		
		List<SubjectBean> list = new ArrayList<SubjectBean>();
		
		 list=model.list(0,0);
		 
		 Iterator it = list.iterator();
		 
		 while(it.hasNext()){
			      bean = (SubjectBean)it.next();
	 		   System.out.print("\t"+bean.getId());
				System.out.print("\t"+bean.getCourseName());
				System.out.println("\t"+bean.getDescription());
			 }
	}
public static void testfindByPk() throws Exception {

	SubjectBean bean = new SubjectBean();

	bean = model.findByPK(1);

	System.out.print("\t"+bean.getId());
	System.out.println("\t"+bean.getCourseName());
}

}
