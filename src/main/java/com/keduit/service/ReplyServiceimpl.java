package com.keduit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyPageDTO;
import com.keduit.domain.ReplyVO;
import com.keduit.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@Controller
@Log4j
@Service // 서비스임을 알려주는 
public class ReplyServiceimpl implements ReplyService{

	@Setter(onMethod_=@Autowired)
	private ReplyMapper mapper;
	
	@Override
	public int register(ReplyVO vo) {
		log.info("댓글 등록 작업 "+vo);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("댓글 상세 "+rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("댓글 수정 " + vo);
		return mapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		log.info("댓글 삭제" + rno);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("댓글 목록 가져오기" + cri + "" + bno);
		return mapper.getListWithPaging(cri, bno);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		log.info("페이지 처리");
		return new ReplyPageDTO(mapper.getCountByBno(bno)
				, mapper.getListWithPaging(cri, bno));
	}
	
}
