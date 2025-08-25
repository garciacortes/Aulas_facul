package Repositories;

import java.util.ArrayList;

import Dao.sqlDao;
import Entitys.Pessoa;

public class PessoaRepository {
	
	sqlDao sqlDao;
	
	public PessoaRepository() {
		this.sqlDao = new sqlDao();
	}
	
	public String Cadastrar(Pessoa p) {
		return sqlDao.inserir(p.getNome(), p.getPeso(), p.getAltura(), p.getImc());
	}
	
	public String Alterar(Pessoa p) {
		return sqlDao.Update(p.getId(), p.getNome(), p.getPeso(), p.getAltura(), p.getImc());
	}
	
	public String Remover(int id) {
		return sqlDao.delete(id);
	}
	
	public ArrayList<Pessoa> Listar() {
		return sqlDao.search();
	}
}
