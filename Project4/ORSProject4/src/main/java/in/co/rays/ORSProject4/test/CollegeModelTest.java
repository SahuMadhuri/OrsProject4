package in.co.rays.ORSProject4.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSProject4.bean.CollegeBean;
import in.co.rays.ORSProject4.exception.ApplicationException;
import in.co.rays.ORSProject4.exception.DuplicateRecordException;
import in.co.rays.ORSProject4.model.CollegeModel;

public class CollegeModelTest {
	
	 public static CollegeModel model = new CollegeModel();
	public static void main(String[] args) throws DuplicateRecordException,ApplicationException {
		//testFindByPK();
		//testAdd();
		testDelete();
		//testUpdate();
        //testList();
		//testSearch();
		//testFindByName();

	}
public static void testFindByPK(){
	try{
		CollegeBean bean=new CollegeBean();
		long pk=5L;
		bean=model.findByPK(pk);
		   if (bean == null) {
               System.out.println("Test Find By PK fail");
           }
           System.out.println(bean.getId());
           System.out.println(bean.getName());
           System.out.println(bean.getAddress());
           System.out.println(bean.getState());
           System.out.println(bean.getCity());
           System.out.println(bean.getPhoneNo());
           System.out.println(bean.getCreatedBy());
           System.out.println(bean.getCreatedDatetime());
           System.out.println(bean.getModifiedBy());
           System.out.println(bean.getModifiedDatetime());
       } catch (ApplicationException e) {
           e.printStackTrace();
       }
	}
public static void testAdd() throws DuplicateRecordException{
	try{
		CollegeBean bean=new CollegeBean();
		bean.setName("Madhu5");
		bean.setAddress("Sheetal nagar3");
		bean.setState("MP2");
		bean.setCity("Indore");
		bean.setPhoneNo("987665433");
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		 bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		 bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		 long pk = model.add(bean);
         System.out.println("Test add succ");
         CollegeBean addedBean = model.findByPK(pk);
         if (addedBean == null) {
             System.out.println("Test add fail");
         }
	}catch (ApplicationException e) {
        e.printStackTrace();
    }
}
public static void testDelete(){
	try{
		CollegeBean bean=new CollegeBean();
		long pk=16L;
		bean.setId(pk);
		model.delete(bean);
		System.out.println("Delete success");
		CollegeBean deletebean=model.findByPK(pk);
		if(deletebean !=null){
			System.out.println("test delete fail");
		}
	}catch(ApplicationException e){
		e.printStackTrace();
	}
}
public static void testUpdate() throws DuplicateRecordException{
	try{
		CollegeBean bean=model.findByPK(4L);
		bean.setName("Madhu");
		bean.setAddress("sheetal");
		model.update(bean);
		System.out.println("test update succ");
		CollegeBean updateBean=model.findByPK(4L);
		/*if(!"mmmm".equals(updateBean.getName())){
			System.out.println("test update fail");
		}*/
	}catch(ApplicationException e){
		e.printStackTrace();
	}
}
public static void testList(){
	try{
		CollegeBean bean = new CollegeBean();
		List<CollegeBean> l1= new ArrayList<CollegeBean>();
		l1= model.list(1,15);
		Iterator<CollegeBean> it= l1.iterator();
		while(it.hasNext()){
			bean = it.next();
			System.out.print(bean.getId());
			System.out.print("\t"+bean.getName());
			System.out.print("\t"+bean.getAddress());
			System.out.print("\t"+bean.getAddress());
            System.out.print("\t"+bean.getState());
            System.out.print("\t"+bean.getCity());
            System.out.print("\t"+bean.getPhoneNo());
            System.out.print("\t"+bean.getCreatedBy());
            System.out.print("\t"+bean.getCreatedDatetime());
            System.out.print("\t"+bean.getModifiedBy());
            System.out.println("\t"+bean.getModifiedDatetime());
		}
		
	}
	catch(Exception e){
e.printStackTrace();
	}
	
}
public static void testSearch() {
    try {
        CollegeBean bean = new CollegeBean();
        List list = new ArrayList();
        bean.setName("GSITS");
        // bean.setAddress("borawan");
        list = model.search(bean, 1, 15);
        if (list.size() < 0) {
            System.out.println("Test Search fail");
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            bean = (CollegeBean) it.next();
            System.out.println(bean.getId());
            System.out.println(bean.getName());
            System.out.println(bean.getAddress());
            System.out.println(bean.getState());
            System.out.println(bean.getCity());
            System.out.println(bean.getPhoneNo());
            System.out.println(bean.getCreatedBy());
            System.out.println(bean.getCreatedDatetime());
            System.out.println(bean.getModifiedBy());
            System.out.println(bean.getModifiedDatetime());
        }
    } catch (ApplicationException e) {
        e.printStackTrace();
    }
}
public static void testFindByName() throws ApplicationException{

    CollegeBean bean = model.findByName("GSITS");
	if (bean == null) {
	    System.out.println("Test Find By Name fail");
	}
	System.out.println(bean.getId());
	System.out.println(bean.getName());
	System.out.println(bean.getAddress());
	System.out.println(bean.getState());
	System.out.println(bean.getCity());
	System.out.println(bean.getPhoneNo());
	System.out.println(bean.getCreatedBy());
	System.out.println(bean.getCreatedDatetime());
	System.out.println(bean.getModifiedBy());
	System.out.println(bean.getModifiedDatetime());

}

}

