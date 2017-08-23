package com.imcs.raghu.bonus.dao;

import java.sql.Connection;

public abstract class AbstractDAO {
	public Connection getConnection(){
		
			return ConnectionFactory.getConnection();
		
	}
}
