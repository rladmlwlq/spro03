package com.keduit.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor    //모든 멤버변수를 매개로갖는 생성자
public class ReplyPageDTO {
	private int replyCnt;
	private List<ReplyVO> list;
	
	
}
