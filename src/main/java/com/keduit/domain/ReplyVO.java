package com.keduit.domain;

import java.util.Date;


import lombok.Data;

@Data
public class ReplyVO {
	
	private Long rno;
	private Long bno;
	private String reply;
	private String replyer;
	private Date replydate;
	private Date updatedate;

	// t_reply 테이블 
}

//create table t_board(
//bno number(10,0),
//title varchar2(200) not null,
//content VARCHAR2(2000) not null,
//writer varchar2(50) not null,
//regdate date default sysdate,
//updatedate date default sysdate,
//primary key(bno)
//);