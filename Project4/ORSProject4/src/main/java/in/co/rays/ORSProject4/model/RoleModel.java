package in.co.rays.ORSProject4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.ORSProject4.bean.RoleBean;
import in.co.rays.ORSProject4.exception.ApplicationException;
import in.co.rays.ORSProject4.exception.DatabaseException;
import in.co.rays.ORSProject4.exception.DuplicateRecordException;
import in.co.rays.ORSProject4.util.JDBCDataSource;

/**
 * @author Madhuri
 * @version 1.0
 *
 */
public class RoleModel {
	Logger log = Logger.getLogger(RoleModel.class);
	public Integer nextPk() throws DatabaseException{
		log.debug("model next pk started");
		System.out.println("Rolemodel next pk started");
		Connection conn=null;
		int pk=0;
		try{
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement("select max(id) from st_role");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				pk=rs.getInt(1);
			}
			rs.close();
		}catch(Exception e){
			log.error("Database Exception",e);
			throw new DatabaseException("Exception: Exception in getting pk");
		}finally{
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model next pk started");
		System.out.println("Rolemodel next pk started");
		return pk+1;
		}
	public long add(RoleBean bean) throws ApplicationException, SQLException, DuplicateRecordException {
		log.debug("Model add Begin");
		System.out.println("Rolemodel add end");
		Connection conn = null;

		RoleBean duplicateBean = findByName(bean.getName());
		// Check if create Role already exist
		if (duplicateBean.getName()!=null) {
			throw new DuplicateRecordException("already exists");
		}
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_role values(?,?,?,?,?,?,?)");

			pk = nextPk();
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			System.out.println("record inserted");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception" + e);
			conn.rollback();
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in add Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		System.out.println("Rolemodel add end");
		return pk;
	}

