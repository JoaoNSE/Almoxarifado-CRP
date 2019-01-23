<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form:form method="post" modelAttribute="mov" action="${url_base}${acao}">
	<div class="row">
		<form:input path="id" type="hidden"  />
		<form:input path="tipo" type="hidden" />
		
		<div class="col-md-4">
			<spring:bind path="obs">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<form:label path="obs">Observação</form:label>
				<form:textarea path="obs" rows="5" cssClass="form-control" />
				<form:errors path="obs" />
			</div>
			</spring:bind>
		</div>
		
		<div class="col-md-4">
		<spring:bind path="data">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<form:label path="data">Data</form:label>
			<form:input path="data" type="text" cssClass="form-control datepicker"/>
			<form:errors path="data" />
		</div>
		</spring:bind>		
		<button type="submit" class="btn btn-primary">Confirmar</button>
		</div>
		
		<c:forEach items="${mov.itens}" varStatus="vs" var="i">
				<form:input path="itens[${vs.index}].id" type="hidden"/>
				<form:input path="itens[${vs.index}].produto.id" type="hidden"/>		
				<form:input path="itens[${vs.index}].qtd" type="hidden" />
				<form:input path="itens[${vs.index}].validade" type="hidden"/>
				<form:input path="itens[${vs.index}].movimentacao" type="hidden"/>						
		</c:forEach>
	</div> <!-- .row -->
</form:form>