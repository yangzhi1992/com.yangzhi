package crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

/**
 * Created by gavinding on 2017/5/29.
 */
public class IpUtils {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(IpUtils.class);

    /**
     * 判断字符串是否是Ip
     * 使用正则表达式
     * @param addr
     * @return
     */
    public static boolean isIP(String addr) {
        if (addr.length() < 7 || addr.length() > 15 || StringUtils.isBlank(addr)) {
            return false;
        }
        /**
         * 判断IP格式和范围
         */
        String rexp = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";

        Pattern pat = Pattern.compile(rexp);

        Matcher mat = pat.matcher(addr);

        boolean ipAddress = mat.find();

        return ipAddress;
    }

    public static List<String> isIpPort(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        List<String> result = new ArrayList<String>();
        //        String ipAndPort="((1?\\d{1,2}|2[1-5][1-5])\\.){3}(1?\\d{1,2}|2[1-5][1-5]):([1-5]?\\d{1,4}|6[1-5][1-5][1-3][1-5])";
        String rexp = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}:[0-9]{2,5}";
        Pattern r = Pattern.compile(rexp);
        String line = text;
        Matcher m = r.matcher(line);
        while (m.find()) {
            // group(0)或group()将会返回整个匹配的字符串（完全匹配）；group(i)则会返回与分组i匹配的字符
            // 这个例子只有一个分组
            //            logger.debug("ipport" + " matches " + m.group(0));
            if(m.group(0).contains("127.0.0.1")){
                continue;
            }
            result.add(m.group(0));
        }
        return result;

    }
}
