<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${url_base}css/bootstrap.min.css">
<title>Login</title>
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4">
		<div class="panel panel-custom center-block">
			<div class="panel-heading">
				<h2>Login</h2>
			</div>	
			<div class="panel-body">
				<c:if test="${not empty msg}">
					<div class="alert alert-danger alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<p>${msg}</p>
					</div>
				</c:if>
				
				<jsp:include page="form_login.jsp" />
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
	
<script src="${url_base}js/jquery-3.2.1.min.js"></script>
<script src="${url_base}js/bootstrap.min.js"></script>
</body>
</html>