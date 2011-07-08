package com.epucjr.engyos.dominio.visualizacao;

import com.epucjr.engyos.dominio.modelo.Administrador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rogerio
 */
public class FormularioDeBuscaDeAdministrador {


    /******************************
     *	ATRIBUTOS
     ******************************/
    private List<Administrador> listaDeAdministradorDaPagina;
    private String parametroDeBusca;
    private int paginaCorrente;
    private int quantidadeTotalDePaginas;
    private String mensagemStatus;

    /******************************
     *	CONSTRUTOR
     ******************************/
    public FormularioDeBuscaDeAdministrador() {
        this.listaDeAdministradorDaPagina = new ArrayList<Administrador>();
        this.parametroDeBusca = "";
        this.paginaCorrente = 1;
        this.quantidadeTotalDePaginas = 0;
        this.mensagemStatus = "";
    }

    public FormularioDeBuscaDeAdministrador(List<Administrador> listaDeAdministradorDaPagina, int paginaCorrente, int quantidadeTotalDePaginas) {
        this.listaDeAdministradorDaPagina = listaDeAdministradorDaPagina;
        this.paginaCorrente = paginaCorrente;
	this.quantidadeTotalDePaginas = quantidadeTotalDePaginas;
    }

    /******************************
     *	METODOS
     ******************************/
    /******************************
     *	GETTERS AND SETTERS
     ******************************/

    public List<Administrador> getListaDeAdministradorDaPagina() {
        return listaDeAdministradorDaPagina;
    }

    public void setListaDeAdministradorDaPagina(List<Administrador> listaDeAdministradorDaPagina) {
        this.listaDeAdministradorDaPagina = listaDeAdministradorDaPagina;
    }

    public String getMensagemStatus() {
        return mensagemStatus;
    }

    public void setMensagemStatus(String mensagemStatus) {
        this.mensagemStatus = mensagemStatus;
    }

    public int getPaginaCorrente() {
        return paginaCorrente;
    }

    public void setPaginaCorrente(int paginaCorrente) {
        this.paginaCorrente = paginaCorrente;
    }

    public String getParametroDeBusca() {
        return parametroDeBusca;
    }

    public void setParametroDeBusca(String parametroDeBusca) {
        this.parametroDeBusca = parametroDeBusca;
    }

    public int getQuantidadeTotalDePaginas() {
        return quantidadeTotalDePaginas;
    }

    public void setQuantidadeTotalDePaginas(int quantidadeTotalDePaginas) {
        this.quantidadeTotalDePaginas = quantidadeTotalDePaginas;
    }

}
