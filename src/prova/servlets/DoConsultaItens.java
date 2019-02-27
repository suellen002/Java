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
 * Servlet implementation class DoConsultaItens
 */
@WebServlet("/DoConsultaItens")
public class DoConsultaItens extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoConsultaItens() {
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
			ItensDAO dao = new ItensDAO();
			Collection<Item> itens = null;
			
			String busca = request.getParameter("termoBusca");
			if(busca == null) busca = "";
			itens = dao.BuscarPorDetalhes(busca);
			request.setAttribute("itens", itens);

		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(SolicitacoesDAO.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(ex.getMessage());
		}

		RequestDispatcher view = request.getRequestDispatcher("inicio.jsp");
		view.forward(request, response);
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
