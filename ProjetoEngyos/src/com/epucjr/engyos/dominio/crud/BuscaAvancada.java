package com.epucjr.engyos.dominio.crud;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
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
	private String mensagemStatus;
	private List<Obreiro> listaDeObreirosEncontrados;
	private List<Congregacao> listaDeCongregacoesEncontradas;
	private List<Reuniao> listaDeReunioesEncontradas;
	private boolean ocorrenciasEncontradas;
	private GeradorQueryLucene luceneQueryGenerator;
	private int quantidadeTotalResultados;
	private final int quantidadeDeResultadosPorPagina = 10;
	private int paginaCorrente;	
	private int quantidadeDePagina;
	private int primeiroResultadoCorrente;



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
		this.paginaCorrente = 1;
		this.quantidadeDePagina = 0;
		this.primeiroResultadoCorrente = 0;


	}

	/************************
	 * METODOS
	 ***********************/

	@SuppressWarnings("unchecked")
	public void buscarObreiros(String parametroBusca, int numeroPagina){

		if(!parametroBusca.equals("")){

			if(numeroPagina > 0){
				this.setPaginaCorrente(numeroPagina);
			}

			try {
				this.getLuceneQueryGenerator().gerarQueryLuceneBuscarObreiro(parametroBusca);
				FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.getEntityManager());
				FullTextQuery query = fullTextEntityManager.createFullTextQuery(this.luceneQueryGenerator.getLuceneQuery(), Obreiro.class);

				//Define a ordenação da lista obtida
				query.setSort(this.ordenaObreiroResult());

				//Definindo Quantidade Total de Resultados
				this.calcularQuantidadeTotalDeResultados(fullTextEntityManager);

				//Definindo a quantidade De Paginas
				this.calcularQuantidadeDePaginas(this.getQuantidadeTotalResultados());

				//Realizando a Paginação
				//Obtendo o primeiro Resultado da pagina do intervalo
				int primeiroResultadoBusca = (numeroPagina - 1) * this.getQuantidadeDeResultadosPorPagina() + 1;
				this.setPrimeiroResultadoCorrente(primeiroResultadoBusca);

				//Realizada a paginação dos resultados de busca
				this.setListaDeObreirosEncontrados(query.setFirstResult(primeiroResultadoBusca - 1).setMaxResults(this.getQuantidadeDeResultadosPorPagina()).getResultList());

				this.setOcorrenciasEncontradas(true);
				this.setMensagemStatus("Busca Realizada");

			} catch (ParseException e) {
				this.setOcorrenciasEncontradas(false);
				this.setMensagemStatus("Ocorreu algo na busca: \n erro cod:01");
				e.printStackTrace();
			}
		}
		else{
			this.buscarTodosObreiros(numeroPagina);
		}
		/*	if(!parametroBusca.equals("")){
			GeradorQueryLucene testeluceneQ = new GeradorQueryLucene();
			try {
				testeluceneQ.buscarObreiro(parametroBusca);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
			Query query = fullTextEntityManager.createFullTextQuery(testeluceneQ.getLuceneQuery(), Obreiro.class);
			listaDeObreiros = query.getResultList();
		}
		else{
			listaDeObreiros = this.buscarTodosObreiros();
		}*/
	}

	@SuppressWarnings("unchecked")
	public void buscarTodosObreiros(int numeroPagina){

		List<Obreiro> listaDeObreiro = new ArrayList<Obreiro>();

		//Pega todos os obreiros
		listaDeObreiro = this.getEntityManager().createQuery("from Obreiro order by nome").getResultList();
		//Definindo Quantidade Total de Resultados
		this.setQuantidadeTotalResultados(listaDeObreiro.size());

		//Definindo a quantidade De Paginas
		this.calcularQuantidadeDePaginas(this.getQuantidadeTotalResultados());

		//Realizando a Paginação
		//Obtendo o primeiro Resultado da pagina do intervalo
		int primeiroResultadoBusca = (numeroPagina - 1) * this.getQuantidadeDeResultadosPorPagina() + 1;
		this.setPrimeiroResultadoCorrente(primeiroResultadoBusca);

		//Obtendo o ultimo resultado no intervalo		
		int ultimoResultado = numeroPagina * this.getQuantidadeDeResultadosPorPagina();
		if(ultimoResultado > this.getQuantidadeTotalResultados()){
			ultimoResultado = this.getQuantidadeTotalResultados();
		}

		this.setListaDeObreirosEncontrados(listaDeObreiro.subList(primeiroResultadoBusca - 1, ultimoResultado));

		if(this.getListaDeObreirosEncontrados().size() > 0){
			this.setOcorrenciasEncontradas(true);
			this.setMensagemStatus("Busca Realizada");
		}
		else{
			this.setOcorrenciasEncontradas(false);
			this.setMensagemStatus("Não existem registros no banco de dados");
		}
		//Realizada a paginação dos resultados de busca
		//this.setListaDeObreirosEncontrados(query.setFirstResult(primeiroResultadoBusca - 1).setMaxResults(this.getQuantidadeDeResultadosPorPagina()).getResultList());

	}

	//Classe que defina a ordenação para o Hibernate Search a ser utilizado pelo FullTextQuery apenas
	public Sort ordenaObreiroResult(){
		Sort sort = null;
		//Define a partir de qual campo será ordenado
		SortField sortField = new SortField("nomeobr_sort", SortField.STRING);
		sort = new Sort(sortField);

		return sort;

	}

	//Classe que defina a ordenação para o Hibernate Search a ser utilizado pelo FullTextQuery apenas
	public Sort ordenaCongregacaoResult(){
		Sort sort = null;
		//Define a partir de qual campo será ordenado
		SortField sortField = new SortField("nomecong_sort", SortField.STRING);
		sort = new Sort(sortField);

		return sort;
	}

	//Classe que defina a ordenação para o Hibernate Search a ser utilizado pelo FullTextQuery apenas
	public Sort ordenaReuniaoResult(){
		Sort sort = null;
		//Define a partir de qual campo será ordenado
		SortField sortField = new SortField("nomecong_sort", SortField.STRING, true);
		sort = new Sort(sortField);

		return sort;
	}


	private void calcularQuantidadeDePaginas(int quantidadeTotalDeResultados){
		int numeroDePaginas = 0;
		numeroDePaginas = (int) Math.ceil( quantidadeTotalDeResultados / (double)this.getQuantidadeDeResultadosPorPagina() );
		this.setQuantidadeDePagina(numeroDePaginas);
	}

	private void calcularQuantidadeTotalDeResultados(FullTextEntityManager fullTextEntityManager) {		
		FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQueryGenerator.getLuceneQuery());		
		this.setQuantidadeTotalResultados(fullTextQuery.getResultSize());
	}

	@SuppressWarnings("unchecked")
	public void buscarCongregacao(String parametroBusca, int numeroPagina){

		if(!parametroBusca.equals("")){

			if(numeroPagina > 0){
				this.setPaginaCorrente(numeroPagina);
			}

			try {
				this.getLuceneQueryGenerator().geraQueryLuceneBuscarCongregacao(parametroBusca);
				FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.getEntityManager());
				FullTextQuery query = fullTextEntityManager.createFullTextQuery(this.luceneQueryGenerator.getLuceneQuery(), Congregacao.class);

				//Define a ordenação da lista obtida
				query.setSort(this.ordenaCongregacaoResult());

				//Definindo Quantidade Total de Resultados
				this.calcularQuantidadeTotalDeResultados(fullTextEntityManager);

				//Definindo a quantidade De Paginas
				this.calcularQuantidadeDePaginas(this.getQuantidadeTotalResultados());

				//Realizando a Paginação
				//Obtendo o primeiro Resultado da pagina do intervalo
				int primeiroResultadoBusca = (numeroPagina - 1) * this.getQuantidadeDeResultadosPorPagina() + 1;
				this.setPrimeiroResultadoCorrente(primeiroResultadoBusca);

				//Realizada a paginação dos resultados de busca
				this.setListaDeCongregacoesEncontradas(query.setFirstResult(primeiroResultadoBusca - 1).setMaxResults(this.getQuantidadeDeResultadosPorPagina()).getResultList());

				this.setOcorrenciasEncontradas(true);
				this.setMensagemStatus("Busca Realizada");

			} catch (ParseException e) {
				this.setOcorrenciasEncontradas(false);
				this.setMensagemStatus("Ocorreu algo na busca: \n erro cod:01");
				e.printStackTrace();
			}

		}
		else{
			this.buscarTodasCongregacoes(numeroPagina);
		}

	}


	@SuppressWarnings("unchecked")
	public void buscarTodasCongregacoes(int numeroPagina){

		List<Congregacao> listaDeCongregacoes = new ArrayList<Congregacao>();

		//Pega todos as Congregações
		listaDeCongregacoes = this.getEntityManager().createQuery("from Congregacao order by nome").getResultList();
		//Definindo Quantidade Total de Resultados
		this.setQuantidadeTotalResultados(listaDeCongregacoes.size());

		//Definindo a quantidade De Paginas
		this.calcularQuantidadeDePaginas(this.getQuantidadeTotalResultados());

		//Realizando a Paginação
		//Obtendo o primeiro Resultado da pagina do intervalo
		int primeiroResultadoBusca = (numeroPagina - 1) * this.getQuantidadeDeResultadosPorPagina() + 1;
		this.setPrimeiroResultadoCorrente(primeiroResultadoBusca);

		//Obtendo o ultimo resultado no intervalo		
		int ultimoResultado = numeroPagina * this.getQuantidadeDeResultadosPorPagina();
		if(ultimoResultado > this.getQuantidadeTotalResultados()){
			ultimoResultado = this.getQuantidadeTotalResultados();
		}

		this.setListaDeCongregacoesEncontradas(listaDeCongregacoes.subList(primeiroResultadoBusca - 1, ultimoResultado));

		if(this.getListaDeCongregacoesEncontradas().size() > 0){
			this.setOcorrenciasEncontradas(true);
			this.setMensagemStatus("Busca Realizada");
		}
		else{
			this.setOcorrenciasEncontradas(true);
			this.setMensagemStatus("Não existem registros no banco de dados");
		}

	}

	@SuppressWarnings("unchecked")
	public void buscarReuniao(String parametroBusca, int numeroPagina){
		if(!parametroBusca.equals("")){

			if(numeroPagina > 0){
				this.setPaginaCorrente(numeroPagina);
			}

			try {
				this.getLuceneQueryGenerator().geraQueryLuceneBuscarReuniao(parametroBusca);
				FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.getEntityManager());
				FullTextQuery query = fullTextEntityManager.createFullTextQuery(this.luceneQueryGenerator.getLuceneQuery(), Reuniao.class);

				//Define a ordenação da lista obtida
				//query.setSort(this.ordenaCongregacaoResult());

				//Definindo Quantidade Total de Resultados
				this.calcularQuantidadeTotalDeResultados(fullTextEntityManager);

				//Definindo a quantidade De Paginas
				this.calcularQuantidadeDePaginas(this.getQuantidadeTotalResultados());

				//Realizando a Paginação
				//Obtendo o primeiro Resultado da pagina do intervalo
				int primeiroResultadoBusca = (numeroPagina - 1) * this.getQuantidadeDeResultadosPorPagina() + 1;
				this.setPrimeiroResultadoCorrente(primeiroResultadoBusca);

				//Realizada a paginação dos resultados de busca
				this.setListaDeReunioesEncontradas(query.setFirstResult(primeiroResultadoBusca - 1).setMaxResults(this.getQuantidadeDeResultadosPorPagina()).getResultList());

				this.setOcorrenciasEncontradas(true);
				this.setMensagemStatus("Busca Realizada");

			} catch (ParseException e) {
				this.setOcorrenciasEncontradas(false);
				this.setMensagemStatus("Ocorreu algo na busca: \n erro cod:01");
				e.printStackTrace();
			}
		}
		else{
			this.buscarTodasReunioes(numeroPagina);
		}

	}


	@SuppressWarnings("unchecked")
	public void buscarTodasReunioes(int numeroPagina){


		List<Reuniao> listaDeReunioes = new ArrayList<Reuniao>();

		//Pega todos os obreiros
		listaDeReunioes = this.getEntityManager().createQuery("from Reuniao order by data desc").getResultList();
		//Definindo Quantidade Total de Resultados
		this.setQuantidadeTotalResultados(listaDeReunioes.size());

		//Definindo a quantidade De Paginas
		this.calcularQuantidadeDePaginas(this.getQuantidadeTotalResultados());

		//Realizando a Paginação
		//Obtendo o primeiro Resultado da pagina do intervalo
		int primeiroResultadoBusca = (numeroPagina - 1) * this.getQuantidadeDeResultadosPorPagina() + 1;
		this.setPrimeiroResultadoCorrente(primeiroResultadoBusca);

		//Obtendo o ultimo resultado no intervalo		
		int ultimoResultado = numeroPagina * this.getQuantidadeDeResultadosPorPagina();
		if(ultimoResultado > this.getQuantidadeTotalResultados()){
			ultimoResultado = this.getQuantidadeTotalResultados();
		}

		this.setListaDeReunioesEncontradas(listaDeReunioes.subList(primeiroResultadoBusca - 1, ultimoResultado));

		if(this.getListaDeReunioesEncontradas().size() > 0){
			this.setOcorrenciasEncontradas(true);
			this.setMensagemStatus("Busca Realizada");
		}
		else{
			this.setOcorrenciasEncontradas(false);
			this.setMensagemStatus("Não existem registros no banco de dados");
		}	

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

	public int getQuantidadeTotalResultados() {
		return quantidadeTotalResultados;
	}

	public void setQuantidadeTotalResultados(int quantidadeTotalResultados) {
		this.quantidadeTotalResultados = quantidadeTotalResultados;
	}

	public int getQuantidadeDeResultadosPorPagina() {
		return quantidadeDeResultadosPorPagina;
	}	

	public int getPaginaCorrente() {
		return paginaCorrente;
	}

	public void setPaginaCorrente(int paginaCorrente) {
		this.paginaCorrente = paginaCorrente;
	}

	public int getQuantidadeDePagina() {
		return quantidadeDePagina;
	}

	public void setQuantidadeDePagina(int quantidadeDePagina) {
		this.quantidadeDePagina = quantidadeDePagina;
	}

	public int getPrimeiroResultadoCorrente() {
		return primeiroResultadoCorrente;
	}

	public void setPrimeiroResultadoCorrente(int primeiroResultadoCorrente) {
		this.primeiroResultadoCorrente = primeiroResultadoCorrente;
	}

}
