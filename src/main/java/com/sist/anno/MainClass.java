package com.sist.anno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.EmpDeptDAO;
import com.sist.dao.EmpVO;

@Component
public class MainClass {
	@Autowired
	private EmpDeptDAO edao;			//empDeptDAO
	
	public static void main(String[] args){
		ApplicationContext app=
				new ClassPathXmlApplicationContext("app.xml");
		
		MainClass mc=(MainClass)app.getBean("mainClass"); 
		
		List<EmpVO> list=mc.edao.empdeptAllData();
		for(EmpVO vo:list){
			System.out.println(vo.getEmpno() +" "+ 
								vo.getEname() +" "+
								vo.getHiredate() +" "+
								vo.getDvo().getDname() +" "+
								vo.getDvo().getLoc());
		}
		
	}
}





























































