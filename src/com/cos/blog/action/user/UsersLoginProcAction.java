package com.cos.blog.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
					
					Script.href("로그인 성공", "/blog/board?cmd=home", response);
				}else {
					Script.back("로그인 실패", response);
				}
	}
	
}
