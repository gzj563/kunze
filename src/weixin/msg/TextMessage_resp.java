package weixin.msg;

import java.util.Date;

/** 
 * �ı���Ϣ 
 * @author zhongjian 
 * @date 2014-11-22
 */ 
public class TextMessage_resp extends BaseMessage_resp { 
    // �ظ�����Ϣ���� 
    private String Content; 
 
    public String getContent() { 
        return Content; 
    } 
 
    public void setContent(String content) { 
        Content = content; 
    } 
    public String toString(){
    	StringBuffer respMsg = new StringBuffer("");
    	respMsg.append("<xml>")
    		.append("<ToUserName><![CDATA[").append(this.getToUserName()).append("]]></ToUserName>")
    		.append("<FromUserName><![CDATA[").append(this.getFromUserName()).append("]]></FromUserName>")
    		.append("<CreateTime>").append(new Date().getTime()).append("</CreateTime>")
    		.append("<MsgType><![CDATA[").append(this.getMsgType()).append("]]></MsgType>")
    		.append("<Content><![CDATA[").append(this.getContent()).append("]]></Content>")
    		.append("</xml>");
    	return respMsg.toString();
    }
} 