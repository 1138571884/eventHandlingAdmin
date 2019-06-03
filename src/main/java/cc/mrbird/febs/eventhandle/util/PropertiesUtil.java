package cc.mrbird.febs.eventhandle.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    private static String  fileName = "/url.properties";

    private static Properties p  = new Properties();

    static {
        InputStream in = null;
        try {
            in = PropertiesUtil.class.getResourceAsStream(fileName);
            p.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String get(String key) {
        String value = null;
        if (p.containsKey(key)) {
            value = p.getProperty(key);
        }
        return value;
    }
}
