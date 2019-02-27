<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <title>Lista de Contatos</title>
    </head>
    <body>

        <br>
        <div class="container">


            <p> Lista de contatos </p>

            <hr>

            <p> Novo contato </p>
            <form action="controller" method="POST">
                <input type="hidden" name="tarefa" value="NovoContato">
                Nome: <input type="text" name="nome"/>
                Email: <input type="email" name="email"/>

                <input type="submit" name="salvar" value="salvar"/>
            </form>

            <br>

            <form action="controller" method="GET">
                <input type="hidden" name="tarefa" value="BuscaContato">
                Buscar contato: <input type="text" name="filtro">
                <input type="submit" value="buscar contato!">
            </form>
            
            <br>

            <form action="controller" method="POST">
                <input type="hidden" name="tarefa" value="Logout">
                <input type="submit" value="Logout">
            </form>
            
            <form action="controller" method="POST">
                <input type="hidden" name="tarefa" value="BuscaContato">
                <input type="hidden" name="metodo" value="metodoTeste">
                <input type="submit" value="Chamar metodo"> 
            </form>
            

        </div>
        <script src="http://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
        <!-- Latest compiled and minified JS -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    </body>
</html>
