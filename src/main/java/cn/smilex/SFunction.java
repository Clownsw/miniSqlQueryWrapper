package cn.smilex;

import java.io.Serializable;
import java.util.function.Function;

/**
 * @author YangLuJia
 * @date 2022/12/9/14:40
 * @since 1.0
 */
@FunctionalInterface
public interface SFunction<T, R> extends Function<T, R>, Serializable {
}
