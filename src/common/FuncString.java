package common;


/**
 *
 * <p>Title: FuncString</p>
 * <p>Description: 有关 String 类型的函数</p>
 * <p>Company: </p>
 * @author 蔡哲懿
 * @version V 0.0.1
 */

public class FuncString {
	public static boolean isEmpty(Object tarObj){
		if(tarObj != null && tarObj.toString().length()>0){
			return false;
		}
		return true;
	}
    /**
     * 将对象转换成 String 。
     * @param obj :
     * @return String : 如果对象为NULL,则返回NULL.
     * @see <br>author 蔡哲懿
     */
    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    /**
     * 将对象转换成能友好显示在页面的 String ;
     * @param obj :
     * @return String :如果是NULL或""，则返回"&nbsp;"；
     * @see <br>author 蔡哲懿
     */
    public static String toHTMLString(Object obj) {
        if (obj == null) {
            return "&nbsp;";
        }
        if (obj.toString().equals("")) {
            return "&nbsp;";
        }
        return obj.toString();
    }


    
}
