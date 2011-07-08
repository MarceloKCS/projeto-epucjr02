/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epucjr.engyos.dominio.visualizacao;

import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeAdministrador;
import com.epucjr.engyos.dominio.modelo.Administrador;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Rogerio
 */
public class FormularioDeAdministrador {

    /******************************
     *	ATRIBUTOS
     ******************************/
    private HashMap<String, String> camposPreenchidos;
    private String mensagemStatus;
    private ValidadorDeFormularioDeAdministrador validadorDeFormularioDeAdministrador;

    /******************************
     *	CONSTRUTOR
     ******************************/
    /**
     * Class constructor
     */
    public FormularioDeAdministrador() {
        camposPreenchidos = new HashMap<String, String>();
        this.mensagemStatus = "";
        this.validadorDeFormularioDeAdministrador = new ValidadorDeFormularioDeAdministrador();
    }

    /******************************
     *	METODOS
     ******************************/
    /**
     * Metodo para definir o preenchimento de um campo do formulário, caso o
     * campo contenha dados, este será capturado e armazenado com basse no nome
     * do campo e os dados inseridos no campo
     *
     * @param nomeDoCampo O nome do campo no formulário
     * @param valorDoCampo Os dados inseridos no formulário
     */
    public void definirCampoPreenchido(String nomeDoCampo, String valorDoCampo) {
        this.camposPreenchidos.put(nomeDoCampo, valorDoCampo);
    }

    public String obterCampoPreenchido(String nomeDoCampo) {
        if (this.camposPreenchidos.containsKey(nomeDoCampo)) {
            return this.camposPreenchidos.get(nomeDoCampo);
        } else {
            return "";
        }
    }

    public boolean verificarCampoPreenchido(String nomeDoCampo) {
        if (this.camposPreenchidos.containsKey(nomeDoCampo)) {
            return true;
        } else {
            return false;
        }
    }

    public void definirDadosDeConfirmacao(String nomeDoCampo, String valorDoCampo) {
        this.camposPreenchidos.put(nomeDoCampo, valorDoCampo);
    }

    public String obterDadoDeConfirmacao(String nomeDoCampo) {
        if (this.camposPreenchidos.containsKey(nomeDoCampo)) {
            return this.camposPreenchidos.get(nomeDoCampo);
        } else {
            return "";
        }
    }

    public boolean verificarDadoDeConfirmacao(String nomeDoCampo) {
        if (this.camposPreenchidos.containsKey(nomeDoCampo)) {
            return true;
        } else {
            return false;
        }
    }


     public void definirCamposPreenchidosPeloUsuario(HttpServletRequest httpServletRequest) {
         String nome = httpServletRequest.getParameter("Nome");
         String cpf = httpServletRequest.getParameter("Cpf");

         if (nome != null && !nome.equals("")) {
            this.definirCampoPreenchido("Nome", nome);
        }

         if (cpf != null && !cpf.equals("")) {
            this.definirCampoPreenchido("Cpf", cpf);
        }
     }

    public void definirCamposPreenchidos(Administrador administrador) {
        String nome = administrador.obterNome();
        String cpf = administrador.obterCPF();
        String senha = administrador.obterSenha();


        if (nome != null && !nome.equals("")) {
            this.definirCampoPreenchido("Nome", nome);
        }

         if (cpf != null && !cpf.equals("")) {
            this.definirCampoPreenchido("Cpf", cpf);
        }

        if (senha != null && !senha.equals("")) {
            this.definirCampoPreenchido("Senha", senha);
        }
    }

    public void definirDadosDeConfirmacaoDeCadastro(String confirmacaoCadastro, String nome, String cpf) {
        this.definirDadosDeConfirmacao("confirmacao_cadastro", confirmacaoCadastro);
        this.definirDadosDeConfirmacao("confirmacao_nome", nome);
        this.definirDadosDeConfirmacao("confirmacao_cpf", cpf);
    }

    public void definirDadosDeConfirmacaoDeEdicao(String confirmacaoEdicao, String nome, String cpf, String msgSenha) {
        this.definirDadosDeConfirmacao("confirmacao_edicao", confirmacaoEdicao);
        this.definirDadosDeConfirmacao("confirmacao_nome", nome);
        this.definirDadosDeConfirmacao("confirmacao_cpf", cpf);
        this.definirDadosDeConfirmacao("confirmacao_senha", msgSenha);
    }

    public String getMensagemStatus() {
        return mensagemStatus;
    }

    public void setMensagemStatus(String mensagemStatus) {
        this.mensagemStatus = mensagemStatus;
    }

    public ValidadorDeFormularioDeAdministrador getValidadorDeFormularioDeAdministrador() {
        return validadorDeFormularioDeAdministrador;
    }

    public void setValidadorDeFormularioDeAdministrador(ValidadorDeFormularioDeAdministrador validadorDeFormularioDeAdministrador) {
        this.validadorDeFormularioDeAdministrador = validadorDeFormularioDeAdministrador;
    }

    /******************************
     *	GETTERS AND SETTERS
     ******************************/

    
}
