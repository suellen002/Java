


<%@page import="java.util.List"%>
<%@page import="prova.beans.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<title>Solicitações</title>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
</head>
<!-- verifica usuario 
    acesso como administrador-->
<body>
	<%@ include file="/WEB-INF/Header.jsp"%>
	<% 
	String periodo = "7";
	if(request.getAttribute("cboPeriodo") != null)
		periodo = request.getAttribute("cboPeriodo").toString();
	%>
	<div class="container">
		<%
			if (usuario == null ? false : usuario.isAdmin()) {
		%>
		<h2>Relatório de Solicitações</h2>
		<hr>
		<form action="DoRelatorioSolicitacoes" method="post" role="form">
			<legend>Período</legend>
			<select name="cboPeriodo">
				<option value="7" <%= (periodo.equals("7") ? "selected" : "")%>>última semana</option>
				<option value="15" <%= (periodo.equals("15") ? "selected" : "")%>>últimos 15 dias</option>
				<option value="30" <%= (periodo.equals("30") ? "selected" : "")%>>último mês</option>
				<option value="365" <%= (periodo.equals("365") ? "selected" : "")%>>último ano</option>
				<option value="-1" <%= (periodo.equals("-1") ? "selected" : "")%>>Tudo</option>
			</select>
			<button type="submit" class="btn btn-success">Gerar</button>
		</form>
		<hr>
		<table class="table table-condensed">
			<thead>
				<tr>
					<td>Id</td>
					<td>Data</td>
					<td>Solicitante</td>
					<td>Items</td>
					<td>Status</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="solicitacao" items="${solicitacoes}">

					<tr>
						<td>${solicitacao.idsol}</td>
						<td>${solicitacao.data}</td>
						<td>${solicitacao.usuario.nome}</td>
						<td><c:forEach var="item" items="${solicitacao.items}">
								<p>Item: ${item.nome}, Categoria: ${item.categoria} , Qtde:
									${item.qtd}</p>
							</c:forEach></td>

						<td><c:if test="${solicitacao.status==1}">
							        Atendida
							    </c:if> <c:if test="${solicitacao.status==0}">

								<form action="DoAprovacaoSolicitacao" method="post" role="form">
									<input type="hidden" name="idsol" value="${solicitacao.idsol}">
									<button type="submit" class="btn btn-success">Atender
									</button>
								</form>

							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<input type="button" value="Imprimir" class="btn btn-success" onclick="print();" />
		
		<%
			}
		%>
	</div>
</body>
</html>