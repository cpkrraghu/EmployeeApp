package com.imcs.raghu.bonus.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.imcs.raghu.bonus.dao.EmpBonusDAO;
import com.imcs.raghu.bonus.dao.EmpBonusDAOImpl;
import com.imcs.raghu.bonus.pojo.Bonus;
import com.imcs.raghu.bonus.pojo.EmpBonus;
import com.imcs.raghu.bonus.pojo.Employee;

public class EmpBonusServiceImpl implements EmpBonusService{
	
	final static Logger logger=Logger.getLogger(EmpBonusService.class);
	
	BonusService bonusService=new BonusServiceImpl();
	EmployeeService empService=new EmployeeServiceImpl();
	private EmpBonusDAO ebimp=new EmpBonusDAOImpl();
	
	public void allocateBonus() {
		logger.info("started allocation process");
		try{
			List<Bonus> list=bonusService.getBonus();
			ExecutorService executorService = Executors.newFixedThreadPool(5);
			long time=new Date().getTime();
			for(Bonus b:list){
				logger.info("started allocating bonus for Dept : "+b.getDeptNo());
				List<Employee> emps=empService.getEmployees(b.getDeptNo());
				Thread t=new Thread(){
					public void run(){
						float rem=b.getRemainingAmount();
						for(Employee emp:emps){
							rem=giveBonus(emp,rem);
						}
						bonusService.updateBonus(b.getDeptNo(),rem);
					}
				};
				t.setName(""+b.getDeptNo());
				t.join();
				executorService.execute(t);
				
				logger.info("Done allocation for DeptNo :"+b.getDeptNo());
			}
			System.out.println((new Date().getTime())-time);
			executorService.shutdown();
		}catch (Exception e) {
			logger.error("Exception : "+e.getMessage());
		}
	}
	public float giveBonus(Employee emp, float remainingAmount) {
		logger.info("alloting bonus for"+emp.getEmpNo());
		float empBonus=0.0f;
		switch(emp.getSalGrade()){
		case 1:
			empBonus=emp.getSalary()*0.1f;
			break;
		case 2:
			empBonus=emp.getSalary()*0.15f;
			break;
		case 3:
			empBonus=emp.getSalary()*0.2f;
			break;
		case 4:
			empBonus=emp.getSalary()*0.25f;
			break;
		default:
			empBonus=emp.getSalary()*0.05f;
			break;	
		}
		if(remainingAmount==0.0f){
			ebimp.setEmpBonus(new EmpBonus(emp.getEmpNo(),"INC",0.0f,new Date()));
			logger.info("Bonus for Employee"+emp.getEmpNo()+" is $"+0.0f);
			return remainingAmount;
		}
		else if(remainingAmount>empBonus){
			ebimp.setEmpBonus(new EmpBonus(emp.getEmpNo(),"CMP",empBonus,new Date()));
			logger.info("Bonus for Employee"+emp.getEmpNo()+" is $"+empBonus);
			return remainingAmount-empBonus;
		}
		else {
			ebimp.setEmpBonus(new EmpBonus(emp.getEmpNo(),"PAR",remainingAmount,new Date()));
			logger.info("Bonus for Employee"+emp.getEmpNo()+" is $"+remainingAmount);
			return 0.0f;
		}	
	}
}
