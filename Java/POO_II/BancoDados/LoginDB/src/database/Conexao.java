package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	
	Connection conexao;
	private final String URL_DB = "jdbc:mysql://localhost:3306/nomebd";
	private final String user = "root";
	private final String senha = "admin";
	
	public Conexao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexao = DriverManager.getConnection(URL_DB, user, senha);
			System.out.println("Conexão realizada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao acessar o Banco de Dados!");
		}
	}

	public Connection getConexao() {
		return conexao;
	}
}
