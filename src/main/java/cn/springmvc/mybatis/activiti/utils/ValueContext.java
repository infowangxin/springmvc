package cn.itcast.ssh.utils;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class ValueContext {

	/**放置在Root栈中*/
	public static void putValueContext(String key,Object value){
		ActionContext.getContext().put(key, value);
	}
	
	/**压入栈顶*/
	public static void putValueStack(Object o){
		ServletActionContext.getContext().getValueStack().push(o);
	}
}
