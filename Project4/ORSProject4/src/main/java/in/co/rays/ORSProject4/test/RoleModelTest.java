package in.co.rays.ORSProject4.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSProject4.bean.RoleBean;
import in.co.rays.ORSProject4.exception.ApplicationException;
import in.co.rays.ORSProject4.exception.DuplicateRecordException;
import in.co.rays.ORSProject4.model.RoleModel;

public class RoleModelTest {
	 public static RoleModel model = new RoleModel();
	public static void main(String[] args) throws ParseException {
		//testAdd();
		//testDelete();
		testUpdate();
		//testSearch();
		//testList();
		//testFindByPK();
	}
public static void testAdd(){
		
		try{
			
			RoleBean bean = new RoleBean();
			
			bean.setName("Student1");
            bean.setDescription("XYZ");
            bean.setCreatedBy("student");
            bean.setModifiedBy("student");
            bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
            bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		                   
		    model.add(bean);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
public static void testFindByPK() {
    try {
        RoleBean bean = new RoleBean();
        long pk = 5L;
        bean = model.findByPk(pk);
        if (bean == null) {
            System.out.println("Test Find By PK fail");
        }
        System.out.println(bean.getId());
        System.out.println(bean.getName());
        System.out.println(bean.getDescription());
    } catch (ApplicationException e) {
        e.printStackTrace();
    }

}
public static void testDelete(){
	try{
		RoleBean bean=new RoleBean();
		long pk=3L;
		bean.setId(pk);
		try {
			model.delete(bean);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RoleBean deletebean=model.findByPk(pk);
		if(deletebean !=null){
			System.out.println("test delete success");
		}
		
		
		
	}catch(ApplicationException e){
		e.printStackTrace();	
	}
	}
  public static void testUpdate(){
	  try {
		  RoleBean bean=model.findByPk(4L);
		  bean.setName("Ad");
		  bean.setDescription("XYZ456");
		  model.update(bean);
		  RoleBean updatedbean=model.findByPk(5L);
		     if (!bean.getName().equals(updatedbean.getName())) {
	                System.out.println("Test Update fail");
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        } catch (DuplicateRecordException e) {
	            e.printStackTrace();
	        }
	  }
  public static void testSearch() {

      try {
          RoleBean bean = new RoleBean();
          List list = new ArrayList();
          bean.setName("student");
          list = model.search(bean, 0, 0);
          if (list.size() < 0) {
              System.out.println("Test Serach fail");
          }
          Iterator it = list.iterator();
          while (it.hasNext()) {
              bean = (RoleBean) it.next();
              System.out.println(bean.getId());
              System.out.println(bean.getName());
              System.out.println(bean.getDescription());
          }

      } catch (ApplicationException e) {
          e.printStackTrace();
      }

  }
  public static void testList(){
		
		RoleBean bean = new RoleBean();
		
		try{
			List al = new ArrayList();
			al = model.list(1,3);
			
			Iterator it = al.iterator();
			while(it.hasNext()){
				bean = (RoleBean)it.next();
   
				System.out.print(bean.getId());
	            System.out.print("\t"+bean.getName());
	            System.out.println("\t"+bean.getDescription());

			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

  
  }


