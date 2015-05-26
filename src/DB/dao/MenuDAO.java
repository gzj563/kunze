package DB.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import DB.DBAccess;
import DB.mo.MenuMO;

public class MenuDAO {
	public List<MenuMO> getAllMenu(){
		ArrayList<MenuMO> resultList=new ArrayList<MenuMO>();
		MenuMO mo=null;
		Connection conn =null;
		ResultSet rs = null;
		Statement st = null;
		DBAccess dbAccess =new DBAccess();
		String sql = "select * from menu";
		try
		{
			conn = dbAccess.getConn();
			st = conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				mo=new MenuMO();
				mo.setId(rs.getString("id"));
				mo.setIndex(rs.getString("index"));
				mo.setKeyword(rs.getString("keyword"));
				mo.setLevel(rs.getString("level"));
				mo.setName(rs.getString("name"));
				mo.setUrl(rs.getString("url"));
				resultList.add(mo);
			}
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
			DBAccess.closeRsAndPs(rs, st);
		}
		return resultList;
	}
}
