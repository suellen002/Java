<%@page import="prova.beans.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a href="DoConsultaItens">Inicio</a> | <a href="DoConsultaCarrinho">Meu Carrinho</a> | 

<%
	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
	if (usuario == null) {
%>

<form action="DoLogin" method="post" role="form">
	<legend>Login</legend>

	<div class="form-group">
		<label for="email">Email</label> <input type="text"
			class="form-control" name="email" id="email" placeholder="Email..."
			size="150">
	</div>

	<div class="form-group">
		<label for="senha">Senha</label> <input type="password"
			class="form-control" name="senha" id="senha" placeholder="Senha..."
			size="150">
	</div>
	<br>

	<div class="form-group">
		<button type="submit" class="btn btn-primary">Logar</button>
		&nbsp;&nbsp; <a href="cadastro.html">Ainda não sou cadastrado</a>
	</div>
</form>

<%
	} else {
%>

	<%
		if (usuario.isAdmin()) {
	%>
	<a href="DoConsultaSolicitacoes">Aprovar Solicitações</a> | 
	<a href="cadastrarItem.jsp">Cadastrar Item</a> | 
	<a href="DoRelatorioEstoque">Relatório Estoque</a> | 
	<a href="DoRelatorioSolicitacoes">Relatório Solicitações</a> | 
	<%
		}
	%>
	<%=usuario.getNome()%>
	
	<form action="DoLogout" method="post" role="form">
		<div class="form-group">
			<button type="submit" class="btn btn-primary">Sair</button>
			&nbsp;&nbsp;
		</div>
	</form>

<%
	}
%>
