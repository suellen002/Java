package prova.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import prova.beans.Item;

public class ItensDAO {

	private static Connection connection;

	public ItensDAO() throws ClassNotFoundException, SQLException {
		ItensDAO.connection = ConnectionFactory.getConnection();
	}

	public Item BuscarPorId(int id) throws SQLException {
		if (id == 0) {
			return null;
		}
		PreparedStatement stmt = ItensDAO.connection.prepareStatement("SELECT * FROM item WHERE iditem = " + id + "");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Item item = new Item();
			item.setIditem(rs.getInt("iditem"));
			item.setNome(rs.getString("nome"));
			item.setCategoria(rs.getString("categoria"));
			item.setValor(rs.getDouble("valor"));
			item.setQtd(rs.getInt("qtd"));
			rs.close();
			stmt.close();
			return item;
		}
		rs.close();
		stmt.close();
		return null;
	}

	public List<Item> BuscarPorDetalhes(String busca) throws SQLException {
		
		String sql = "";
		if (busca == null) {
			sql = "SELECT * FROM item";
		}else
		{
			sql = "SELECT * FROM item WHERE (nome like '%" + busca + "%' or categoria like '%" + busca + "%') and qtd > 0";
		}
		PreparedStatement stmt = ItensDAO.connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<Item> retorno = new ArrayList<Item>();
		while (rs.next()) {
			Item item = new Item();
			item.setIditem(rs.getInt("iditem"));
			item.setNome(rs.getString("nome"));
			item.setCategoria(rs.getString("categoria"));
			item.setValor(rs.getDouble("valor"));
			item.setQtd(rs.getInt("qtd"));
			retorno.add(item);
		}
		rs.close();
		stmt.close();
		return retorno;
	}

	public boolean Inserir(Item item) throws SQLException {
		if (item == null) {
			return false;
		}
		PreparedStatement stmt = ItensDAO.connection
				.prepareStatement("INSERT INTO item (nome,categoria,valor,qtd) VALUES ('" + item.getNome() + "','"
						+ item.getCategoria() + "'," + item.getValor() + "," + item.getQtd() + ");");
		return stmt.execute();
	}

}
