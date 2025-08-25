package Repositories;

import java.sql.ResultSet;
import java.util.ArrayList;

import Dao.sqlDao;
import Entitys.Aluno;

public class AlunoRepository {
	
	sqlDao sqlDao;
	
	public AlunoRepository() {
		this.sqlDao = new sqlDao();
	}
	
	public String Cadastrar(Aluno a) {
		return sqlDao.inserir(a.listDados());
	}
	
	public String Update(Aluno a) {
		return sqlDao.Update(a.listDados(), a.getId());
	}
	
	public String delete(int id) {
		return sqlDao.delete(id);
	}
	
	public ArrayList<Aluno> search() {
		return sqlDao.search();
	}

}
