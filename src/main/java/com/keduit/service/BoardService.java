package com.keduit.service;

import java.util.List;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

public interface BoardService {
	
	public Long register(BoardVO bVo);
	
	public BoardVO get(Long bno); 

//	public List<BoardVO> getList();  
	
	public List<BoardVO> getList(Criteria cri);  

	public int modify(BoardVO bVo); 
	
	public boolean remove(Long bno); 
	
	public int 	getTotalCount(Criteria cri);
	
}
