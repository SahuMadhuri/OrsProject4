package in.co.rays.ORSProject4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.ORSProject4.bean.CourseBean;
import in.co.rays.ORSProject4.bean.SubjectBean;
import in.co.rays.ORSProject4.bean.TimetableBean;
import in.co.rays.ORSProject4.exception.ApplicationException;
import in.co.rays.ORSProject4.exception.DatabaseException;
import in.co.rays.ORSProject4.util.JDBCDataSource;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
public class TimetableModel {
	private static Logger log = Logger.getLogger(TimetableModel.class);

	/**
	 * Find next PK of Timetable
	 * 
	 * @throws DatabaseException
	 */
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		System.out.println("TimeTableModel nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_TIMETABLE");
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
		System.out.println("TimeTableModel nextPK End");
		return pk + 1;
	}

	/**
	 * Add a Timetable
	 * 
	 * @param bean
	 * @return
	 * @throws Exception 
	 */
	public long add(TimetableBean bean) throws Exception {
		System.out.println("TimeTableModel add Started");
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		// get Course Name
		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());
		bean.setCourseName(courseBean.getName());

		// get Subject Name
		SubjectModel subjectModel = new SubjectModel();
		SubjectBean subjectBean = subjectModel.findByPK(bean.getSubjectId());
		bean.setSubjectName(subjectBean.getName());
		System.out.println("Subject 67 "+subjectBean.getName());

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_TIMETABLE VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getCourseId());
			pstmt.setString(3, bean.getCourseName());
			pstmt.setLong(4, bean.getSubjectId());
			pstmt.setString(5, bean.getSubjectName());
			pstmt.setString(6, bean.getSemester());
			pstmt.setString(7, bean.getDescription());
			pstmt.setDate(8, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(9, bean.getExamTime());
			
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());
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
			throw new ApplicationException("Exception : Exception in add Timetable");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		System.out.println("TimeTableModel add End");
		return pk;
	}

	/**
	 * Delete a Timetable
	 * 
	 * @param bean
	 * @throws ApplicationException
	 */
	public void delete(TimetableBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		System.out.println("TimeTableModel DELETE Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_TIMETABLE WHERE ID=?");
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
			throw new ApplicationException("Exception : Exception in delete Timetable");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
		System.out.println("TimeTableModel delete End");
	}

	/**
	 * @param examTime
	 * @return
	 * @throws ApplicationException
	 */
	public TimetableBean checkByExamTime(int i, int j, String semester, Date examDate, String examTime,
			String description) throws ApplicationException {
		System.out.println("TimeTableModel nextPK Started");
		log.debug("Model checkByExamTime Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_TIMETABLE WHERE COURSE_ID=? AND SUBJECT_ID=? AND SEMESTER=? AND EXAM_DATE=? AND EXAM_TIME=? AND DESCRIPTION=?");
		TimetableBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, i);
			pstmt.setLong(2, j);
			pstmt.setString(3, semester);
			pstmt.setDate(4, new java.sql.Date(examDate.getTime()));
			pstmt.setString(5, examTime);
			pstmt.setString(6, description);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimetableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getInt(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getInt(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setDescription(rs.getString(7));
				bean.setExamDate(rs.getDate(8));
				bean.setExamTime(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));

			}
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get Timetable");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model checkByExamTime End");
		System.out.println("TimeTableModel checkByExamTime Started");
		return bean;
	}

	/**
	 * @param i
	 * @param examDate
	 * @return
	 * @throws ApplicationException
	 */
	public TimetableBean checkByCourseName(int i, Date examDate) throws ApplicationException {
		log.debug("Model checkByCourseName Started");
		System.out.println("TimeTableModel checkByCourseName Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE COURSE_ID=? AND EXAM_DATE=?");
		TimetableBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, i);
			pstmt.setDate(2, new java.sql.Date(examDate.getTime()));

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimetableBean();
				bean.setId(rs.getInt(1));
				bean.setCourseId(rs.getInt(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getInt(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setDescription(rs.getString(7));
				bean.setExamDate(rs.getDate(8));
				bean.setExamTime(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));

			}
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get Timetable");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model checkByCourseName End");
		System.out.println("TimeTableModel checkByCourseName END");
		return bean;
	}

	/**
	 * @param i
	 * @param j
	 * @param examDate
	 * @return
	 * @throws ApplicationException
	 */
	public TimetableBean checkBySubjectName(int i, int j, Date examDate) throws ApplicationException {
		log.debug("Model checkBySubjectName Started");
		System.out.println("TimeTableModel checkBySubjectName Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_TIMETABLE WHERE COURSE_ID=? AND SUBJECT_ID=? AND EXAM_DATE=?");
		TimetableBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, i);
			pstmt.setLong(2, j);
			pstmt.setDate(3, new java.sql.Date(examDate.getTime()));

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimetableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getInt(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getInt(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setDescription(rs.getString(7));
				bean.setExamDate(rs.getDate(8));
				bean.setExamTime(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));

			}
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get Timetable");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model checkBySubjectName End");
		System.out.println("TimeTableModel checkBySubjectName End");
		return bean;
	}

	/**
	 * @param i
	 * @param j
	 * @param semester
	 * @param examDate
	 * @return
	 * @throws ApplicationException
	 */
	public TimetableBean checkBySemester(int i, int j, String semester, Date examDate)
			throws ApplicationException {
		log.debug("Model checkBySemester Started");
		System.out.println("TimeTableModel checkBySemester Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE COURSE_ID=? AND SUBJECT_ID=? AND SEMESTER=? AND EXAM_DATE=?");
		TimetableBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, i);
			pstmt.setLong(2, j);
			pstmt.setString(3, semester);
			pstmt.setDate(4, new java.sql.Date(examDate.getTime()));

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimetableBean();
				bean.setId(rs.getInt(1));
				bean.setCourseId(rs.getInt(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getInt(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setDescription(rs.getString(7));
				bean.setExamDate(rs.getDate(8));
				bean.setExamTime(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));

			}
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get Timetable");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model checkBySemester End");
		System.out.println("TimeTableModel checkBySemester End");
		return bean;
	}

	/**
	 * Find Timetable by PK
	 * 
	 * @param id
	 *            : get parameter
	 * @return bean
	 * @throws ApplicationException
	 */

	public TimetableBean findByPK(long id) throws ApplicationException {
		log.debug("Model findByPK Started");
		System.out.println("TimeTableModel FindByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE ID=?");
		TimetableBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimetableBean();
				bean.setId(rs.getInt(1));
				bean.setCourseId(rs.getInt(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getInt(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setDescription(rs.getString(7));
				bean.setExamDate(rs.getDate(8));
				bean.setExamTime(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Timetable by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		System.out.println("TimeTableModel FindByPK End");
		return bean;
	}

	/**
	 * Update a Timetable
	 * 
	 * @param bean
	 * @throws Exception 
	 */
	public void update(TimetableBean bean) throws Exception {
		log.debug("Model update Started");
		System.out.println("TimeTableModel update Started");
		Connection conn = null;

		// get Course Name
		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());
		bean.setCourseName(courseBean.getName());

		// get Subject Name
		SubjectModel subjectModel = new SubjectModel();
		SubjectBean subjectBean = subjectModel.findByPK(bean.getSubjectId());;
		bean.setSubjectName(subjectBean.getName());

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_TIMETABLE SET COURSE_ID=?,COURSE_NAME=?,SUBJECT_ID=?,SUBJECT_NAME=?,SEMESTER=?,DESCRIPTION=?,EXAM_DATE=?,EXAM_TIME=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setLong(1, bean.getCourseId());
			pstmt.setString(2, bean.getCourseName());
			pstmt.setLong(3, bean.getSubjectId());
			pstmt.setString(4, bean.getSubjectName());
			pstmt.setString(5, bean.getSemester());
			pstmt.setString(6, bean.getDescription());
			pstmt.setDate(7, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(8, bean.getExamTime());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.setLong(13, bean.getId());
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
			throw new ApplicationException("Exception in updating Timetable ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
		System.out.println("TimeTableModel update end");
	}

	/**
	 * Search Timetable
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws ApplicationException
	 */

	public List<TimetableBean> search(TimetableBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search Timetable with pagination
	 * 
	 * @return list : List of Timetable
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws ApplicationException
	 */

	@SuppressWarnings("deprecation")
	public List<TimetableBean> search(TimetableBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		System.out.println("TimeTableModel search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE 1=1");
System.out.println("787989808");


		if (bean != null) {
			System.out.println(bean);
			if (bean.getId() > 0) 
			{
				sql.append(" AND ID = " + bean.getId());
				System.out.println(sql.append(" AND ID = " + bean.getId()));
			}
			if (bean.getCourseId() > 0) {
				sql.append(" AND COURSE_ID = " + bean.getCourseId());
				System.out.println(sql.append(" AND COURSE_ID = " + bean.getCourseId()));
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" AND COURSE_NAME like '" + bean.getCourseName() + "%'");
				System.out.println(sql.append(" AND COURSE_NAME like '" + bean.getCourseName() + "%'"));
			}
			if (bean.getSubjectId() > 0) {
				sql.append(" AND SUBJECT_ID = " + bean.getSubjectId());
				System.out.println(sql.append(" AND SUBJECT_ID = " + bean.getSubjectId()));
			}
			if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
				System.out.println(sql.append(" AND SUBJECT_NAME like '" + bean.getSubjectName() + "%'"));
				sql.append(" AND SUBJECT_NAME like '" + bean.getSubjectName() + "%'");
				
				
			}
			if (bean.getSemester() != null && bean.getSemester().length() > 0) {
				sql.append(" AND SEMESTER like '" + bean.getSemester() + "%'");
				System.out.println(sql.append(" AND SEMESTER like '" + bean.getSemester() + "%'"));
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" AND DESCRIPTION like '" + bean.getDescription() + "%'");
				System.out.println(" AND DESCRIPTION like '" + bean.getDescription() + "%'");
			}
			if (bean.getExamDate() != null && bean.getExamDate().getDate() > 0) {
				sql.append(" AND EXAM_DATE like '" + new java.sql.Date(bean.getExamDate().getTime()) + "%'");
				System.out.println(sql.append(" AND EXAM_DATE like '" + new java.sql.Date(bean.getExamDate().getTime()) + "%'"));
			}
			if (bean.getExamTime() != null && bean.getExamTime().length() > 0) {
				System.out.println(sql.append(" AND EXAM_TIME like '" + bean.getExamTime() + "%'"));
				sql.append(" AND EXAM_TIME like '" + bean.getExamTime() + "%'");
				
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList<TimetableBean> list = new ArrayList<TimetableBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimetableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getInt(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getInt(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setDescription(rs.getString(7));
				bean.setExamDate(rs.getDate(8));
				bean.setExamTime(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Timetable");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		System.out.println("TimeTableModel search End");
		return list;
	}

	/**
	 * Get List of Timetable
	 * 
	 * @return list : List of Timetable
	 * @throws ApplicationException
	 */

	public List<TimetableBean> list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of Timetable with pagination
	 * 
	 * @return list : List of Timetable
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */

	public List<TimetableBean> list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		System.out.println("TimeTableModel list Started");
		ArrayList<TimetableBean> list = new ArrayList<TimetableBean>();
		StringBuffer sql = new StringBuffer("select * from ST_TIMETABLE ORDER BY COURSE_NAME");
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
				TimetableBean bean = new TimetableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getInt(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getInt(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setDescription(rs.getString(7));
				bean.setExamDate(rs.getDate(8));
				bean.setExamTime(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Timetable");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		System.out.println("TimeTableModel list End");
		return list;

	}

}
