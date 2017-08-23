package com.imcs.raghu.bonus.service;

import com.imcs.raghu.bonus.pojo.Employee;

public interface EmpBonusService {
	public void allocateBonus();
	public float giveBonus(Employee emp, float remainingAmount) ;
	
}
