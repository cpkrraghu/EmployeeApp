package com.imcs.raghu.bonus.dao;

import java.util.List;

import com.imcs.raghu.bonus.pojo.EmpSortEnum;
import com.imcs.raghu.bonus.pojo.Employee;

public interface EmployeeDAO {
	public List<Employee> getEmployees(int deptNo);

	public Employee getEmployee(int empId);

	public boolean deleteEmployee(int empId);
	
	public boolean updateEmployee(Employee emp);
	
	public int addEmployee(Employee emp);
	
}
