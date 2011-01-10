package com.epucjr.engyos.dominio.crud;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.ferramentas.GeradorQueryLucene;
import com.epucjr.engyos.tecnologia.persistencia.EmFactory;


public class BuscaAvancada {

	EntityManager em;



	public BuscaAvancada() {
		em = EmFactory.getEntityManager();

	}

	public List<Obreiro> buscarObreiros(String nome, String cargo, String cpf, String congregacao){
		List<Obreiro> listaDeObreiros;
		if(!nome.equals("")){
			GeradorQueryLucene testeluceneQ = new GeradorQueryLucene();
			testeluceneQ.buscarObreiro(nome, cargo, cpf, congregacao);
	
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
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
	
	
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
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
	
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
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


		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
		//Query query = fullTextEntityManager.createFullTextQuery(testeluceneQ.getLuceneQuery(), Congregacao.class);
		//query.setFirstResult(paginacao.getFirstResult()).setMaxResults(1);
		// JOptionPane.showMessageDialog(null,"TesteHiberSearchUtil setFirstResult: "+ paginacao.getFirstResult());
		List<Congregacao> listaDeCongregacao = new ArrayList<Congregacao>();


		listaDeCongregacao = em.createQuery("from Congregacao").getResultList();



		return listaDeCongregacao;



	}

	public List<Obreiro> buscarTodosObreiros(){

		GeradorQueryLucene testeluceneQ = new GeradorQueryLucene();


		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
		List<Obreiro> listaDeObreiro = new ArrayList<Obreiro>();


		listaDeObreiro = em.createQuery("from Obreiro").getResultList();



		return listaDeObreiro;



	}

	public List<Reuniao> buscarTodasReunioes(){

		GeradorQueryLucene testeluceneQ = new GeradorQueryLucene();


		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
		List<Reuniao> listaDeReuniao = new ArrayList<Reuniao>();


		listaDeReuniao = em.createQuery("from Reuniao").getResultList();



		return listaDeReuniao;



	}



}
