package cn.smilex;

import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author smilex
 */
@SuppressWarnings({"unused", "Duplicates"})
public class QueryWrapper extends AbstractWrapper {
    public QueryWrapper() {
        super(null, new LinkedList<>());
    }

    public QueryWrapper(String baseSql) {
        super(baseSql, new LinkedList<>());
    }

    @Override
    public QueryWrapper eq(String column, Object value) {
        super.eq(column, value);
        return this;
    }

    @Override
    public QueryWrapper orderByAsc(String column) {
        super.orderByAsc(column);
        return this;
    }

    @Override
    public QueryWrapper orderByDesc(String column) {
        super.orderByDesc(column);
        return this;
    }

    @Override
    public QueryWrapper limit(Long offset) {
        super.limit(offset);
        return this;
    }

    @Override
    public QueryWrapper limit(Long current, Long offset) {
        super.limit(current, offset);
        return this;
    }

    public QueryWrapper filter(Supplier<Boolean> filter, Consumer<QueryWrapper> handler) {
        if (filter.get()) {
            handler.accept(this);
        }

        return this;
    }

    @SuppressWarnings("all")
    @Override
    public String buildSql() {
        return super.buildSql();
    }
}
