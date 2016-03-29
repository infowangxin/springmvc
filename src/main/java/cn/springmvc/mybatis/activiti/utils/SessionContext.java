package cn.springmvc.mybatis.activiti.utils;

import cn.springmvc.mybatis.entity.activiti.Employee;

//import org.apache.struts2.ServletActionContext;

public class SessionContext {

    public static final String GLOBLE_USER_SESSION = "globle_user";

    public static void setUser(Employee user) {
        if (user != null) {
            // ServletActionContext.getRequest().getSession().setAttribute(GLOBLE_USER_SESSION, user);
        } else {
            // ServletActionContext.getRequest().getSession().removeAttribute(GLOBLE_USER_SESSION);
        }
    }

    public static Employee get() {
        // return (Employee) ServletActionContext.getRequest().getSession().getAttribute(GLOBLE_USER_SESSION);
        return null;
    }
}
