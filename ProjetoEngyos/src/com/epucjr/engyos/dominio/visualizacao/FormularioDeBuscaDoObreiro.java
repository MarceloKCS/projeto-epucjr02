package com.epucjr.engyos.dominio.visualizacao;

import java.util.ArrayList;

import com.epucjr.engyos.dominio.modelo.Obreiro;

public class FormularioDeBuscaDoObreiro {
	
	///////////////
	// ATRIBUTOS //
	///////////////
	private ArrayList<Obreiro> listaDeObreiroDaPagina;
	private int paginaTotal;

	////////////////
	// CONSTRUTOR //
	////////////////
	public FormularioDeBuscaDoObreiro(ArrayList<Obreiro> paginaAtualDaCongregacao, int paginaTotal){
		this.listaDeObreiroDaPagina = new ArrayList<Obreiro>();
		this.paginaTotal = paginaTotal;
		if(paginaAtualDaCongregacao != null){
			this.carregarDadosDoFormulario(paginaAtualDaCongregacao);
		}
	}
	
	private void carregarDadosDoFormulario(ArrayList<Obreiro> paginaAtualDoObreiro){
		this.listaDeObreiroDaPagina = paginaAtualDoObreiro;
	}
	
	////////////
	// GETTER //
	////////////
	public ArrayList<Obreiro> getListaDeCongregacaoDaPagina() {
		return listaDeObreiroDaPagina;
	}
	public int getPaginaTotal(){
		return this.paginaTotal;
	}
}
