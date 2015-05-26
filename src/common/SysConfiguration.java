package common;
import java.util.*;

public class SysConfiguration {

    private static Hashtable<String,String> config_hash = null;

    public void init() {
        setConfigParam();
    }


    private void setConfigParam() {
        //==获得配置文件
        ResourceBundle rb_config = ResourceBundle.getBundle("config");
        Enumeration enu = rb_config.getKeys();

        config_hash = new Hashtable<String,String>();
        String key_name;

        if (enu != null) {
            while (enu.hasMoreElements()) {
                key_name = String.valueOf(enu.nextElement());
                config_hash.put(key_name, rb_config.getString(key_name));
            }
        }   
    }

	public static String getConfigParam(String param_name) {
        return FuncString.toString(config_hash.get(param_name));
    }
	
}