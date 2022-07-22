package com.keduit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyVO;

public interface ReplyMapper {
	
	public int insert(ReplyVO vo);
	
	public ReplyVO read(Long bno); //bno로 읽어야지 
	
	public int delete(Long rno);
	
	public int update(ReplyVO vo);
	
	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri
			, @Param("bno") Long bno);
	
	public int getCountByBno(Long bno);
	
}
//Mybatis의 SQL 문장에 다수의 파라미터를 전달할 때는 
	//전달되는 변수들에 꼭 @Param 어노테이션을 붙여줘야한다.