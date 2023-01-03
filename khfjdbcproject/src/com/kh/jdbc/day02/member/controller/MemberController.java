package com.kh.jdbc.day02.member.controller;

import java.util.List;

import com.kh.jdbc.day02.member.model.dao.MemberDAO;
import com.kh.jdbc.day02.member.model.vo.Member;

public class MemberController {
	/**
	 * 학생 전체 목록 조회
	 * 
	 * @return mList
	 */
	public List<Member> printAll() {  //SELECT * FROM MEMBER_TBL
		MemberDAO mDao = new MemberDAO();
		List<Member> mList = mDao.selectAll();
		return mList;
	}
	
	/**
	 * 회원 아이디로 조회
	 * 
	 * @param memberId
	 * @return member
	 */
	public Member printObeById(String memberId) {  //SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?
		//ResultSet이 1개면 Member
		//ResultSet이 1개 이상이면 List<Member>
		MemberDAO mDao = new MemberDAO();
		Member member = mDao.selectOneById(memberId);
		return member;
	}

	/**
	 * 회원 이름으로 조회
	 * 
	 * @param memberName
	 * @return
	 */
	public List<Member> printMemberByName(String memberName) {  //SELECT * FROM MEMBER_TBL WHERE MEMBER_NAME = ?
		//ResultSet이 1개면 Member
		//ResultSet이 1개 이상이면 List<Member>
		//동명이인이 있을 수 있어서 1명 이상이 나올수 있음
		MemberDAO mDao = new MemberDAO();
		List<Member> mList = mDao.selectMemberByName(memberName);
		return mList;
	}

	/**
	 * 로그인 하기
	 * 
	 * @param member
	 * @return
	 */
	public int checkInfo(Member member) {   //SELECT * FROM MEMBER_TBL WHERE MEMBER_TD = ? AND MEMBER_PWD = ?
		MemberDAO mDao = new MemberDAO();
		int result = mDao.checkLogin(member);
		return result;
	}

	/**
	 * 회원 가입
	 * 
	 * @param member
	 * @return
	 */
	public int registerMember(Member member) {  //INSERT INTO MEMBER_TBL VALUES(?,?,?,?,?,?,?,?,?,DEFAULT)
		MemberDAO mDao = new MemberDAO();
		int result = mDao.insertMember(member);
		return result;
	}

	/**
	 * 회원 정보 수정
	 * @param member
	 * @return
	 */
	public int modifyMemberInfo(Member member) {
		MemberDAO mDao = new MemberDAO();
		int result = mDao.updateMember(member);
		return result;  // return 0으로 두지 않아요
	}

	/**
	 * 회원 탈퇴
	 * 
	 * @param memberId
	 * @return
	 */
	public int removeMember(String memberId) {  //DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?
		MemberDAO mDao = new MemberDAO();
		int result = mDao.deleteMember(memberId);
		return result;
	}
}
