package prova.dao;

import prova.beans.Usuario;
import prova.dao.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UsuarioDAO {
    
    private static Connection connection;
    
    public UsuarioDAO() throws ClassNotFoundException, SQLException{
        UsuarioDAO.connection = ConnectionFactory.getConnection();
    }
    
    public Usuario buscaPorEmailESenha(String email, String senha) throws SQLException {
        if (email == null || senha == null){
            return null;
        }
        Usuario usuario = null;
        
        PreparedStatement stmt = UsuarioDAO.connection.prepareStatement("SELECT * FROM usuario WHERE email = '" + email + "'");
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            usuario = new Usuario( rs.getString("email"), rs.getString("senha"));
            if (usuario.getSenha().equals(senha)) {
            	usuario.setIdusuario(rs.getInt("idusuario"));
            	usuario.setNome(rs.getString("nome"));
            	if(rs.getInt("admin") == 1) {
            		usuario.setAdmin(true);
            	}
            	
            }else
            {
            	usuario = null;
            }

        }  
        rs.close();
        stmt.close();
    	return usuario;
    }
    
    
    public void adiciona(String nome, String email, String senha) throws SQLException {
            String sql = "insert into usuario (nome, email,senha, admin) values (?,?,?,0);";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            stmt.execute();
            stmt.close();
    }
}
