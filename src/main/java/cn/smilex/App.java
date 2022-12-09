package cn.smilex;

/**
 * @author smilex
 */
public class App {
    public static void main(String[] args) {
        // testReplace();
        // testWrapperInstruction();
        // testWrapperInstruction2();
        testQueryWrapper();
        // testSetStringValue();
    }

    private static void testSetStringValue() {
        String str = "";
        ReflectionUtil.setStringValue(str, new char[]{'a', 'b', 'c'});
        System.out.println(str);
    }

    public static void testWrapperInstruction() {
        WrapperInstruction wrapperInstruction = new WrapperInstruction("{} = {}");
        System.out.println(wrapperInstruction.formatSql("a", 1));
    }

    public static void testWrapperInstruction2() {
        System.out.println(WrapperConfig.ORDER_BY_ASC.formatSql("test"));
    }

    public static void testQueryWrapper() {
        Wrapper queryWrapper = new QueryWrapper("SELECT * FROM t_user")
                .eq("user_name", "xuda")
                .eq("pass_word", 123123)
                .orderByDesc("create_time")
                .limit(5L, 10L);

        System.out.println(queryWrapper.buildSql());
    }

    @SuppressWarnings("all")
    public static void testReplace() {
        String[] params = {"user_name", "xuda", "xuda2", "xuda3"};
        String str = "{} = {} = {} = {}";
        str = WrapperUtil.replace(str, str.indexOf("{}"), params[0]);
        str = WrapperUtil.replace(str, str.indexOf("{}"), params[1]);
        str = WrapperUtil.replace(str, str.indexOf("{}"), params[2]);
        str = WrapperUtil.replace(str, str.indexOf("{}"), params[3]);
        System.out.println(str);
    }
}
