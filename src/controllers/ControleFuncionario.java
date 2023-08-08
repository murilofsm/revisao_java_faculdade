package controllers;

import entities.Coordenador;
import entities.Funcionario;
import util.Input;
import util.MenusUtils;

import java.util.ArrayList;
import java.util.Collections;

public class ControleFuncionario {
    protected static ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
    public static void menuControleFuncionario(){
        boolean travaTela = true;
        do{
            System.out.println("==Controle Funcionario==");
            System.out.println("| 1 - Cadastrar        |");
            System.out.println("| 2 - Buscar           |");
            System.out.println("| 3 - Alterar          |");
            System.out.println("| 4 - Remover          |");
            System.out.println("| 5 - Listar           |");
            System.out.println("| 0 - VOLTAR           |");
            System.out.println("========================");
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
        Funcionario func = new Funcionario();
        if (setarDados(func)){
            listaFuncionarios.add(func);
            System.out.println("\nFuncionário cadastrado com sucesso !!\n");
        }else {
            System.out.println("\nDeu merda no cadastro !!!\n");
        }
    }
    protected static boolean setarDados(Funcionario fun){
        ControlePessoa.setarDados(fun);
        System.out.print("CTPS: ");
        fun.setCtps(Input.nextLine());
        System.out.print("Salário: ");
        fun.setSalario(Input.nextDouble());
        return true;
    }
    protected static void buscar(){
        System.out.println("Insira o nome/cpf do funcionário: ");
        String dadoBusca = Input.nextLine();

        ArrayList<Funcionario> resultado = pesquisaContains(listaFuncionarios, dadoBusca.toLowerCase());

        if(resultado.isEmpty()){
            System.out.println("Nenhum funcionário foi encontrado !!");
        }else{
            listar(resultado);
        }
    }
    protected static ArrayList<Funcionario> pesquisaContains(ArrayList<Funcionario> listaFuncionarios, String dadoBusca){
        ArrayList<Funcionario> resultado = new ArrayList<>();
        for (Funcionario listaFuncionario : listaFuncionarios){
            if (listaFuncionario.getNome().toLowerCase().contains(dadoBusca) || listaFuncionario.getCpf().contains(dadoBusca)){
                resultado.add(listaFuncionario);
            }
        }
        return resultado;
    }
    protected static void alterar(){
        System.out.println("Insira o Nome/Cpf do funcionário: ");
        String dadoBusca = Input.nextLine();

        Funcionario funcNovo = pesquisaEquals(listaFuncionarios, dadoBusca.toLowerCase());
        if(funcNovo == null){
            System.out.println("\nNenhum professor encontrado !!\n");
        }else{
            setarDados(funcNovo);
            System.out.println("\nProfessor alterado com sucesso !!\n");
        }
    }
    protected static void remover(){
        System.out.println("Insira o Cpf do funcionário: ");
        String dadoBusca = Input.nextLine();

        Funcionario funcRemove = pesquisaEquals(listaFuncionarios, dadoBusca.toLowerCase());
        if(funcRemove == null){
            System.out.println("\nNenhum professor encontrado !!\n");
        }else{
            listaFuncionarios.remove(funcRemove);
            System.out.println("\nProfessor removido com sucesso !!\n");
        }
    }
    protected static Funcionario pesquisaEquals(ArrayList<Funcionario> listaFuncionarios, String dadoBusca){
        for (Funcionario listaFuncionario : listaFuncionarios) {
            if(listaFuncionario.getNome().toLowerCase().equals(dadoBusca) || listaFuncionario.getCpf().equals(dadoBusca)){
                return listaFuncionario;
            }
        }
        return null;
    }
    protected static void menuListar(){
        System.out.println("\n1 - Crescente \n2 - Decrescente\n3 - VOLTAR");
        System.out.print("R: ");
        int resp = Input.nextInt();
        switch (resp){
            case 1 -> inverterOrdemLista(listaFuncionarios, "crescente");
            case 2 -> inverterOrdemLista(listaFuncionarios, "decrescente");
            default -> System.out.println("\nEscolha Inválida !!\n");
        }
    }
    protected static void inverterOrdemLista(ArrayList<Funcionario> listaFuncionarios , String ordem){
        ArrayList<Funcionario> listaOrdem = new ArrayList<>();
        listaOrdem = listaFuncionarios;

        if (ordem.equals("crescente")) {
            Collections.sort(listaOrdem);
        }
        if (ordem.equals("decrescente")){
            listaFuncionarios.sort(Collections.reverseOrder());
        }
        listar(listaOrdem);
    }
    protected static void listar(ArrayList<Funcionario> listaFuncionarios){
        if(listaFuncionarios.isEmpty()){
            System.out.println("\nA lista está vazia !!\n");
            return;
        }
        System.out.println("\nFuncionários: ");
        for (Funcionario listaFuncionario : listaFuncionarios){
            listaFuncionario.exibirInformacoes();
            MenusUtils.pularLinha();
        }
    }

}
