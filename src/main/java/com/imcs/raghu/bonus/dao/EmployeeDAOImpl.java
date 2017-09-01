package com.imcs.raghu.bonus.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.imcs.raghu.bonus.pojo.EmpSortEnum;
import com.imcs.raghu.bonus.pojo.Employee;

public class EmployeeDAOImpl extends AbstractDAO implements EmployeeDAO{
	final static Logger logger=Logger.getLogger(EmployeeDAOImpl.class);
	
	public List<Employee> getEmployees(int deptNo) {
		logger.info("get employees by age for dept"+deptNo);
		List<Employee> list=new ArrayList<>();
		ResultSet rs=null;
		try (Connection conn = ConnectionFactory.getConnection();
				Statement st=conn.createStatement();
				PreparedStatement ps = conn.prepareStatement("select empNo,deptNo,doj,dob,salary,salaryGrade from tbl_employee where deptNo=?")) {
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

	@Override
	public Employee getEmployee(int empId) {
		ResultSet rs=null;
		Employee e=null;
		try (Connection conn = ConnectionFactory.getConnection();
				Statement st=conn.createStatement();
				PreparedStatement ps = conn.prepareStatement("select empNo,deptNo,doj,dob,salary,salaryGrade from tbl_employee where empNo=?")){
				ps.setInt(1, empId);
				rs=ps.executeQuery();
				while(rs.next()){
					e=new Employee(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getDate(4),rs.getFloat(5),rs.getInt(6));
				}
				
		}catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return e;
	}

	@Override
	public boolean deleteEmployee(int empId) {
		int updCount=0;
		try (Connection conn = ConnectionFactory.getConnection();
				Statement st=conn.createStatement();
				PreparedStatement ps = conn.prepareStatement("delete from tbl_employee where empNo=?")){
				ps.setInt(1, empId);
				updCount=ps.executeUpdate();
		}catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return updCount>0?true:false;
	}

	@Override
	public boolean updateEmployee(Employee emp) {
		int updCount=0;
		try (Connection conn = ConnectionFactory.getConnection();
				Statement st=conn.createStatement();
				PreparedStatement ps = conn.prepareStatement("update tbl_employee set deptNo=?,doj=?,dob=?,salary=?,salaryGrade=? where empNo=?")){
				ps.setInt(1, emp.getDeptNo());
				ps.setDate(2, new Date(emp.getDoj().getTime()));
				ps.setDate(3, new Date(emp.getDob().getTime()));
				ps.setFloat(4, emp.getSalary());
				ps.setInt(5, emp.getSalGrade());
				ps.setInt(6, emp.getEmpNo());
				updCount=ps.executeUpdate();
		}catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return updCount>0?true:false;
	}

	@Override
	public int addEmployee(Employee emp) {
		int addCount=0;
		int id=0;
		try (Connection conn = ConnectionFactory.getConnection();
				Statement st=conn.createStatement();
				PreparedStatement ps = conn.prepareStatement("insert into tbl_employee values(?,?,?,?,?,?)")){
				id=getNextEmpId();
				ps.setInt(2, emp.getDeptNo());
				ps.setDate(3, new Date(emp.getDoj().getTime()));
				ps.setDate(4, new Date(emp.getDob().getTime()));
				ps.setFloat(5, emp.getSalary());
				ps.setInt(6, emp.getSalGrade());
				ps.setInt(1, id);
				addCount=ps.executeUpdate();
		}catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return addCount>0?id:0;
	}

	private int getNextEmpId() {
		int empId=0;
		try (Connection conn = ConnectionFactory.getConnection();
				Statement st=conn.createStatement();
				PreparedStatement ps = conn.prepareStatement("select max(empNo) from tbl_employee")){
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					empId=rs.getInt(1);
				}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return empId+1;
	}
	
}
