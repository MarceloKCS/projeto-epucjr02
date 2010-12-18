package com.epucjr.engyos.dominio.visualizacao;

import java.util.ArrayList;

import com.epucjr.engyos.dominio.modelo.Congregacao;

public class FormularioDeBuscaDaCongregacao {
	
	///////////////
	// ATRIBUTOS //
	///////////////
	private ArrayList<Congregacao> listaDeCongregacaoDaPagina;
	private int paginaTotal;

	////////////////
	// CONSTRUTOR //
	////////////////
	public FormularioDeBuscaDaCongregacao(ArrayList<Congregacao> paginaAtualDaCongregacao, int paginaTotal){
		this.listaDeCongregacaoDaPagina = new ArrayList<Congregacao>();
		this.paginaTotal = paginaTotal;
		if(paginaAtualDaCongregacao != null){
			this.carregarDadosDoFormulario(paginaAtualDaCongregacao);
		}
	}
	
	private void carregarDadosDoFormulario(ArrayList<Congregacao> paginaAtualDaCongregacao){
		this.listaDeCongregacaoDaPagina = paginaAtualDaCongregacao;
	}
	
	////////////
	// GETTER //
	////////////
	public ArrayList<Congregacao> getListaDeCongregacaoDaPagina() {
		return listaDeCongregacaoDaPagina;
	}
	public int getPaginaTotal(){
		return this.paginaTotal;
	}
}
