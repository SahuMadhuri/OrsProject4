package in.co.rays.ORSProject4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.chainsaw.Main;

import in.co.rays.ORSProject4.bean.StudentBean;
import in.co.rays.ORSProject4.exception.ApplicationException;
import in.co.rays.ORSProject4.exception.DuplicateRecordException;
import in.co.rays.ORSProject4.model.StudentModel;

public class StudentModelTest {
	public static StudentModel model=new StudentModel();
	public static void main(String[] args) throws ParseException {
		//testFindByEmailId();
		//testAdd();
		//testDelete();
		//testUpdate();
		//testSearch();
		testList();
		// testFindByPK();
	}
	public static void testFindByEmailId(){
		try{
			StudentBean bean=new StudentBean();
			bean=model.findByEmailId("Kanika@gmail.com");
			if(bean !=null){
				System.out.println("test find by email id success");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getEmail());
			System.out.println(bean.getCollegeId());
		}catch(ApplicationException e){
			e.printStackTrace();
			
		}
	}
	public static void testAdd() throws ParseException {
		StudentBean bean = new StudentBean();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			  // bean.setId(1L);
            bean.setFirstName("Kanika2");
            bean.setLastName("Viswkarma3");
            bean.setDob(sdf.parse("12/09/1998"));
            bean.setMobileNo("8788684843");
            bean.setEmail("Kanika345@gmail.com");
            bean.setCollegeId(6L);
            bean.setCollegeName("scmips");
            bean.setCreatedBy("Admin");
            bean.setModifiedBy("Admin");
            bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
            bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			model.add(bean);
			// StudentBean add= model.nextPk(pk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
		}
	public static void testDelete() {
		try{
			StudentBean bean=new StudentBean();
			long pk=5L;
			bean.setId(5L);
			model.delete(bean);
			/*StudentBean deletebean=model.findByPK(pk);
			if(deletebean!=null){
				System.out.println("test delete fail");
			}*/
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void testUpdate(){
		try {

			StudentBean bean = new StudentBean();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			bean.setId(5L);
			bean.setCollegeName("gsits");
			bean.setFirstName("priyanshi");
			bean.setLastName("sahu");
			bean.setDob(sdf.parse("03/02/1997"));
			bean.setMobileNo("9165254357");
			bean.setEmail("gitanshi@gmail.com");
			bean.setCollegeId(4L);
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

			model.update(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 public static void testSearch() {

	        try {
	            StudentBean bean = new StudentBean();
	            List list = new ArrayList();
	            bean.setFirstName("Priyanka");
	            list=model.search(bean,0,0);
	            if (list.size() < 0) {
	                System.out.println("Test Serach fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (StudentBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getFirstName());
	                System.out.println(bean.getLastName());
	                System.out.println(bean.getDob());
	                System.out.println(bean.getMobileNo());
	                System.out.println(bean.getEmail());
	                System.out.println(bean.getCollegeId());
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }
	 public static void testList(){
			
			
			try{
				StudentBean bean = new StudentBean();
			    List list = new ArrayList();
				list= model.list(1,5);
				 if (list.size() < 0) {
		                System.out.println("Test list fail");
		            }
				Iterator it = list.iterator();
				while(it.hasNext()){
					bean = (StudentBean) it.next();
					System.out.println(bean.getId());
					  System.out.println(bean.getId());
		                System.out.println(bean.getFirstName());
		                System.out.println(bean.getLastName());
		                System.out.println(bean.getDob());
		                System.out.println(bean.getMobileNo());
		                System.out.println(bean.getEmail());
		                System.out.println(bean.getCollegeId());
		                System.out.println(bean.getCreatedBy());
		                System.out.println(bean.getCreatedDatetime());
		                System.out.println(bean.getModifiedBy());
		                System.out.println(bean.getModifiedDatetime());
					
					
				}
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	 public static void testFindByPK() {

			try {
				StudentBean bean = model.findByPK(2L);
				System.out.println(bean);
				if(bean==null)
				{
					System.out.println("Null Hai");
				}else{
				System.out.println(bean.getCollegeId());
				System.out.println(bean.getCollegeName());
				System.out.println(bean.getDob());
				System.out.println(bean.getFirstName());
				System.out.println(bean.getLastName());
				}
				if (bean != null) {

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
