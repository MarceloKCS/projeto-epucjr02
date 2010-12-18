package com.epucjr.engyos.tecnologia.ferramentas;


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;



public class GeradorQueryLucene {
	private String searchQuery;
	QueryParser queryParser;
	Query luceneQuery;
	
	
	public GeradorQueryLucene() {
		searchQuery = "";
		queryParser = new QueryParser("nome", new StandardAnalyzer());
	}
	

	public void buscarObreiro(String nome, String cargo, String cpf, String congregacao){
		
		
		
		GeradorDeQuery geradorDeQuery = new GeradorDeQuery();
		
		searchQuery = geradorDeQuery.verificaNome(nome) + geradorDeQuery.verificaCargo(cargo) + geradorDeQuery.verificaCpf(cpf)
		+ geradorDeQuery.verificaCongregacao(congregacao);
		
		searchQuery = geradorDeQuery.removerAndFinal(searchQuery);
		
		
		
		
		
		System.out.println(searchQuery.toString());
		
		try{
			luceneQuery = queryParser.parse(searchQuery);
			System.out.println("lucene query: " + luceneQuery );
		}
		catch (ParseException e) {
			throw new RuntimeException("Unable to parse query: " + searchQuery, e);
		}
	}
	
	public void buscarCongregacao(String nome , String endereco){
       GeradorDeQuery geradorDeQuery = new GeradorDeQuery();
		
		searchQuery = geradorDeQuery.verificaCongregacaoNome(nome) + geradorDeQuery.verificaEndereco(endereco);
		
		searchQuery = geradorDeQuery.removerAndFinal(searchQuery);
		
		
		
		
		
		System.out.println(searchQuery.toString());
		
		try{
			luceneQuery = queryParser.parse(searchQuery);
			System.out.println("lucene query: " + luceneQuery );
		}
		catch (ParseException e) {
			throw new RuntimeException("Unable to parse query: " + searchQuery, e);
		}
		
	}

	public Query getLuceneQuery() {
		return luceneQuery;
	}

	public void setLuceneQuery(Query luceneQuery) {
		this.luceneQuery = luceneQuery;
	}
	
	public void buscarReuniao(String data , String hora, String local){
	       GeradorDeQuery geradorDeQuery = new GeradorDeQuery();
			
			searchQuery = geradorDeQuery.verificaData(data) + geradorDeQuery.verificaHora(hora) + geradorDeQuery.verificaLocal(local);
			
			searchQuery = geradorDeQuery.removerAndFinal(searchQuery);
			
			
			
			
			
			System.out.println(searchQuery.toString());
			
			try{
				luceneQuery = queryParser.parse(searchQuery);
				System.out.println("lucene query: " + luceneQuery );
			}
			catch (ParseException e) {
				throw new RuntimeException("Unable to parse query: " + searchQuery, e);
			}
			
		}

		
	
	

}

