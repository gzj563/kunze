package weixin;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import weixin.msg.Article;
import weixin.msg.BaseMessage_req;
import weixin.msg.MessageUtil;
import weixin.msg.NewMessage_resp;
import weixin.msg.TextMessage_resp;

public class BusinessLogicControl {
	private static Logger logger = Logger.getLogger(BusinessLogicControl.class);
	/**
	 * ����΢�ŷ���������
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// Ĭ�Ϸ��ص��ı���Ϣ����
			String respContent = "�������쳣�����Ժ��ԣ�";
			// xml�������
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// ���ͷ��ʺţ�open_id��
			String fromUserName = requestMap.get("FromUserName");
			// �����ʺ�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");
			
			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String textContent = requestMap.get(BaseMessage_req.CONTENT);
				
				if(textContent.indexOf("����")!=-1){
					respMessage = new BusinessLogicControl().newsMessageDemo(fromUserName, toUserName);
				}
				else{
					respContent = "�����͵����ı���Ϣ��\n";
					respContent += "�����ǣ�" + textContent;
					respContent +="�����Գ��Իظ������¡��͡���ҳ����";
					respMessage = new BusinessLogicControl().textMessageDemo(fromUserName, toUserName, respContent);
				}
			}
			// ͼƬ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "�����͵���ͼƬ��Ϣ��";
			}
			// ����λ����Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "�����͵��ǵ���λ����Ϣ��";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "�����͵���������Ϣ��";
			}
			// ��Ƶ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "�����͵�����Ƶ��Ϣ��";
			}
			// �¼�����
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ����
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "лл���Ĺ�ע�����ǽ�Ŭ�����������ܶ�Ĺ��ܡ����ر�����֧�֣�";
				}
				// ȡ������
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ
				}
				// �Զ���˵�����¼�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO �Զ���˵�Ȩû�п��ţ��ݲ����������Ϣ
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("BusinessLogicControl.processRequest-->");
		return respMessage;
	}
	private String textMessageDemo(String fromUserName,String toUserName,String respContent){
		logger.debug("��֯�ظ����ı���Ϣ");
		TextMessage_resp textMessage = new TextMessage_resp();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent(respContent);
		return textMessage.toString();
		//respMessage = MessageUtil.textMessageToXml(textMessage);
	}
	private String newsMessageDemo(String fromUserName,String toUserName){
		logger.debug("��֯�ظ���ͼ����Ϣ");
		Article article = new Article();
		article.setTitle("titleDemoinJava");
		article.setDescription("descForThisArticleInJava");
		article.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/RnQg7zcicb69QAnVkuiaKLQYo4iawphlKzAuy3APEt5wyYUWBGH6sPiblsTA9tZmcSIlMaTJoO8qQI6OtYCRSg8zvg/0");
		article.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5MDA3MDQ4OQ==&mid=201156858&idx=1&sn=dcaffb36ebc1818b40aae44467a82769#rd");
		NewMessage_resp newsMessage = new NewMessage_resp();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setArticleCount(1);//just a demo
		newsMessage.addArticle(article);
		return newsMessage.toString();
	}
	public boolean checkURLForValid(HttpServletRequest request) throws IOException{
		// ΢�ż���ǩ��
		String signature = request.getParameter("signature");
		// ʱ���
		String timestamp = request.getParameter("timestamp");
		// �����
		String nonce = request.getParameter("nonce");
		// ����ַ���
		String echostr = request.getParameter("echostr");
		return true;
		
	}
}
