package cn.itcast.ssh.service.impl;

import cn.itcast.ssh.dao.IEmployeeDao;
import cn.itcast.ssh.domain.Employee;
import cn.itcast.ssh.service.IEmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	private IEmployeeDao employeeDao;

	public void setEmployeeDao(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	/**使用用户名作为查询条件，查询用户对象*/
	@Override
	public Employee findEmployeeByName(String name) {
		Employee employee = employeeDao.findEmployeeByName(name);
		return employee;
	}
	
}
