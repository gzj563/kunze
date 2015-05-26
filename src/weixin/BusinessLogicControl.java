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
	 * 处理微信发来的请求
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			
			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String textContent = requestMap.get(BaseMessage_req.CONTENT);
				
				if(textContent.indexOf("文章")!=-1){
					respMessage = new BusinessLogicControl().newsMessageDemo(fromUserName, toUserName);
				}
				else{
					respContent = "您发送的是文本消息！\n";
					respContent += "内容是：" + textContent;
					respContent +="您可以尝试回复‘文章’和‘网页’。";
					respMessage = new BusinessLogicControl().textMessageDemo(fromUserName, toUserName, respContent);
				}
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注！我们将努力开发尽可能多的功能。来回报您的支持！";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 自定义菜单权没有开放，暂不处理该类消息
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("BusinessLogicControl.processRequest-->");
		return respMessage;
	}
	private String textMessageDemo(String fromUserName,String toUserName,String respContent){
		logger.debug("组织回复的文本信息");
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
		logger.debug("组织回复的图文信息");
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
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		return true;
		
	}
}
