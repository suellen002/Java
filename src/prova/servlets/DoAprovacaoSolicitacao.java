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

import prova.beans.Item;
import prova.beans.Solicitacao;
import prova.beans.Usuario;
import prova.dao.ItensDAO;
import prova.dao.SolicitacoesDAO;

/**
 * Servlet implementation class DoAprovacaoSolicitacao
 */
@WebServlet("/DoAprovacaoSolicitacao")
public class DoAprovacaoSolicitacao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoAprovacaoSolicitacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

		if (usuario == null) {
			response.sendRedirect("inicio.jsp");
			return;
		}

		if (usuario.isAdmin()) {
			try {
				SolicitacoesDAO dao = new SolicitacoesDAO();
				ItensDAO daoItens = new ItensDAO();

				int idsol = Integer.parseInt(request.getParameter("idsol"));
				boolean valido = true;
				Collection<Solicitacao> solicitacoes = dao.listarSolicitacoes(idsol);
				Solicitacao solicitacao = (Solicitacao) solicitacoes.toArray()[0];
				
				for (int i = 0; i < solicitacao.getItems().size(); i++) {
					Item itemSol = (Item) solicitacao.getItems().toArray()[i];
					Item itemBD = daoItens.BuscarPorId(itemSol.getIditem());
					if (itemSol.getQtd() > itemBD.getQtd()) {
						valido = false;
					}
				}

				if (valido) {
					new SolicitacoesDAO().atender(idsol);
				}

			} catch (ClassNotFoundException | SQLException ex) {

				Logger.getLogger(SolicitacoesDAO.class.getName()).log(Level.SEVERE, null, ex);
				System.out.println(ex.getMessage());
			}

		}

		response.sendRedirect("DoConsultaSolicitacoes");

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
