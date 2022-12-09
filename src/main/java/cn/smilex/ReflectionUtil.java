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

    public static Field searchField(Class<?> clazz, String fieldName) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        return null;
    }
}
