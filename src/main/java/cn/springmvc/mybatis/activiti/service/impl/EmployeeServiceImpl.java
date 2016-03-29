package cn.springmvc.mybatis.activiti.service.impl;

import cn.springmvc.mybatis.entity.activiti.Employee;
import cn.springmvc.mybatis.service.activiti.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

    /** 使用用户名作为查询条件，查询用户对象 */
    @Override
    public Employee findEmployeeByName(String name) {
        // Employee employee = employeeDao.findEmployeeByName(name);
        return null;
    }

}
