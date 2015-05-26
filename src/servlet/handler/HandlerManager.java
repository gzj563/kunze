package servlet.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import weixin.BusinessLogicControl;
import common.SysConfiguration;

public class HandlerManager  extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(HandlerManager.class);
	
	private static final long serialVersionUID = 1L;
	public static Properties p= new Properties();
	public void init(ServletConfig aConfig) throws ServletException{
		InputStream in;
		try {
			in = this.getClass().getResourceAsStream("handler.properties");
			p.load(in);
			logger.debug("Nb of total handers is = "+p.size());
			SysConfiguration sys_global = new SysConfiguration();
			sys_global.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws
    	ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String handler_code = req.getParameter("handler");
        String className = p.getProperty(handler_code);
        logger.debug("request method:"+req.getMethod());//"GET" "POST"
        Handler handler;
        try {
        	handler =(Handler) Class.forName(className).newInstance();
        	handler.initParameter(req, res);
        	handler.execute();
		} catch (Exception e) {
			logger.error("(error)handlerManager");
			logger.error(e.toString());
			e.printStackTrace();
		}finally{
			handler =null;
		}
        logger.debug("action over");
	}
}
