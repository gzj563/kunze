package servlet.handler;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

public class TestHandler extends Handler{
	private static Logger logger = Logger.getLogger(TestHandler.class);
	@Override
	public void execute() throws Exception {
		logger.debug("-->TestHandler");
		String methodName =(String)this.request.getParameter("method");
		if(methodName!=null){
			if("gotoBasicOpe_jquery".equals(methodName)){
				Method method = this.getClass().getDeclaredMethod("gotoWebInfJsp");
				method.invoke(this);
			}
			
		}
	}
	private void gotoWebInfJsp() throws ServletException, IOException{
		logger.debug("-->TestHandler.gotoWebInfJsp");
		this.request.getRequestDispatcher("/WEB-INF/jsp/basicOpe_jquery.jsp").forward(this.request, this.response);
	}

}
