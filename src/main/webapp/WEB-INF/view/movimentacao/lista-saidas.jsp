<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${url_base}css/bootstrap.min.css">
<title>Saídas</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container">
		<div class="panel panel-custom">
			<div class="panel-heading">
				<span class="session-title">Saídas</span>
				<div class="pull-right">
					<a class="btn btn-success" href="${url_base}saidas/add" role="button">Adicionar</a>
					<a class="btn btn-default" href="${url_base}saidas" role="button">Atualizar</a>
				</div>
				
			</div>
			<div class="panel-body">
				<c:if test="${not empty msg}">
					<div class="alert alert-success alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<p>${msg}</p>
					</div>
				</c:if>
				
				
				<c:if test="${not empty movs}">
				<table class="table table-hover">
					<tr>
						<th>Id</th>
						<th>Observação</th>
						<th>Data</th>
						<th>Ações</th>
					</tr>
					<c:forEach items="${movs}" var="e">
						<tr>
							<td>${e.id}</td>
							<td>${e.obs}</td>
							<td>
							<fmt:parseDate value="${e.data}" pattern="yyyy-MM-dd" var="parseDate"/>
							<fmt:formatDate pattern="dd/MM/yyyy" value="${parseDate}" />
							</td>
							
							<td>
							    <a class="btn btn-primary" href="${url_base}saidas/${e.id}" role="button" >Detalhes</a>
								<a class="btn btn-warning" href="${url_base}saidas/${e.id}/update" role="button" >Editar</a>					
								<a class="btn btn-danger" href="#" data-href="${url_base}saidas/${e.id}/delete" data-toggle="modal" data-target="#confirm-delete">Deletar</a> 
							</td>
						</tr>
					</c:forEach>
				</table>
				</c:if>
				<c:if test="${empty movs}">
				<div class="center-block">
					<p>Não há saídas cadastradas</p>
				</div>
			</c:if>
			</div>
	    </div>
	</div>
	<jsp:include page="../footer.jsp" />
	
	<!-- Modal de confirmação -->
	<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                Deletar Saída
	            </div>
	            <div class="modal-body">
	                <p>Deseja deletar esta saída?</p>
	                <p>Isso deletará também todos os itens associados a ela.</p>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
	                <a class="btn btn-danger btn-ok">Deletar</a>
	            </div>
	        </div>
	    </div>
    </div>
<script src="/js/jquery-3.2.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/validadeNum.js"></script>
<script>
	$('#confirm-delete').on('show.bs.modal', function(e) {
	    $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
	});
</script>
</body>
</html>