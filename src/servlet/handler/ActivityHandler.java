package servlet.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import DB.DBAccess;
import DB.mo.ActivityMO;

import common.FuncString;

public class ActivityHandler extends Handler{
	private static Logger logger = Logger.getLogger(ActivityHandler.class);
	@Override
	public void execute() throws Exception {
		logger.debug("-->ActivityHandler");
		String methodName =(String)this.request.getParameter("method");
		if(FuncString.isEmpty(methodName)){
			methodName =(String)this.request.getParameter("methodName");
		}
		logger.debug("methodName:"+methodName);
		if(methodName!=null){
			Method method =null;
			method = this.getClass().getDeclaredMethod(methodName);
			method.invoke(this);
		}
	}
	/*
	 * request form weixin in phone
	 * url: http://kunze.sinaapp.com/1/kunze/HandlerManager?handler=activityHandler&method=activity_show&id=5
	 */
	private void activity_show() throws Exception{
		logger.debug("-->ActivityHandler.activity_show");
		ActivityMO activityMo=new ActivityMO();
		String id = this.request.getParameter("id");
		String sql="select "+activityMo.ID+","+activityMo.TITLE+","+activityMo.CONTRNT+" from activity where id ="+id;
		
		Connection conn =null;
		Statement st = null;
		ResultSet rs=null;
		DBAccess dbAccess =new DBAccess();
		try{
			conn = dbAccess.getConn();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				activityMo.setId(rs.getString(activityMo.ID));
				activityMo.setTitle(rs.getString(activityMo.TITLE));
				activityMo.setContent(rs.getString(activityMo.CONTRNT));
			}
		}catch(Exception e){
			logger.error("活动维护->活动查询失败");
			e.printStackTrace();
		}finally{
			DBAccess.closeRsAndPs(rs, st);
			if(conn!=null){
				conn.close();
			}
			conn=null;
		}
		this.request.setAttribute("activity", activityMo);
		this.request.getRequestDispatcher("/web/activity/activity_show.jsp").forward(this.request, this.response);
	}
	private void edit_activity_save() throws Exception{
		String errorMsg="活动保存修改失败";
//		this.request.setCharacterEncoding("gbk");
		logger.debug("-->ActivityHandler.edit_activity_save - 保存修改的活动");
		String id = this.request.getParameter("id");
		String title = this.request.getParameter("activity_title");
		if(!FuncString.isEmpty(title)){
			title = java.net.URLDecoder.decode(title,"UTF-8");
		}
		logger.debug("title:"+title);
		String contentDesc = this.request.getParameter("contentDesc");
		if(!FuncString.isEmpty(contentDesc)){
			contentDesc = java.net.URLDecoder.decode(contentDesc,"UTF-8");
		}
		logger.debug("contentDesc:"+contentDesc);
		String endtime=this.request.getParameter("endTime");
		if(FuncString.isEmpty(endtime)){
			endtime="2099-12-31 23:59:59";
		}else{
			endtime+=" 23:59:59";
		}
		String sql="update activity set title = ?,contentDesc=?,endTime = ? where id = "+ id;
		Connection conn =null;
		PreparedStatement ps = null;
		DBAccess dbAccess =new DBAccess();
		try{
			errorMsg="数据库链接异常";
			conn = dbAccess.getConn();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, contentDesc);
			ps.setString(3, endtime);
			errorMsg="数据库入库失败";
			ps.executeUpdate();
			conn.commit();
			errorMsg="1";
		}catch(Exception e){
			conn.rollback();
			e.printStackTrace();
			logger.debug("活动修改时发生异常");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			DBAccess.closeRsAndPs(null, ps);
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn=null;
		}
		this.response.setCharacterEncoding("gbk");
		PrintWriter pw=this.response.getWriter();
		pw.write(errorMsg);
		pw.close();
	}
	private void edit_activity_init() throws Exception{
		logger.debug("-->ActivityHandler.edit_activity");
		String id = this.request.getParameter("id");
		String sql="select * from activity where id ="+id;
		
		Connection conn =null;
		Statement st = null;
		ResultSet rs=null;
		DBAccess dbAccess =new DBAccess();
		ActivityMO activityMo=null;
		try{
			conn = dbAccess.getConn();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				activityMo=new ActivityMO();
				activityMo.setId(rs.getString(activityMo.ID));
				activityMo.setTitle(rs.getString(activityMo.TITLE));
				activityMo.setContent(rs.getString(activityMo.CONTRNT));
			}
		}catch(Exception e){
			logger.error("活动维护->活动查询失败");
			e.printStackTrace();
		}finally{
			DBAccess.closeRsAndPs(rs, st);
			if(conn!=null){
				conn.close();
			}
			conn=null;
		}
		this.request.setAttribute("activity", activityMo);
		this.request.getRequestDispatcher("/WEB-INF/web/activity/activity_editor.jsp").forward(this.request, this.response);
	}
	private void del_activity() throws Exception{
		logger.debug("-->ActivityHandler.del_activity");
		String errorMsg="活动删除失败";
		String id=this.request.getParameter("id");
		String sql="delete from activity where id="+id;
		
		Connection conn =null;
		Statement st = null;
		DBAccess dbAccess =new DBAccess();
		try{
			conn = dbAccess.getConn();
			conn.setAutoCommit(false);
			st = conn.createStatement();
			st.executeUpdate(sql);
			conn.commit();
		}catch(Exception e){
			conn.rollback();
			logger.error("活动维护->删除活动失败");
			e.printStackTrace();
		}finally{
			DBAccess.closeRsAndPs(null, st);
			if(conn!=null){
				conn.close();
			}
			conn=null;
		}
		errorMsg="1";
		this.response.setCharacterEncoding("gbk");
		PrintWriter pw=this.response.getWriter();
		pw.write(errorMsg);
		pw.close();
	}
	private void activity_search() throws Exception{
		ArrayList<ActivityMO> activity_rs_list=new ArrayList<ActivityMO>();
		String title=this.request.getParameter("title");
		String endTime=this.request.getParameter("endTime");
		String sql="select * from activity where 1=1 ";
		if(!FuncString.isEmpty(title)){
			sql+= " and title like '%"+title+"%'";
		}
		if(!FuncString.isEmpty(endTime)){
			sql+= " and unix_timestamp(endTime) < unix_timestamp('"+endTime+"')";
		}
		
		Connection conn =null;
		Statement st = null;
		ResultSet rs=null;
		DBAccess dbAccess =new DBAccess();
		ActivityMO activityMo=null;
		try{
			conn = dbAccess.getConn();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				activityMo=new ActivityMO();
				activityMo.setId(rs.getString(activityMo.ID));
				activityMo.setTitle(rs.getString(activityMo.TITLE));
				activityMo.setContent(rs.getString(activityMo.CONTRNT));
				if(!FuncString.isEmpty(rs.getDate(activityMo.CREATETIME))){
					activityMo.setCreateTime(rs.getDate(activityMo.CREATETIME).toString());
				}
				if(!FuncString.isEmpty(rs.getDate(activityMo.ENDTIME))){
					activityMo.setEndTime(rs.getString(activityMo.ENDTIME));
				}
				activity_rs_list.add(activityMo);
			}
		}catch(Exception e){
			logger.error("活动维护->活动查询失败");
			e.printStackTrace();
		}finally{
			DBAccess.closeRsAndPs(rs, st);
			if(conn!=null){
				conn.close();
			}
			conn=null;
		}
		this.request.setAttribute("resultList",activity_rs_list);
		this.request.getRequestDispatcher("/web/activity/activity_searchresult.jsp").forward(this.request, this.response);
	}
	private void activity_maintain() throws ServletException, IOException{
		logger.debug("-->ActivityHandler - 活动维护");
//		this.request.getRequestDispatcher("/WEB-INF/web/activity/activity_maintain.jsp").forward(this.request, this.response);
		this.request.getRequestDispatcher("/web/activity/activity_search.jsp").forward(this.request, this.response);
	}
	private void activity_add() throws Exception{
		logger.debug("-->ActivityHandler - 新增页面初始化");
		this.request.getRequestDispatcher("/WEB-INF/web/activity/activity_add.jsp").forward(this.request, this.response);
	}
	private void activity_add_save() throws Exception{
		String errorMsg="活动保存失败";
		logger.debug("-->ActivityHandler - 保存新增的活动");
		String title = this.request.getParameter("activity_title");
		logger.debug("title:"+title);
		String contentDesc = this.request.getParameter("contentDesc");
		String end_time = this.request.getParameter("end_time");
		if(FuncString.isEmpty(end_time)){
			end_time="2099-12-31 23:59:59";
		}else{
			end_time+=" 23:59:59";
		}
		String sql="insert into activity(title,contentDesc,createTime,endTime) values(?,?,?,?)";
		Connection conn =null;
		PreparedStatement ps = null;
		DBAccess dbAccess =new DBAccess();
		try{
			errorMsg="数据库链接异常";
			conn = dbAccess.getConn();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, contentDesc);
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setString(4, end_time);
			errorMsg="数据库入库失败";
			ps.executeUpdate();
			conn.commit();
			errorMsg="新增活动成功";
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("活动新增时发生异常");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			DBAccess.closeRsAndPs(null, ps);
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn=null;
		}
		this.response.setCharacterEncoding("gbk");
		PrintWriter pw=this.response.getWriter();
		pw.write(errorMsg);
		pw.close();
		logger.debug("LoginHandler-->");
	}
}
