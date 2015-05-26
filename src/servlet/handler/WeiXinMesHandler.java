package servlet.handler;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

import weixin.BusinessLogicControl;

public class WeiXinMesHandler extends Handler{
	private static Logger logger = Logger.getLogger(WeiXinMesHandler.class);
	@Override
	public void execute() throws Exception {
		if("GET".equalsIgnoreCase(this.request.getMethod())){
			doCheck();
		}else{
			doBusiness();
		}
	}
	private void doBusiness()  throws Exception {
		logger.debug("-->WeiXinMesHandler.doBusiness");
//		this.request.setCharacterEncoding("UTF-8");
		this.response.setCharacterEncoding("UTF-8");
		
		// 调用核心业务类接收消息、处理消息  
		String respMessage = BusinessLogicControl.processRequest(this.request);
		
		// 响应消息
		PrintWriter out = response.getWriter();
		out.print(respMessage);
		out.close();
		logger.debug("WeiXinMesHandler.doBusiness-->");
	}
	private void doCheck() throws IOException{
		// 随机字符串
		String echostr = request.getParameter("echostr");
		
		BusinessLogicControl blcControl = new BusinessLogicControl();
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		boolean validFlag = blcControl.checkURLForValid(this.request);
		if(validFlag){
			PrintWriter out = response.getWriter();
			out.print(echostr);
			out.close();
			out = null;
		}
		
	}

}
