<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form:form method="post" modelAttribute="usuario" action="${url_base}${acao}">
	<form:input path="id" type="hidden"  />
	
	<spring:bind path="nome">
	<div class="form-group ${status.error ? 'has-error' : ''}">
		<form:label path="nome">Nome de Usuario</form:label>
		<form:input path="nome" type="text" cssClass="form-control" />
		<form:errors path="nome" />
	</div>
	</spring:bind>
	
	<spring:bind path="senha">
	<div class="form-group ${status.error ? 'has-error' : ''}">
		<form:label path="senha">Senha</form:label>
		<form:input path="senha" type="password" cssClass="form-control" />
		<form:errors path="senha" />
	</div>
	</spring:bind>
	
	<c:if test="${acao eq '/login'}">
	<button type="submit" class="btn btn-primary">Entrar</button>
	</c:if>
	<c:if test="${acao eq '/usuarios'}">
	<button type="submit" class="btn btn-primary">Confirmar</button>
	</c:if>
</form:form>
