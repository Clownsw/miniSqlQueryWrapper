package cn.smilex;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author smilex
 */
public interface Wrapper {
    Wrapper eq(String column, Object value);

    Wrapper orderByAsc(String column);

    Wrapper orderByDesc(String column);

    Wrapper limit(Long offset);

    Wrapper limit(Long current, Long offset);

    Wrapper filter(Supplier<Boolean> filter, Consumer<Wrapper> handler);

    String buildSql();
}
