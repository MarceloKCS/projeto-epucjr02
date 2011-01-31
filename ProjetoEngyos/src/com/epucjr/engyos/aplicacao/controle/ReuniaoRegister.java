package com.epucjr.engyos.aplicacao.controle;

import java.util.ArrayList;
import java.util.List;

import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.PresencaObreiro;

public class ReuniaoRegister {
	
	private String mensagemStatus;
	private boolean isOperacaoExecutada;
	
	
	public ReuniaoRegister() {
		this.mensagemStatus = "";
		this.isOperacaoExecutada = false;
	}
	
		
	public List<PresencaObreiro> inserirObreirosNaLista(List<Obreiro> listaDeObreiros){
		
		List<PresencaObreiro> listaDePresenca = new ArrayList<PresencaObreiro>();
		for(Obreiro obreiro : listaDeObreiros){
			listaDePresenca.add(new PresencaObreiro(obreiro));
		}		
		return listaDePresenca;
	}
	
	public List<PresencaObreiro> atualizarListaDePresencaDeObreiros(List<Obreiro> novaListaDeObreiros, List<PresencaObreiro> listaDePresenca){
		boolean obreiroRepetido = false;
		
		//Verifica se há elementos repetidos na atualização da lista e faz a insercao
		for(Obreiro obreiro : novaListaDeObreiros){
			for(PresencaObreiro presencaObreiro : listaDePresenca){
				if(presencaObreiro.getObreiro().getCpf().equals(obreiro.getCpf())){
					obreiroRepetido = true;
				}
			}
			if(!obreiroRepetido){
				listaDePresenca.add(new PresencaObreiro(obreiro));
			}
			obreiroRepetido = false;
		}
		
		return listaDePresenca;
	}	
	
	
	//Para Implementar estes pensar talvez na query???
	//TODO
	public void adicionarListaObreirosTabelaPresenca(){
		
	}
	
	public void atualizarListaObreirosTabelaPresenca(){
		
	}


	public String getMensagemStatus() {
		return mensagemStatus;
	}


	public void setMensagemStatus(String mensagemStatus) {
		this.mensagemStatus = mensagemStatus;
	}


	public boolean isOperacaoExecutada() {
		return isOperacaoExecutada;
	}


	public void setOperacaoExecutada(boolean isOperacaoExecutada) {
		this.isOperacaoExecutada = isOperacaoExecutada;
	}
	
	

}
