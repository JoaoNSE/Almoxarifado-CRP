<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
<title>Cadastrar Usuário</title>
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<div class="container">
		<div class="panel panel-custom">
			<div class="panel-heading">
				<h2>Cadastrar Usuário</h2>
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
				
				<jsp:include page="form_usuario.jsp" />
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
	
<script src="/js/jquery-3.2.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/validadeNum.js"></script>
</body>
</html>