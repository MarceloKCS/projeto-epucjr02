package com.epucjr.engyos.dominio.visualizacao;

import com.epucjr.engyos.dominio.administrativo.ValidadorLogin;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Projeto Engyos Team
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class PaginaDeLogin {

    /******************************
     *	ATRIBUTOS
     ******************************/
    private String loginUsuario;
    private String senha;
    private String mensagemStatus;
    private HashMap<String, String> camposPreenchidos;
    private ValidadorLogin validadorLogin;

    /******************************
     *	CONSTRUTOR
     ******************************/
    /**
     * Class constructor
     */
    public PaginaDeLogin() {
        this.camposPreenchidos = new HashMap<String, String>();
        this.loginUsuario = "";
        this.senha = "";
        this.mensagemStatus = "";
        this.validadorLogin = new ValidadorLogin();
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
    private void definirCampoPreenchido(String nomeDoCampo, String valorDoCampo) {
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
        //TODO: Por enquanto o login está definido como CPF do administrador
        String login = httpServletRequest.getParameter("login");

        if(login != null && !login.equals("")){
            this.definirCampoPreenchido("login", login);
        }
    }


    /******************************
     *	GETTERS AND SETTERS
     ******************************/
    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getMensagemStatus() {
        return mensagemStatus;
    }

    public void setMensagemStatus(String mensagemStatus) {
        this.mensagemStatus = mensagemStatus;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ValidadorLogin getValidadorLogin() {
        return validadorLogin;
    }

    public void setValidadorLogin(ValidadorLogin validadorLogin) {
        this.validadorLogin = validadorLogin;
    }

    
}
