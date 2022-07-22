
package com.keduit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;
import com.keduit.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@Service
@RequiredArgsConstructor      

public class BoardServiceimpl implements BoardService {

	private final BoardMapper mapper;
	
	@Override
	public Long register(BoardVO bVo) {
		log.info("register - - - - - -" +bVo);
		
		mapper.insertSelectKey(bVo);
	
		return bVo.getBno();
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("get...");
		return mapper.read(bno);
	}

//	@Override
//	public List<BoardVO> getList() {
//		
//		log.info("getList.....");
//		
//		return mapper.getList();
//		
//	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		
		log.info("getList(Criteria cri) ... ... ... ");
				
		return mapper.getListWithPaging(cri);
		
	}

	@Override
	public int modify(BoardVO bVo) {
		log.info("update.....");
		
		return mapper.update(bVo);
	}

	@Override
	public boolean remove(Long bno) {
		
		log.info("Delete ~");
		
		return mapper.getDelete(bno)==1;    
	}

	@Override
	public int getTotalCount(Criteria cri) {
		log.info("getTotalCiunt");
		
		return mapper.getTotalCount(cri);
	}

}
