package com.cos.blog.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.Script;

public class UsersUpdateProcAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0번 인증 확인
		HttpSession session = request.getSession();
		if (session.getAttribute("principal") == null) {
			Script.getMessage("잘못된 접근입니다.", response);
			return; // 여기서 return이 없으면 코드 아래를 타고 내려간다.
		}
		
		// 1. request에 title값과 content 값 null 인지 공백 확인
		if 
		(
				request.getParameter("id").equals("") ||
				request.getParameter("id")==null ||
				request.getParameter("password").equals("") ||
				request.getParameter("password")==null ||
				request.getParameter("email").equals("") ||
				request.getParameter("email")==null ||
				request.getParameter("address").equals("") ||
				request.getParameter("address")==null
		) {
			Script.back("입력되지 않은 필드가 있습니다.", response);
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		Users user = Users.builder()
				.id(id)
				.password(password)
				.email(email)
				.address(address)
				.build();
		
		UsersRepository usersRepository = 
				UsersRepository.getInstance();
		int result = usersRepository.update(user);
		
		if (result == 1) {
			Users principal = usersRepository.findById(id);
			session.setAttribute("principal", principal);
			Script.href("수정 성공", "/blog/index.jsp", response);
		} else {
			Script.back("수정에 실패하였습니다.", response);
		}
	}
}
