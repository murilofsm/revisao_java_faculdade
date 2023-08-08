package controllers;

import entities.Pessoa;
import util.Input;

public class ControlePessoa {
    protected static void setarDados(Pessoa pessoa) {
        System.out.print("Nome: ");
        pessoa.setNome(Input.nextLine());
        System.out.print("CPF: ");
        pessoa.setCpf(Input.nextLine());
        System.out.print("Data de nascimento: ");
        pessoa.setDataNascimento(Input.nextLocalDate());
        pessoa.setEndereco(ControleEndereco.setarDadosEndereco());
    }
}
