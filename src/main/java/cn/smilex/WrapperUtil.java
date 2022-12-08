package cn.smilex;

/**
 * @author smilex
 */
public final class WrapperUtil {
    public static String replace(String str, int index, int len, String value) {
        StringBuilder sb = new StringBuilder(str);
        sb.replace(index, index + len, value);
        return sb.toString();
    }
}
