package cn.smilex;

import lombok.Data;

/**
 * @author smilex
 */
@Data
public class WrapperInstruction implements Comparable<WrapperInstruction> {
    private final int order;
    private final WrapperInstructionType type;
    private final String instruction;

    protected WrapperInstruction(String instruction) {
        this.order = -1;
        this.type = null;
        this.instruction = instruction;
    }

    public WrapperInstruction(int order, WrapperInstructionType type, String instruction) {
        this.order = order;
        this.type = type;
        this.instruction = instruction;
    }

    public String formatSql(Object... values) {
        if (values == null || values.length == 0) {
            return this.instruction;
        }

        String tmp = this.instruction;

        for (Object value : values) {
            tmp = WrapperUtil.replace(
                    tmp,
                    tmp.indexOf(WrapperConfig.SPLICE),
                    WrapperConfig.SPLICE.length(),
                    value.toString()
            );
        }

        return tmp;
    }

    public WrapperInstruction format(Object... values) {
        return new WrapperInstruction(
                this.order,
                this.type,
                formatSql(values)
        );
    }

    @Override
    public int compareTo(WrapperInstruction o) {
        return Integer.compare(this.order, o.order);
    }
}
