package com.cos.blog.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.Script;

public class UsersJoinProcAction implements Action{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 유효성 검사 ,(원래 이메일 형식에 대한 유효성 검사도 해야 함 @ 등등)
		if 
		(
				request.getParameter("username").equals("") ||
				request.getParameter("username")==null ||
				request.getParameter("password").equals("") ||
				request.getParameter("password")==null ||
				request.getParameter("email").equals("") ||
				request.getParameter("email")==null ||
				request.getParameter("address").equals("") ||
				request.getParameter("address")==null
		) {
			return;
		}
		
		// 1. 파라메터 받기(x-www-form-urlencoded 라는 MINE타입 key=value)
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String userRole = RoleType.USER.toString();
		
		// 2. User 오브젝트 변환
		Users user = Users.builder()
				.username(username)
				.password(password)
				.email(email)
				.address(address)
				.userRole(userRole)
				.build();
		
		// 3. DB연결 - UsersRepository의 save() 호출
		UsersRepository usersRepository =
				UsersRepository.getInstance();
		int result = usersRepository.save(user);

		// 4. index.jsp 페이지로 이동
		if (result == 1) {
//			response.sendRedirect("/blog/user?cmd=login"); //거의 안씀
//			Script.href("회원가입에 성공하였습니다.", "/blog/user?cmd=login", response);
			
			Script.href("회원가입에 성공하였습니다.", "/blog/user?cmd=login", response);
		}else {
			Script.back("회원가입에 실패하였습니다.", response);
		}
	}
}
