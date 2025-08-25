
public class Coordenador extends Professor {
	
	private String curso;
	
	public Coordenador(String nome, String curso, long cpf, double salario) {
		super(nome, salario, cpf);
		this.curso = curso;
	}

	public String getCurso() {
		return curso;
	}
	
	public void Imprime() {
		System.out.println("Nome: " + super.getNome() + " | CPF: " + super.getCpf() + " | salario: " + super.getSalario() + " | Curso: " + getCurso());
	}
}
