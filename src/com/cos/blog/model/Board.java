package com.cos.blog.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
	private int id;
	private int userId;
	private String title;
	private String content;
	private int readCount;
	private Timestamp createDate;
	
//	// 롬북 사용해서 중복됨. getTitel 실행 시 하단 코드 실행 됨.
//	public String getTitle() {
//		// this가 리턴 될 시 계속 . 하여 부를 수 있음.
//		// DB 에서 꺼낼 때 타이틀 양식을 바꿔줌. (넣을 때 바꾸고 싶으면 set만들면 됨)
//		title = title.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
//		return title;
//	}
}
