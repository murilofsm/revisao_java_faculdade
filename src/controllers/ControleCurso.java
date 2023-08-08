package controllers;

import entities.Coordenador;
import entities.Curso;
import entities.Professor;
import util.Input;
import util.MenusUtils;
import java.util.ArrayList;

public class ControleCurso {
    protected static ArrayList<Curso> listaCursos = new ArrayList<>();
    public static void menuControleCurso(){
        boolean travaTela = true;
        do{
            System.out.println("===Controle Cursos===");
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
                case 5 -> listar(listaCursos);
                case 0 -> travaTela = false;
                default -> MenusUtils.opcaoInvalida();
            }
        }while(travaTela);
    }
    protected static void cadastrar(){
        Curso curso = new Curso();
        setarDados(curso);
        listaCursos.add(curso);
        System.out.println("\nCurso cadastrado com sucesso !!\n");
    }
    protected static void setarDados(Curso curso){
        System.out.print("Nome do Curso: ");
        curso.setNome(Input.nextLine());
        System.out.print("Carga horária: ");
        curso.setCargaHoraria(Input.nextInt());
        System.out.print("Quantidade de Semestres: ");
        curso.setQtdSemestres(Input.nextInt());
        if(!ControleProfessor.listaProfessores.isEmpty()){
            System.out.println("Deseja adicionar um Coordenador? S/N  \n");
            System.out.print("R:");
            String resp = Input.nextLine();
            if(MenusUtils.confirmacaoSim(resp)){
                Coordenador novoCoord;
                int contTentativas = 1;
                do{
                    novoCoord = adicionarCoordCurso(curso);
                    if (novoCoord != null){
                        curso.setCoordenador(novoCoord);
                    }else {

                        if(contTentativas == 3){
                            System.out.println("\nNão foi possível Adicionar o coordenador !! Número de tentativas excedidas !!\n");
                            break;
                        }
                        System.out.println("\nProfessor não encontrado !! Tente novamente !!\n");
                        contTentativas++;
                    }
                }while (novoCoord == null);

            }
        }else {
            curso.setCoordenador(new Coordenador());
        }
    }
    protected static Coordenador adicionarCoordCurso(Curso cursoPesquisa){
        Coordenador coord = new Coordenador();
        System.out.print("Insira o Cpf do Professor que irá se transformar em Coordenador: ");
        String buscaCord = Input.nextLine();
        Professor prof = ControleProfessor.pesquisaEquals(ControleProfessor.listaProfessores, buscaCord, "cpf");

        if(prof == null){
            System.out.println("\nNovo coordenador não encontrado !!\n");
            return null;
        }else {
            coord.setNome(prof.getNome());
            coord.setCpf(prof.getCpf());
            coord.setFormacao(prof.getFormacao());
            coord.setCursoCoordenado(cursoPesquisa);
            ControleCoordenador.listaCoordenadores.add(coord);
        }
        return coord;
    }
    protected static void alterar(){
        System.out.println("Insira o curso que deseja alterar: ");
        String pesquisaAlterar = Input.nextLine();

        Curso cursoPesquisa = pesquisaCursoEquals(pesquisaAlterar.toLowerCase());

        if (cursoPesquisa == null){
            System.out.println("\nCurso não encontrado !!\n");
        }else{
            System.out.print("Deseja somente alterar o coordenador ao curso? S/N  R:");
            String resp = Input.nextLine();
            if(MenusUtils.confirmacaoSim(resp)){
                Coordenador novoCoord = adicionarCoordCurso(cursoPesquisa);
                if (novoCoord != null){
                    cursoPesquisa.setCoordenador(novoCoord);
                    System.out.println("\nCoordenador alterado com sucesso !!\n");
                }
            }else {
                setarDados(cursoPesquisa);
                System.out.println("\nCurso alterado com sucesso !!\n");
            }
        }
    }
    protected static void buscar(){
        System.out.print("Insira o nome do curso que deseja buscar: ");
        String nomeCurso = Input.nextLine();
        if(buscarListaCursos(nomeCurso.toLowerCase()).isEmpty()){
            System.out.println("Nenhum curso foi encontrado !! ");
        }else {
            System.out.println("Cursos encontrados: ");
            listar(buscarListaCursos(nomeCurso));
        }
    }
    protected static ArrayList<Curso> buscarListaCursos(String nomeCurso){
        ArrayList<Curso> resultadoBusca = new ArrayList<>();
        for (Curso listaCurso : listaCursos){
            if(listaCurso.getNome().toLowerCase().contains(nomeCurso)){
                resultadoBusca.add(listaCurso);
            }
        }
        return resultadoBusca;
    }
    protected static void remover(){
        System.out.println("Insira o nome do curso que deseja remover: ");
        String nomeRemocao = Input.nextLine();
        Curso resultadoPesquisa = pesquisaCursoEquals(nomeRemocao.toLowerCase());

        if(resultadoPesquisa == null){
            System.out.println("\nCurso não encontrado !!\n");
        }else {
            System.out.println("Deseja realmente remover o curso de " + resultadoPesquisa.getNome() + "? S/N");
            String resp = Input.nextLine();
            if(MenusUtils.confirmacaoSim(resp)){
                listaCursos.remove(resultadoPesquisa);
                System.out.println("\nCurso removido com sucesso !! \n");
            }else {
                System.out.println("\nOperação cancelada !!\n");
            }
        }
    }
    protected static Curso pesquisaCursoEquals(String nomeCurso){
        for (Curso listaCurso : listaCursos){
            if(listaCurso.getNome().toLowerCase().equals(nomeCurso)){
                return listaCurso;
            }
        }
        return null;
    }
    protected static void listar(ArrayList<Curso> listaCursos){
        if(listaCursos.isEmpty()){
            System.out.println("\nA lista está vazia !!\n");
            return;
        }
        System.out.println("Cursos: ");
        for(Curso listaCurso: listaCursos){
            listaCurso.exibirInformacoes(listaCurso);
            MenusUtils.pularLinha();
        }
    }
}
