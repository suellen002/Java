<%@page import="java.util.List"%>
<%@page import="prova.beans.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
	<title> Cadastrar Item</title>
	
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet"href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
</head>
<!-- verifica usuario 
    acesso como administrador-->
<body>
	<%@ include file="/WEB-INF/Header.jsp"%>
	<form action="DoCadastroItem" method="post" role='form'>
		Nome: <input type="text" name="nome" value="" /><br/>
		Categoria: <input type="text" name="categoria" value="" /><br/>
		Valor: <input type="text" name="valor" value="" /><br/>
		Quantidade: <input type="text" name="qtd" value="" /><br/>
		<input type="submit" value="Cadastrar" />
	</form>
</body>
</html>