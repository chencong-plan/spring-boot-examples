package cc.ccoder.springbootexcel.util;

import org.apache.commons.lang3.StringUtils;

/**
 * <B>系统名称: </B>收付款系统<BR>
 * <B>模块名称: </B><BR>
 * <B>中文类名: </B><BR>
 * <B>概要说明: </B><BR>
 *
 * @author chencong@jytpay.com
 * @since 2019/1/7 15:05
 */
public class DataUtil {

    /**
     * 将原数据脱敏处理
     *
     * @param source 原数据
     * @return 脱敏之后数据
     */
    public static String getEncData(String source) {
        //为空或者1位的，不屏蔽
        if (StringUtils.isBlank(source) || source.length() == 1) {
            return "";
        }
        //两位的，屏蔽一位
        if (source.length() == 2) {
            return source.substring(0, 1) + "*";
        }
        //三到五位，显示一头一尾
        if (source.length() < 6) {
            return source.substring(0, 1) + getStars(source.length() - 2) + source.substring(source.length() - 1);
        }
        //六到十二位，三头四尾
        if (source.length() < 13) {
            return source.substring(0, 3) + getStars(source.length() - 7) + source.substring(source.length() - 4);
        }
        //十三位以上，四头三尾
        return source.substring(0, 4) + getStars(source.length() - 7) + source.substring(source.length() - 3);
    }

    /**
     * 给出指定长度的*号
     *
     * @param count 长度
     * @return string
     */
    private static String getStars(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("*");
        }
        return sb.toString();
    }
}
