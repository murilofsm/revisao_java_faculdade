package util;

import java.util.ArrayList;

public class MenusUtils {
    public static void cadastroRealizadoComSucesso(){
        System.out.println("\nCadastro realizado com sucesso !!\n");
    }

    public static void opcaoInvalida(){
        System.out.println("\nOpção inválida! Tente novamente!!\n");
    }
    public static void usuarioNaoEncontrado(){
        System.out.println("\nUsuário não encontrado !!\n");
    }
    public static void remocaoSucesso(){
        System.out.println("\nRemoção ocorrida com sucesso !!\n");
    }
    public static void pularLinha(){
        System.out.println();
    }
    public static boolean confirmacaoSim(String resp){
        return resp.equalsIgnoreCase("sim") || resp.equalsIgnoreCase("s");
    }
}
