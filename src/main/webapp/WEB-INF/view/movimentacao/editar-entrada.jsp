<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/bootstrap-datepicker.min.css">
<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
<title>Editar Entrada</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container">
		<div class="panel panel-custom">
			<div class="panel-heading">
				<span class="session-title">Editar Entrada</span>
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
				<jsp:include page="form_entrada.jsp" />
				
				
				<div class="row">
					<div class = "col-md-12">
						<div class="panel panel-custom">
						<div class="panel-heading">
							<h4><strong>Produtos</strong>
							<a class="btn btn-success pull-right" href="${url_base}/entradas/${movId}/itens/add" role="button">Adicionar Produto</a>
							</h4>
						</div>
						<div class="panel-body">
						
						<c:if test="${not empty mov.itens}">
							<table class="table table-hover">
								<tr>
									<th>Id</th>
									<th>Produto</th>
									<th>Quantidade</th>
									<th>Validade</th>
									<th>Ações</th>
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
										<td>
											<a class="btn btn-warning" href="${url_base}/entradas/${mov.id}/itens/${i.id}/update" role="button" >Editar</a>	
											
											<a class="btn btn-danger" href="#" data-href="${url_base}/entradas/${mov.id}/itens/${i.id}/delete" data-toggle="modal" data-target="#confirm-delete">Remover</a>
										</td>
									</tr>
								</c:forEach>
							</table>
						</c:if>
						<c:if test="${empty mov.itens}">
							Não há produtos nessa entrada.
						</c:if>
						</div> <!-- panel-body -->
						</div> <!-- panel -->
					</div>
				</div>
				
			</div> <!-- Panel Body Principal -->
	    </div>
	</div>
	<jsp:include page="../footer.jsp" />
	<!-- Modal de confirmação -->
	<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                Deletar Item
	            </div>
	            <div class="modal-body">
	                <p>Deseja deletar este item?</p>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
	                <a class="btn btn-danger btn-ok">Deletar</a>
	            </div>
	        </div>
	    </div>
    </div>
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
<script>
	$('#confirm-delete').on('show.bs.modal', function(e) {
	    $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
	});
</script>
</body>
</html>