<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form:form method="post" modelAttribute="item" action="${url_base}${acao}">
	<form:input path="id" type="hidden"/>
	<form:input path="movimentacao.id" type="hidden"/>
	
	<spring:bind path="produto">
	<div class="form-group ${status.error ? 'has-error' : ''}">
		<form:label path="produto">Id do Produto</form:label>
		<form:select path="produto" cssClass="selectpicker">
			<c:forEach var="p" items="${produtos}">					
				<form:option value="${p.id}">${p.nome}</form:option>
			</c:forEach>
		</form:select>
		<!--<form:input path="produto" type="number" cssClass="form-control" /> -->
		<form:errors path="produto" />
	</div>
	</spring:bind>
	
	<spring:bind path="qtd">
	<div class="form-group ${status.error ? 'has-error' : ''}">
		<form:label path="qtd">Quantidade</form:label>
		<form:input path="qtd" type="number" cssClass="form-control" />
		<form:errors path="qtd" />
	</div>
	</spring:bind>
	
	<spring:bind path="validade">
	<div class="form-group ${status.error ? 'has-error' : ''}">
		<form:label path="validade">Validade</form:label>
		<form:input path="validade" type="text" cssClass="form-control datepicker"/>
		<form:errors path="validade" />
	</div>
	</spring:bind>		
	
	
	
	<button type="submit" class="btn btn-primary">Confirmar</button>
</form:form>
