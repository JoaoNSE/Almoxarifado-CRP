<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/bootstrap-datepicker.min.css">
<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
<title>Adicionar Saída</title>
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<div class="container">
	<div class="panel panel-custom">
		<div class="panel-heading">
			<h2>Adicionar Saída</h2>
		</div>
		<div class="panel-body">
			<jsp:include page="form_entrada.jsp" />
		</div>
	</div>
	</div>
	<jsp:include page="../footer.jsp" />
	
<script src="/js/jquery-3.2.1.min.js"></script>
<script src="/js/bootstrap-datepicker.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/validadeNum.js"></script>

<script>
$(document).ready(function () {
    $('.datepicker').datepicker({
        format: "dd/mm/yyyy",
        language: "pt-BR"
    });
  });
</script>
</body>
</html>