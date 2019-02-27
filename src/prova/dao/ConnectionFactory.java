package prova.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        try {
            Connection connection = null;

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/mydb";
            String username = "root";
            String password = "";
            connection = DriverManager.getConnection(url, username, password);

            return connection;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException("Problema ao se conectar: "+ ex.getMessage());
        }
    }

}
