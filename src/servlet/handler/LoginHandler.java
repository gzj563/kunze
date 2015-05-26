package servlet.handler;

import java.sql.Connection;

import org.apache.log4j.Logger;

import DB.DBAccess;
import DB.dao.ManagerUserDAO;
import DB.mo.ManagerUserMO;

public class LoginHandler extends Handler{
	
	private static Logger logger = Logger.getLogger(LoginHandler.class);
	@Override
	public void execute() throws Exception {
		logger.debug("-->LoginHandler");
		String name = this.request.getParameter("userName");
		String password = this.request.getParameter("userPwd");
		ManagerUserMO user =null;
		Connection conn =null;
		DBAccess dbAccess =new DBAccess();
		try{
			conn = dbAccess.getConn();
			ManagerUserDAO userDao=new ManagerUserDAO();
			user = userDao.getUserByNameAndPwd(conn, name, password);
			this.request.getSession().setAttribute("user", user);
		}catch(Exception e){
			logger.debug("-->µÇÂ½Âß¼­·¢ÉúÒì³£");
		}finally{
			conn.close();
			conn=null;
		}
		if(user!=null){
			this.request.getRequestDispatcher("/WEB-INF/web/index.jsp").forward(this.request, this.response);
		}else{
			this.request.setAttribute("errorMsg", "µÇÂ½Ê§°Ü£¬ÇëºË¶ÔÓÃ»§ÃûºÍÃÜÂë");
			this.request.getRequestDispatcher("/login.jsp").forward(this.request, this.response);
		}
//		String jsonStr="{'msg':'msgText','error':'errorText'}";
//		PrintWriter pw=this.response.getWriter();
//		pw.write(jsonStr);
//		logger.debug("LoginHandler-->");
	}
}
