package com.imcs.raghu.bonus.service;

import java.util.List;

import com.imcs.raghu.bonus.pojo.Employee;

public interface EmployeeService {
	public List<Employee> getEmployees(int deptNo);
	public Employee getEmployee(int empId);
}
