package entities;

import controllers.ControleAluno;

public class Curso {
    protected String nome;
    protected int cargaHoraria;
    protected int qtdSemestres;
    protected Coordenador coordenador;
    public Curso() {
    }

    public Curso(String nome, int cargaHoraria, int qtdSemestres, Coordenador coordenador) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.qtdSemestres = qtdSemestres;
        this.coordenador = coordenador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getQtdSemestres() {
        return qtdSemestres;
    }

    public void setQtdSemestres(int qtdSemestres) {
        this.qtdSemestres = qtdSemestres;
    }

    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    public int getQndAlunosCurso(Curso curso){
        return ControleAluno.calcAlunosCurso(curso);
    }

    public void exibirInformacoes(Curso curso){
        System.out.println("Nome: " + nome + " | Carga horária: " + cargaHoraria + " | Quantidade semestre: " + qtdSemestres + " | Coordenador: " + coordenador.getNome() + " | Quantidade alunos: " + getQndAlunosCurso(curso));
    }
}
