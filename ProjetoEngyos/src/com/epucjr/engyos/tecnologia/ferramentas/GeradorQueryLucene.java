package com.epucjr.engyos.tecnologia.ferramentas;


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;



public class GeradorQueryLucene {
	private String searchQuery;
	QueryParser queryParser;
	Query luceneQuery;


	@SuppressWarnings("deprecation")
	public GeradorQueryLucene() {
		searchQuery = "";
		queryParser = new QueryParser("nome", new StandardAnalyzer());
	}


	public void gerarQueryLuceneBuscarObreiro(String parametroBusca) throws ParseException{

		if(parametroBusca == null || parametroBusca.equals("")){
			parametroBusca = "\"\"";
		}

		/*GeradorDeQuery geradorDeQuery = new GeradorDeQuery();

		searchQuery = geradorDeQuery.verificaNome(nome) + geradorDeQuery.verificaCargo(cargo) + geradorDeQuery.verificaCpf(cpf)
		+ geradorDeQuery.verificaCongregacao(congregacao);

		searchQuery = geradorDeQuery.removerAndFinal(searchQuery);*/

		this.setSearchQuery("nome:" + parametroBusca + " OR cargo:" + parametroBusca + " OR congregacao.nome:" + parametroBusca);

		this.setLuceneQuery(this.getQueryParser().parse(this.getSearchQuery()));
		System.out.println("GQL:33: " + this.getLuceneQuery().toString());
		System.out.println(searchQuery.toString());
		/*
		try{
			luceneQuery = queryParser.parse(searchQuery);
			System.out.println("lucene query: " + luceneQuery );
		}
		catch (ParseException e) {
			throw new RuntimeException("Unable to parse query: " + searchQuery, e);
		}*/
	}

	public void geraQueryLuceneBuscarCongregacao(String parametroBusca) throws ParseException{

		if(parametroBusca == null || parametroBusca.equals("")){
			parametroBusca = "\"\"";
		}

		this.setSearchQuery("nome:" + parametroBusca + " OR endereco: " + parametroBusca);

		this.setLuceneQuery(this.getQueryParser().parse(this.getSearchQuery()));

	}


	public void geraQueryLuceneBuscarReuniao(String parametroBusca) throws ParseException{

		if(parametroBusca == null || parametroBusca.equals("")){
			parametroBusca = "\"\"";
		}

		this.setSearchQuery("local:" + parametroBusca + " OR data:" + parametroBusca + " OR hora:" + parametroBusca);

		this.setLuceneQuery(this.getQueryParser().parse(this.getSearchQuery()));
		
		System.out.println("GQL:33: " + this.getLuceneQuery().toString());
		System.out.println(searchQuery.toString());


	}


	public String getSearchQuery() {
		return searchQuery;
	}


	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}


	public QueryParser getQueryParser() {
		return queryParser;
	}


	public void setQueryParser(QueryParser queryParser) {
		this.queryParser = queryParser;
	}

	public Query getLuceneQuery() {
		return luceneQuery;
	}

	public void setLuceneQuery(Query luceneQuery) {
		this.luceneQuery = luceneQuery;
	}

}

