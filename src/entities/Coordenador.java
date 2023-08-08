package entities;

public class Coordenador extends Professor{
    protected Curso cursoCoordenado;
    public Coordenador() {
    }

    public Curso getCursoCoordenado() {
        return cursoCoordenado;
    }

    public void setCursoCoordenado(Curso cursoCoordenado) {
        this.cursoCoordenado = cursoCoordenado;
    }

    public void exibirInformacoes(){
        System.out.println("Nome: " + nome + " | Cpf: " + cpf + " | Idade: " + calcularIdade() + " anos " +
                " | Cidade: "+ endereco.getCidade() + " | Rua : " + endereco.getRua() + " | Número: " + endereco.getNumero() + " | "
                + "Ctps: " + ctps + " |  Salario: " + salario + " | Formação: " + formacao + " | Curso coordenado: " + cursoCoordenado.getNome()
        );
    }
}
