package cn.springmvc.mybatis.activiti.utils;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.springmvc.mybatis.entity.activiti.Employee;
import cn.springmvc.mybatis.service.activiti.EmployeeService;

/**
 * 员工经理任务分配
 *
 */
@SuppressWarnings("serial")
public class ManagerTaskHandler implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        /** 懒加载异常 */
        // Employee employee = SessionContext.get();
        // //设置个人任务的办理人
        // delegateTask.setAssignee(employee.getManager().getName());
        /** 从新查询当前用户，再获取当前用户对应的领导 */
        Employee employee = SessionContext.get();
        // 当前用户
        String name = employee.getName();
        // 使用当前用户名查询用户的详细信息
        // 从web中获取spring容器
        WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
        EmployeeService employeeService = (EmployeeService) ac.getBean("employeeService");
        Employee emp = employeeService.findEmployeeByName(name);
        // 设置个人任务的办理人
        delegateTask.setAssignee(emp.getManager().getName());

    }

}
