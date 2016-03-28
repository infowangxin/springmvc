package cn.springmvc.mybatis.service.activiti.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.mybatis.entity.activiti.Employee;
import cn.springmvc.mybatis.mapper.activiti.EmployeeMapper;
import cn.springmvc.mybatis.service.activiti.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /** 使用用户名作为查询条件，查询用户对象 */
    @Override
    public Employee findEmployeeByName(String name) {
        return employeeMapper.findEmployeeByName(name);
    }

}
