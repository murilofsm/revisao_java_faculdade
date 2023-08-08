package application;

import controllers.*;
import util.Input;
import util.MenusUtils;

public class Main {
    public static void main(String[] args) {
        boolean travaTela = true;
        do{
            System.out.println("=========Menu principal==========");
            System.out.println("| 1 - Controle Aluno            |");
            System.out.println("| 2 - Controle Funcionario      |");
            System.out.println("| 3 - Controle Professor        |");
            System.out.println("| 4 - Controle Coordenador      |");
            System.out.println("| 5 - Controle Curso            |");
            System.out.println("| 0 - SAIR                      |");
            System.out.println("=================================");
            System.out.print("Escolha: ");
            int escolha = Input.nextInt();
            switch (escolha) {
                case 1 -> ControleAluno.menuControleAluno();
                case 2 -> ControleFuncionario.menuControleFuncionario();
                case 3 -> ControleProfessor.menuControleProfessor();
                case 4 -> ControleCoordenador.menuControleCoordenador();
                case 5 -> ControleCurso.menuControleCurso();
                case 0 -> {
                        System.out.println("Saindo...");
                        travaTela = false;
                }
                default -> MenusUtils.opcaoInvalida();
            }
        }while(travaTela);
    }
}
