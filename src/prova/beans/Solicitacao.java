package prova.beans;

import java.util.Date;
import java.util.List;
import prova.beans.Usuario;

public class Solicitacao {

	private int idsol;
	private Usuario usuario;
	private int status;
	private Date data;
	private List<Item> items;
	
	public int getIdsol() {
		return idsol;
	}
	public void setIdsol(int idsol) {
		this.idsol = idsol;
	}
	public Usuario getUsuario() {
		return this.usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
}