	 public RoleBean findByName(String name) throws ApplicationException {
			log.debug("Model findByName Started");
			System.out.println("Rolemodel findByName started");
			RoleBean bean = new RoleBean();

			StringBuffer sql = new StringBuffer("select * from st_role where name=?");
			Connection conn = null;
			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, name);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					int pk = 0;
					pk = nextPk();
					// can we take pk
					bean.setId(rs.getLong(1));
					bean.setName(rs.getString(2));
					bean.setDescription(rs.getString(3));
					bean.setCreatedBy(rs.getString(4));
					bean.setModifiedBy(rs.getString(5));
					bean.setCreatedDatetime(rs.getTimestamp(6));
					bean.setModifiedDatetime(rs.getTimestamp(7));

				}
				rs.close();
				pstmt.close();
			} catch (Exception e) {
				log.error("Database Exception..", e);
				e.printStackTrace();
				throw new ApplicationException("Exception : Exception in getting User by findByName");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
			log.debug("Model findByName Started");
			System.out.println("Rolemodel findByName end");
			return bean;
		}

	 public RoleBean findByPk(long pk) throws ApplicationException {
			log.error("Model findByPK Started");
			System.out.println("Rolemodel findByPK Started");
			StringBuffer sql = new StringBuffer("select * from st_role where id=?");
			

			Connection conn = null;
			RoleBean bean = new RoleBean();
			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				pstmt.setLong(1, pk);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					bean.setId(rs.getLong(1));
					bean.setName(rs.getString(2));
					bean.setDescription(rs.getString(3));
					bean.setCreatedBy(rs.getString(4));
					bean.setModifiedBy(rs.getString(5));
					bean.setCreatedDatetime(rs.getTimestamp(6));
					bean.setModifiedDatetime(rs.getTimestamp(7));

				}
				rs.close();
				pstmt.close();
			} catch (Exception e) {
				log.error("Databade exception" + e);
				e.printStackTrace();
				throw new ApplicationException("Exception : Exception in getting User by pk");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
			log.debug("Model findByPK end");
		//	System.out.println(" RoleModel pk : "+pk);
			System.out.println("Rolemodel findByPK end");
			return bean;
		}
	 public void delete(RoleBean bean) throws ApplicationException, SQLException {
			log.debug("Model delete Started");
			System.out.println("Rolemodel delete Started");
			Connection conn = null;
			try {
				conn = JDBCDataSource.getConnection();
				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn.prepareStatement("delete from st_role where id=?");
				pstmt.setLong(1, bean.getId());
				pstmt.executeUpdate();
				conn.commit();
				pstmt.close();
			} catch (Exception e) {
				log.error("Database exception" + e);
				conn.rollback();
				e.printStackTrace();
				throw new ApplicationException("Exception : Exception in delete Role");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
			log.debug("Model delete end");
			System.out.println("Rolemodel delete end");
		}
	 public void update(RoleBean bean) throws ApplicationException,
     DuplicateRecordException {
 log.debug("Model update Started");
 System.out.println("Rolemodel update Started");
 Connection conn = null;

 /*RoleBean duplicataRole = findByName(bean.getName());
 // Check if updated Role already exist
 if (duplicataRole != null && duplicataRole.getId() != bean.getId()) {
     throw new DuplicateRecordException("Role already exists");
 }*/
 try {
     conn = JDBCDataSource.getConnection();

     conn.setAutoCommit(false); // Begin transaction
     PreparedStatement pstmt = conn.prepareStatement("UPDATE ST_ROLE SET NAME=?,DESCRIPTION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
     pstmt.setString(1, bean.getName());
     pstmt.setString(2, bean.getDescription());
     pstmt.setString(3, bean.getCreatedBy());
     pstmt.setString(4, bean.getModifiedBy());
     pstmt.setTimestamp(5, bean.getCreatedDatetime());
     pstmt.setTimestamp(6, bean.getModifiedDatetime());
     pstmt.setLong(7, bean.getId());
     pstmt.executeUpdate();
     conn.commit(); // End transaction
     pstmt.close();
 } catch (Exception e) {
     log.error("Database Exception..", e);
     try {
         conn.rollback();
     } catch (Exception ex) {
         throw new ApplicationException( "Exception : Delete rollback exception "+ ex.getMessage());
     }
     throw new ApplicationException("Exception in updating Role ");
 } finally {
     JDBCDataSource.closeConnection(conn);
 }
 log.debug("Model update End");
 System.out.println("Rolemodel update End");
}
	 public List search(RoleBean bean) throws ApplicationException {
	        return search(bean, 0, 0);
	    }
	 public List search(RoleBean bean, int pageNo, int pageSize)
	            throws ApplicationException {
	        log.debug("Model search Started");
	        System.out.println("Rolemodel search Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM ST_ROLE WHERE 1=1");

	        if (bean != null) {
	            if (bean.getId() > 0) {
	                sql.append(" AND id = " + bean.getId());
	            }
	            if (bean.getName() != null && bean.getName().length() > 0) {
	                sql.append(" AND NAME like '" + bean.getName() + "%'");
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

	        ArrayList list = new ArrayList();
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new RoleBean();
	                bean.setId(rs.getLong(1));
	                bean.setName(rs.getString(2));
	                bean.setDescription(rs.getString(3));
	                bean.setCreatedBy(rs.getString(4));
	                bean.setModifiedBy(rs.getString(5));
	                bean.setCreatedDatetime(rs.getTimestamp(6));
	                bean.setModifiedDatetime(rs.getTimestamp(7));
	                list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException("Exception : Exception in search Role");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }

	        log.debug("Model search End");
	        System.out.println("Rolemodel search End");
	        return list;
	    }
	 public List list() throws ApplicationException {
	        return list(0, 0);
	    }
	 public List list(int pageNo, int pageSize) throws ApplicationException {
			log.debug("Model list begin");
			System.out.println("Rolemodel list begin");
			Connection conn = null;

			StringBuffer sql = new StringBuffer("select * from ST_ROLE");

			if (pageSize > 0) {
				// Calculate start record index
				pageNo = (pageNo - 1) * pageSize;
				sql.append(" limit " + pageNo + "," + pageSize);
			}

			List<RoleBean> list = new ArrayList();

			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					RoleBean bean = new RoleBean();
					bean.setId(rs.getLong(1));
					bean.setName(rs.getString(2));
					bean.setDescription(rs.getString(3));
					bean.setCreatedBy(rs.getString(4));
					bean.setModifiedBy(rs.getString(5));
					bean.setCreatedDatetime(rs.getTimestamp(6));
					bean.setModifiedDatetime(rs.getTimestamp(7));
					list.add(bean);
				}
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				throw new ApplicationException("Exception : Exception in getting list of Role");
			}
			log.debug("Model list End");
			System.out.println("Rolemodel list End");
			return list;
		}
		
}
