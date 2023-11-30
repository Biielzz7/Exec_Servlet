package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.cliente;
import persistence.ClienteDAO;
import persistence.GenericDAO;


@WebServlet("/cliente")
public class SERVLET extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SERVLET() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String botao = request.getParameter("botao");
		GenericDAO gDAO = new GenericDAO();
		ClienteDAO cDAO = new ClienteDAO(gDAO);
		cliente cliente = new cliente();
		List<cliente> clientes = new ArrayList<>();
		String erro = "";
		String saida = "";
		
		try {
			if (botao.equalsIgnoreCase("listar")) {
				clientes = cDAO.listar();
			} else {
				cliente.setCpf((request.getParameter("cpf")));
				if (botao.equalsIgnoreCase("buscar") || botao.equalsIgnoreCase("excluir")) {
					if (botao.equalsIgnoreCase("buscar")) {
						saida = cDAO.inserir(cliente);
					} else {
						saida = cDAO.excluir(cliente);
						cliente = new cliente();
					}
				} else {
					cliente.setNome(request.getParameter("nome"));
					cliente.setEmail(request.getParameter("email"));
					cliente.setLimite_credito(Float.parseFloat(request.getParameter("limite_credito")));
					cliente.setData_nasc(Date.parse(request.getParameter("data_nasc")));
					if (botao.equalsIgnoreCase("inserir")) {
						saida = cDAO.inserir(cliente);
						cliente = new cliente();
					} else {
						saida = cDAO.alterar(cliente);
						cliente = new cliente();
					}
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("cliente", cliente);
			request.setAttribute("clientes", clientes);

			RequestDispatcher dispatcher = request.getRequestDispatcher("cliente.jsp");
			dispatcher.forward(request, response);
		}
	}

}
