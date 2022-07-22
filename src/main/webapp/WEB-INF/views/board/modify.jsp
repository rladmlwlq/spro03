<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시글 수정</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">게시글  수정</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-6">
						<form role="form" action="/board/modify" method="post">
							<div class="form-group">
								<label>번호</label> <input class="form-control" name="bno" 
								value='<c:out value="${bVo.bno}"/>' readonly>
							</div>
							<div class="form-group">
								<label>제목</label> <input class="form-control" name="title" 
								value='<c:out value="${bVo.title}"/>' >
							</div>
							<div class="form-group">
								<label>내용</label>
								<textarea class="form-control" rows="3" name="content" >
									<c:out value="${bVo.content}"/>
								</textarea>	
							</div>
							<div class="form-group">
								<label>작성자</label> <input class="form-control"
									placeholder="Enter text" name="writer" 
									value='<c:out value="${bVo.writer}"/>' readonly>
							</div>
							<div class="form-group">
								<label>수정일</label> <input class="form-control"
									placeholder="Enter text" name="updatedate" 
									value='<fmt:formatDate pattern="yyyy/MM/dd" value="${bVo.updatedate}"/>' readonly>
							</div>
							<button type="submit" data-oper="modify" class="btn btn-default">수정</button>
							<button type="submit" data-oper="list" class="btn btn-info">목록</button>
							<button type="submit" data-oper="remove" class="btn btn-danger">삭제</button>
						
							<input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum}"/>'>
							<input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'>
							<input
								type="hidden" name="type"
								value='<c:out value="${cri.type}"/>'> <input
								type="hidden" name="keyword"
								value='<c:out value="${cri.keyword}"/>'>
						</form>
					</div>
					<!-- /.col-lg-6 (nested) -->
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="../includes/footer.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	var formObj = $("form");	//role 의  form
	
	$('button').on("click",function(e){
		e.preventDefault();
		var operation =  $(this).data("oper");   //여기서 this = 버튼
		console.log(operation);
		
		if(operation === "remove"){
			formObj.attr("action","/board/remove"); 
			// form태그의 action을 /board/remove로
			// 지금 form 태그의 action 의 디폴트가 modify임 .	
		}else if(operation ==="list"){
			formObj.attr("action","/board/list").attr("method","get");
			var pageNumTag = $("input[name='pageNum']").clone(); // input name pagenum을 백업

			var amountTag = $("input[name='amount']").clone(); // input name amount를 백업
			
			var typeTag =$("input[name='type']").clone();
			
			var keywordTag =$("input[name='keyword']").clone();
			
			
			formObj.empty();
			formObj.append(pageNumTag);
			formObj.append(amountTag);
			formObj.append(typeTag);
			formObj.append(keywordTag);
			
			//self.location="/board/list"; 
			//얘는 리스트로 걍 돌아가는거니까 sendRedirect처리 비슷하게 한거
			//return			

		}
		formObj.submit();
	});
	
});
</script>
