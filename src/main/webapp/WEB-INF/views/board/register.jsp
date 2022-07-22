<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시글 작성</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Basic Form Elements</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-6">
						<form role="form" action="/board/register" method="post">
							<div class="form-group">
								<label>제목</label> <input class="form-control" name="title">
							</div>
							<div class="form-group">
								<label>내용</label>
								<textarea class="form-control" rows="3" name="content"></textarea>
							</div>
							<div class="form-group">
								<label>작성자</label> <input class="form-control"
									placeholder="Enter text" name="writer">
							</div>
							<button type="submit" class="btn btn-default">글 쓰기</button>
							<button type="reset" class="btn btn-default">다시 작성</button>
						</form>
					</div>
					<!-- /.col-lg-6 (nested) -->
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="../includes/footer.jsp"%>
