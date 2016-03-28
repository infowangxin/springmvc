package cn.springmvc.mybatis.mapper.activiti;

import cn.springmvc.mybatis.entity.activiti.Employee;
import cn.springmvc.mybatis.mapper.BaseMapper;

public interface EmployeeMapper extends BaseMapper<String, Employee> {

    public Employee findEmployeeByName(String name);

}
