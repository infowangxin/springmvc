package cn.itcast.ssh.utils;

import cn.itcast.ssh.domain.Employee;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


/**
 * 登录验证拦截器
 *
 */
@SuppressWarnings("serial")
public class LoginInteceptor implements Interceptor {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	/**每次访问Action类之前，先执行intercept方法*/
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//获取当前访问Action的URL
		String actionName = invocation.getProxy().getActionName();
		//如果当前访问Action的URL是"loginAction_login"表示此时还没有Sesion，需要放行
		if(!"loginAction_login".equals(actionName)){
			//从Session中获取当前用户对象
			Employee employee = SessionContext.get();
			//如果Session不存在，跳转到登录页面
			if(employee==null){
				return "login";
			}
		}
		//放行，访问Action类中方法
		return invocation.invoke();
		
	}

}
