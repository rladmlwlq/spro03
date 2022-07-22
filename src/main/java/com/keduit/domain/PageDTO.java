package com.keduit.domain;

import lombok.Data;

@Data
public class PageDTO {
	private int startPage; // 화면에 보여질 첫 페이지
	private int endPage;	// 화면에 보여질 마지막 페이지 
	private boolean prev,next;
	private int total; 
	private Criteria cri;   // 페이지 넘버 , 어마운트
//	private int realStart =1;
	
	public PageDTO(Criteria cri, int total) {
		this.cri=cri;
		this.total=total;
		
		this.endPage= (int) (Math.ceil(cri.getPageNum() / 10.0) * 10);
		this.startPage =this.endPage-9; 
		// getPageNum으로 가져온 페이지가 5면 5-9 니까 -4가 나오잖아... ?
		int realEnd =(int)(Math.ceil((total * 1.0)/cri.getAmount()));
		
		
		if(realEnd<this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;     // prev = true.
		// 2page 부터는 previus가 있어야 하니까 
		
		this.next = this.endPage < realEnd; // next = true.
		// 마지막 page에서는 next가 없어야 하니까 
		
//		this.first= this.startPage>=1;
		
//		this.last = this.startPage >=1;
		
		
		System.out.println("[" + cri.getPageNum() + "]★");
//		Cloneable.class
//		clone()
		
	}
	
}
// EX) total 151 , pageNum 5 , amount 10  -> ???
