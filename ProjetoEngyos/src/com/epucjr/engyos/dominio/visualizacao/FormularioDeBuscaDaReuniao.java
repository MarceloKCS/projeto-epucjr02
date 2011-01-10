package com.epucjr.engyos.dominio.visualizacao;

import java.util.ArrayList;

import com.epucjr.engyos.dominio.modelo.Reuniao;

public class FormularioDeBuscaDaReuniao {
	
	///////////////
	// ATRIBUTOS //
	///////////////
	private ArrayList<Reuniao> listaDeReuniaoDaPagina;
	private int paginaTotal;

	////////////////
	// CONSTRUTOR //
	////////////////
	public FormularioDeBuscaDaReuniao(ArrayList<Reuniao> paginaAtualDaReuniao, int paginaTotal){
		this.listaDeReuniaoDaPagina = new ArrayList<Reuniao>();
		this.paginaTotal = paginaTotal;
		if(paginaAtualDaReuniao != null){
			this.carregarDadosDoFormulario(paginaAtualDaReuniao);
		}
	}
	
	private void carregarDadosDoFormulario(ArrayList<Reuniao> paginaAtualDaReuniao){
		this.listaDeReuniaoDaPagina = paginaAtualDaReuniao;
	}
	
	////////////
	// GETTER //
	////////////
	public ArrayList<Reuniao> getListaDeCongregacaoDaPagina() {
		return listaDeReuniaoDaPagina;
	}
	public int getPaginaTotal(){
		return this.paginaTotal;
	}
}
