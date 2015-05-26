package DB.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DB.DBAccess;
import DB.mo.ManagerUserMO;

public class ManagerUserDAO {
	public ManagerUserMO getUserByNameAndPwd(Connection conn,String name,String pwd) throws Exception{
		ManagerUserMO user=null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "SELECT * FROM web_manager_user where name = ? and password = ?";
		try
		{
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pwd);
			rs = ps.executeQuery();
			if(rs.next()){
				user = new ManagerUserMO();
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			conn.rollback();
			throw ex;
		}
		finally
		{
			DBAccess.closeRsAndPs(rs, ps);
		}
		return user;
	}
}
