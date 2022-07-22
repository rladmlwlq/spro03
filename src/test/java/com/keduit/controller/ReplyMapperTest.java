package com.keduit.controller;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyVO;
import com.keduit.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j

public class ReplyMapperTest {
	
	private Long[] bnoArr= {15331L,15330L,15329L,15319L,15323L,15325L};
	
	@Setter(onMethod_ =@Autowired)
	private ReplyMapper mapper;

	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	
	@Test
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i -> { // closed 쓰면 <= 임
			ReplyVO vo = new ReplyVO();
			
			vo.setBno(bnoArr[i % 5]); //1,2,3,4,0,1,2,3,4,0
			vo.setReply("댓글테스트" + i );
			vo.setReplyer("댓글 작성자 " + i );
			mapper.insert(vo);
			log.info("["+i % 5 +"]");
		});
	}
	
	@Test
	public void testRead() {
		Long rno = 18L ;
		ReplyVO vo = mapper.read(rno);
		log.info(vo);
		
	}

	@Test
	public void testDelete() {
		Long rno=49L;
		
		int result  = mapper.delete(rno);
		
		log.info("["+result+"]");
	}
	
	
	//Long ==nullable  long =not null
	@Test
	public void testUpdate() { 
	//update 같은 경우는 오라클이 잡고있으면 충돌이 날 수있으니 끄거나 오라클에서 커밋을 해주어야함
		Long rno= 18L;
		
		ReplyVO vo = mapper.read(rno);
		
		vo.setReply("안녕 업데이트 리플리 테스트야");
		
		int result = mapper.update(vo);
		
		log.info(result);
		
		vo =mapper.read(rno);
		log.info(vo +"업데이트 후 결과화면");
		
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		List<ReplyVO> replies= mapper.getListWithPaging(cri, bnoArr[2]);
		replies.forEach(reply -> log.info(reply));
		
	}
	@Test
	public void testList2() {
		Criteria cri = new Criteria();
		List<ReplyVO> replies= mapper.getListWithPaging(cri, 15425L);
		replies.forEach(reply -> log.info(reply));
		
	}
	
}
//15331	
//15330	
//15329	
//15328	
//15325	15323	
//15322	
//15321	15320	
//15319	