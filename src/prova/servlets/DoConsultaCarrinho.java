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
@WebServlet("/DoConsultaCarrinho")
public class DoConsultaCarrinho extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoConsultaCarrinho() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
			Solicitacao solicitacao = (Solicitacao) request.getSession().getAttribute("solicitacao");
			if (solicitacao == null) {
				response.sendRedirect("DoConsultaItens");
				return;
			}
			
			request.setAttribute("itens", solicitacao.getItems());
		RequestDispatcher view = request.getRequestDispatcher("carrinho.jsp");
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
