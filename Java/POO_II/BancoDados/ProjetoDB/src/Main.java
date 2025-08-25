import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	
	public static void insert(ConexaoDB objDB) throws SQLException {
		if(objDB != null) {
			Statement insert = objDB.conexao.createStatement();
			String querry = "INSERT INTO pessoa(nome, sobrenome, nivelusuario) VALUES ('RENATA', 'SILVA', 3)";
			insert.execute(querry);
		}
	}
	
	public static void read(ConexaoDB objDB) throws SQLException {
		Statement read = objDB.conexao.createStatement();
		ResultSet readResult = read.executeQuery("SELECT * FROM credentials");
		
		while(readResult.next()) {
			System.out.println(readResult.getString("nome") + " " + readResult.getString("sobrenome") + " Nivel: " + readResult.getString("nivelusuario"));
		}
	}
	
	public static void update(ConexaoDB objDB) throws SQLException {
		Statement update = objDB.conexao.createStatement();
		update.execute("UPDATE pessoa set nome = 'JUCA' WHERE id = 1");
	}
	
	public static void delete(ConexaoDB objDB) throws SQLException {
		Statement delete = objDB.conexao.createStatement();
		delete.execute("DELETE FROM pessoa WHERE id = 2 ");
	}
	
		
	public static void main(String[] args) throws SQLException {
		
		ConexaoDB objDB = new ConexaoDB();
		
		objDB.Fechar();
		
		//objDB.conectar();
		
		//insert(objDB);
		
		//read(objDB);
		
		//update(objDB);
		
		//delete(objDB);
		
		
	}

}
