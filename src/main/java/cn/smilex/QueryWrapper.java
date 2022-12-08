package cn.smilex;

import org.apache.commons.lang.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author smilex
 */
@SuppressWarnings({"unused", "Duplicates"})
public class QueryWrapper implements Wrapper {
    private final String baseSql;
    private final List<WrapperInstruction> wrapperInstructionList;

    public QueryWrapper() {
        this.baseSql = null;
        this.wrapperInstructionList = new LinkedList<>();
    }

    public QueryWrapper(String baseSql) {
        this.baseSql = baseSql;
        this.wrapperInstructionList = new LinkedList<>();
    }

    @Override
    public Wrapper eq(String column, Object value) {
        this.wrapperInstructionList.add(
                WrapperConfig.EQ.format(column, value)
        );

        return this;
    }

    @Override
    public Wrapper orderByAsc(String column) {
        this.wrapperInstructionList.add(
                WrapperConfig.ORDER_BY_ASC.format(
                        column
                )
        );

        return this;
    }

    @Override
    public Wrapper orderByDesc(String column) {
        this.wrapperInstructionList.add(
                WrapperConfig.ORDER_BY_DESC.format(column)
        );

        return this;
    }

    @Override
    public Wrapper limit(Long offset) {
        this.wrapperInstructionList.add(
                WrapperConfig.LIMIT.format(offset)
        );

        return this;
    }

    @Override
    public Wrapper limit(Long current, Long offset) {
        this.wrapperInstructionList.add(
                WrapperConfig.LIMIT_OFFSET.format(current, offset)
        );

        return this;
    }

    @SuppressWarnings("all")
    @Override
    public String buildSql() {
        List<WrapperInstruction> list = this.wrapperInstructionList.stream()
                .sorted(WrapperInstruction::compareTo)
                .collect(Collectors.toList());

        AtomicBoolean existsWhere = new AtomicBoolean(false);
        AtomicBoolean existsEq = new AtomicBoolean(false);

        StringBuilder sb = new StringBuilder(
                StringUtils.isBlank(this.baseSql) ? WrapperConfig.EMPTY_STRING : this.baseSql + WrapperConfig.SPACE
        );

        list.forEach(wrapperInstruction -> {
            switch (wrapperInstruction.getType()) {
                case EQ: {
                    if (!existsWhere.get()) {
                        sb.append(WrapperConfig.WHERE.formatSql() + WrapperConfig.SPACE);
                        existsWhere.compareAndSet(false, true);
                    }

                    if (existsEq.get()) {
                        sb.append(WrapperConfig.AND.formatSql() + WrapperConfig.SPACE);
                    }

                    sb.append(wrapperInstruction.formatSql() + WrapperConfig.SPACE);

                    if (!existsEq.get()) {
                        existsEq.compareAndSet(false, true);
                    }
                    break;
                }

                case ORDER_BY_ASC:
                case ORDER_BY_DESC:
                case LIMIT_OFFSET:
                case LIMIT: {
                    sb.append(wrapperInstruction.formatSql() + WrapperConfig.SPACE);
                    break;
                }
            }
        });

        return sb.toString()
                .trim();
    }
}
