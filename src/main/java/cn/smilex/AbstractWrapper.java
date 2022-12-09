package cn.smilex;

import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author smilex
 */
public abstract class AbstractWrapper {
    protected final String baseSql;
    protected final List<WrapperInstruction> wrapperInstructionList;

    public AbstractWrapper(String baseSql, List<WrapperInstruction> wrapperInstructionList) {
        this.baseSql = baseSql;
        this.wrapperInstructionList = wrapperInstructionList;
    }

    public AbstractWrapper eq(String column, Object value) {
        this.wrapperInstructionList.add(
                WrapperConfig.EQ.format(column, value)
        );

        return this;
    }

    public AbstractWrapper orderByAsc(String column) {
        this.wrapperInstructionList.add(
                WrapperConfig.ORDER_BY_ASC.format(
                        column
                )
        );

        return this;
    }

    public AbstractWrapper orderByDesc(String column) {
        this.wrapperInstructionList.add(
                WrapperConfig.ORDER_BY_DESC.format(column)
        );
        return this;
    }

    public AbstractWrapper limit(Long offset) {
        this.wrapperInstructionList.add(
                WrapperConfig.LIMIT.format(offset)
        );
        return this;
    }

    public AbstractWrapper limit(Long current, Long offset) {
        this.wrapperInstructionList.add(
                WrapperConfig.LIMIT_OFFSET.format(current, offset)
        );
        return this;
    }

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
                        sb.append(WrapperConfig.WHERE.formatSql())
                                .append(WrapperConfig.SPACE);
                        existsWhere.compareAndSet(false, true);
                    }

                    if (existsEq.get()) {
                        sb.append(WrapperConfig.AND.formatSql())
                                .append(WrapperConfig.SPACE);
                    }

                    sb.append(wrapperInstruction.formatSql())
                            .append(WrapperConfig.SPACE);

                    if (!existsEq.get()) {
                        existsEq.compareAndSet(false, true);
                    }
                    break;
                }

                case ORDER_BY_ASC:
                case ORDER_BY_DESC:
                case LIMIT_OFFSET:
                case LIMIT: {
                    sb.append(wrapperInstruction.formatSql())
                            .append(WrapperConfig.SPACE);
                    break;
                }
            }
        });

        return sb.toString()
                .trim();
    }
}
