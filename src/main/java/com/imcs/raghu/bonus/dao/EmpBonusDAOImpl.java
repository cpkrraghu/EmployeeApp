package com.imcs.raghu.bonus.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.imcs.raghu.bonus.pojo.EmpBonus;

public class EmpBonusDAOImpl extends AbstractDAO implements EmpBonusDAO{
	final static Logger logger=Logger.getLogger(EmpBonusDAOImpl.class);
	
	public void setEmpBonus(EmpBonus empBonus) {
		logger.info("setting emp bonus"+empBonus.getEmpNo());
		try (Connection conn = ConnectionFactory.getConnection();
				Statement st=conn.createStatement();
				PreparedStatement ps = conn.prepareStatement("insert into tbl_empBonus values(?,?,?,?)")) {
			ps.setInt(1, empBonus.getEmpNo());
			ps.setString(2, empBonus.getStatus());
			ps.setFloat(3, empBonus.getAmount());
			ps.setDate(4, new Date(empBonus.getDateAllocated().getTime()));
			ps.executeUpdate();
				
		}catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}
	
	
}
