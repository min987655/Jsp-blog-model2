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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UsersProfileUploadProcAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("image");
		// 디비에 경로를 저장하기위한 코드.
		int id;
		String fileName = null;
		String contextPath = request.getServletContext().getContextPath(); 
		String userProfile = null; // DB에 들어갈 변수
		
		System.out.println("realPath : " + realPath);
		System.out.println("contextPath : " + contextPath);
		
		try {
			MultipartRequest multi = new MultipartRequest // 파일만 받을 수 있음
					(
							request, 
							realPath,
							1024*1024*2,
							"UTF-8",
							new DefaultFileRenamePolicy() //파일명에 UUID 붙이면 이 코드 안써도 됨
					);
			fileName = multi.getFilesystemName("userProfile");
			System.out.println("fileName : " + fileName);
			id = Integer.parseInt(multi.getParameter("id"));
			
			userProfile = contextPath+"/image/"+fileName;
			
			UsersRepository usersRepository = 
					UsersRepository.getInstance();
			int result = usersRepository.update(id, userProfile);
			
			if (result ==1) {
				HttpSession session = request.getSession();
				Users principal = usersRepository.findById(id);
				session.setAttribute("principal", principal);
				
				Script.href("사진 변경 완료", "/blog/index.jsp", response);
			} else {
				Script.back("사진 변경 실패", response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Script.getMessage("오류 : " + e.getMessage(), response);
		}
	}
}
