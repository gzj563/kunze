package weixin.msg;

/** 
 * text message
 *  
 * @author zhongjian 
 * @date 2014-11-22 
 */ 
public class TextMessage_req extends BaseMessage_req { 
    // ÏûÏ¢ÄÚÈİ 
    private String Content; 
 
    public String getContent() { 
        return Content; 
    } 
 
    public void setContent(String content) { 
        Content = content; 
    } 
} 