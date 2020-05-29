package com.cos.blog.test;

import org.junit.Test;

import com.cos.blog.db.DBConn;

public class DBConnTest {
	
	@Test
	public void 데이터베이스_연결_테스트() {
	//톰켓이 메모리에 정보를 띄워줘야 동작 됨
		DBConn.getConnection();
	}
}