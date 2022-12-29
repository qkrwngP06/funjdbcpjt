package com.kh.jdbc.day01.student.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day01.student.model.vo.Student;


public class StudentDAO {
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "STUDENT";
	private final String PASSWORD = "STUDENT";
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	
	public List<Student> selectAll() {
		String sql = "SELECT * FROM STUDENT_TBL";
		Student student = null;
		List<Student> sList = null;
		try {
			// 1. 드라이버 등록
			Class.forName(DRIVER_NAME);
			// 2. DB 연결 생성
			Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
			// 3. 쿼리문 실행준비(Statement 객체 생성)
			Statement stmt = conn.createStatement();
			// 4. 쿼리문 실행 및 결과 받기(SELECT)
			ResultSet rset = stmt.executeQuery(sql);
			sList = new ArrayList<Student>();
			// 후처리
			while(rset.next()) {
				student = new Student();
				student.setStudentId(rset.getString("STUDENT_ID"));
				student.setStudentPwd(rset.getString("STUDENT_PWD"));
				student.setStudentName(rset.getString("STUDENT_NAME"));
				student.setGender(rset.getString("GENDER"));
				student.setAge(rset.getInt("AGE"));  //시험에 나올 수 있음
				student.setEmail(rset.getString("EMAIL"));
				student.setPhone(rset.getString("PHONE"));
				student.setAddress(rset.getString("ADDRESS"));
				student.setHobby(rset.getString("HOBBY"));
				student.setEnrollDate(rset.getDate("ENROLL_DATE")); //시험에 나올 수 있음
				sList.add(student); //중요!!! 
			}
			//6. 자원 해제
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sList; //중요!!
	}
	
	//이름으로 조회
	public List<Student> selectAllByName(String studentName) {
		List<Student> sList = null;
		Student student = null;
		String sql = "SELECT * FROM STUDENT_TBL WHERE STUDENT_NAME = '"+studentName+"'";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(sql);
			sList = new ArrayList<Student>();
			while(rset.next()) {
				student = new Student();
				//setter
				student.setStudentId(rset.getString("STUDENT_ID"));
				student.setStudentPwd(rset.getString("STUDENT_PWD"));
				student.setStudentName(rset.getString("STUDENT_NAME"));
				student.setGender(rset.getString("GENDER"));
				student.setAge(rset.getInt("AGE"));
				student.setEmail(rset.getString("EMAIL"));
				student.setPhone(rset.getString("PHONE"));
				student.setAddress(rset.getString("ADDRESS"));
				student.setHobby(rset.getString("HOBBY"));
				student.setEnrollDate(rset.getDate("ENROLL_DATE"));
				//택배 상차
				sList.add(student);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sList;
	}

	//아이디로 조회
	public Student selectOneById(String studentId) {
		Student student = null;
		String sql = "SELECT * FROM STUDENT_TBL WHERE STUDENT_ID = '"+studentId+"'";
		try {
			//1. 드라이버 등록
			Class.forName(DRIVER_NAME);
			//2. DB 연결 객체 생성
			Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
			//3.Statement 생성, 쿼리문 실행 준비 완료
			Statement stmt = conn.createStatement();
			//4. 쿼리문 실행, 5. 결과 받기
			ResultSet rset = stmt.executeQuery(sql);
			//후처리, rset을 그대로 못쓰니까
			if(rset.next()) {
				student = new Student();
				student.setStudentId(rset.getString("STUDENT_ID"));
				student.setStudentPwd(rset.getString("STUDENT_PWD"));
				student.setStudentName(rset.getString("STUDENT_NAME"));
				student.setGender(rset.getString("GENDER"));
				student.setAge(rset.getInt("AGE"));
				student.setEmail(rset.getString("EMAIL"));
				student.setPhone(rset.getString("PHONE"));
				student.setAddress(rset.getString("ADDRESS"));
				student.setHobby(rset.getString("HOBBY"));
				student.setEnrollDate(rset.getDate("ENROLL_DATE"));
			}
			//6. 자원 해제
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	
	//회원 가입
	public int insertMember(Student student) {
		//String sql = "INSERT INTO STUDENT_TBL VALUES('"+student.getStudentId()+"','"+student.getStudentPwd()+"','"+student.getStudentName()+"','"+student.getGender()+"',"+student.getAge()+",'"+student.getEmail()+"','"+student.getPhone()+"','"+student.getAddress()+"','"+student.getHobby()+"',SYSDATE)";
		String sql = "INSERT INTO STUDENT_TBL VALUES("
				+ "'"+student.getStudentId()+"',"
					+ "'"+student.getStudentPwd()+"',"
						+ "'"+student.getStudentName()+"',"
							+ "'"+student.getGender()+"',"
								+student.getAge()+","
									+ "'"+student.getEmail()+"',"
										+ "'"+student.getPhone()+"',"
											+ "'"+student.getAddress()+"',"
												+ "'"+student.getHobby()+"',"
													+ "SYSDATE)";

		int result = 0;
		try {
			// 1. 드라이버 등록
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
			Statement stmt = conn.createStatement();
			//쿼리문 실행 - DML(INSERT,UPDATE,DELETE)
			result = stmt.executeUpdate(sql);  
			//시험문제!!!!!! select문에서는 executeQuery해서 리턴타입은 ResultSet
			//INSERT,UPDATE,DELETE executeUpdate해서 리턴타입은 int
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//학생 탈퇴
	public int deleteMember(String studentId) {
		int result = 0;
		String sql = "DELETE FROM STUDENT_TBL WHERE STUDENT_ID = '"+studentId+"'";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
			Statement stmt = conn.createStatement();
			//DML(INSERT, UPDATE, DELETE) -> executeUpdate -> int
			result = stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//학생 수정
	public int updateStudent(Student student) {
		int result = 0;
		String sql = "UPDATE STUDENT_TBL SET STUDENT_PWD = '"+student.getStudentPwd()+"', EMAIL = '"+student.getEmail()+"', PHONE = '"+student.getPhone()+"', ADDRESS = '"+student.getAddress()+"', HOBBY = '"+student.getHobby()+"' WHERE STUDENT_ID = '"+student.getStudentId()+"'";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
