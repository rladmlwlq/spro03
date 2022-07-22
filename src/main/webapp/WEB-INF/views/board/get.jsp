<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
   <div class="col-lg-12">
      <h1 class="page-header">게시글 상세</h1>
   </div>
   <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
   <div class="col-lg-12">
      <div class="panel panel-default">
         <div class="panel-heading">게시글 상세</div>
         <div class="panel-body">
            <div class="row">
               <div class="col-lg-6">
                  <div class="form-group">
                     <label>번호</label> <input class="form-control" name="bno"
                        value='<c:out value="${bVo.bno}"/>' readonly>
                  </div>
                  <div class="form-group">
                     <label>제목</label> <input class="form-control" name="title"
                        value='<c:out value="${bVo.title}"/>' readonly>
                  </div>
                  <div class="form-group">
                     <label>내용</label>
                     <textarea class="form-control" rows="3" name="content" readonly>
                           <c:out value="${bVo.content}" />
                        </textarea>
                  </div>
                  <div class="form-group">
                     <label>작성자</label> <input class="form-control"
                        placeholder="Enter text" name="writer"
                        value='<c:out value="${bVo.writer}"/>' readonly>
                  </div>
                  <button data-oper="modify" class="btn btn-default">수정</button>
                  <button data-oper="list" class="btn btn-default">목록</button>
                  
                  
                  <form id="operForm" action="/board/modify" method="get">
                        <input type="hidden" id="bno" name="bno" value='<c:out value="${bVo.bno}"/>'>
                         <input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum}"/>'>  
                         <input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'>
                         <input type="hidden" name="type" value='<c:out value="${cri.type}"/>'>
                          <input type="hidden" name="keyword" value='<c:out value="${cri.keyword}"/>'>
                  
                  </form>
               </div>
               <!-- /.col-lg-6 (nested) -->
            </div>
				<div class ="panel-heading">
					<i class ="fa fa-comments fa-fw"></i>reply
					<button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">
					New Reply</button> <!-- 이벤트 처리 예정 -->
				</div>
         </div>
      </div>
   </div>
</div>
   
   <!-- 댓글 -->
   <div class="row">
      <div class="col-lg-12">
         <!-- /.panel -->
         <div class="pannel pannel default">
            <i class ="fa fa-comments fa-fw"></i> Reply
         </div>
         
         <!-- /.panel-heading -->
            <div class ="panel-body">
            
               <ul class ="chat">
               <!-- start reply -->
                  <li class="Left clearfix" data-rno='12'>
                  
                  <div class="header">
                  
                     <strong class="primary-font"> user 00 </strong>
                     <small class="pull-right text-muted">2021-07-15 15:18</small>
                  </div>
                  <p>Good job!</p>
                  
                  </div>
               </li>
               <!-- end reply -->
            </ul>
            <!-- end ul -->
      </div>
      <div class="pannel-footer">
      		
      </div> 
      <!-- pannel-footer end -->
   </div>



<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">댓글 등록</h4>
			</div>
			<div class="modal-body">
				<div class ="modal-group">
					<label>댓&emsp;글</label>
					<input class ="form-control" name="reply" value ="new reply">
				</div>
				<div class ="modal-group">
					<label>작성자</label>
					<input class ="form-control" name="replyer" value ="replyer">
				</div>
				<div class ="modal-group">
					<label>작성일</label>
					<input class ="form-control" name="replydate" value ="">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-warning" id="modalModBtn">Modify</button>
				<button type="button" class="btn btn-danger" id="modalRmvBtn">Remove</button>
				<button type="button" class="btn btn-primary" id="modalRegiBtn">Register</button>
				<button type="button" class="btn btn-default" data-dismiss="modal" id="modalCloBtn">Close</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<%@include file="../includes/footer.jsp"%>

