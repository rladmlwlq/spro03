package com.keduit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyPageDTO;
import com.keduit.domain.ReplyVO;
import com.keduit.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies")
@Log4j
@RestController
@AllArgsConstructor
public class ReplyController {
	
	
	private ReplyService rps;
	
	@PostMapping(value ="/new", consumes = "application/json",produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		log.info("ReplyVO = " + vo);
		
		int insertCount = rps.register(vo);
		
		log.info("댓글 작성 Count" + insertCount);
		
		return insertCount ==1? new ResponseEntity<>("success" ,HttpStatus.OK): 
			new ResponseEntity<>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@GetMapping(value = "/pages/{bno}/{page}",produces = {MediaType.APPLICATION_XML_VALUE 
				, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyPageDTO>getList(@PathVariable("page") int page
			, @PathVariable("bno") Long bno){
		
		log.info("Reply getList .. " + page +" "+ bno);
		Criteria cri = new Criteria(page , 10);
		log.info(cri);

		
		return new ResponseEntity<>(rps.getListPage(cri,bno), HttpStatus.OK);	
	}
	
//	@GetMapping(value = "/pages/{bno}/{page}",produces = {MediaType.APPLICATION_XML_VALUE 
//			, MediaType.APPLICATION_JSON_VALUE})
//	public ResponseEntity<List<ReplyVO>>getList(@PathVariable("page") int page
//		, @PathVariable("bno") Long bno){
//	
//	log.info("Reply getList .. " + page +" "+ bno);
//	Criteria cri = new Criteria(page , 10);
//	log.info(cri);
//
//	
//	return new ResponseEntity<List<>>(rps.getList(cri,bno), HttpStatus.OK);	
//}
	
	@GetMapping(value ="/{rno}" , produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
		log.info("ReplyGet" + rno);
		return new ResponseEntity<ReplyVO>(rps.get(rno),HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{rno}" ,produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		log.info("Reply Remove" + rno);
		return rps.remove(rno) ==1 ? new ResponseEntity<String>("success",HttpStatus.OK) 
				: new ResponseEntity<String>("fail" ,HttpStatus.INTERNAL_SERVER_ERROR);
				
	}
	
	@RequestMapping(method = {RequestMethod.PUT , RequestMethod.PATCH},
			value ="/{rno}",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo , @PathVariable("rno") Long rno){
		
		vo.setRno(rno);
		log.info("Modify Reply rno -> " + rno);
		log.info("Modify Reply vo -> " + vo );
		return rps.modify(vo) ==1 ? new ResponseEntity<String>("success" , HttpStatus.OK) :
			new ResponseEntity<String>("fail" ,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
}
