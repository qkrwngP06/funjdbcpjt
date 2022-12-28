package com.kh.jdbc.day00;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCRun {
	public static void main(String[] args) {
		/*
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성(연결된 상태)
		 * 3. Statement 객체 생성(쿼리문 실행준비)
		 * 4. SQL전송 (쿼리문 실행된 상태)
		 * 5. 결과값 받기 (ResultSet을 받은 상태) -> 후처리
		 * 6. 자원해제(close())
		 */
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "KH";
			String password = "KH";
			String sql = "SELECT * FROM EMPLOYEE";
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. DB 연결 생성
			Connection conn = DriverManager.getConnection(url,user,password);
			// 3. 쿼리문 실행준비(Statement 객체 생성)
			Statement stmt = conn.createStatement();
			// 4. 쿼리문 실행
			ResultSet rset = stmt.executeQuery(sql);
			// 후처리 - 디비에서 가져온 데이터 사용
			while(rset.next()) {
				System.out.println("사원 아이디 : " + rset.getString("EMP_ID"));
			}
			// 자원해제
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
