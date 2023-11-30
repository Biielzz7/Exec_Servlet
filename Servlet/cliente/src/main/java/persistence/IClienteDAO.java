package persistence;

import java.sql.SQLException;
import java.util.List;

import model.cliente;

public interface IClienteDAO <cliente> {
	public cliente buscar (cliente cli) throws ClassNotFoundException, SQLException;
	public String excluir (cliente cli) throws ClassNotFoundException, SQLException;
	public String alterar (cliente cli) throws ClassNotFoundException, SQLException;
    public List<cliente> listar () throws ClassNotFoundException, SQLException;
    public String inserir (cliente cli) throws ClassNotFoundException, SQLException;
}         
