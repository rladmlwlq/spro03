package com.keduit.service;

import java.util.List;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyPageDTO;
import com.keduit.domain.ReplyVO;

public interface ReplyService {
	
	public int register(ReplyVO vo);

	public ReplyVO get(Long rno);   // PK주고 vo받고

	public int modify(ReplyVO vo);
	
	public int remove(Long rno);

    public List<ReplyVO> getList(Criteria cri, Long bno); //전체목록 읽어야하니 bno
    
    public ReplyPageDTO getListPage(Criteria cri ,Long bno);

}
