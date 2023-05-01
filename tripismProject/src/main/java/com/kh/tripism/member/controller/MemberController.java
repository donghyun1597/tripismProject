package com.kh.tripism.member.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MemberController {

	@RequestMapping("idfind.do")
	public String EnrollMemberForm() {
		return "member/idFind";
	}
	
	@RequestMapping("idResult.do")
	public String idResult() {
		return "member/idResult";
	}
	
	@RequestMapping("pwdFind.do")
	public String pwdFind() {
		return "member/pwdFind";
	}
	
	@RequestMapping("pwdAuthentication.do")
	public String pwdAuthentication() {
		return "member/pwdAuthentication";
	}
	
	@RequestMapping("pwdResult.do")
	public String pwdResult() {
		return "member/pwdResult";
	}
	
	@RequestMapping("mypage.bo")
	public String mypage() {
		return "member/myPage";
	}
	
	@RequestMapping("enrollForm.do")
	public String enrollForm() {
		return "member/enrollForm";
	}

	
}
