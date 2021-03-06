package in.co.rays.ORSProject4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.ORSProject4.bean.CourseBean;
import in.co.rays.ORSProject4.bean.SubjectBean;
import in.co.rays.ORSProject4.exception.ApplicationException;
import in.co.rays.ORSProject4.exception.DatabaseException;
import in.co.rays.ORSProject4.exception.DuplicateRecordException;
import in.co.rays.ORSProject4.util.JDBCDataSource;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
public class SubjectModel {
	
	private static Logger log = Logger.getLogger(SubjectModel.class);

	/**
	 * Find next PK of Subject
	 * 
	 * @throws DatabaseException
	 */
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		System.out.println("SubjectModel nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_SUBJECT");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		System.out.println("SubjectModel nextPK End");
		return pk + 1;
	}

	/**
	 * Add a Subject
	 * 
	 * @param bean
	 * @return
	 * @throws Exception 
	 */
	public long add(SubjectBean bean) throws Exception {
		log.debug("Model add Started");
		System.out.println("SubjectModel add Started");
		Connection conn = null;
		int pk = 0;

		// get Course Name
		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());
		bean.setCourseName(courseBean.getName());

		SubjectBean duplicateSubject = findByName(bean.getName());
		// Check if create Subject Name already exist
		if (duplicateSubject != null) {
			throw new DuplicateRecordException("Subject Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_SUBJECT VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setLong(3, bean.getCourseId());
			pstmt.setString(4, bean.getCourseName());
			pstmt.setString(5, bean.getDescription());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Subject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		System.out.println("SubjectModel add End");
		return pk;
	}

	/**
	 * Delete a Subject
	 * 
	 * @param bean
	 * @throws ApplicationException
	 */
	public void delete(SubjectBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		System.out.println("SubjectModel delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_SUBJECT WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Subject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
		System.out.println("SubjectModel delete Started");
	}

	/**
	 * Find Subject by Subject Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return bean
	 * @throws ApplicationException
	 */

	public SubjectBean findByName(String name) throws ApplicationException {
		log.debug("Model findBy Subject Name Started");
		System.out.println("SubjectModel findBy Subject Name Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE SUBJECT_NAME=?");
		SubjectBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				 bean = new SubjectBean();
				 bean.setId(rs.getLong(1));
				 bean.setName(rs.getString(2));
				 bean.setCourseId(rs.getInt(3));
				 bean.setCourseName(rs.getString(4));
				 bean.setDescription(rs.getString(5));
				 bean.setCreatedBy(rs.getString(6));
				 bean.setModifiedBy(rs.getString(7));
				 bean.setCreatedDatetime(rs.getTimestamp(8));
				 bean.setModifiedDatetime(rs.getTimestamp(9));

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Subject by Subject Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy Subject Name End");
		System.out.println("SubjectModel findBy Subject Name End");
		return bean;
	}

	
	/**
	 * Find Subject by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws ApplicationException
	 */

	public SubjectBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		System.out.println("SubjectModel findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE ID=?");
		SubjectBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setCourseName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Subject by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		System.out.println("SubjectModel findByPK End");
		return bean;
	}

	/**
	 * Update a Subject
	 * 
	 * @param bean
	 * @throws Exception 
	 */
	public void update(SubjectBean bean) throws Exception {
		log.debug("Model update Started");
		System.out.println("SubjectModel update Started");
		Connection conn = null;
		// get Course Name
		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());
		bean.setCourseName(courseBean.getName());
		/*
		 * SubjectBean duplicateSubject = findByName(bean.getName()); // Check
		 * if updated Subject already exist if (duplicateSubject != null &&
		 * duplicateSubject.getId() != bean.getId()) { throw new
		 * DuplicateRecordException("Suject already exists"); }
		 */ try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_SUBJECT SET SUBJECT_NAME=?,COURSE_ID=?,COURSE_NAME=?,DESCRIPTION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getName());
			pstmt.setLong(2, bean.getCourseId());
			pstmt.setString(3, bean.getCourseName());
			pstmt.setString(4, bean.getDescription());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Subject ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
		System.out.println("SubjectModel update End");
	}

	/**
	 * Search Subject
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws ApplicationException
	 */

	public List<SubjectBean> search(SubjectBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search Subject with pagination
	 * 
	 * @return list : List of Subject
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws ApplicationException
	 */

	public List<SubjectBean> search(SubjectBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		System.out.println("SubjectModel search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND SUBJECT_NAME like '" + bean.getName() + "%'");
			}
			if (bean.getCourseId() > 0) {
				sql.append(" AND COURSE_ID = " + bean.getCourseId());
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" AND COURSE_NAME like '" + bean.getCourseName() + "%'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" AND DESCRIPTION like '" + bean.getDescription() + "%'");
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList<SubjectBean> list = new ArrayList<SubjectBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setCourseName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Subject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		System.out.println("SubjectModel search End");
		return list;
	}

	/**
	 * Get List of Subject
	 * 
	 * @return list : List of Subject
	 * @throws ApplicationException
	 */

	public List<SubjectBean> list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of Subject with pagination
	 * 
	 * @return list : List of Subject
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */

	public List<SubjectBean> list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		System.out.println("SubjectModel list Started");
		ArrayList<SubjectBean> list = new ArrayList<SubjectBean>();
		StringBuffer sql = new StringBuffer("select * from ST_SUBJECT ORDER BY SUBJECT_NAME");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				SubjectBean bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setCourseName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Subject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		System.out.println("SubjectModel list End");
		return list;

	}

}
	
