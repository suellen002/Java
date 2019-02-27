package prova.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prova.beans.Item;
import prova.beans.Solicitacao;
import prova.beans.Usuario;
import prova.dao.ItensDAO;
import prova.dao.SolicitacoesDAO;

/**
 * Servlet implementation class DoCadastroSolicitacao
 */
@WebServlet("/DoCadastroSolicitacao")
public class DoCadastroSolicitacao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoCadastroSolicitacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
			if (usuario == null) {
				response.sendRedirect("DoConsultaItens");
				return;
			}
			
			Solicitacao solicitacao = (Solicitacao) request.getSession().getAttribute("solicitacao");
			if (solicitacao == null) {
				response.sendRedirect("DoConsultaItens");
				return;
			}

			solicitacao.setUsuario(usuario);
			
			SolicitacoesDAO dao = new SolicitacoesDAO();
			dao.CadastrarSolicitacao(solicitacao);
			
			request.getSession().setAttribute("solicitacao", null);

		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(SolicitacoesDAO.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(ex.getMessage());
		}

		response.sendRedirect("DoConsultaItens");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