<script type ="text/javascript" src="/resources/js/reply.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
						console.log("=========");
						console.log("JS TEST");

						var bnoValue = '<c:out value ="${bVo.bno}"/>';

						var replyUL = $(".chat");

						showList(1);
						
						function showList(page) {
							replyService.getList({bno : bnoValue,page : page || 1},

							function(replyCnt,list) {
								console.log("replyCnt" + replyCnt);
								console.log("list" +list);
								console.log(list);
								if(page == -1){
									pageNum =Math.ceil(replyCnt/10.0);
									showList(pageNum);
									
									return;
								}
								
								var str = "";
								
								if (list == null
										|| list.length == 0) {
									replyUL.html("");
									return;
								}
								for (var i = 0, len = list.length || 0; i < len; i++) {
									str += "<li class='Left clearfix' data-rno='"
         									 +list[i].rno +"'>";
									str += "<div> <div class='header'><strong class='primary-font'>"
											+ list[i].replyer
											+ "</strong>";
									str += "<small class='pull-right text-muted'>"
											+ replyService
													.displayTime(list[i].replydate)
											+ "</small></div>";
									str += "<p>"
											+ list[i].reply
											+ "</p></div></li>";

								}
								replyUL.html(str); //댓글 뿌려주는부분 
								showReplyPage(replyCnt); //댓글에대한 페이지 뿌리는부분
							}); //endfunction
						} //end showList
						
	
						
						
						
		var modal =$(".modal");
		var modalInputReply =modal.find("input[name='reply']");
		var modalInputReplyer = modal.find("input[name='replyer']");
		var modalInputReplydate = modal.find("input[name='replydate']");
		
		var modalModBtn =$("#modalModBtn");
		var modalRmvBtn =$("#modalRmvBtn");
		var modalCloBtn =$("#modalCloBtn");
		var modalRegiBtn =$("#modalRegiBtn");
		
		$('#addReplyBtn').on("click", function(e){ // 신규 입력 댓글인 경우에 
			modal.find("input").val("");
			modalInputReplydate.closest("div").hide(); // 가장 가까운 조상을 hide() 해줘 
			modal.find("button[id !='modalCloBtn']").hide();
			modalRegiBtn.show();
			
			$(".modal").modal("show");
			
		});
		
		modalRegiBtn.on("click",function(e){
			var reply = {
					reply:modalInputReply.val(),
					replyer:modalInputReplyer.val(),
					bno:bnoValue
			};

			replyService.add(reply,function(result){ // 애드 하고 나서 function진행 
				alert(result);
		
				modal.find("input").val("");
				modal.modal("hide"); // register 누르면 사라지게 
				showList(-1); // 새로운 댓글 추가 시에 마지막 페이지를 호출하게된다  
				
			});
		});
		
		$(".chat").on("click","li",function(){  //chat이라는 클래스 안의 li를 누르고 진행->function진행
			var rno = $(this).data("rno");   // rno를 받아옴 (클릭하면 li를)
			
			replyService.get(rno,function(reply){
				modalInputReply.val(reply.reply);
				modalInputReplyer.val(reply.replyer);
				modalInputReplydate.val(replyService.displayTime(reply.replydate)).attr("readonly","readonly");
				modal.data("rno",reply.rno);
				modal.find("button[id != 'modalCloBtn']").hide();
				
				modalModBtn.show();
				modalRmvBtn.show();
				modalInputReplydate.closest("div").show(); // 가장 가까운 조상을 hide() 해줘 
				
				$(".modal").modal("show");  // modalCloBtn,modalModBtn,modalRmvBtn 을 show 해주는
			});
		});
		
		modalModBtn.on("click",function(e){
			var reply={rno:modal.data("rno") , reply:modalInputReply.val()};
		
			replyService.update(reply,function(result){
				alert(result);
				modal.modal("hide");
				showList(pageNum);
				
			});
			
		});
		
		modalRmvBtn.on("click",function(e){
			var rno=modal.data("rno");
			replyService.remove(rno,function(result){
				alert(result);
				modal.modal("hide");
				showList(pageNum);
			
			});
		});
		
		var pageNum=1;
		var replyPageFooter =$(".pannel-footer");
		
		function showReplyPage(replyCnt){
			var endNum= Math.ceil(pageNum/10.0)*10;
			var startNum = endNum-9;
			var prev = startNum != 1;
			var next = false;
			
			if(endNum * 10 >= replyCnt){
				endNum =Math.ceil(replyCnt / 10.0 );
			}
			if(endNum * 10 <replyCnt){
				next = true;
			}
			var str ="<ul class ='pagination pull-right'>";
			if(prev){
				str+="<li class ='page-item'>"+
				"<a class ='page-link' href='"+(startNum-1)+"'>Prev</a></li>";
			}
		    
			for(var i=startNum; i<=endNum; i++){
				var active = pageNum == i?'active':'';
				str += "<li class ='page-item "+active +"'><a class='page-link' href='"+i+"'>"+i+"</a></li>";
			}
				str += "</ul></div>";
				console.log(str);
				replyPageFooter.html(str);
		}
		
		replyPageFooter.on("click","li a",function(e){
			e.preventDefault();
			console.log("click page...");
			
			var targetPageNum = $(this).attr("href");
			console.log("targetPageNum" +targetPageNum);
			pageNum = targetPageNum;
			showList(pageNum);
		
		});		
		
		
		
		//for replyService add Test

						/* replyService.add(
						  {reply:"JS TEST" , replyer:"tester",bno:bnoValue}, //reply
						  
						  function(result){
						     alert("RESULT" + result); // RESULTsuccess
						  } //callback
						
						
						); */

						/*    replyService.getList(
						 {bno:bnoValue , page:1},
						
						 function(list){
						 for(var i =0 , len = list.length || 0;  i<len; i++){
						 console.log(list[i]);
						 }   
						
						 }); */

						/* replyService.remove(51,
						  function(count){
						  console.log(count);
						  if(count === "success"){
						     alert("remove success");
						  }
						}, 
						function(er){
						     alert("remove fail == error");
						}
						
						);   */
						//매개변수 3개 rno , callback, error 
						/*  replyService.update({
						     rno:1,
						     bno:bnoValue,
						     reply:"MODIFYCOMPLETE1"
						  },function(result){
						     alert("수정완료");
						  }
						  
						  ); 
						 */

						/*  replyService.get(18,function(data){
						  console.log("댓글 한 건 읽기  테스트 결과 : " +data);
						});    
						     
						  console.log(replyService); */

					});
</script>


<script type="text/javascript">
$(document).ready(function(){
   var operForm = $("#operForm");
   console.log(operForm.find("input[name='type']").val());
   
   $("button[data-oper='modify']").on('click',function(e){
      operForm.attr("action","/board/modify").submit();   
   });
   
   $("button[data-oper='list']").on('click',function(e){
      operForm.find("#bno").remove();
      operForm.attr("action","/board/list");
      operForm.submit();
   });
   
   
   
});
</script>