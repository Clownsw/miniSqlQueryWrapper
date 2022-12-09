package cn.smilex;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author smilex
 */
@SuppressWarnings("unused")
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WrapperField {
    String value();
}
