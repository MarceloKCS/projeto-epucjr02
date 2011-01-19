package com.epucjr.engyos.teste;

import java.util.List;

import com.epucjr.engyos.dominio.crud.BuscaAvancada;
import com.epucjr.engyos.dominio.modelo.Obreiro;

public class SearchTest_B {
	
	public static void main(String[] args){
		SearchTest_B.buscarObreiroTest();
		
	}
	
	public static void buscarObreiroTest(){
		BuscaAvancada buscaAvancada = new BuscaAvancada();
		
		List<Obreiro> listaDeObreiro = buscaAvancada.buscarObreiros("Rogério", null, null, null);
		
		System.out.println("Resultados = " + listaDeObreiro.size());
		
		for(Obreiro obreiro : listaDeObreiro){
			System.out.println("nome Obreiro = " + obreiro.getNome());
		}
		
	}

}
