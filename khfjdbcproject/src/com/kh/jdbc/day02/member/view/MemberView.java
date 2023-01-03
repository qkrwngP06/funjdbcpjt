package com.kh.jdbc.day02.member.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day02.member.model.vo.Member;

public class MemberView {
	public int mainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("=== === === 회원관리 프로그램 === === === ");
		System.out.println("1. 회원 전체 조회");
		System.out.println("2. 회원 아이디로 조회");
		System.out.println("3. 회원 이름으로 조회");
		System.out.println("4. 회원 가입");
		System.out.println("5. 회원 정보 수정");
		System.out.println("6. 회원 탈퇴");
		System.out.println("7. 로그인하기");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int choice = sc.nextInt();
		return choice;
	}

	// 전체 목록 출력
	public void showAll(List<Member> member) {
		System.out.println("=== === 전체 회원 조회 결과 === === ");
		for (Member mOne : member) {
			System.out.print("아이디 : " + mOne.getMemberId());
			System.out.print(", 비밀번호 : " + mOne.getMemberPwd());
			System.out.print(", 이름 : " + mOne.getMemberName());
			System.out.print(", 성별 : " + mOne.getMemberGender());
			System.out.print(", 나이 : " + mOne.getMemberAge());
			System.out.print(", 이메일 : " + mOne.getMemberEmail());
			System.out.print(", 전화번호 : " + mOne.getMemberPhone());
			System.out.print(", 주소 : " + mOne.getMemberAddress());
			System.out.print(", 취미 : " + mOne.getMemberHobby());
			System.out.println(", 가입날짜 : " + mOne.getMemberDate());
		}
	}
	
	// 회원 아이디로 조회
	public String inputMemberId(String message) {
		Scanner sc = new Scanner(System.in);
		System.out.print(message + "할 아이디 입력: ");
		String memberId = sc.next();
		return memberId;
	}

	// 회원 아이디로 검색
	public void showOne(Member member) {
		System.out.println("=== === "+member.getMemberId()+" 회원 조회 결과 === === ");
		System.out.print("아이디: " + member.getMemberId());
		System.out.print(", 비밀번호: " + member.getMemberPwd());
		System.out.print(", 이름: " + member.getMemberName());
		System.out.print(", 성별: " + member.getMemberGender());
		System.out.print(", 나이: " + member.getMemberAge());
		System.out.print(", 이메일: " + member.getMemberEmail());
		System.out.print(", 전화번호: " + member.getMemberPhone());
		System.out.print(", 주소: " + member.getMemberAddress());
		System.out.print(", 취미: " + member.getMemberHobby());
		System.out.println(", 가입날짜: " + member.getMemberDate());
	}

	// 회원 이름으로 조회
	public String inputMemberName(String message) {
		Scanner sc = new Scanner(System.in);
		System.out.print(message + "할 이름 입력:");
		String memberName = sc.next();
		return memberName;
	}

	//로그인
	public Member inputLoginInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("=== === 로그인 정보 입력 === ===");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPwd = sc.next();
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPwd(memberPwd);
		return member;
	}

	// 회원 가입
	public Member inputMember() {
		Scanner sc = new Scanner(System.in);
		System.out.println("=== === 회원 정보 입력 === ===");
		System.out.print("아이디: ");
		String memberId = sc.next();
		System.out.print("비밀번호: ");
		String memberPwd = sc.next();
		System.out.print("이름: ");
		String memberName = sc.next();
		System.out.print("성별: ");
		String memberGender = sc.next();
		System.out.print("나이: ");
		int memberAge = sc.nextInt();
		System.out.print("이메일: ");
		String memberEmail = sc.next();
		System.out.print("전화번호: ");
		String memberPhone = sc.next();
		System.out.print("주소: ");
		sc.nextLine();
		String memberAddress = sc.nextLine();
		System.out.print("취미(,로 구분): ");
		String memberHobby = sc.next();

		// 매개변수 있는 객체
		Member member = new Member(memberId, memberPwd, memberName, memberGender, memberAge, memberEmail, memberPhone,
				memberAddress, memberHobby);
		return member;
	}

	// 회원 수정
	public Member modifyMember(String memberId) {
		Scanner sc = new Scanner(System.in);
		Member member = new Member();
		member.setMemberId(memberId);  //중요!!!
		System.out.print("수정할 비밀번호 입력: ");
		member.setMemberPwd(sc.next());
		System.out.print("수정할 이메일 입력: ");
		member.setMemberEmail(sc.next());
		System.out.print("수정할 전화번호 입력: ");
		member.setMemberPhone(sc.next());
		System.out.print("수정할 주소 입력: ");
		sc.nextLine();
		member.setMemberAddress(sc.nextLine());
		System.out.print("수정할 취미 입력: ");
		member.setMemberHobby(sc.next());
		return member;
	}

	public void printMessage(String msg) {
		System.out.println(msg);
	}

	public void displaySuccess(String message) {
		System.out.println("[서비스 성공] : " + message);
	}

	public void displayError(String message) {
		System.out.println("[서비스 실패] : " + message);
	}
}
