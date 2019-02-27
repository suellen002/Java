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

/**
 * Servlet implementation class DoRelatorioSolicitacoes
 */
@WebServlet("/DoRelatorioSolicitacoes")
public class DoRelatorioSolicitacoes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRelatorioSolicitacoes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		if (usuario == null) {
			response.sendRedirect("inicio.jsp");
			return;
		}

		Collection<Solicitacao> solicitacoes = null;

		if (usuario.isAdmin()) {
			try {

				int periodo = request.getParameter("cboPeriodo") == null 
						? 7 
						: Integer.parseInt(request.getParameter("cboPeriodo")) ;
				solicitacoes = new SolicitacoesDAO().listarSolicitacoes(0, periodo);

			} catch (ClassNotFoundException | SQLException ex) {

				Logger.getLogger(SolicitacoesDAO.class.getName()).log(Level.SEVERE, null, ex);
				System.out.println(ex.getMessage());
			}

		}

		request.setAttribute("solicitacoes", solicitacoes);
		request.setAttribute("cboPeriodo", request.getParameter("cboPeriodo"));
		// response.sendRedirect("solicitacoes.jsp");
		RequestDispatcher view = request.getRequestDispatcher("relatorioSolicitacoes.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
