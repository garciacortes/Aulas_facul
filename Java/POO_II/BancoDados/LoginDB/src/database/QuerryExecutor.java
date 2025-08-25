package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuerryExecutor {
	
	public static void insert(Conexao objDB) throws SQLException {
		if(objDB != null) {
			Statement insert = objDB.conexao.createStatement();
			String querry = "INSERT INTO credentials(user, password) VALUES ('Garciacortes', '159753')";
			insert.execute(querry);
		}
	}
	
	public static void read(Conexao objDB) throws SQLException {
		Statement read = objDB.conexao.createStatement();
		ResultSet readResult = read.executeQuery("SELECT * FROM credentials  WHERE user = 'Garciacortes' AND password = '159753' ");
		
		if(readResult.next()) {
		}
	}
	
	public static void main(String[] args) throws SQLException {
		
		Conexao objDB = new Conexao();
		
		//insert(objDB);
		
		//read(objDB);
		
	}

}

