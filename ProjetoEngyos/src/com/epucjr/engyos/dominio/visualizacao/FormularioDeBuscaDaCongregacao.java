package com.epucjr.engyos.dominio.visualizacao;

import java.util.ArrayList;
import java.util.List;

import com.epucjr.engyos.dominio.modelo.Congregacao;

public class FormularioDeBuscaDaCongregacao {
	
	/******************************
	 *	ATRIBUTOS
	 ******************************/
	private List<Congregacao> listaDeCongregacaoDaPagina;
	private String parametroDeBusca;
	private int paginaCorrente;
	private int quantidadeTotalDePaginas;
	private String mensagemStatus;
	

	/******************************
	 *	CONSTRUTOR
	 ******************************/
	
	public FormularioDeBuscaDaCongregacao() {
		this.listaDeCongregacaoDaPagina = new ArrayList<Congregacao>();
		this.parametroDeBusca = "";
		this.paginaCorrente = 1;
		this.quantidadeTotalDePaginas = 0;
		this.mensagemStatus = "";
	}
	
	public FormularioDeBuscaDaCongregacao(List<Congregacao> listaDeCongregacaoDaPagina, int paginaCorrente, int quantidadeTotalDePaginas){
		this.listaDeCongregacaoDaPagina = listaDeCongregacaoDaPagina;
		this.paginaCorrente = paginaCorrente;
		this.quantidadeTotalDePaginas = quantidadeTotalDePaginas;
		
	}
	
	/******************************
	 *	METODOS
	 ******************************/
	
	/******************************
	 *	GETTERS AND SETTERS
	 ******************************/

	public List<Congregacao> getListaDeCongregacaoDaPagina() {
		return listaDeCongregacaoDaPagina;
	}

	public void setListaDeCongregacaoDaPagina(
			List<Congregacao> listaDeCongregacaoDaPagina) {
		this.listaDeCongregacaoDaPagina = listaDeCongregacaoDaPagina;
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
