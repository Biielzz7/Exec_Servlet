package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.cliente;

public class ClienteDAO implements IClienteDAO <cliente>{
	
	 private GenericDAO gDAO;
	 public ClienteDAO (GenericDAO gDAO) {
		 this.gDAO = gDAO;
	 }
	 private String CallProcedure (String opcao, cliente cliente) throws ClassNotFoundException, SQLException {
		 Connection c = gDAO.getConnection();
		 String sql = "{CALL sp_cli(?,?,?,?,?,?,?)}";
		 CallableStatement cs = c.prepareCall(sql);
		 cs.setString(1, opcao);
		 cs.setString(2, cliente.getCpf());
		 cs.setString(3, cliente.getNome());
		 cs.setString(4, cliente.getEmail());
		 cs.setFloat(5, cliente.getLimite_credito());
		 cs.setDate(6, cliente.getData_nasc());
		 cs.registerOutParameter(7, Types.VARCHAR);
		 cs.execute();
		 String saida = cs.getString(7);
		 
		 cs.close();
		 c.close();
		 
		 return saida;
		 
		 
	 }
	@Override
	public cliente buscar(cliente cliente) throws ClassNotFoundException, SQLException {
		Connection c = gDAO.getConnection();
		 String sql = "SELECT * FROM cliente WHERE cpf = ?";
		 PreparedStatement ps = c.prepareStatement(sql);
		 ps.setString(1, cliente.getCpf());
		 ResultSet rs = ps.executeQuery(sql);
		 
		 if (rs.next()) {
			 cliente.setNome(rs.getString("nome"));
			 cliente.setEmail(rs.getString("email"));
			 cliente.setLimite_credito(rs.getFloat("limite_credito"));
			 cliente.setData_nasc(rs.getDate("data_nasc"));
			 
			 
			 System.out.println(cliente.toString());
		 }
		rs.close();
		ps.close();
		c.close();
		return cliente;
	}

	@Override
	public String excluir(cliente cliente) throws ClassNotFoundException, SQLException {
		Connection c = gDAO.getConnection();
		 String sql = "{CALL sp_cli(?,?,?,?,?,?,?)}";
		 CallableStatement cs = c.prepareCall(sql);
		 cs.setString(1, "D");
		 cs.setString(2, cliente.getCpf());
		 cs.setNull(3, Types.VARCHAR);
		 cs.setNull(4, Types.VARCHAR);
		 cs.setNull(5, Types.VARCHAR);
		 cs.setNull(6, Types.VARCHAR);
		 cs.registerOutParameter(7, Types.VARCHAR);
		 
		 cs.execute();
		 
		 String saida = cs.getString(7);
		 
		 cs.close();
		 c.close();
		 
		 
		return saida;
	}

	@Override
	public String alterar(cliente cliente) throws ClassNotFoundException, SQLException {
		String saida = CallProcedure("U",cliente);
		return saida;
		
	}

	@Override
	public List<cliente> listar() throws ClassNotFoundException, SQLException {
		List<cliente> clientes = new ArrayList<>();
		Connection c = gDAO.getConnection();
		 String sql = "SELECT * FROM cliente";
		 PreparedStatement ps = c.prepareStatement(sql);
		 ResultSet rs = ps.executeQuery(sql);
		 while (rs.next()) {
			 cliente cliente = new cliente();
			 cliente.setCpf(rs.getString("cpf"));
			 cliente.setNome(rs.getString("nome"));
			 cliente.setEmail(rs.getString("email"));
			 cliente.setLimite_credito(rs.getFloat("limite_credito"));
			 cliente.setData_nasc(rs.getDate("data_nasc"));
			 
			 clientes.add(cliente);
			
			
		}
		rs.close();
		ps.close();
		c.close();
		 
		
		return clientes;
	}

	@Override
	public String inserir(cliente cliente) throws ClassNotFoundException, SQLException {
		String saida = CallProcedure("I",cliente);
		return saida;
	}

	
	
}
