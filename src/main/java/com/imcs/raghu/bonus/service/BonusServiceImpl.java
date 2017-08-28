package com.imcs.raghu.bonus.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.imcs.raghu.bonus.dao.BonusDAO;
import com.imcs.raghu.bonus.dao.BonusDAOImpl;
import com.imcs.raghu.bonus.pojo.Bonus;

public class BonusServiceImpl implements BonusService{
	final static Logger logger=Logger.getLogger(BonusServiceImpl.class);
	private BonusDAO bimpl=new BonusDAOImpl();
	
	public void loadBonus(List<Bonus> bonus){
		bimpl.loadBonus(bonus);
	}
	public List<Bonus> getBonus() {
		return bimpl.getAllBonus();
	}
	public void updateBonus(int	deptNo, float rem){
		bimpl.updateBonus(deptNo,rem);
	}
	public Bonus getBonus(int deptNo) {
		 return bimpl.getBonus(deptNo);
	}
}
