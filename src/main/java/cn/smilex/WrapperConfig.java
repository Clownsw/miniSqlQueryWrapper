package cn.smilex;

/**
 * @author smilex
 */
public class WrapperConfig {
    public static final String EMPTY_STRING = "";
    public static final String SPACE = " ";
    public static final String SPLICE = "{}";
    public static final WrapperInstruction WHERE = new WrapperInstruction(1, WrapperInstructionType.WHERE, "WHERE");
    public static final WrapperInstruction AND = new WrapperInstruction(1, WrapperInstructionType.AND, "AND");
    public static final WrapperInstruction EQ = new WrapperInstruction(1, WrapperInstructionType.EQ, "{} = {}");
    public static final WrapperInstruction ORDER_BY = new WrapperInstruction(2, WrapperInstructionType.ORDER_BY, "ORDER BY {} {}");
    public static final WrapperInstruction ORDER_BY_ASC = new WrapperInstruction(2, WrapperInstructionType.ORDER_BY_ASC, ORDER_BY.formatSql("ASC", "{}"));
    public static final WrapperInstruction ORDER_BY_DESC = new WrapperInstruction(2, WrapperInstructionType.ORDER_BY_DESC, ORDER_BY.formatSql("DESC", "{}"));
    public static final WrapperInstruction LIMIT_OFFSET = new WrapperInstruction(3, WrapperInstructionType.LIMIT_OFFSET, "LIMIT {}, {}");
    public static final WrapperInstruction LIMIT = new WrapperInstruction(3, WrapperInstructionType.LIMIT, "LIMIT {}");
}
