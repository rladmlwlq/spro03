package com.keduit.mapper;

import java.util.List;

//import org.apache.ibatis.annotations.Select;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

public interface BoardMapper {
//	@Select("select * from t_board where bno > 0") XML에 기입되어있는 쿼리문
	public List<BoardVO> getList();
	
//	public int getInsert(BoardVO bVo);  //  이렇게도 한다 보통은. 
	
	public void getInsert(BoardVO bVo);
	
	public void insertSelectKey(BoardVO bVo);
	
	public BoardVO read(Long bno);
	
	public int getDelete(Long bno);

	public int update(BoardVO bVo);
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public int getTotalCount(Criteria cri);
}
