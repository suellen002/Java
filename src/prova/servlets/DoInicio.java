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

/**
 * Servlet implementation class DoInicio
 */
@WebServlet("/DoInicio")
public class DoInicio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoInicio() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		String tarefa = request.getParameter("tarefa");
//		System.out.println(tarefa);
//		if(tarefa == "atender") {
//			System.out.println("f");
//			int idsol = Integer.parseInt(request.getParameter("idsol"));
//			System.out.println("fa");
//			try {
//				SolicitacoesDAO sol = new SolicitacoesDAO();
//				sol.atender(idsol);
//	            
//	        } catch (ClassNotFoundException | SQLException ex) {
//	        	
//	            Logger.getLogger(SolicitacoesDAO.class.getName()).log(Level.SEVERE, null, ex);
//	            System.out.println(ex.getMessage());
//	        }
//		}
		
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
//
//		Collection<Solicitacao> solicitacoes = null;
//		
//		if(usuario.isAdmin()) {
//			try {
//				
//	            solicitacoes = new SolicitacoesDAO().listarSolicitacoes(0);
//	            
//	        } catch (ClassNotFoundException | SQLException ex) {
//	        	
//	            Logger.getLogger(SolicitacoesDAO.class.getName()).log(Level.SEVERE, null, ex);
//	            System.out.println(ex.getMessage());
//	        }
//			
//		}
//        
//		request.setAttribute("solicitacoes", solicitacoes);
		request.setAttribute("isadmin", usuario.isAdmin());

        
        if(usuario.isAdmin()==false) {
        	RequestDispatcher view = request.getRequestDispatcher("inicio.jsp");
            view.forward(request, response);        	
        }
		
		
		
		
		

	}

}
