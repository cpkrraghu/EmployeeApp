package com.imcs.raghu.bonus.dao;

import java.util.List;

import com.imcs.raghu.bonus.pojo.EmpSortEnum;
import com.imcs.raghu.bonus.pojo.Employee;

public interface EmployeeDAO {
	public List<Employee> getEmployees(int deptNo,EmpSortEnum sort);
	
}
