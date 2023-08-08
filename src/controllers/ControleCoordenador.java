package controllers;

import entities.Coordenador;
import util.Input;
import util.MenusUtils;

import java.util.ArrayList;
import java.util.Collections;

public class ControleCoordenador {
    protected static ArrayList<Coordenador> listaCoordenadores = new ArrayList<>();

    public static void menuControleCoordenador(){
        boolean travaTela = true;
        do{
            System.out.println("===Controle Coordenador===");
            System.out.println("| 1 - Buscar             |");
            System.out.println("| 2 - Listar             |");
            System.out.println("| 0 - VOLTAR             |");
            System.out.println("==========================");
            System.out.print("Escolha: ");
            int escolha = Input.nextInt();
            switch (escolha) {
                case 1 -> buscar();
                case 2 -> menuListar();
                case 0 -> travaTela = false;
                default -> MenusUtils.opcaoInvalida();
            }
        }while(travaTela);
    }
    protected static void buscar(){
        System.out.println("Insira o nome/cpf do professor: ");
        String dadoBusca = Input.nextLine();

        ArrayList<Coordenador> resultado = pesquisaContains(listaCoordenadores, dadoBusca.toLowerCase());

        if(resultado.isEmpty()){
            System.out.println("Nenhum coordenador foi encontrado !!");
        }else{
            listar(resultado);
        }
    }
    protected static ArrayList<Coordenador> pesquisaContains(ArrayList<Coordenador> listaCoordenadores, String busca){
        ArrayList<Coordenador> resultadoPesquisa = new ArrayList<>();

        for (Coordenador listaCoordenador: listaCoordenadores){
            if (listaCoordenador.getNome().toLowerCase().contains(busca) || listaCoordenador.getCpf().contains(busca)){
                resultadoPesquisa.add(listaCoordenador);
            }
        }
        return resultadoPesquisa;
    }
    protected static void menuListar(){
        System.out.println("\n1 - Crescente \n2 - Decrescente\n3 - VOLTAR");
        System.out.print("R: ");
        int resp = Input.nextInt();
        switch (resp){
            case 1 -> inverterOrdemLista(listaCoordenadores, "crescente");
            case 2 -> inverterOrdemLista(listaCoordenadores, "decrescente");
            default -> System.out.println("\nEscolha Inválida !!\n");
        }
    }
    protected static void inverterOrdemLista(ArrayList<Coordenador> listaCoordenadores , String ordem){
        ArrayList<Coordenador> listaOrdem;
        listaOrdem = listaCoordenadores;

        if (ordem.equals("crescente")) {
            Collections.sort(listaOrdem);
        }
        if (ordem.equals("decrescente")){
            listaCoordenadores.sort(Collections.reverseOrder());
        }
        listar(listaOrdem);
    }
    protected static void listar(ArrayList<Coordenador> listaCoordenadores){
        if(listaCoordenadores.isEmpty()){
            System.out.println("\nA lista está vazia !!\n");
            return;
        }
        System.out.println("\nCoordenadores: ");
        for (Coordenador listaCoordenador : listaCoordenadores){
            listaCoordenador.exibirInformacoes();
            MenusUtils.pularLinha();
        }
    }
}
