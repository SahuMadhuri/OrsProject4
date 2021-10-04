package in.co.rays.ORSProject4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSProject4.bean.MarksheetBean;
import in.co.rays.ORSProject4.exception.ApplicationException;
import in.co.rays.ORSProject4.exception.DuplicateRecordException;
import in.co.rays.ORSProject4.model.MarksheetModel;

public class MarksheetModelTest {
	   public static MarksheetModel model = new MarksheetModel();
	   public static void main(String[] args) {
		  // testFindByPK();
		  // testFindByRollNo();
		 // testAdd();
		   testDelete();
		  //testUpdate();
		  // testSearch();
		//testList();
		 // testMeritList();
	}
	   public static void testFindByPK() {
	        try {
	            MarksheetBean bean = new MarksheetBean();
	            long pk = 3;
	            bean = model.findByPK(pk);
	            if (bean == null) {
	                System.out.println("Test Find By PK fail");
	            }
	            System.out.println(bean.getId());
	            System.out.println(bean.getRollNo());
	            System.out.println(bean.getName());
	            System.out.println(bean.getPhysics());
	            System.out.println(bean.getChemistry());
	            System.out.println(bean.getMaths());
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }
	   public static void testFindByRollNo() {

	        try {
	            MarksheetBean bean = model.findByRollNo("2");
	            if (bean == null) {
	                System.out.println("Test Find By RollNo fail");
	            }
	            System.out.println(bean.getId());
	            System.out.println(bean.getRollNo());
	            System.out.println(bean.getName());
	            System.out.println(bean.getPhysics());
	            System.out.println(bean.getChemistry());
	            System.out.println(bean.getMaths());
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }
	   public static void testAdd(){
			
			MarksheetBean bean = new MarksheetBean();
			   
			try{
			     	
			     	bean.setRollNo("005");
		            bean.setPhysics(98);
		            bean.setChemistry(67);
		            bean.setMaths(99);
		            bean.setStudentId(1L);
		            bean.setCreatedBy("Admin");
		            bean.setModifiedBy("Admin");
		            
		           model.add(bean);
		       
		            
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	   public static void testDelete() {

	        try {
	            MarksheetBean bean = new MarksheetBean();
	            long pk = 3L;
	            bean.setId(pk);
	            model.delete(bean);
	            System.out.println("model deleted");
	            MarksheetBean deletedbean = model.findByPK(pk);
	            if (deletedbean != null) {
	                System.out.println("Test Delete fail");
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	   public static void testUpdate() {

	        try {
	            MarksheetBean bean = model.findByPK(3);
	            bean.setName("Akash");
	            bean.setChemistry(44);
	            bean.setMaths(44);
	            bean.setPhysics(66);
	             bean.setStudentId(3);
	            model.update(bean);

	            MarksheetBean updatedbean = model.findByPK(3L);
	            System.out.println("Test Update succ");
	            if (!"IIM".equals(updatedbean.getName())) {
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
	            MarksheetBean bean = new MarksheetBean();
	            List list = new ArrayList();
	            //bean.setName("IPS");
	            list=model.search(bean,0,0);
	            if (list.size() < 0) {
	                System.out.println("Test Search fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (MarksheetBean) it.next();
	                System.out.print(bean.getId());
	                System.out.print("\t"+bean.getRollNo());
	                System.out.print("\t"+bean.getName());
	                System.out.print("\t"+bean.getPhysics());
	                System.out.print("\t"+bean.getChemistry());
	                System.out.println("\t"+bean.getMaths());
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	   public static void testList() {
	        try {
	            MarksheetBean bean = new MarksheetBean();
	            List list = new ArrayList();
	            list = model.list(1, 6);
	            if (list.size() < 0) {
	                System.out.println("Test List fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (MarksheetBean) it.next();
	                System.out.print(bean.getId());
	                System.out.print("\t"+bean.getRollNo());
	                System.out.print("\t"+bean.getName());
	                System.out.print("\t"+bean.getPhysics());
	                System.out.print("\t"+bean.getChemistry());
	                System.out.print("\t"+bean.getMaths());
	                System.out.print("\t"+bean.getCreatedBy());
	                System.out.print("\t"+bean.getCreatedDatetime());
	                System.out.print("\t"+bean.getModifiedBy());
	                System.out.println("\t"+bean.getModifiedDatetime());
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }
	    public static void testMeritList() {
	        try {
	            MarksheetBean bean = new MarksheetBean();
	            List list = new ArrayList();
	            list = model.getMeritList(1, 5);
	            if (list.size() < 0) {
	                System.out.println("Test List fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (MarksheetBean) it.next();
	                System.out.print(bean.getId());
	                System.out.print("\t"+bean.getRollNo());
	                System.out.print("\t"+bean.getName());
	                System.out.print("\t"+bean.getPhysics());
	                System.out.print("\t"+bean.getChemistry());
	                System.out.println("\t"+bean.getMaths());
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }

}
	 

