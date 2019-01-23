<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="/css/header.css">
<div>
	<nav class="navbar navbar-static-top navbar-custom">
	    <div class="container-fluid">
	        <div class="navbar-header">
	            <div class="navbar-brand">
	            	<div class="thumbnail">
                		<img alt="Logo" src="/img/logo.jpg">
                	</div>
            	</div>
	        </div>
	        
	        <c:if test="${usuarioLogado != null}">
	        <ul class="nav navbar-nav">
	            <li><a href="/">Home</a></li>
				<li><a href="/produtos">Produtos</a></li>
				<li role="presentation" class="dropdown">
				    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
				      Estoque <span class="caret"></span>
				    </a>
				    <ul class="dropdown-menu">
				    	<li><a href="/estoque/produtos">Produtos</a></li>
				    	<li><a href="/estoque/validades">Validades</a></li>
				    </ul>
				</li>
				<li><a href="/entradas">Entradas</a></li>
				<li><a href="/saidas">Saídas</a></li>
				<li><a href="/urgente">Urgentes <span class="badge" id="valQtd"></span></a></li>
				<li><a href="/usuarios">Usuários</a></li>
	        </ul>
			
			
			<div class="navbar-right navbar-text" style= "margin-right: 10px;">
				Olá, ${usuarioLogado.nome}.
				<a class="btn btn-danger btn-sm" href="/logout" role="button">Logout</a>
			</div>
			</c:if>
			
			<c:if test="${usuarioLogado == null}">
			<div class="navbar-right navbar-text" style= "margin-right: 10px;">
				<a class="btn btn-default " href="#" role="button" data-toggle="modal" data-target="#cadastro">Cadastrar-se</a>
			</div>
			
			
		   </c:if>
			
	        
	    </div>
	    
	</nav>
	<c:if test="${usuarioLogado == null}">
	<div class="modal fade" id="cadastro" tabindex="-1" role="dialog" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                Cadastro
	            </div>
	            <div class="modal-body">
	                Para cadastrar-se contate um administrador do almoxarifado.
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
	            </div>
	        </div>
	    </div>
   </div>
   </c:if>
</div>
