package com.imcs.raghu.bonus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.imcs.raghu.bonus.pojo.EmpSortEnum;
import com.imcs.raghu.bonus.pojo.Employee;

public class EmployeeDAOImpl extends AbstractDAO implements EmployeeDAO{
	final static Logger logger=Logger.getLogger(EmployeeDAOImpl.class);
	
	public List<Employee> getEmployees(int deptNo,EmpSortEnum sort) {
		logger.info("get employees by age for dept"+deptNo);
		List<Employee> list=new ArrayList<>();
		ResultSet rs=null;
		try (Connection conn = ConnectionFactory.getConnection();
				Statement st=conn.createStatement();
				PreparedStatement ps = conn.prepareStatement("select empNo,deptNo,doj,dob,salary,salaryGrade from tbl_employee where deptNo=? order by "+sort.value)) {
				ps.setInt(1, deptNo);
				rs=ps.executeQuery();
				while(rs.next()){
					list.add(new Employee(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getDate(4),rs.getFloat(5),rs.getInt(6)));
				}
				
		}catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return list;
	}
}
