


<%@page import="java.util.List"%>
<%@page import="prova.beans.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<!--  essa página deverá ser a inicial tanto pro administrador quanto pro usuario -->
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
	<div class="container">
		<!--  acesso como usuario -->
		<h2>Acesso de usuario</h2>
		<hr>
		<form action="DoConsultaItens" method="post" role="form">
			<legend>Itens disponíveis para encomenda</legend>
			Busca: <input type="text" name="termoBusca" value="<%= (request.getParameter("termoBusca") == null ? "" : request.getParameter("termoBusca"))%>" />
			<button type="submit" class="btn btn-success">Buscar</button>
		</form>

		<hr>
		<form action="" method="post" role="form">
			<legend>Itens Disponíveis</legend>
		</form>
		<table class="table table-condensed">
			<thead>
				<tr>
					<td>Id</td>
					<td>Nome</td>
					<td>Categoria</td>
					<td>Valor</td>
					<td>Qtd. Disp.</td>
					<td>Comprar</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${itens}">

					<tr>
						<td>${item.iditem}</td>
						<td>${item.nome}</td>
						<td>${item.categoria}</td>
						<td>${item.valor}</td>
						<td>${item.qtd}</td>
						<td>
							<form action="DoAdicionaItem" method="post" role="form">
								<input type="hidden" name="iditem" value="${item.iditem}">
								<input type="number" name="qtd" value="0" min="0" max="${item.qtd}">
								<button type="submit" class="btn btn-success">Add</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- 
		<form action="DoCadastroSolicitacao" method="post">
			<input type="submit" value="Comprar" class="btn btn-success" />
		</form>
		 -->
	</div>
</body>
</html>