package com.imcs.raghu.bonus.service;

import java.util.List;

import com.imcs.raghu.bonus.pojo.Bonus;

public interface BonusService {
	public void loadBonus(List<Bonus> bonus);
	public List<Bonus> getBonus();
	public void updateBonus(int	deptNo, float rem);
}
