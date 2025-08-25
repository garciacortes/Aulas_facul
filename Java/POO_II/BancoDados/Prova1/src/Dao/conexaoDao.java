package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexaoDao {
	
	private Connection cnn;
	private String url;
	private String user;
	private String password;
	
	public conexaoDao() {
		url = "jdbc:mysql://localhost:3306/provadb";
		user = "root";
		password = "admin";
	}
	
	public Connection initConexao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cnn = DriverManager.getConnection(url, user, password);
			System.out.println("Conexão realizada com sucesso");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao acessar o banco de dados " + e);
		}
		
		return cnn;
	}
	
	public void closeConexao(Connection cnn) throws SQLException {
		cnn.close();
	}
}
