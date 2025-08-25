package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entitys.Pessoa;

public class sqlDao {
	
	conexaoDao cDao;
	Connection cnn;
	private String msg;
	
	public sqlDao() {
		cDao = new conexaoDao();
	}
	
	public String inserir(String nome, double peso, double altura, double imc) {
		String sql = "INSERT INTO Pessoa(nome, peso, altura, imc) VALUES(?,?,?,?)";
		cnn = cDao.initConexao();
		
		PreparedStatement ps;
		try {
			ps = cnn.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setDouble(2, peso);
			ps.setDouble(3, altura);
			ps.setDouble(4, imc);
			ps.execute();
			
			cDao.closeConexao(cnn);
			
			return "Cadastro realizado com sucesso";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Erro! cadastro não realizado";
		}
	}
	
	public String Update(int id, String nome, double peso, double altura, double imc) {
		String sql = "UPDATE pessoa SET nome = ?, peso = ?, altura = ?, imc = ? WHERE id = ?";
		cnn = cDao.initConexao();
		
		PreparedStatement ps;
		try {
			ps = cnn.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setDouble(2, peso);
			ps.setDouble(3, altura);
			ps.setDouble(4, imc);
			ps.setInt(5, id);
			ps.execute();
			
			cDao.closeConexao(cnn);
			
			return "Dados Alterados com sucesso";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Erro! Update não realizado";
		}
	}
	
	public String delete(int id) {
		String sql = "DELETE FROM pessoa WHERE id = ?";
		cnn = cDao.initConexao();
		
		PreparedStatement ps;
		try {
			ps = cnn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			
			cDao.closeConexao(cnn);
			
			msg = "Dados removidos com sucesso";
		} catch (SQLException e) {
			e.printStackTrace();
			msg = "Erro! remove não realizado";
		}
		
		return msg;
	}
	
	public ArrayList<Pessoa> search() {
		ArrayList<Pessoa> relatorio = new ArrayList<Pessoa>();
		String sql = "SELECT * FROM pessoa";
		ResultSet result = null;
		cnn = cDao.initConexao();
		
		try {
			Statement stm = cnn.createStatement();
			result = stm.executeQuery(sql);
			
			while(result.next()) {
				int id = result.getInt("id");
				String nome = result.getString("nome");
				double peso = result.getDouble("peso");
				double altura = result.getDouble("altura");
				double imc = result.getDouble("imc");
				relatorio.add(new Pessoa(nome, peso, altura, imc, id));
			}
			
			cDao.closeConexao(cnn);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro! remove não realizado");
		}
		
		return relatorio;
	}
}
