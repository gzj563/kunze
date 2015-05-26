package common;


/**
 *
 * <p>Title: FuncString</p>
 * <p>Description: �й� String ���͵ĺ���</p>
 * <p>Company: </p>
 * @author ����ܲ
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
     * ������ת���� String ��
     * @param obj :
     * @return String : �������ΪNULL,�򷵻�NULL.
     * @see <br>author ����ܲ
     */
    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    /**
     * ������ת�������Ѻ���ʾ��ҳ��� String ;
     * @param obj :
     * @return String :�����NULL��""���򷵻�"&nbsp;"��
     * @see <br>author ����ܲ
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
