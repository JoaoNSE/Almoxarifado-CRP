<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="produtosN" value="active" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
<title>Estoque</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container">
		<div class="panel panel-custom">
			<div class="panel-heading">
				<span class="session-title">Estoque</span>
				
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
				
				
				<c:if test="${not empty itens}">
				<table class="table table-hover">
					<tr>
						<th>Produto</th>
						<th>Quantidade</th>
						<th>Unidade</th>
					</tr>
					<c:forEach items="${itens}" var="i">
						<tr>
							<td>${i.produto.nome}</td>
							<td>${i.qtd}</td>
							<td>${i.produto.un}</td>
						</tr>
					</c:forEach>
				</table>
				</c:if>
				<c:if test="${empty itens}">
				<div class="center-block">
					<p>Não há produtos cadastrados</p>
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
	                Deletar Produto
	            </div>
	            <div class="modal-body">
	                Deseja deletar este produto?
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