<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form method="post" action="${url_base}${acao}">
	
	<div class="form-group ${status.error ? 'has-error' : ''}">
		<label for="nome">Nome de Usuario</label>
		<input name="nome" type="text" class="form-control" />
	</div>
	
	<div class="form-group ${status.error ? 'has-error' : ''}">
		<label for="senha">Senha</label>
		<input name="senha" type="password" class="form-control" />
	</div>
	
	<c:if test="${acao eq '/signin'}">
	<button type="submit" class="btn btn-primary">Entrar</button>
	</c:if>
	<c:if test="${acao eq '/usuarios'}">
	<button type="submit" class="btn btn-primary">Confirmar</button>
	</c:if>
</form>
