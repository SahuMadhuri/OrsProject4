package in.co.rays.ORSProject4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.ORSProject4.bean.CollegeBean;
import in.co.rays.ORSProject4.bean.StudentBean;
import in.co.rays.ORSProject4.exception.ApplicationException;
import in.co.rays.ORSProject4.exception.DatabaseException;
import in.co.rays.ORSProject4.exception.DuplicateRecordException;
import in.co.rays.ORSProject4.util.JDBCDataSource;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
public class StudentModel {
 private static Logger log=Logger.getLogger(StudentModel.class);
 public Integer nextPk() throws ApplicationException {
		log.debug("Model nextPK Started");
		System.out.println("SubjectModel nextPK Started");
		int pk = 0;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_student");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApplicationException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		System.out.println("SubjectModel nextPK End");
		return pk = pk + 1;
	}
 public long add(StudentBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		System.out.println("SubjectModel add Started");
		Connection conn = null;

		// get College Name
		CollegeModel cModel = new CollegeModel();
		CollegeBean collegeBean = cModel.findByPK(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());

		StudentBean duplicateName = findByEmailId(bean.getEmail());
		int pk = 0;

		if (duplicateName != null) {
			throw new DuplicateRecordException("Email already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_STUDENT VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getCollegeId());
			pstmt.setString(3, bean.getCollegeName());
			pstmt.setString(4, bean.getFirstName());
			pstmt.setString(5, bean.getLastName());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getGender());
			pstmt.setString(8, bean.getMobileNo());
			pstmt.setString(9, bean.getEmail());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		System.out.println("SubjectModel add End");
		return pk;
	}
 public StudentBean findByEmailId(String Email) throws ApplicationException{
	 log.debug("model findBy Email Started");
	 System.out.println("SubjectModel findBy Email Started");
	 StringBuffer sql=new StringBuffer("select * from st_student where email=?");
	 StudentBean bean=null;
	 Connection conn=null;
	 try{
		 conn=JDBCDataSource.getConnection();
		 
		 PreparedStatement pstmt=conn.prepareStatement(sql.toString());
		 pstmt.setString(1, Email);
		 ResultSet rs=pstmt.executeQuery();
		 while(rs.next()){
			  bean = new StudentBean();
              bean.setId(rs.getLong(1));
              bean.setCollegeId(rs.getLong(2));
              bean.setCollegeName(rs.getString(3));
              bean.setFirstName(rs.getString(4));
              bean.setLastName(rs.getString(5));
              bean.setDob(rs.getDate(6));
              bean.setGender(rs.getString(7));
              bean.setMobileNo(rs.getString(8));
              bean.setEmail(rs.getString(9));
              bean.setCreatedBy(rs.getString(10));
              bean.setModifiedBy(rs.getString(11));
              bean.setCreatedDatetime(rs.getTimestamp(12));
              bean.setModifiedDatetime(rs.getTimestamp(13));
		 }
		 rs.close();
	 }catch(Exception e){
		 log.error("Database exception", e);
		 throw new ApplicationException("Exception :Exception in getting User by Email");
	 }finally{
		 JDBCDataSource.closeConnection(conn);
	 }
	 log.debug("model findBy Email End");
	 System.out.println("SubjectModel findBy Email End");
	 return bean;
 }
 public StudentBean findByPK(long pk) throws ApplicationException {
     log.debug("Model findByPK Started");
     System.out.println("SubjectModel findByPK Started");
     StringBuffer sql = new StringBuffer("SELECT * FROM ST_STUDENT WHERE ID=?");
     StudentBean bean = null;
     Connection conn = null;
     try {
         conn = JDBCDataSource.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql.toString());
         pstmt.setLong(1, pk);
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
             bean = new StudentBean();
             bean.setId(rs.getLong(1));
             bean.setCollegeId(rs.getLong(2));
             bean.setCollegeName(rs.getString(3));
             bean.setFirstName(rs.getString(4));
             bean.setLastName(rs.getString(5));
             bean.setDob(rs.getDate(6));
             bean.setGender(rs.getString(7));
             bean.setMobileNo(rs.getString(8));
             bean.setEmail(rs.getString(9));
             bean.setCreatedBy(rs.getString(10));
             bean.setModifiedBy(rs.getString(11));
             bean.setCreatedDatetime(rs.getTimestamp(12));
             bean.setModifiedDatetime(rs.getTimestamp(13));
         }
         rs.close();
     } catch (Exception e) {
         log.error("Database Exception..", e);
         throw new ApplicationException( "Exception : Exception in getting User by pk");
     } finally {
         JDBCDataSource.closeConnection(conn);
     }
     log.debug("Model findByPK End");
     System.out.println("SubjectModel findByPK End");
     return bean;
 }
 public void delete(StudentBean bean) throws ApplicationException{
	 log.debug("model delete started");
	 System.out.println("SubjectModel delete started");
	 Connection conn=null;
	 try{
		 conn=JDBCDataSource.getConnection();
		 conn.setAutoCommit(false);
		 PreparedStatement pstmt=conn.prepareStatement("delete from st_student where id=?");
		 pstmt.setLong(1, bean.getId());
		 int i=pstmt.executeUpdate();
	     System.out.println("i="+i);
		  conn.commit();
	     pstmt.close();
	 }catch(Exception e){
		 log.error("Database excetion", e);
		 try{
			 conn.rollback();
		 }catch(Exception ex){
			 throw new ApplicationException("Exception : Delete rollback exception"+ex.getMessage());
		 }finally{
			 JDBCDataSource.closeConnection(conn);
		 }
		 log.debug("Model delete Started");
		 System.out.println("SubjectModel delete Started");
	 }
 }
 public void update(StudentBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		System.out.println("SubjectModel update Started");
		Connection conn = null;

		//StudentBean beanExist = findByEmail(bean.getEmail());
		// Checks if updated Roll no already exist
		/*if (beanExist != null && beanExist.getId() == bean.getId()) {
			throw new DuplicateRecordException("Emailid already exist");
		}*/

		CollegeModel cmodel = new CollegeModel();
		CollegeBean cbean = cmodel.findByPK(bean.getId());
		bean.setCollegeName(cbean.getName());

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_STUDENT SET COLLEGE_ID=?,COLLEGE_NAME=?,FIRST_NAME=?,LAST_NAME=?,DATE_OF_BIRTH=?,Gender=?,MOBILE_NO=?,EMAIL=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setLong(1, bean.getCollegeId());
			pstmt.setString(2, bean.getCollegeName());
			pstmt.setString(3, bean.getFirstName());
			pstmt.setString(4, bean.getLastName());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7,bean.getGender());
			pstmt.setString(8, bean.getEmail());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.setLong(13, bean.getId());
			pstmt.executeUpdate();
			System.out.println("record updated");
			conn.commit();
			
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception" + e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
				throw new ApplicationException("exception in rollback");
			}
			throw new ApplicationException("Exception in getting update");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
		System.out.println("SubjectModel update End");
	}
 public List search(StudentBean bean) throws ApplicationException {
	 System.out.println("SubjectModel nextPK Started");
     return search(bean, 0, 0);
 }
 public List search(StudentBean bean, int pageNo, int pageSize)throws ApplicationException {
     log.debug("Model search Started");
     System.out.println("SubjectModel search Started");
     StringBuffer sql = new StringBuffer("SELECT * FROM st_STUDENT WHERE 1=1");

     if (bean != null) {
         if (bean.getId() > 0) {
             sql.append(" AND id = " + bean.getId());
         }
         if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
             sql.append(" AND FIRST_NAME like '" + bean.getFirstName()
                     + "%'");
         }
         if (bean.getLastName() != null && bean.getLastName().length() > 0) {
             sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%'");
         }
         if (bean.getDob() != null && bean.getDob().getDate() > 0) {
             sql.append(" AND DOB = " + bean.getDob());
         }
         if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
             sql.append(" AND MOBILE_NO like '" + bean.getMobileNo() + "%'");
         }
         if (bean.getEmail() != null && bean.getEmail().length() > 0) {
             sql.append(" AND EMAIL like '" + bean.getEmail() + "%'");
         }
         if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
             sql.append(" AND COLLEGE_NAME like '" + bean.getCollegeName() + "%'");
         }

     }

     
     if (pageSize > 0) {
         // Calculate start record index
         pageNo = (pageNo - 1) * pageSize;

         sql.append(" Limit " + pageNo + ", " + pageSize);
         // sql.append(" limit " + pageNo + "," + pageSize);
     }

     ArrayList list = new ArrayList();
     Connection conn = null;
     try {
         conn = JDBCDataSource.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql.toString());
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
             bean = new StudentBean();
             bean.setId(rs.getLong(1));
             bean.setCollegeId(rs.getLong(2));
             bean.setCollegeName(rs.getString(3));
             bean.setFirstName(rs.getString(4));
             bean.setLastName(rs.getString(5));
             bean.setDob(rs.getDate(6));
             bean.setGender(rs.getString(7));
             bean.setMobileNo(rs.getString(8));
             bean.setEmail(rs.getString(9));
             bean.setCreatedBy(rs.getString(10));
             bean.setModifiedBy(rs.getString(11));
             bean.setCreatedDatetime(rs.getTimestamp(12));
             bean.setModifiedDatetime(rs.getTimestamp(13));
             list.add(bean);
         }
         rs.close();
     } catch (Exception e) {
         log.error("Database Exception..", e);
         throw new ApplicationException("Exception : Exception in search Student");
     } finally {
         JDBCDataSource.closeConnection(conn);
     }

     log.debug("Model search End");
     System.out.println("SubjectModel search End");
     return list;
 }
 public List list() throws ApplicationException {
     return list(0, 0);
 }
 public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Begin");
		System.out.println("SubjectModel list Started");
		Connection conn = null;
		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_student");

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				StudentBean bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setCollegeId(rs.getLong(2));
				bean.setCollegeName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setGender(rs.getString(7));
				bean.setMobileNo(rs.getString(8));
				bean.setEmail(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			log.error("database exception" , e);
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting list of Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model list end");
		System.out.println("SubjectModel list end");
		return list;

	}

}
