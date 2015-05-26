package weixin.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import DB.mo.ActivityMO;
import DB.mo.MenuMO;

import java.net.URL;
import  java.net.HttpURLConnection;
import java.util.ArrayList;

import javax.servlet.ServletException;

import servlet.handler.Handler;


public class MenuHandler extends Handler{
	private static Logger logger = Logger.getLogger(MenuHandler.class);

	@Override
	public void execute() throws Exception {
		String methodName =(String)this.request.getParameter("method");
		logger.debug("-->MenuHandler[methodName:"+methodName+"]");
		if(methodName!=null){
			Method method =null;
			method = this.getClass().getDeclaredMethod(methodName);
			method.invoke(this);
		}
	}
	private void queryAllMenu() throws Exception{
		ArrayList<MenuMO> moList=new ArrayList<MenuMO>();
		this.request.setAttribute("resultList", moList);
		this.request.getRequestDispatcher("/web/menu/menu_show.jsp").forward(this.request, this.response);
	}
	/**
	 * ��΢��ƽ̨������ȥ������
	 * @throws IOException
	 */
	private void createMenu() throws IOException{
		String menu = "{\"button\":[{\"type\":\"click\",\"name\":\"MENU01\",\"key\":\"1\"},{\"type\":\"view\",\"name\":\"�\",\"url\":\"http://kunze.sinaapp.com/1/kunze/HandlerManager?handler=activityHandler&method=activity_show&id=5\"}]}";
        logger.debug("�˵���"+menu);
		String respMsg="����ʧ��";
        //�˴���Ϊ�Լ���Ҫ�Ľṹ�壬�滻����
		RequestToWeixinUtils utilToWeixin=new RequestToWeixinUtils();
        String access_token= utilToWeixin.getToken(this.request);
        String action = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
        OutputStream os=null;
        InputStream is = null;
        try {
           URL url = new URL(action);
           HttpURLConnection http =   (HttpURLConnection) url.openConnection();    
             
           http.setRequestMethod("POST");        
           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
           http.setDoOutput(true);        
           http.setDoInput(true);
           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//���ӳ�ʱ30��
           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //��ȡ��ʱ30��
 
           http.connect();
           os= http.getOutputStream();    
           os.write(menu.getBytes("UTF-8"));//�������    
           os.flush();
         
           is =http.getInputStream();
           int size =is.available();
           byte[] jsonBytes =new byte[size];
           is.read(jsonBytes);
           String message=new String(jsonBytes,"UTF-8");
           
           respMsg = "msg from weixin:"+message;
       } catch (Exception e) {
           e.printStackTrace();
       }finally{
    	   os.close();
    	   is.close();
       }
       
       PrintWriter pw=this.response.getWriter();
       pw.write(respMsg);
       pw.close();
       pw = null;
	}
	
}
