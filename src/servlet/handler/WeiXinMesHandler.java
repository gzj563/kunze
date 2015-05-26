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
		
		// ���ú���ҵ���������Ϣ��������Ϣ  
		String respMessage = BusinessLogicControl.processRequest(this.request);
		
		// ��Ӧ��Ϣ
		PrintWriter out = response.getWriter();
		out.print(respMessage);
		out.close();
		logger.debug("WeiXinMesHandler.doBusiness-->");
	}
	private void doCheck() throws IOException{
		// ����ַ���
		String echostr = request.getParameter("echostr");
		
		BusinessLogicControl blcControl = new BusinessLogicControl();
		// ͨ������signature���������У�飬��У��ɹ���ԭ������echostr����ʾ����ɹ����������ʧ��
		boolean validFlag = blcControl.checkURLForValid(this.request);
		if(validFlag){
			PrintWriter out = response.getWriter();
			out.print(echostr);
			out.close();
			out = null;
		}
		
	}

}
