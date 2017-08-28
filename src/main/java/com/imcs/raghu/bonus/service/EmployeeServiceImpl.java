package com.imcs.raghu.bonus.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.imcs.raghu.bonus.dao.EmployeeDAO;
import com.imcs.raghu.bonus.dao.EmployeeDAOImpl;
import com.imcs.raghu.bonus.pojo.EmpSortEnum;
import com.imcs.raghu.bonus.pojo.Employee;

public class EmployeeServiceImpl implements EmployeeService{
	final static Logger logger=Logger.getLogger(EmployeeServiceImpl.class);
	private EmployeeDAO bimpl=new EmployeeDAOImpl();
	
	public List<Employee> getEmployees(int deptNo){
		if(deptNo%2==0)
			return bimpl.getEmployees(deptNo,EmpSortEnum.Age);
		else
			return bimpl.getEmployees(deptNo,EmpSortEnum.Joining);
	}

	@Override
	public Employee getEmployee(int empId) {
		// TODO Auto-generated method stub
		return bimpl.getEmployee(empId);
	}
	
}
