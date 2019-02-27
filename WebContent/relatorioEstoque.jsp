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
		<h2>Relatório Estoque</h2>
		<hr>
		<table class="table table-condensed">
			<thead>
				<tr>
					<td>Id</td>
					<td>Nome</td>
					<td>Categoria</td>
					<td>Valor</td>
					<td>Qtd. Disp.</td>
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
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		
		<input type="button" value="Imprimir" class="btn btn-success" onclick="print();" />
		
		
	</div>
</body>
</html>