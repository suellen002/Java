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
import prova.beans.Usuario;
import prova.dao.ItensDAO;
import prova.dao.SolicitacoesDAO;

/**
 * Servlet implementation class DoCadastroItem
 */
@WebServlet("/DoCadastroItem")
public class DoCadastroItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoCadastroItem() {
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

		ItensDAO dao;
		try {
			dao = new ItensDAO();
			Item item = new Item();
			
			item.setNome(request.getParameter("nome"));
			item.setCategoria(request.getParameter("categoria"));
			item.setValor(Double.parseDouble(request.getParameter("valor")));
			item.setQtd(Integer.parseInt(request.getParameter("qtd")));
			
			dao.Inserir(item);

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
