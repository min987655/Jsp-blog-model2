package com.cos.blog.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.Script;

public class UsersLoginProcAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 유효성 검사 ,(원래 이메일 형식에 대한 유효성 검사도 해야 함 @ 등등)
				if 
				(
						request.getParameter("username").equals("") ||
						request.getParameter("username")==null ||
						request.getParameter("password").equals("") ||
						request.getParameter("password")==null
				) {
					return;
				}
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				
				UsersRepository usersRepository = UsersRepository.getInstance();
				Users user = usersRepository.findByUsernameAndPassword(username, password);
				
				if (user != null) {
					HttpSession session = request.getSession(); // 이미 만들어져있는 세션에 접근
					session.setAttribute("principal", user); // principal : 인증 주체
					
					// 아이디 남기기 체크박스에 체크했을 때 on 날라옴. ==> not null
					// 쿠키에는 쿠키만 add 할 수 있음
					// 쿠키 오브젝트(이름, 값) 띄움
					// 체크박스 체크 안했으 때 else 탐.
					if (request.getParameter("remember") != null) {
//						key => Set-Cookie
//						value => remember=ssar
//						response.setHeader("Set-Cookie","remember=ssar");
						
						Cookie cookie = new Cookie("remember", user.getUsername());
						response.addCookie(cookie);
					} else {
						Cookie cookie = new Cookie("remember", ""); // value 공백
						cookie.setMaxAge(0); // 쿠키 저장시간 0초 (바로 삭제 됨)
						response.addCookie(cookie);
					}
					
					Script.href("로그인 성공", "/blog/board?cmd=home", response);
				}else {
					Script.back("로그인 실패", response);
				}
	}
	
}
