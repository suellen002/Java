package prova.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.Map;

import prova.beans.Item;
import prova.beans.Solicitacao;
import prova.beans.Usuario;

public class SolicitacoesDAO {

	private static Connection connection;

	public SolicitacoesDAO() throws ClassNotFoundException, SQLException {
		SolicitacoesDAO.connection = ConnectionFactory.getConnection();
	}

	private List<Item> listarItensSolicitacao(Integer idSolicitacao) throws SQLException {

		List<Item> Itens = new ArrayList<>();

		PreparedStatement stmt = SolicitacoesDAO.connection.prepareStatement(
				"SELECT si.qtd, i.nome, i.categoria, i.iditem "
				+ "FROM solicitacao_item si JOIN item i ON si.iditem=i.iditem "
				+ "WHERE si.idsol=?;");
		stmt.setInt(1, idSolicitacao);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			int qtd = rs.getInt("qtd");
			String categoria = rs.getString("categoria");
			String nome = rs.getString("nome");

			Item item = new Item();
			item.setIditem(rs.getInt("iditem"));
			item.setNome(nome);
			item.setCategoria(categoria);
			item.setQtd(qtd);
			Itens.add(item);
		}

		rs.close();
		stmt.close();
		return Itens;
	}

	public Collection<Solicitacao> listarSolicitacoes(int idsol) throws SQLException {
		return listarSolicitacoes(idsol, 0);
	}

	public Collection<Solicitacao> listarSolicitacoes(int idsol, int periodo) throws SQLException {

		List<Solicitacao> solicitacoes = new ArrayList<>();
		PreparedStatement stmt = null;
		
		if(periodo == 0)
		{
			stmt = SolicitacoesDAO.connection.prepareStatement(
					"SELECT s.*, nome AS nome_usuario "
					+ "FROM solicitacao s JOIN usuario u ON u.idusuario=s.idusuario "
					+ "WHERE ? = 0  or idsol = ?");
			stmt.setInt(1, idsol);
			stmt.setInt(2, idsol);
		}
		else
		{
			Calendar prazo = Calendar.getInstance();
			prazo.add(Calendar.DATE, 0-periodo);

			stmt = SolicitacoesDAO.connection.prepareStatement(
					"SELECT s.*, nome AS nome_usuario "
					+ "FROM solicitacao s JOIN usuario u ON u.idusuario=s.idusuario "
					+ "WHERE status = 1  and (data >= ? or ? = -1)");
			stmt.setDate(1, new java.sql.Date( prazo.getTime().getTime() ) );
			stmt.setInt(2, periodo);
		}
		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {

			Solicitacao solicitacao = new Solicitacao();
			solicitacao.setIdsol(rs.getInt("idsol"));
			solicitacao.setStatus(rs.getInt("status"));
			solicitacao.setData(rs.getDate("data"));
			Usuario u = new Usuario();
			u.setIdusuario(rs.getInt("idusuario"));
			u.setNome(rs.getString("nome_usuario"));

			solicitacao.setUsuario(u);

			List<Item> itens = this.listarItensSolicitacao(rs.getInt("idsol"));

			solicitacao.setItems(itens);

			solicitacoes.add(solicitacao);
		}

		rs.close();
		stmt.close();
		return solicitacoes;
	}

	public void atender(int idsol) throws SQLException {
	
		String sql1 = "UPDATE item AS i " + 
				"INNER JOIN solicitacao_item si ON si.iditem = i.iditem " + 
				"SET  " + 
				"i.qtd = i.qtd - si.qtd " + 
				"WHERE  si.idsol = ?;";
		PreparedStatement stmt1 = connection.prepareStatement(sql1);
		stmt1.setInt(1, idsol);
		stmt1.execute();
		stmt1.close();
		
		
		String sql2 = "update solicitacao set status=1 where idsol=?;";
		PreparedStatement stmt2 = connection.prepareStatement(sql2);
		stmt2.setInt(1, idsol);
		stmt2.execute();
		stmt2.close();
	}
	
	public boolean CadastrarSolicitacao(Solicitacao solicitacao) throws SQLException {
		
		PreparedStatement stmt = SolicitacoesDAO.connection.prepareStatement(
				"INSERT INTO solicitacao (status,idusuario) VALUES (0,?);");
		stmt.setInt(1, solicitacao.getUsuario().getIdusuario());
		stmt.execute();

		stmt = SolicitacoesDAO.connection.prepareStatement(
				"SELECT MAX(idsol) idsol FROM solicitacao");
		ResultSet rs = stmt.executeQuery();
		
		int idsol = 0;
		if(rs.next()) idsol = rs.getInt("idsol");
		if(idsol == 0) return false;
		for(int i=0;i<solicitacao.getItems().size();i++)
		{
			Item item = (Item)solicitacao.getItems().toArray()[i];
			PreparedStatement stmt2 = SolicitacoesDAO.connection.prepareStatement(
					"INSERT INTO solicitacao_item (idsol,iditem,qtd) VALUES (?,?,?);");
			stmt2.setInt(1, idsol);
			stmt2.setInt(2, item.getIditem());
			stmt2.setInt(3, item.getQtd());
			stmt2.execute();
			stmt2.close();
		}
		stmt.close();
		
		return true;
	}
}
