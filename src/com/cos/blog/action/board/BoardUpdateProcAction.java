package com.cos.blog.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Board;
import com.cos.blog.model.Users;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.Script;

public class BoardUpdateProcAction implements Action {
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
				request.getParameter("title").equals("") ||
				request.getParameter("title")==null ||
				request.getParameter("content").equals("") ||
				request.getParameter("content")==null
		) {
			return;
		}
		// 2. request에 title값과 content 값 받기
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 3. title, content, ptincipal.getId(session연결해서 값 받아와야 함) 값을 board 오브젝트에 담기
		Board board = Board.builder()
				.id(id)
				.title(title)
				.content(content)
				.build();
		
		// 4. BoardRepository 연결해서 save(board)함수 호출
		BoardRepository boardRepository = 
				BoardRepository.getInstance();
		int result = boardRepository.update(board);
		
		// 5. result == 1이면 성공로직(index.jsp로 이동)
		// 6. result != 1이면 실패로직(history.back())
		if (result == 1) {
			Script.href("수정 성공", "/blog/board?cmd=detail&id="+id, response);
		} else {
			Script.back("수정에 실패하였습니다.", response);
		}
	}
}
