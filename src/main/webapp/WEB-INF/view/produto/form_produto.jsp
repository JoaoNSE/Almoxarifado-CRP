<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form method="post" modelAttribute="produto" action="${url_base}${acao}">
	<form:input path="id" type="hidden"  />
	
	<spring:bind path="nome">
	<div class="form-group ${status.error ? 'has-error' : ''}">
		<form:label path="nome">Nome</form:label>
		<form:input path="nome" type="text" cssClass="form-control" />
		<form:errors path="nome" />
	</div>
	</spring:bind>
	
	<spring:bind path="un">
	<div class="form-group ${status.error ? 'has-error' : ''}">
		<form:label path="un">Unidade de Medida</form:label>
		<form:input path="un" type="text" cssClass="form-control" />
		<form:errors path="un" />
	</div>
	</spring:bind>
	
	<button type="submit" class="btn btn-primary">Confirmar</button>
</form:form>
