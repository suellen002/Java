<%@page import="java.util.Collection"%>
<%@page import="java.util.List"%>
<%@page import="prova.beans.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
</head>
<body>
	<%@ include file="/WEB-INF/Header.jsp"%>
	<div class="container">
	<%
			if (usuario == null ? false : usuario.isAdmin()) {
		%>
		<h2>Acesso de administrador</h2>
		<hr>
		<form action="" method="post" role="form">
			<legend>Solicitações recebidas</legend>
		</form>
		<table class="table table-condensed">
			<thead>
				<tr>
					<td>Id</td>
					<td>Solicitante</td>
					<td>Items</td>
					<td>Status</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="solicitacao" items="${solicitacoes}">

					<tr>
						<td>${solicitacao.idsol}</td>
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
		<%
			}
		%>
	</div>

</body>
</html>