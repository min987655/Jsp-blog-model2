package com.cos.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.db.DBConn;
import com.cos.blog.dto.ReplyResponseDto;
import com.cos.blog.model.Reply;


// DAO 
public class ReplyRepository {
	// 싱글톤
	private static final String TAG = "ReplyRepository : ";
	private static ReplyRepository instance = new ReplyRepository();
	private ReplyRepository() {}
	public static ReplyRepository getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public int save(Reply reply) {
		final String SQL = "INSERT INTO reply(id, boardId, userId, content, createDate) VALUES(reply_seq.nextval, ?, ?, ?, sysdate)";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setInt(1, reply.getBoardId());
			pstmt.setInt(2, reply.getUserId());
			pstmt.setString(3, reply.getContent());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "save : " +e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}
		return -1;
	}
	
	public int update(Reply reply) {
		final String SQL = "";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "update : " +e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}
		return -1;
	}
	
	public int deleteById(int id) {
		final String SQL = "";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "deleteById : " +e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}
		return -1;
	}
	
	public List<ReplyResponseDto> findAll(int boardId) {
		/* System.out.println("boardId : "+boardId); */
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT r.id, r.userId, r.boardId, r.content, r.createDate, ");
		sb.append("u.username, u.userProfile ");
		sb.append("FROM reply r INNER JOIN users u ");
		sb.append("ON r.userid = u.id ");
		sb.append("WHERE boardId = ?");
		sb.append("ORDER BY r.id DESC");
		final String SQL = sb.toString();
		List<ReplyResponseDto> replyDtos = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setInt(1, boardId);
			rs = pstmt.executeQuery();
			
			// while 돌려서 rs -> java 오브젝트에 집어넣기
			while(rs.next()) {
				System.out.println("안타지?");
				Reply reply = Reply.builder()
						.id(rs.getInt(1))
						.userId(rs.getInt(2))
						.boardId(rs.getInt(3))
						.content(rs.getString(4))
						.createDate(rs.getTimestamp(5))
						.build();
				ReplyResponseDto replyDto = ReplyResponseDto.builder()
							.reply(reply)
							.username(rs.getString(6))
							.userProfile(rs.getString(7))
							.build();
				replyDtos.add(replyDto);
				System.out.println("replyDto : " +replyDto);
			}
			return replyDtos;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll(boardId) : " +e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public List<Reply> findAll() {
		final String SQL = "";
		List<Reply> replys = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			
			// while 돌려서 rs -> java 오브젝트에 집어넣기
			return replys;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " +e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public Reply findById(int id) {
		final String SQL = "";
		Reply replys = new Reply();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			
			// if 돌려서 rs -> java 오브젝트에 집어넣기
			return replys;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " +e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}
	
}