package cn.springmvc.mybatis.service.activiti;

import cn.springmvc.mybatis.entity.activiti.Employee;

public interface EmployeeService {

    Employee findEmployeeByName(String name);

}
