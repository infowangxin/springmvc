package cn.itcast.ssh.dao;

import cn.itcast.ssh.domain.Employee;


public interface IEmployeeDao {

	Employee findEmployeeByName(String name);

	

}
