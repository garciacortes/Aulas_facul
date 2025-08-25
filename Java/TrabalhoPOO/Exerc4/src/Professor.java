
public class Professor extends Pessoa{
	
	private double salario;
	
	public Professor(String nome, double salario, long cpf) {
		super(nome, cpf);
		this.salario = salario;
	}
	
	public double getSalario() {
		return salario;
	}
	
	public void Imprime() {
		System.out.println("Nome: " + super.getNome() + " | CPF: " + super.getCpf() + " | salario: " + salario);
	}
}
