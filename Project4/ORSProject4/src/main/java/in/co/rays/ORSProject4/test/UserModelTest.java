package in.co.rays.ORSProject4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.rays.ORSProject4.bean.UserBean;
import in.co.rays.ORSProject4.exception.ApplicationException;
import in.co.rays.ORSProject4.exception.DuplicateRecordException;
import in.co.rays.ORSProject4.exception.RecordNotFoundException;
import in.co.rays.ORSProject4.model.UserModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class UserModelTest {
	public static UserModel model = new UserModel();
	public static void main(String[] args) throws Exception {
   //testAdd();
   // testDelete();
   // testUpdate();
   // testFindByPK();
	//testFindByLogin();
	//testSearch();
	testList();
	//testAuthenticate();
	//testGetRoles();
		//testforgetPassword(); 
	//testRegisterUser();
		//testchangePassword();
		//testresetPassword();
	}
	public static void testAdd() throws ApplicationException,Exception{
		 try {
	            UserBean bean = new UserBean();
	            SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-yyyy");

	            // bean.setId(1);
	            bean.setFirstName("Aditi45");
	            bean.setLastName("Sharma");
	            bean.setLogin("Aditi45@gmail.com");
	            bean.setPassword("Aditi@123");
	            bean.setDob(sdf.parse("21-05-1999"));
	            bean.setMobileNo("99897979");
	            bean.setRoleId(3L);
	            bean.setUnSuccessfulLogin(2);
	            bean.setGender("Female");
	            bean.setLastLogin(new Timestamp(new Date().getTime()));
	            bean.setLock("Yes");
	            bean.setRegisteredIP("56");
	            bean.setConfirmPassword("Aditi@123");
	            //UserModel model=new UserModel();
	            model.add(bean);
	            
	            System.out.println("Test add succ");
	            
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	}
	  public static void testDelete() throws Exception {

	        try {
	            UserBean bean = new UserBean();
	           // long pk = 0L;
	            bean.setId(3);
	            //UserModel model=new UserModel();
	           model.delete(bean);
	            System.out.println("Test Delete succ" + bean.getId());
	          /* UserBean deletedbean = model.findByPK(pk);
	            if (deletedbean == null) {
	                System.out.println("Test Delete fail");
	            }*/
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	  public static void testUpdate() throws DuplicateRecordException{
		  try{
			  UserBean bean=model.findByPK(2L);
			  bean.setFirstName("Madhu");
			  bean.setLastName("Sahu");
			  bean.setLogin("MSahu12@gmail.com");
			  bean.setPassword("90909191");
			  model.update(bean);
			  //UserBean updatebean=model.findByPk(1L);
			  
			  
		  } catch (ApplicationException e) {
	            e.printStackTrace();
	        } 
	  }
	  public static void testFindByPK() {
	        try {
	            UserBean bean = new UserBean();
	            long pk = 3L;
	            bean = model.findByPK(pk);
	            if (bean == null) {
	                System.out.println("Test Find By PK fail");
	            }
	            System.out.println(bean.getId());
	            System.out.println(bean.getFirstName());
	            System.out.println(bean.getLastName());
	            System.out.println(bean.getLogin());
	            System.out.println(bean.getPassword());
	            System.out.println(bean.getDob());
	            System.out.println(bean.getRoleId());
	            System.out.println(bean.getUnSuccessfulLogin());
	            System.out.println(bean.getGender());
	            System.out.println(bean.getLastLogin());
	            System.out.println(bean.getLock());
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }
	  public static void testFindByLogin(){
		   
		   try{
			   UserBean bean = new UserBean();
			   bean= model.findByLogin("MSahu@gmail.com");
			   System.out.println("bean value is"+bean);
			   if(bean==null){
				   System.out.println("Test Find By PK fail");
			   }
			   
			   
				   System.out.println(bean.getId());
				   System.out.println(bean.getFirstName());
				   System.out.println(bean.getLastName());
		            System.out.println(bean.getLogin());
		            System.out.println(bean.getPassword());
		            System.out.println(bean.getDob());
		            System.out.println(bean.getId());
		            System.out.println(bean.getUnSuccessfulLogin());
		            System.out.println(bean.getGender());
		            System.out.println(bean.getLastLogin());
		            System.out.println(bean.getLock());
			   
			   
			   
		   }
		   catch(Exception e){
			   
		   }
	   }
	  public static void testSearch(){
		   UserBean bean = new UserBean();
		   List list = new ArrayList();
		   //bean.setFirstName("Madhu1");
		    try {
			   list= model.search(bean,1,5);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		   bean.setId(2);
		   
		   Iterator it =list.iterator();
		   while(it.hasNext()){
			   bean=(UserBean)it.next();
			   System.out.print(bean.getId());
	           System.out.print("\t"+bean.getFirstName());
	           System.out.print("\t"+bean.getLastName());
	           System.out.print("\t"+bean.getLogin());
	           System.out.print("\t"+bean.getPassword());
	           System.out.print("\t"+bean.getDob());
	           System.out.print("\t"+bean.getId());
	           System.out.print("\t"+bean.getUnSuccessfulLogin());
	           System.out.print("\t"+bean.getGender());
	           System.out.print("\t"+bean.getLastLogin());
	           System.out.println("\t"+bean.getLock());
		   }
	   }
	  public static void testList(){
		  try{
			  UserBean bean=new UserBean();
			  List list=new ArrayList();
			  list=model.list();
			  if(list.size()<0){
				  System.out.println("Test list fail");
				  
			  }
			  Iterator it=list.iterator();
			  while(it.hasNext()){
				  bean=(UserBean) it.next();
				  System.out.print(bean.getId());
				  System.out.print("\t"+bean.getFirstName());
				  System.out.print("\t"+bean.getLastName());
				  System.out.print("\t"+bean.getLogin());
				  System.out.print("\t"+bean.getLogin());
	              System.out.print("\t"+bean.getPassword());
	              System.out.print("\t"+bean.getDob());
	              System.out.print("\t"+bean.getRoleId());
	              System.out.print("\t"+bean.getUnSuccessfulLogin());
	              System.out.print("\t"+bean.getGender());
	              System.out.print("\t"+bean.getLastLogin());
	              System.out.print("\t"+bean.getLock());
	              System.out.print("\t"+bean.getMobileNo());
	              System.out.print("\t"+bean.getCreatedBy());
	              System.out.print("\t"+bean.getModifiedBy());
	              System.out.print("\t"+bean.getCreatedDatetime());
	              System.out.println("\t"+bean.getModifiedDatetime());
			  }
			  
		  } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	  }
	  public static void testAuthenticate(){
		  try{
			  UserBean bean =new UserBean();
			  bean.setLogin("MSahu@gmail.com");
			  bean.setPassword("Madhuri@1245");
			  bean=model.authenticate(bean.getLogin(), bean.getPassword());
			  if(bean!=null){
				  System.out.println("Successfully login");
			  }else{
				  System.out.println("invalid login id& password");
			  }
		  }catch(ApplicationException e){
			e.printStackTrace();  
		  }
	  }
	   public static void testforgetPassword() {
	        try {
	            boolean b = model.forgetPassword("Aditi21@gmail.com");

	            System.out.println("Suucess : Test Forget Password Success");

	        } catch (RecordNotFoundException e) {
	            e.printStackTrace();
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	  public static void testGetRoles(){
		  try{
			  UserBean bean=new UserBean();
			  List list=new ArrayList();
			  bean.setRoleId(4L);
			  list=model.getRoles(bean);
			  if(list.size()<0){
				  System.out.println("Test Get Roles Fail");
			  }
			  Iterator it =list.iterator();
			  while(it.hasNext()){
				  bean = (UserBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getFirstName());
	                System.out.println(bean.getLastName());
	                System.out.println(bean.getLogin());
	                System.out.println(bean.getPassword());
	                System.out.println(bean.getDob());
	                System.out.println(bean.getRoleId());
	                System.out.println(bean.getUnSuccessfulLogin());
	                System.out.println(bean.getGender());
	                System.out.println(bean.getLastLogin());
	                System.out.println(bean.getLock());
			  }
		  }catch(ApplicationException e){
			  e.printStackTrace();
		  }
	  }
	  public static void testRegisterUser(){
		   UserBean bean = new UserBean();
		   try{
			   
			   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			   
			   bean.setFirstName("Yatika1");
				bean.setLastName("gaud1");
				bean.setLogin("Yatika14@gmail.com");
				bean.setPassword("Yatika@123");
				bean.setConfirmPassword("Yatika5@123");
				bean.setDob(sdf.parse("12/7/1996"));
				bean.setMobileNo("998979794");
				bean.setRoleId(7);
				bean.setGender("Female");
				bean.setCreatedBy("student");
				bean.setModifiedBy("student");
				bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
				bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
	       long pk = model.registerUser(bean);
	       System.out.println("Successfully register");
		   }
		   catch(Exception e){
			   e.printStackTrace();
		   }
	   }
	  public static void testchangePassword() {

	        try {
	            UserBean bean = model.findByLogin("Pinky123@gmail.com");
	            String oldPassword = bean.getPassword();
	           // bean.setId(8l);
	            bean.setPassword("Raystech@21");
	            bean.setConfirmPassword("Raystech@21");
	            String newPassword = bean.getPassword();
	            try {
	                model.changePassword(4L, oldPassword, newPassword);
	                System.out.println("password has been change successfully");
	            } catch (RecordNotFoundException e) {
	                e.printStackTrace();
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }
	  public static void testresetPassword() {
	        UserBean bean = new UserBean();
	        try {
	            bean = model.findByLogin("Aditi21@gmail.com");
	            if (bean != null) {
	                boolean pass = model.resetPassword(bean);
	                if (pass = false) {
	                    System.out.println("Test Update fail");
	                }
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }
	  
}


