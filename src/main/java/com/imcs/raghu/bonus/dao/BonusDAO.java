package com.imcs.raghu.bonus.dao;

import java.util.List;

import com.imcs.raghu.bonus.pojo.Bonus;

public interface BonusDAO {
	public List<Integer> getDepartments();
	public void loadBonus(List<Bonus> bonus);
	public List<Bonus> getAllBonus();
	public void updateBonus(int deptNo,float amount);
}
