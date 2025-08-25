package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class sqlDao {
	
	conexaoDao cDao;
	Connection cnn;
	
	public sqlDao() {
		cDao = new conexaoDao();
	}
	
	public String inserir(String nome, String end, String tel) {
		
		String msg;
		String sql = "INSERT INTO pessoa(nome, endereco, telefone) VALUES(?,?,?)";
		cnn = cDao.initConexao();
		
		PreparedStatement ps;
		try {
			ps = cnn.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, end);
			ps.setString(3, tel);
			ps.execute();
			
			cDao.closeConexao(cnn);
			
			msg = "Cadastro realizado com sucesso";
		} catch (SQLException e) {
			e.printStackTrace();
			msg = "Erro! cadastro não realizado";
		}
		
		return msg;
	}
}
