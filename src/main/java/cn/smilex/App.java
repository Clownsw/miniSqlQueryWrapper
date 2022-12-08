package cn.smilex;

/**
 * @author smilex
 */
public class App {
    public static void main(String[] args) {
        // testWrapperInstruction();
        // testWrapperInstruction2();
        testQueryWrapper();
    }

    public static void testWrapperInstruction() {
        WrapperInstruction wrapperInstruction = new WrapperInstruction("{} = {}");
        System.out.println(wrapperInstruction.formatSql("a", 1));
    }

    public static void testWrapperInstruction2() {
        System.out.println(WrapperConfig.ORDER_BY_ASC.formatSql("test"));
    }

    public static void testQueryWrapper() {
        Wrapper queryWrapper = new QueryWrapper()
                .eq("user_name", "xuda")
                .eq("pass_word", 123123)
                .orderByDesc("create_time")
                .limit(5L, 10L);

        System.out.println(queryWrapper.buildSql());
    }

    public static void testReplace() {
        String str = "{} = {}";
        str = WrapperUtil.replace(str, 0, 2, "name");
        str = WrapperUtil.replace(str, str.indexOf("{}"), 2, "xuda");
        System.out.println(str);
    }
}
