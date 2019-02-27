<%@page import="java.util.List"%>
<%@page import="prova.beans.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<!--  essa página deverá ser a inicial tanto pro administrador quanto pro usuario -->
<head>
<title>Meu Carrinho</title>

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
		<h2>Meu Carrinho</h2>
		<hr>
		<form action="" method="post" role="form">
			<legend>Itens no Carrinho</legend>
		</form>
		<table class="table table-condensed">
			<thead>
				<tr>
					<td>Nome</td>
					<td>Categoria</td>
					<td>Valor</td>
					<td>Qtd.</td>
					<td>Valor</td>
					<td>Remover</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${itens}">

					<tr>
						<td>${item.nome}</td>
						<td>${item.categoria}</td>
						<td>${item.valor}</td>
						<td>${item.qtd}</td>
						<td>${item.valor * item.qtd}</td>
						<td>
							<form action="DoRemoveItem" method="post" role="form">
								<input type="hidden" name="iditem" value="${item.iditem}">
								<button type="submit" class="btn btn-danger">Remove</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<form action="DoCadastroSolicitacao" method="post">
			<input type="submit" value="Comprar" class="btn btn-success" />
		</form>
	</div>
</body>
</html>