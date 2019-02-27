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

import prova.beans.Usuario;
import prova.dao.UsuarioDAO;

/**
 * Servlet implementation class DoCadastroUsuario
 */
@WebServlet("/DoCadastroUsuario")
public class DoCadastroUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoCadastroUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		try {
			UsuarioDAO dao = new UsuarioDAO();
			
			dao.adiciona(
					request.getParameter("nome"),
					request.getParameter("email"),
					request.getParameter("senha")
				);
			
			response.sendRedirect("index.html");			
			
			
		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(DoLogin.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(ex.getMessage());
		}
		
	}

}
