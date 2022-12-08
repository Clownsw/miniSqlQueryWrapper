package cn.smilex;

/**
 * @author smilex
 */
public interface Wrapper {
    Wrapper eq(String column, Object value);
    Wrapper orderByAsc(String column);
    Wrapper orderByDesc(String column);
    Wrapper limit(Long offset);
    Wrapper limit(Long current, Long offset);
    String buildSql();
}
