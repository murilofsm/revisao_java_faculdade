package controllers;

import entities.Aluno;
import entities.Curso;
import entities.Professor;
import util.DateUtils;
import util.Input;
import util.MenusUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class ControleAluno {

    public static ArrayList<Aluno> listaAlunos = new ArrayList<>();

    public static void menuControleAluno(){
        boolean travaTela = true;
        do{
            System.out.println("===Controle Alunos===");
            System.out.println("| 1 - Cadastrar     |");
            System.out.println("| 2 - Buscar        |");
            System.out.println("| 3 - Alterar       |");
            System.out.println("| 4 - Remover       |");
            System.out.println("| 5 - Listar        |");
            System.out.println("| 0 - VOLTAR        |");
            System.out.println("=====================");
            System.out.print("Escolha: ");
            int escolha = Input.nextInt();
            switch (escolha) {
                case 1 -> cadastrar();
                case 2 -> buscar();
                case 3 -> alterar();
                case 4 -> remover();
                case 5 -> menuListar();
                case 0 -> travaTela = false;
                default -> MenusUtils.opcaoInvalida();
            }
        }while(travaTela);
    }
    protected static void cadastrar(){
        Aluno aluno = new Aluno();
        if(setarDados(aluno)){
            listaAlunos.add(aluno);
            System.out.println("\nAluno cadastrado com sucesso !!\n");
        }else{
            System.out.println("\nOcorreu um erro no cadastro !!\n");
        }
    }
    protected static boolean setarDados(Aluno aluno){
        Curso cursoPesquisa;
        String nomeCurso;
        int contTentativas = 1;
        ControlePessoa.setarDados(aluno);
        System.out.print("RA: ");
        aluno.setRa(Input.nextLine());
        aluno.setDataMatricula(LocalDate.now());

        System.out.println("Deseja adicionar o curso? S/N");
        String resp = Input.nextLine();

        if(MenusUtils.confirmacaoSim(resp)){
            do{
                System.out.print("Curso: ");
                nomeCurso = Input.nextLine();
                cursoPesquisa = ControleCurso.pesquisaCursoEquals(nomeCurso);
                if(cursoPesquisa == null){
                    if (contTentativas == 3){
                        System.out.println("\nNão foi possível adicionar o Curso !! Número de tentativas ultrapassadas.\n");
                        aluno.setCurso(new Curso());
                        break;
                    }else {
                        System.out.println("\nCurso não encontrado !! Tente novamente !!\n");
                        contTentativas++;
                    }
                }else{
                    aluno.setCurso(cursoPesquisa);
                    System.out.print("Situação: ");
                    String retorno = escolhaSituacao();
                    if(retorno.equals("limite")){
                        System.out.println("\nNúmero de tentativas ultrapassada !!\n");
                        return false;
                    }else{
                        aluno.setSituacao(retorno);
                    }

                }
            }while(cursoPesquisa == null);
        }else {
            aluno.setCurso(new Curso());
        }

        return true;
    }
    protected static String escolhaSituacao(){
        int contTen = 1;
        do {
            System.out.println("\n1 - Em andamento\n2 - Concluido\n3 - Trancada\n4 - Desistente");
            System.out.print("R: ");
            int op = Input.nextInt();
            switch (op){
                case 1 -> {
                    return "Em andamento";
                }
                case 2 -> {
                    return "Concluido";
                }
                case 3 -> {
                    return "Trancada";
                }
                case 4 -> {
                    return "Desistente";
                }
                default -> {
                    contTen++;
                    System.out.println("\nValor inválido !!\n");
                    if (contTen == 3) {
                        return "limite";
                    }
                }
            }
        }while (true);
    }
    protected static void buscar(){
        System.out.println("Insira o nome/cpf/RA do aluno: ");
        String dadoBusca = Input.nextLine();

        ArrayList<Aluno> resultado = pesquisaContains(listaAlunos, dadoBusca.toLowerCase());

        if(resultado.isEmpty()){
            System.out.println("Nenhum aluno foi encontrado !!");
        }else{
            listar(resultado);
        }
    }
    protected static ArrayList<Aluno> pesquisaContains(ArrayList<Aluno> listaAlunos, String busca){
        ArrayList<Aluno> resultadoPesquisa = new ArrayList<>();

        for (Aluno listaAluno : listaAlunos){
            if (listaAluno.getNome().toLowerCase().contains(busca) || listaAluno.getCpf().contains(busca) || listaAluno.getRa().contains(busca)){
                resultadoPesquisa.add(listaAluno);
            }
        }
        return resultadoPesquisa;
    }
    protected static void alterar(){
        System.out.println("Insira o Nome/Cpf/RA do aluno: ");
        String dadoBusca = Input.nextLine();

        Aluno alunoNovo = pesquisaEquals(listaAlunos, dadoBusca.toLowerCase());
        setarDados(alunoNovo);
        System.out.println("Aluno alterado com sucesso !!");
    }
    protected static void remover(){
        System.out.println("Insira o Nome/Cpf/RA do aluno: ");
        String dadoBusca = Input.nextLine();

        Aluno aluno = pesquisaEquals(listaAlunos, dadoBusca.toLowerCase());
        listaAlunos.remove(aluno);
        System.out.println("Aluno removido com sucesso !!");
    }
    protected static Aluno pesquisaEquals(ArrayList<Aluno> listaAlunos, String busca){
        for (Aluno listaAluno : listaAlunos){
            if (listaAluno.getNome().toLowerCase().equals(busca) || listaAluno.getCpf().equals(busca) || listaAluno.getRa().equals(busca)){
                return listaAluno;
            }
        }
        return null;
    }
    protected static void menuListar(){
        System.out.println("\n1 - Crescente \n2 - Decrescente\n3 - VOLTAR");
        System.out.print("R: ");
        int resp = Input.nextInt();
        switch (resp){
            case 1 -> inverterOrdemLista(listaAlunos, "crescente");
            case 2 -> inverterOrdemLista(listaAlunos, "decrescente");
            default -> System.out.println("\nEscolha Inválida !!\n");
        }
    }
    protected static void inverterOrdemLista(ArrayList<Aluno> listaAlunos , String ordem){
        ArrayList<Aluno> listaOrdem;
        listaOrdem = listaAlunos;

        if (ordem.equals("crescente")) {
            Collections.sort(listaOrdem);
        }
        if (ordem.equals("decrescente")){
            listaAlunos.sort(Collections.reverseOrder());
        }
        listar(listaOrdem);
    }
    public static void listar(ArrayList<Aluno> listaAlunos){
        System.out.println("\nAlunos: ");

        for(Aluno listaAluno : listaAlunos){
            listaAluno.exibirInformacoes();
            MenusUtils.pularLinha();
        }
    }
    public static int calcAlunosCurso(Curso curso){
        int contAlunos = 0;

        for (Aluno listaAluno : listaAlunos){
            if (listaAluno.getSituacao().equals("Em andamento") && listaAluno.getCurso().equals(curso)){
                contAlunos++;
            }
        }
        return contAlunos;
    }
}
