package com.epucjr.engyos.dominio.visualizacao;

import java.util.ArrayList;
import java.util.List;

import com.epucjr.engyos.dominio.modelo.Reuniao;

public class FormularioDeBuscaDaReuniao {
	
	/******************************
	 *	ATRIBUTOS
	 ******************************/
	private List<Reuniao> listaDeReuniaoDaPagina;
	private String parametroDeBusca;
	private int paginaCorrente;
	private int quantidadeTotalDePaginas;
	private String mensagemStatus;

	/******************************
	 *	CONSTRUTOR
	 ******************************/
	
	public FormularioDeBuscaDaReuniao() {
		this.listaDeReuniaoDaPagina = new ArrayList<Reuniao>();
		this.parametroDeBusca = "";
		this.paginaCorrente = 1;
		this.quantidadeTotalDePaginas = 0;
		this.mensagemStatus = "";
	}
	
	public FormularioDeBuscaDaReuniao(List<Reuniao> listaDeReuniaoDaPagina, int paginaCorrente, int quantidadeTotalDePaginas){
		this.listaDeReuniaoDaPagina = listaDeReuniaoDaPagina;		
		this.paginaCorrente = paginaCorrente;
		this.quantidadeTotalDePaginas = quantidadeTotalDePaginas;
		
	}
	
	/******************************
	 *	METODOS
	 ******************************/
	
	/******************************
	 *	GETTERS AND SETTERS
	 ******************************/

	public List<Reuniao> getListaDeReuniaoDaPagina() {
		return listaDeReuniaoDaPagina;
	}

	public void setListaDeReuniaoDaPagina(List<Reuniao> listaDeReuniaoDaPagina) {
		this.listaDeReuniaoDaPagina = listaDeReuniaoDaPagina;
	}

	public String getParametroDeBusca() {
		return parametroDeBusca;
	}

	public void setParametroDeBusca(String parametroDeBusca) {
		this.parametroDeBusca = parametroDeBusca;
	}

	public int getPaginaCorrente() {
		return paginaCorrente;
	}

	public void setPaginaCorrente(int paginaCorrente) {
		this.paginaCorrente = paginaCorrente;
	}

	public int getQuantidadeTotalDePaginas() {
		return quantidadeTotalDePaginas;
	}

	public void setQuantidadeTotalDePaginas(int quantidadeTotalDePaginas) {
		this.quantidadeTotalDePaginas = quantidadeTotalDePaginas;
	}

	public String getMensagemStatus() {
		return mensagemStatus;
	}

	public void setMensagemStatus(String mensagemStatus) {
		this.mensagemStatus = mensagemStatus;
	}	
	
	
}
