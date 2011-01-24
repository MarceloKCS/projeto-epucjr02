package com.epucjr.engyos.dominio.visualizacao;

import java.util.ArrayList;
import java.util.List;

import com.epucjr.engyos.dominio.modelo.Obreiro;

public class FormularioDeBuscaDoObreiro {
	
	/******************************
	 *	ATRIBUTOS
	 ******************************/
	private List<Obreiro> listaDeObreiroDaPagina;
	private String parametroDeBusca;
	private int paginaCorrente;
	private int quantidadeTotalDePaginas;
	private String mensagemStatus;

	/******************************
	 *	CONSTRUTOR
	 ******************************/
	public FormularioDeBuscaDoObreiro() {
		this.listaDeObreiroDaPagina = new ArrayList<Obreiro>();
		this.parametroDeBusca = "";
		this.paginaCorrente = 1;
		this.quantidadeTotalDePaginas = 0;
		this.mensagemStatus = "";
	}	
	
	public FormularioDeBuscaDoObreiro(List<Obreiro> listaDeObreiroDaPagina, int paginaCorrente, int quantidadeTotalDePaginas){
		this.listaDeObreiroDaPagina = listaDeObreiroDaPagina;
		this.paginaCorrente = paginaCorrente;
		this.quantidadeTotalDePaginas = quantidadeTotalDePaginas;
	}
	
	/******************************
	 *	METODOS
	 ******************************/
	
	/******************************
	 *	GETTERS AND SETTERS
	 ******************************/
	
	public String getParametroDeBusca() {
		return parametroDeBusca;
	}

	public void setParametroDeBusca(String parametroDeBusca) {
		this.parametroDeBusca = parametroDeBusca;
	}

/*	private void carregarDadosDoFormulario(ArrayList<Obreiro> paginaAtualDoObreiro){
		this.listaDeObreiroDaPagina = paginaAtualDoObreiro;
	}*/

	

	public int getPaginaCorrente() {
		return paginaCorrente;
	}

	public List<Obreiro> getListaDeObreiroDaPagina() {
		return listaDeObreiroDaPagina;
	}

	public void setListaDeObreiroDaPagina(List<Obreiro> listaDeObreiroDaPagina) {
		this.listaDeObreiroDaPagina = listaDeObreiroDaPagina;
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
