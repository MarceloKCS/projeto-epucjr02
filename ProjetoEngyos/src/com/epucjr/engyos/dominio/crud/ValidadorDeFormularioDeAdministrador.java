package com.epucjr.engyos.dominio.crud;

import java.util.HashMap;

/**
 *
 * @author Rogerio
 */
public class ValidadorDeFormularioDeAdministrador {

    private boolean formularioValido;
    private HashMap<String, String> errors;

    public ValidadorDeFormularioDeAdministrador() {
        this.formularioValido = false;
        this.errors = new HashMap<String, String>();
    }

    /******************************
     *	METODOS
     ******************************/
    public void definirCampoComErro(String nomeDoCampo, String valorDoCampo) {
        this.errors.put(nomeDoCampo, valorDoCampo);
    }

    public String obterCampoComErro(String nomeDoCampo) {
        if (this.errors.containsKey(nomeDoCampo)) {
            return this.errors.get(nomeDoCampo);
        } else {
            return "";
        }
    }

    public boolean verificarCampoComErro(String nomeDoCampo) {
        if (this.errors.containsKey(nomeDoCampo)) {
            return true;
        } else {
            return false;
        }
    }

    public void verificarCamposValidos(String nome, String cpf, String senha, String senhaConfirmacao) {
        this.setFormularioValido(true);

        if (nome == null || nome.trim().length() == 0) {
            this.definirCampoComErro("Nome", "Nome Obrigatório");
            this.setFormularioValido(false);
        }
        if (!ValidatorCpf.valido(cpf)) {
            this.definirCampoComErro("Cpf", "CPF Inválido");
            this.setFormularioValido(false);
        }

        if (senha == null || senha.trim().length() == 0) {
            this.errors.put("Senha", "Senha Inválida");
            this.setFormularioValido(false);
        }

        if (senhaConfirmacao == null || senhaConfirmacao.trim().length() == 0) {
            this.errors.put("SenhaConfirmacao", "Confirmacao De Senha Inválida");
            this.setFormularioValido(false);
        }

        this.verificarSenhaMatchSenhaConfirmacao(senha, senhaConfirmacao);
    }

    private void verificarSenhaMatchSenhaConfirmacao(String senha, String senhaConfirmacao) {

        if ((senha != null && senha.trim().length() > 0) && (senhaConfirmacao != null && senhaConfirmacao.trim().length() > 0)) {
            if (!senha.equals(senhaConfirmacao)) {
                this.errors.put("SenhaMatch", "Senha e Confirmação de Senha diferem");
                this.setFormularioValido(false);
            }

        }
    }

    /******************************
     *	GETTERS AND SETTERS
     ******************************/
    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }

    public boolean isFormularioValido() {
        return formularioValido;
    }

    public void setFormularioValido(boolean formularioValido) {
        this.formularioValido = formularioValido;
    }






}
