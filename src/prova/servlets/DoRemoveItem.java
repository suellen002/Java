package prova.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prova.beans.Item;
import prova.beans.Solicitacao;

/**
 * Servlet implementation class DoRemoveItem
 */
@WebServlet("/DoRemoveItem")
public class DoRemoveItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRemoveItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Solicitacao solicitacao = (Solicitacao) request.getSession().getAttribute("solicitacao");
		if (solicitacao == null) {
			response.sendRedirect("DoConsultaItens");
			return;
		}
		
		int idItem = Integer.parseInt(request.getParameter("iditem"));
		for(int i=0;i<solicitacao.getItems().size();i++) {
			Item item = (Item)solicitacao.getItems().toArray()[i];
			if(item.getIditem() == idItem)
				solicitacao.getItems().remove(i);
			
		}
		request.getSession().setAttribute("solicitacao", solicitacao);
		request.setAttribute("itens", solicitacao.getItems());
		response.sendRedirect("DoConsultaCarrinho");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
