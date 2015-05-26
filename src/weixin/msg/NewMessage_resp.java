package weixin.msg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class NewMessage_resp extends BaseMessage_resp {
	// 图文消息个数，限制为10条以内 
    private int ArticleCount; 
    // 多条图文消息信息，默认第一个item为大图 
    private List<Article> Articles = new ArrayList<Article>(); 
 
    public int getArticleCount() { 
        return ArticleCount; 
    } 
 
    public void setArticleCount(int articleCount) { 
        ArticleCount = articleCount; 
    } 
    public void addArticle(Article article){
    	Articles.add(article);
    }
    
    public String toString(){
    	StringBuffer respMsg = new StringBuffer("");
    	respMsg.append("<xml>")
    		.append("<ToUserName><![CDATA[").append(this.getToUserName()).append("]]></ToUserName>")
    		.append("<FromUserName><![CDATA[").append(this.getFromUserName()).append("]]></FromUserName>")
    		.append("<CreateTime>").append(new Date().getTime()).append("</CreateTime>")
    		.append("<MsgType><![CDATA[").append(this.getMsgType()).append("]]></MsgType>")
    		.append("<ArticleCount>").append(this.getArticleCount()).append("</ArticleCount>");
    	
    	respMsg.append("<Articles>");
    	for(Article article : Articles){
    		respMsg.append("<item>")
    			.append("<Title><![CDATA[").append(article.getTitle()).append("]]></Title>")
    			.append("<Description><![CDATA[").append(article.getDescription()).append("]]></Description>")
    			.append("<PicUrl><![CDATA[").append(article.getPicUrl()).append("]]></PicUrl>")
    			.append("<Url><![CDATA[").append(article.getUrl()).append("]]></Url>")
    			.append("</item>");
    	}
    	respMsg.append("</Articles>")
    		.append("</xml>");
    	return respMsg.toString();
    }
}
