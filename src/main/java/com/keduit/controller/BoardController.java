package com.keduit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;
import com.keduit.domain.PageDTO;
import com.keduit.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@Log4j
@RequestMapping("/board/*")
public class BoardController {
	private final BoardService service;
	
//	@GetMapping("/list")
//	public void list(Model model) {
//		log.info("list. . .");
//		model.addAttribute("list",service.getList());
//		 
//		
//	}
	
//	@GetMapping("/list")
//	public void list(Model model) {
//		log.info("list. . .");
//		model.addAttribute("list",service.getList(new Criteria()));
//		
//		
//	}
	
	@GetMapping("/list")
	public void list(Model model ,Criteria cri) {
		log.info("list. . .");
		log.info("["+cri.getType() +"]");
		model.addAttribute("list",service.getList(cri));
		int total = service.getTotalCount(cri);
		log.info(total);
		model.addAttribute("pageMaker" ,new PageDTO(cri,total));

		
	}
	
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	
	@PostMapping("/register")
	public String register(BoardVO bVo ,RedirectAttributes ras) {
		log.info("register . . ." + bVo);
	
		Long bno = service.register(bVo);
		log.info("bno : " + bno);

		ras.addFlashAttribute("result" , bVo.getBno());
		return "redirect:/board/list";
	}
	
//	@GetMapping("/get")
//	public void get(@RequestParam("bno") Long bno, Model model) {
//		log.info("/get...");
//		model.addAttribute("bVo" , service.get(bno));
//	}
	
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno , Model model ,@ModelAttribute("cri") Criteria cri) {
		log.info("/get || /modify. ..");
		model.addAttribute("bVo" ,service.get(bno));
		
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bVo , RedirectAttributes ras, @ModelAttribute("cri") Criteria cri) {
		log.info("modify..." +bVo);
		int cnt = service.modify(bVo);
		if(cnt==1) {
			ras.addFlashAttribute("result" , "수정 성공");
		}
		
//		ras.addAttribute("pageNum",cri.getPageNum());
//		ras.addAttribute("amount",cri.getAmount());
//		ras.addAttribute("type" ,cri.getType());
//		ras.addAttribute("keyword",cri.getKeyword()); Criteria에 메소드만들어버림걍
		
		return "redirect:/board/list" +cri.getListLink();		
		
	}
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes ras, @ModelAttribute("cri") Criteria cri) {
		log.info("remove...");
		
		if(service.remove(bno)) {
			ras.addFlashAttribute("result" ,"삭제 성공");
		}
//			ras.addAttribute("pageNum",cri.getPageNum());
//			ras.addAttribute("amount",cri.getAmount());
//			ras.addAttribute("type" ,cri.getType());
//			ras.addAttribute("keyword",cri.getKeyword());
			
			
		return "redirect:/board/list" + cri.getListLink();
	}
	
}
