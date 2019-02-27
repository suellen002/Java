package prova.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import prova.dao.ItensDAO;
import prova.dao.SolicitacoesDAO;

/**
 * Servlet implementation class DoAdicionaItem
 */
@WebServlet("/DoAdicionaItem")
public class DoAdicionaItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoAdicionaItem() {
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
			
			Solicitacao solicitacao = (Solicitacao) request.getSession().getAttribute("solicitacao");
			if (solicitacao == null) {
				solicitacao = new Solicitacao();
				solicitacao.setItems(new ArrayList<Item>());
			}

			int iditem = Integer.parseInt(request.getParameter("iditem"));
			int qtd = Integer.parseInt(request.getParameter("qtd"));

			ItensDAO dao;

			dao = new ItensDAO();
			Item item = dao.BuscarPorId(iditem);
			item.setQtd(qtd);

			solicitacao.getItems().add(item);
			request.getSession().setAttribute("solicitacao", solicitacao);
			
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
