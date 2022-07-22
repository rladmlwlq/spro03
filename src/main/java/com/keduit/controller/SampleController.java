package com.keduit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.keduit.domain.SampleVO;
import com.keduit.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j

public class SampleController {
	@GetMapping(value = "/getText", produces = "text/plain; charset=utf-8")
	public String getText() {
		log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);

		return "반갑습니다";
	}

	@GetMapping(value = "/getSample", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public SampleVO getSample() {
		return new SampleVO(1004, "스타", "로드");
	}

	@GetMapping(value = "/getSample2")
	public SampleVO getSample2() {

		return new SampleVO(1005, "로드", "스타");
	}

	@GetMapping(value = "/getList")
	public List<SampleVO> getList() {

		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i + "first", i + "last"))
				.collect(Collectors.toList());
	}

	@GetMapping(value = "/getMap")

	public Map<String, SampleVO> getMap() {
		Map<String, SampleVO> map = new HashMap<String, SampleVO>();
		map.put("FIRST", new SampleVO(1007, "수박", "딸기"));
		return map;
	}
	
	@GetMapping(value="/check" , params= {"height","weight"})
	public ResponseEntity<SampleVO> check(Double height,Double weight) {
	
		SampleVO vo = new SampleVO(0,""+height,""+weight);
		
		ResponseEntity<SampleVO> result=null;
		
		if(height<150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
			
		}
		
		return result; //ResponseEntity 타입을  리턴하면  메시지바디를 주는거다
	}
	
//	@ResponseStatus(HttpStatus.ACCEPTED)
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(
		@PathVariable  String cat, // 파라미터와 변수이름이 같으면 생략가능하긴함 
		@PathVariable("pid") Integer pid) {
		
		return new String[] {"category" + cat , "productid" +pid};
		
	}
	
	@PostMapping("/ticket")
	
	public Ticket convert(@RequestBody Ticket ticket){
		log.info(ticket);
		return ticket;
	}
	
}

//Content-Type: application/xml

//Content-Type: application/xhtml+xml

//{"FIRST":{"firstName":"수박","lastName":"딸기","mno":1007}}

//[{"firstName":"1first","lastName":"1last","mno":1}
