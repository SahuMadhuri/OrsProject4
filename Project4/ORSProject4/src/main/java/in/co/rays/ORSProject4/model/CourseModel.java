package in.co.rays.ORSProject4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.ORSProject4.bean.CourseBean;
import in.co.rays.ORSProject4.exception.ApplicationException;
import in.co.rays.ORSProject4.exception.DuplicateRecordException;
import in.co.rays.ORSProject4.util.JDBCDataSource;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
public class CourseModel {
	private static Logger log = Logger.getLogger(CourseModel.class);
	public Integer nextPk() throws Exception {
		log.debug("model nextpk started");
		System.out.println("CourseModel next pk sterted ");
		int pk = 0;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_COURSE");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		System.out.println("CourseModel next pk end ");
		return pk = pk + 1;
	}

	
	public long add(CourseBean bean) throws DuplicateRecordException, SQLException, ApplicationException {
		log.debug("");
		System.out.println("CourseModel add sterted ");
		int pk = 0;
		CourseBean beanExist=null;
		try {
			beanExist = findByName(bean.getName());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		if(beanExist!=null){
			throw new DuplicateRecordException("course name already exist!!! enter new course");
		}
		Connection conn = null;
		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_course values(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDuration());
			pstmt.setString(4, bean.getDescription());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			System.out.println("record inserted");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new ApplicationException("Exception in roll back"+e1.getMessage());
			}
			throw new ApplicationException("Exception in add course");
		} finally {
			conn.close();
		}
		log.debug("");
		System.out.println("CourseModel add end ");
		return pk;
	}
	public void delete(CourseBean bean) throws Exception {

		log.debug("");
		System.out.println("CourseModel delete sterted ");
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_course where id=?");
			pstmt.setLong(1, bean.getId());

			int i = pstmt.executeUpdate();
			System.out.println("record deleted" +i);
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		log.debug("model add end");
		System.out.println("CourseModel delete end ");
	}
	public void update(CourseBean bean) throws Exception {
		log.debug("model update started");
		System.out.println("CourseModel update sterted ");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update st_course set course_name=?,duration=?,description=?,created_by=?,modified_by=?,created_datetime=?,modified_datetime=? where id=?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDuration());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());
			int i = pstmt.executeUpdate();
			System.out.println("record updated"+i);
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		log.debug("model update end");
		System.out.println("CourseModel update end");
	}

	public CourseBean findByPk(long pk) throws Exception {
		log.debug("model findBypk Started");
		System.out.println("CourseModel find By pk sterted ");
        System.out.println("insidde pk");
		CourseBean bean = null;
		Connection conn = null;
		StringBuffer sql = new StringBuffer("select * from st_course where id=?");
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			System.out.println("course name is"+bean.getName());
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return bean;
	}

	public CourseBean findByName(String name) throws Exception {
		log.debug("");
		System.out.println("CourseModel find By Name sterted ");

		CourseBean bean = null;
		Connection conn = null;
       
		StringBuffer sql = new StringBuffer("select * from st_course where course_name=?");
       
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1,name);

			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			while (rs.next()) {
				bean = new CourseBean();

				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}

			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		System.out.println("CourseModel findByName sterted ");

		return bean;
	}

	public List search(CourseBean bean) throws Exception{
		return search(bean,0,0);
	}

	public List search(CourseBean bean,int pageNo,int pageSize) throws Exception{		
		log.debug("");
		System.out.println("Course Model search Started");		
		Connection conn= null;
		StringBuffer sql = new StringBuffer("select * from st_course where 1=1");
		if(bean!=null){
			if(bean.getId()>0){
				sql.append(" AND id = " + bean.getId());
			}
			if(bean.getName()!=null && bean.getName().length()>0){
				sql.append(" AND COURSE_NAME like '" +bean.getName()+ "%'");
			}
		   if(bean.getDuration()!=null && bean.getDuration().length()>0){
				sql.append(" AND DURATION like '" + bean.getDuration()+ "%'");
			}
			if(bean.getDescription()!=null && bean.getDescription().length()>0){
				sql.append(" AND DESCRIPTION like '" + bean.getDescription()+ "%'");
			}
			}
		if(pageSize>0){
			pageNo= (pageNo-1)*pageSize;
			sql.append(" LIMIT "+pageNo+ " ," +pageSize);
		}
		List<CourseBean> list = new ArrayList<CourseBean>();
		try{
			
			conn= JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			CourseBean bean1;
			while(rs.next()){
				bean1= new CourseBean();
				
				bean1.setId(rs.getLong(1));
				bean1.setName(rs.getString(2));
				bean1.setDuration(rs.getString(3));
				bean1.setDescription(rs.getString(4));
				bean1.setCreatedBy(rs.getString(5));
				bean1.setModifiedBy(rs.getString(6));
				bean1.setCreatedDatetime(rs.getTimestamp(7));
				bean1.setModifiedDatetime(rs.getTimestamp(8));
				
				list.add(bean1);
			}
			rs.close();
			pstmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		finally{
			conn.close();
		}
		System.out.println("CourseModel search End ");
		return list;
	}
	
	public List list(int pageNo,int pageSize) throws Exception{
		
		log.debug("model list started");
		System.out.println("CoueseModel list Started");
		
		List<CourseBean> list = new ArrayList<CourseBean>();
		
		StringBuffer sql = new StringBuffer("select * from st_course");
		
		if(pageSize>0){
			pageNo= (pageNo-1)*pageSize;
			sql.append(" limit "+pageNo+" ,"+pageSize);
		}
		
		Connection conn=null;
		
		try{
			conn= JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			
			ResultSet rs = pstmt.executeQuery();
			CourseBean bean1;
			while(rs.next()){
				bean1 = new CourseBean();
				
				bean1.setId(rs.getLong(1));
				bean1.setName(rs.getString(2));
				bean1.setDuration(rs.getString(3));
				bean1.setDescription(rs.getString(4));
				bean1.setCreatedBy(rs.getString(5));
				bean1.setModifiedBy(rs.getString(6));
				bean1.setCreatedDatetime(rs.getTimestamp(7));
				bean1.setModifiedDatetime(rs.getTimestamp(8));
				
				list.add(bean1);
			}
			rs.close();
			pstmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			conn.close();
		}
		return list;
	}
	
	public List list() throws Exception{
		return list(0,0);
	}
}
