package cn.itcast.ssh.service;

import cn.itcast.ssh.domain.Employee;


public interface IEmployeeService {

	Employee findEmployeeByName(String name);


}
