import java.time.YearMonth;

public class Pessoa extends Universidade{

	private int dia, mes, ano, idade;
	private String nome;
	
	public Pessoa() {
		super();
		dia = 0;
		mes = 0;
		ano= 0;
		nome = "";
	}	
	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int ano) {
		this.idade = YearMonth.now().getYear() - ano;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFacul() {
		return super.getFaculNome();
	}
	public void setFacul(String facul) {
		super.setFaculNome(facul);
	}
}
