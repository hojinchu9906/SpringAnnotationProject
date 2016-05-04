package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;

@Repository
public class EmpDeptDAO {
	private Connection conn;
	private CallableStatement cs;
	private final String url="jdbc:oracle:thin:@211.238.142.20:1521:ORCL";
	private final String user="scott5";
	private final String pwd="tiger5";
	
	public EmpDeptDAO(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public void getConnection(){
		try{
			conn=DriverManager.getConnection(url,user,pwd);
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public void disConnection(){
		try{
			if(cs!=null)
				cs.close();
			if(conn!=null)
				conn.close();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public List<EmpVO> empdeptAllData(){
		List<EmpVO> list=new ArrayList<EmpVO>();
		
		try{
			getConnection();
			String sql="{CALL empdeptAllData(?)}";
			
			cs=conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.executeUpdate();
			
			ResultSet rs=(ResultSet)cs.getObject(1);
			/*
			 * OracleTypes.CURSOR => ResultSet
			 * OracleTypes.NUMBER, INTEGER => int
			 * OracleTypes.VARCHAR => String
			 * OracleTypes.BLOB,BFILE => InputStream
			 * OracleTypes.DATE, TIMESTAMP => Date
			 */
			
			while(rs.next()){
				EmpVO vo=new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				vo.getDvo().setDname(rs.getString(6));
				vo.getDvo().setLoc(rs.getString(7));
				
				list.add(vo);
			}
			rs.close();
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			disConnection();
		}
		
		return list;
	}
}


























































