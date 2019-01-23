<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
<title>Detalhes da Entrada</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container">
		<div class="panel panel-custom">
			<div class="panel-heading">
				<span class="session-title">Detalhes da Entrada</span>
				<div class="pull-right">
					<a class="btn btn-warning" href="/entradas/${mov.id}/update" role="button" >Editar</a>					
					<a class="btn btn-danger" href="#" data-href="/entradas/${mov.id}/delete" data-toggle="modal" data-target="#confirm-delete">Deletar</a>
				</div>
			</div>
			<div class="panel-body">
				<c:if test="${not empty mov}">
				<div class="panel panel-custom">
				<div class="panel-body">
				<div class="row">
				
					<div class="col-md-4">
				    	<p><strong>Código</strong></p>
				    	<p>${mov.id}</p>
				 	</div>
				 	<div class="col-md-4">
				    	<p><strong>Observação</strong></p>
				    	<p>${mov.obs}</p>
				 	</div>
					<div class="col-md-4">
						<p><strong>Data</strong></p>
						
						<p>
						<fmt:parseDate value="${mov.data}" pattern="yyyy-MM-dd" var="parseDate"/>
						<fmt:formatDate pattern="dd/MM/yyyy" value="${parseDate}" />
						</p>
					</div>
				
				</div> <!-- .row -->
				</div> <!-- panel body -->
				</div> <!-- panel -->
				<div class="row">
					<div class = "col-md-12">
						<div class="panel panel-custom">
						<div class="panel-body">
						<p><strong>Produtos</strong></p>
						<c:if test="${not empty mov.itens}">
							<table class="table table-hover">
								<tr>
									<th>Id</th>
									<th>Produto</th>
									<th>Quantidade</th>
									<th>Validade</th>
								</tr>
								<c:forEach items="${mov.itens}" var="i">
									<tr>
										<td>${i.id}</td>
										<td>${i.produto.nome}</td>
										<td>${i.qtd}</td>
										
										<td>
											<fmt:parseDate value="${i.validade}" pattern="yyyy-MM-dd" var="parseDate"/>
											<fmt:formatDate pattern="dd/MM/yyyy" value="${parseDate}" />
										</td>
									</tr>
								</c:forEach>
							</table>
						</c:if>
						</div> <!-- panel-body -->
						</div> <!-- panel -->
					</div>
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
	                Deletar Entrada
	            </div>
	            <div class="modal-body">
	                <p>Deseja deletar esta entrada?</p>
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