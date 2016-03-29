package cn.springmvc.mybatis.activiti.utils;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.springmvc.mybatis.entity.activiti.Employee;
import cn.springmvc.mybatis.entity.auth.User;
import cn.springmvc.mybatis.service.activiti.EmployeeService;
import cn.springmvc.mybatis.service.auth.AuthService;

/**
 * 员工经理任务分配
 *
 */
@SuppressWarnings("serial")
public class ManagerTaskHandler implements TaskListener {

    @Autowired
    private AuthService authService;

    @Override
    public void notify(DelegateTask delegateTask) {
        /** 懒加载异常 */
        // Employee employee = SessionContext.get();
        // //设置个人任务的办理人
        // delegateTask.setAssignee(employee.getManager().getName());
        /** 从新查询当前用户，再获取当前用户对应的领导 */
        // Employee employee = SessionContext.get();
        // 当前用户
        // String name = employee.getName();
        // 使用当前用户名查询用户的详细信息
        // 从web中获取spring容器
        // WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
        // EmployeeService employeeService = (EmployeeService) ac.getBean("employeeService");
        // Employee emp = employeeService.findEmployeeByName(name);

        // 这里应该是查询出当前用户对应的管理员用户 , 这里简单处理，先固定一位用户
        User emp = authService.findUserByName("wzj");

        // 设置个人任务的办理人
        delegateTask.setAssignee(emp.getId());

    }

}
