package com.imcs.raghu.bonus.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.imcs.raghu.bonus.dao.EmployeeDAO;
import com.imcs.raghu.bonus.dao.EmployeeDAOImpl;
import com.imcs.raghu.bonus.pojo.EmpSortEnum;
import com.imcs.raghu.bonus.pojo.Employee;

public class EmployeeServiceImpl implements EmployeeService{
	final static Logger logger=Logger.getLogger(EmployeeServiceImpl.class);
	private EmployeeDAO empDao=new EmployeeDAOImpl();
	
	public List<Employee> getEmployees(int deptNo){
		if(deptNo%2==0)
			return empDao.getEmployees(deptNo,EmpSortEnum.Age);
		else
			return empDao.getEmployees(deptNo,EmpSortEnum.Joining);
	}

	@Override
	public Employee getEmployee(int empId) {
		return empDao.getEmployee(empId);
	}

	@Override
	public boolean deleteEmployee(int empId) {
		return empDao.deleteEmployee(empId);
	}

	@Override
	public boolean updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return empDao.updateEmployee(emp);
	}

	@Override
	public boolean addEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return empDao.addEmployee(emp);
	}
	
}
