package cn.smilex;

import java.util.Arrays;

/**
 * @author smilex
 */
public final class WrapperUtil {
    public static String replace(String str, int index, String param) {
        if (index == 0) {
            return param + subString(str, index + 2);
        }
        return subString(str, 0, index) + param + subString(str, index + 2);
    }

    public static String subString(String str, int beginIndex) {
        char[] value = str.toCharArray();
        int subLen = value.length - beginIndex;
        return (beginIndex == 0) ? str : ReflectionUtil.setStringValue("", Arrays.copyOfRange(value, beginIndex, beginIndex + subLen));
    }

    public static String subString(String str, int beginIndex, int endIndex) {
        char[] value = str.toCharArray();
        int subLen = endIndex - beginIndex;
        return ((beginIndex == 0) && (endIndex == value.length)) ? str
                : ReflectionUtil.setStringValue("", Arrays.copyOfRange(value, beginIndex, beginIndex + subLen));
    }
}
