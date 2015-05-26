package DB.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DBAccess;

public class TokenDAO {
	public void updateToken(String token_value){
		Connection conn =null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		DBAccess dbAccess =new DBAccess();
		String sql = "update token set token_value=?, begin_time = now() where id=1";
		try
		{
			conn = dbAccess.getConn();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, token_value);
			ps.executeUpdate();
			conn.commit();
		}
		catch (Exception ex)
		{
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		}
		finally
		{
			DBAccess.closeRsAndPs(rs, ps);
		}
	}
}
