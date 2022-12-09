package cn.smilex;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

/**
 * @author smilex
 */
public final class ReflectionUtil {

    @SneakyThrows
    public static String setStringValue(String str, char[] value) {
        Field valueField = str.getClass()
                .getDeclaredField("value");
        valueField.setAccessible(true);
        valueField.set(str, value);
        return str;
    }
}
