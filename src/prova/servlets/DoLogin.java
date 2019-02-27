package prova.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prova.beans.Solicitacao;
import prova.beans.Usuario;
import prova.dao.SolicitacoesDAO;
import prova.dao.UsuarioDAO;

import prova.servlets.DoInicio;

/**
 * Servlet implementation class DoLogin
 */
@WebServlet("/DoLogin")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = null;

		try {

			if(request.getSession().getAttribute("usuarioLogado") == null) {
			usuario = new UsuarioDAO().buscaPorEmailESenha(request.getParameter("email"),
					request.getParameter("senha"));
			}else {
				usuario = (Usuario)request.getSession().getAttribute("usuarioLogado");
			}
			
			if (usuario == null) {
				// nao encontrou usuario com a senha ou ela é inválida

				response.sendRedirect("index.html");

			} else {
				// usuário encontrado e senha válida

				request.getSession().setAttribute("usuarioLogado", usuario);

				request.setAttribute("isadmin", usuario.isAdmin());

				//response.sendRedirect("DoConsultaItens");
				//RequestDispatcher view = request.getRequestDispatcher("inicio.jsp");
				//view.forward(request, response);
				
				if(usuario.isAdmin()) {
					
					response.sendRedirect("DoConsultaSolicitacoes");

				}else {
					
					response.sendRedirect("DoConsultaItens");

				}

			}

		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(DoLogin.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(ex.getMessage());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
