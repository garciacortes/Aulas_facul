package repositories;

import Dao.sqlDao;
import Entitys.Pessoa;

public class PessoaRepository {
	
	Pessoa pessoa;
	sqlDao sqlDao;
	
	public PessoaRepository() {
		this.sqlDao = new sqlDao();
	}
	
	public String Cadastrar(Pessoa p) {
		pessoa = new Pessoa(p);
		return sqlDao.inserir(p.getNome(), p.getEnd(), p.getTel());
	}
}
