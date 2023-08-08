package controllers;

import entities.Funcionario;
import entities.Professor;
import util.Input;
import util.MenusUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class ControleProfessor {
    protected static ArrayList<Professor> listaProfessores = new ArrayList<>();
    public static void menuControleProfessor(){
        boolean travaTela = true;
        do{
            System.out.println("===Controle Professores===");
            System.out.println("| 1 - Cadastrar          |");
            System.out.println("| 2 - Buscar             |");
            System.out.println("| 3 - Alterar            |");
            System.out.println("| 4 - Remover            |");
            System.out.println("| 5 - Listar             |");
            System.out.println("| 0 - VOLTAR             |");
            System.out.println("=========================");
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
        Professor prof = new Professor();
        if (setarDados(prof)){
            listaProfessores.add(prof);
            System.out.println("\nProfessor adicionado com sucesso !!\n");
        }else {
            System.out.println("\nERRO NO CADASTRO PROFESSOR\n");
        }

    }
    protected static boolean setarDados(Professor prof){
        ControleFuncionario.setarDados(prof);
        System.out.print("Formação: ");
        prof.setFormacao(Input.nextLine());
        return true;
    }
    protected static void buscar(){
        System.out.println("Insira o nome/cpf do professor: ");
        String dadoBusca = Input.nextLine();

        ArrayList<Professor> resultado = pesquisaContains(listaProfessores, dadoBusca.toLowerCase());

        if(resultado.isEmpty()){
            System.out.println("\nNenhum professor foi encontrado !!\n");
        }else{
            listar(resultado);
        }
    }
    protected static ArrayList<Professor> pesquisaContains(ArrayList<Professor> listaProfessores, String busca){
        ArrayList<Professor> resultadoPesquisa = new ArrayList<>();

        for (Professor listaProfessor : listaProfessores){
            if (listaProfessor.getNome().toLowerCase().contains(busca) || listaProfessor.getCpf().contains(busca)){
                resultadoPesquisa.add(listaProfessor);
            }
        }
        return resultadoPesquisa;
    }
    protected static void alterar(){
        System.out.println("Insira o Cpf do professor: ");
        String dadoBusca = Input.nextLine();

        Professor profNovo = pesquisaEquals(listaProfessores, dadoBusca.toLowerCase(), "cpf");
        if(profNovo == null){
            System.out.println("\nNenhum professor encontrado !!\n");
        }else{
            setarDados(profNovo);
            System.out.println("\nProfessor alterado com sucesso !!\n");
        }
    }
    protected static void remover(){
        System.out.print("Insira o Cpf do professor: ");
        String dadoBusca = Input.nextLine();

        Professor profRemove = pesquisaEquals(listaProfessores, dadoBusca.toLowerCase(), "cpf");
        if(profRemove == null){
            System.out.println("\nNenhum professor encontrado !!\n");
        }else{
            System.out.println("Deseja realmente remover o professor? S/N ");
            profRemove.exibirInformacoes();
            System.out.print("R: ");
            String resp = Input.nextLine();
            if(MenusUtils.confirmacaoSim(resp)){
                listaProfessores.remove(profRemove);
                MenusUtils.remocaoSucesso();
            }else {
                System.out.println("\nOperação cancelada !!\n");
            }
        }
    }
    protected static Professor pesquisaEquals(ArrayList<Professor> listaProfessores, String busca, String tipoBusca){
        switch (tipoBusca) {
            case "cpf" -> {
                for (Professor listaFuncionario : listaProfessores) {
                    if (listaFuncionario.getCpf().equals(busca)) {
                        return listaFuncionario;
                    }
                }
            }
            case "nome" -> {
                for (Professor listaFuncionario : listaProfessores) {
                    if (listaFuncionario.getNome().toLowerCase().equals(busca)) {
                        return listaFuncionario;
                    }
                }
            }
            case "nomecpf" -> {
                for (Professor listaFuncionario : listaProfessores) {
                    if (listaFuncionario.getNome().toLowerCase().equals(busca) || listaFuncionario.getCpf().equals(busca)) {
                        return listaFuncionario;
                    }
                }
            }
        }
        return null;
    }
    protected static void menuListar(){
        System.out.println("\n1 - Crescente \n2 - Decrescente\n3 - VOLTAR");
        System.out.print("R: ");
        int resp = Input.nextInt();
        switch (resp){
            case 1 -> inverterOrdemLista(listaProfessores, "crescente");
            case 2 -> inverterOrdemLista(listaProfessores, "decrescente");
            default -> System.out.println("\nEscolha Inválida !!\n");
        }
    }
    protected static void inverterOrdemLista(ArrayList<Professor> listaProfessores , String ordem){
        ArrayList<Professor> listaOrdem;
        listaOrdem = listaProfessores;

        if (ordem.equals("crescente")) {
            Collections.sort(listaOrdem);
        }
        if (ordem.equals("decrescente")){
            listaProfessores.sort(Collections.reverseOrder());
        }
        listar(listaOrdem);
    }
    protected static void listar(ArrayList<Professor> listaProfessores){

        if(listaProfessores.isEmpty()){
            System.out.println("\nA lista está vazia !!\n");
            return;
        }

        System.out.println("\nProfessores: ");
        for (Professor listaProfessor : listaProfessores) {
            listaProfessor.exibirInformacoes();
            MenusUtils.pularLinha();
        }
    }

}
