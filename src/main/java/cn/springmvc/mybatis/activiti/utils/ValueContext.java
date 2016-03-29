package cn.springmvc.mybatis.activiti.utils;

public class ValueContext {

    /** 放置在Root栈中 */
    public static void putValueContext(String key, Object value) {
        // ActionContext.getContext().put(key, value);
    }

    /** 压入栈顶 */
    public static void putValueStack(Object o) {
        // ServletActionContext.getContext().getValueStack().push(o);
    }
}
