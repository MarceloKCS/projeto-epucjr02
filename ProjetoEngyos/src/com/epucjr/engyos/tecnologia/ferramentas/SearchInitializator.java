package com.epucjr.engyos.tecnologia.ferramentas;

import javax.persistence.EntityManager;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import com.epucjr.engyos.tecnologia.persistencia.EmFactory;


public class SearchInitializator {
	
	/************************
	 * ATRIBUTOS
	 ***********************/
	private EntityManager entityManager;
	private boolean operacaoExecutada;
	private String mensagemStatus;
	
	
	/************************
	 * CONSTRUTOR
	 ***********************/
	public SearchInitializator() {
		this.entityManager = EmFactory.getEntityManager();
	}
	
	
	/************************
	 * METODOS
	 ***********************/
	
	public void atualizarIndicesLucene(){
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.getEntityManager());
		try {
			fullTextEntityManager.createIndexer().startAndWait();
			this.setOperacaoExecutada(true);
			this.setMensagemStatus("Indices Atualizados");
		} catch (InterruptedException e) {
			this.setOperacaoExecutada(false);
			this.setMensagemStatus("Ocorreu um erro na atualização dos índices");
			e.printStackTrace();
		}
		//MassIndexer massIndexer = fullTextEntityManager.createIndexer();
		//massIndexer.startAndWait();

	}

	
	/************************
	 * GETTERS/SETTERS
	 ***********************/

	public boolean isOperacaoExecutada() {
		return operacaoExecutada;
	}


	public void setOperacaoExecutada(boolean operacaoExecutada) {
		this.operacaoExecutada = operacaoExecutada;
	}


	public String getMensagemStatus() {
		return mensagemStatus;
	}


	public void setMensagemStatus(String mensagemStatus) {
		this.mensagemStatus = mensagemStatus;
	}


	private EntityManager getEntityManager() {
		return entityManager;
	}


}
