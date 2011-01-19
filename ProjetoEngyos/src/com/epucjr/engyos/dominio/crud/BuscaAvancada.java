package com.epucjr.engyos.dominio.crud;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.lucene.queryParser.ParseException;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.ferramentas.GeradorQueryLucene;
import com.epucjr.engyos.tecnologia.persistencia.EmFactory;


public class BuscaAvancada {
	
	/************************
	 * ATRIBUTOS
	 ***********************/

	private EntityManager entityManager;
	private List<Obreiro> listaDeObreirosEncontrados;
	private List<Congregacao> listaDeCongregacoesEncontradas;
	private List<Reuniao> listaDeReunioesEncontradas;
	private boolean ocorrenciasEncontradas;
	private GeradorQueryLucene luceneQueryGenerator;
	private String mensagemStatus;
	

	/************************
	 * CONSTRUTOR
	 ***********************/

	public BuscaAvancada() {
		entityManager = EmFactory.getEntityManager();
		this.listaDeObreirosEncontrados = new ArrayList<Obreiro>();
		this.listaDeCongregacoesEncontradas = new ArrayList<Congregacao>();
		this.listaDeReunioesEncontradas = new ArrayList<Reuniao>();
		this.luceneQueryGenerator = new GeradorQueryLucene();
		this.ocorrenciasEncontradas = false;
		this.mensagemStatus = "";

	}
	
	/************************
	 * METODOS
	 ***********************/

	public List<Obreiro> buscarObreiros(String nome, String cargo, String cpf, String congregacao){
		List<Obreiro> listaDeObreiros = null;
		if(!nome.equals("")){
			GeradorQueryLucene testeluceneQ = new GeradorQueryLucene();
			try {
				testeluceneQ.buscarObreiro(nome, cargo, cpf, congregacao);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
			Query query = fullTextEntityManager.createFullTextQuery(testeluceneQ.getLuceneQuery(), Obreiro.class);
			//query.setFirstResult(paginacao.getFirstResult()).setMaxResults(1);
			// JOptionPane.showMessageDialog(null,"TesteHiberSearchUtil setFirstResult: "+ paginacao.getFirstResult());
			listaDeObreiros = query.getResultList();
		}
		else{
			listaDeObreiros = this.buscarTodosObreiros();
		}
		//System.out.println (listaDeObreiros.get(0).getNome());

		return listaDeObreiros;


	}

	public List<Congregacao> buscarCongregacao(String nome, String endereco){
		List<Congregacao> listaDeCongregacao;
		if(!nome.equals("")){
			GeradorQueryLucene testeluceneQ = new GeradorQueryLucene();
			testeluceneQ.buscarCongregacao(nome, endereco);
	
	
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
			Query query = fullTextEntityManager.createFullTextQuery(testeluceneQ.getLuceneQuery(), Congregacao.class);
			//query.setFirstResult(paginacao.getFirstResult()).setMaxResults(1);
			// JOptionPane.showMessageDialog(null,"TesteHiberSearchUtil setFirstResult: "+ paginacao.getFirstResult());
			listaDeCongregacao = query.getResultList();
		}
		else{
			listaDeCongregacao = this.buscarTodasCongregacoes();
		}

		System.out.println (listaDeCongregacao.get(0).getNome());

		return listaDeCongregacao;


	}

	public List<Reuniao> buscarReuniao(String data, String hora, String local){
		List<Reuniao> listaDeReuniao;
		
		if(!data.equals("00000000")){
			GeradorQueryLucene testeluceneQ = new GeradorQueryLucene();
			testeluceneQ.buscarReuniao(data, hora, local);
	
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
			Query query = fullTextEntityManager.createFullTextQuery(testeluceneQ.getLuceneQuery(), Reuniao.class);
			//query.setFirstResult(paginacao.getFirstResult()).setMaxResults(1);
			// JOptionPane.showMessageDialog(null,"TesteHiberSearchUtil setFirstResult: "+ paginacao.getFirstResult());
			listaDeReuniao = query.getResultList();
		}
		else{
			listaDeReuniao = this.buscarTodasReunioes();
		}


		return listaDeReuniao;


	}

	public List<Congregacao> buscarTodasCongregacoes(){

		GeradorQueryLucene testeluceneQ = new GeradorQueryLucene();


		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		//Query query = fullTextEntityManager.createFullTextQuery(testeluceneQ.getLuceneQuery(), Congregacao.class);
		//query.setFirstResult(paginacao.getFirstResult()).setMaxResults(1);
		// JOptionPane.showMessageDialog(null,"TesteHiberSearchUtil setFirstResult: "+ paginacao.getFirstResult());
		List<Congregacao> listaDeCongregacao = new ArrayList<Congregacao>();


		listaDeCongregacao = entityManager.createQuery("from Congregacao").getResultList();



		return listaDeCongregacao;



	}

	public List<Obreiro> buscarTodosObreiros(){

		GeradorQueryLucene testeluceneQ = new GeradorQueryLucene();


		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		List<Obreiro> listaDeObreiro = new ArrayList<Obreiro>();


		listaDeObreiro = entityManager.createQuery("from Obreiro").getResultList();



		return listaDeObreiro;



	}

	public List<Reuniao> buscarTodasReunioes(){

		GeradorQueryLucene testeluceneQ = new GeradorQueryLucene();


		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		List<Reuniao> listaDeReuniao = new ArrayList<Reuniao>();


		listaDeReuniao = entityManager.createQuery("from Reuniao").getResultList();



		return listaDeReuniao;

	}
	
	
	/************************
	 * GETTERS/SETTERS
	 ***********************/

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Obreiro> getListaDeObreirosEncontrados() {
		return listaDeObreirosEncontrados;
	}

	public void setListaDeObreirosEncontrados(
			List<Obreiro> listaDeObreirosEncontrados) {
		this.listaDeObreirosEncontrados = listaDeObreirosEncontrados;
	}

	public List<Congregacao> getListaDeCongregacoesEncontradas() {
		return listaDeCongregacoesEncontradas;
	}

	public void setListaDeCongregacoesEncontradas(
			List<Congregacao> listaDeCongregacoesEncontradas) {
		this.listaDeCongregacoesEncontradas = listaDeCongregacoesEncontradas;
	}

	public List<Reuniao> getListaDeReunioesEncontradas() {
		return listaDeReunioesEncontradas;
	}

	public void setListaDeReunioesEncontradas(
			List<Reuniao> listaDeReunioesEncontradas) {
		this.listaDeReunioesEncontradas = listaDeReunioesEncontradas;
	}

	public boolean isOcorrenciasEncontradas() {
		return ocorrenciasEncontradas;
	}

	public void setOcorrenciasEncontradas(boolean ocorrenciasEncontradas) {
		this.ocorrenciasEncontradas = ocorrenciasEncontradas;
	}

	public GeradorQueryLucene getLuceneQueryGenerator() {
		return luceneQueryGenerator;
	}

	public void setLuceneQueryGenerator(GeradorQueryLucene luceneQueryGenerator) {
		this.luceneQueryGenerator = luceneQueryGenerator;
	}

	public String getMensagemStatus() {
		return mensagemStatus;
	}

	public void setMensagemStatus(String mensagemStatus) {
		this.mensagemStatus = mensagemStatus;
	}
	
	

	



}
