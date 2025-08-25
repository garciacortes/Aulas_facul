package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entitys.Aluno;

public class sqlDao {
	
	conexaoDao cDao;
	Connection cnn;
	private String msg;
	
	public sqlDao() {
		cDao = new conexaoDao();
	}
	
	public String inserir(ArrayList<String> dados) {
		String sql = "INSERT INTO alunos(nome, endereco, telefone, cpf, tiposangue, curso, contatoemergencia, telefoneemergencia) VALUES(?,?,?,?,?,?,?,?)";
		cnn = cDao.initConexao();
		
		PreparedStatement ps;
		try {
			ps = cnn.prepareStatement(sql);
			for(int i = 0; i < dados.size(); i++) {
				ps.setString(i + 1, dados.get(i));
			}
			ps.execute();
			
			cDao.closeConexao(cnn);
			
			msg = "Cadastro realizado com sucesso";
		} catch (SQLException e) {
			e.printStackTrace();
			msg = "Erro! cadastro não realizado";
		}
		
		return msg;
	}
	
	public String Update(ArrayList<String> dados, int id) {
		String sql = "UPDATE alunos SET nome = ?, endereco = ?, telefone = ?, cpf = ?, tiposangue = ?, curso = ?, contatoemergencia = ?, telefoneemergencia = ? WHERE id = ?";
		cnn = cDao.initConexao();
		
		PreparedStatement ps;
		try {
			ps = cnn.prepareStatement(sql);
			for(int i = 0; i < dados.size(); i++) {
				System.out.println((i+1) + dados.get(i));
				ps.setString(i + 1, dados.get(i));
			}
			ps.setInt(9, id);
			ps.execute();
			
			cDao.closeConexao(cnn);
			
			msg = "Dados Alterados com sucesso";
		} catch (SQLException e) {
			e.printStackTrace();
			msg = "Erro! Update não realizado";
		}
		
		return msg;
		
	}
	
	public String delete(int id) {
		String sql = "DELETE FROM alunos WHERE id = ?";
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
	
	public ArrayList<Aluno> search() {
		ArrayList<Aluno> relatorio = new ArrayList<Aluno>();
		String sql = "SELECT * FROM alunos";
		ResultSet result = null;
		cnn = cDao.initConexao();
		
		try {
			Statement stm = cnn.createStatement();
			result = stm.executeQuery(sql);
			
			while(result.next()) {
				int id = result.getInt("id");
				String nome = result.getString("nome");
				String end = result.getString("endereco");
				String tel = result.getString("telefone");
				String cpf = result.getString("cpf");
				String tipoSangue = result.getString("tiposangue");
				String curso = result.getString("curso");
				String contEmerg = result.getString("contatoemergencia");
				String telEmerg = result.getString("telefoneemergencia");
				
				System.out.println(id);
				relatorio.add(new Aluno(nome, end, tel, cpf, tipoSangue, curso, contEmerg, telEmerg, id));
			}
			
			cDao.closeConexao(cnn);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro! remove não realizado");
		}
		
		return relatorio;
	}
}
