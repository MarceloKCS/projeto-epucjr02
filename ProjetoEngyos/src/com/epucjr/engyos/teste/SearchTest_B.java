package com.epucjr.engyos.teste;

import java.util.List;
import java.util.Scanner;

import com.epucjr.engyos.dominio.crud.BuscaAvancada;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.ferramentas.SearchInitializator;

public class SearchTest_B {

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);		
		int opcao = 0;
		String parametroBusca = "";

		do{
			System.out.print("Digite uma opção: ");
			opcao = input.nextInt();
			switch(opcao){
			case 1: 
				System.out.println("Busca por parametro fixo:");
				SearchTest_B.buscarObreiroTest();
				break;
			case 2: 
				System.out.println("Busca Por parametro especificado na variável");
				System.out.print("Entre com o Parâmetro de busca: ");
				parametroBusca = input.next();
				SearchTest_B.buscarObreiroParamTest(parametroBusca);
				break;
			case 3: 
				System.out.println("Busca por Obreiro sem envio de parâmetro");
				SearchTest_B.buscarObreiroTestParamVazio();
				break;
			case 4:
				System.out.println("Busca por obreiros com paginação");
				System.out.print("Entre com o Parâmetro de busca: ");
				parametroBusca = input.next();
				SearchTest_B.buscarObreiroPaginados(parametroBusca);
				break;
			case 5: 
				System.out.println("Busca por congregacao com paginação No Param");
				//System.out.print("Entre com o Parâmetro de busca: ");
				//parametroBusca = input.next();
				SearchTest_B.buscarCongregacoesPaginadasNoParam();
				break;
			case 6: 
				System.out.println("Busca por congregacao com paginação");
				System.out.print("Entre com o Parâmetro de busca: ");
				parametroBusca = input.next();
				SearchTest_B.buscarCongregacoesPaginadas(parametroBusca);
				break;
			case 7:
				System.out.println("Busca por Reunião com paginação No Param");
				//System.out.print("Entre com o Parâmetro de busca: ");
				//parametroBusca = input.next();
				SearchTest_B.buscarReunioesPaginadasNoParam();
				break;
			case 8:
				System.out.println("Busca por Reuniao com paginação");
				System.out.print("Entre com o Parâmetro de busca: ");
				parametroBusca = input.next();
				SearchTest_B.buscarReunioesPaginadas(parametroBusca);
				break;
				

			}
		}
		while(opcao != 0);
		
		System.out.println("Encerrado");
		System.exit(0);

	}

	public static void buscarObreiroTest(){
		BuscaAvancada buscaAvancada = new BuscaAvancada();
		SearchInitializator searchInitializator = new SearchInitializator();
		searchInitializator.atualizarIndicesLucene();

		if(searchInitializator.isOperacaoExecutada()){

			System.out.println("MSG Index Init Search = " + searchInitializator.getMensagemStatus());
		}
		else{
			System.out.println("MSG Index Init Search Error = " + searchInitializator.getMensagemStatus());
		}
		buscaAvancada.buscarObreiros("Rogério", 1);
		List<Obreiro> listaDeObreiro = buscaAvancada.getListaDeObreirosEncontrados();
		System.out.println("Resultados = " + listaDeObreiro.size());

		for(Obreiro obreiro : listaDeObreiro){
			System.out.println("nome Obreiro = " + obreiro.getNome());
		}

	}
	
	public static void buscarObreiroTestParamVazio(){
		BuscaAvancada buscaAvancada = new BuscaAvancada();
		SearchInitializator searchInitializator = new SearchInitializator();
		searchInitializator.atualizarIndicesLucene();

		if(searchInitializator.isOperacaoExecutada()){

			System.out.println("MSG Index Init Search = " + searchInitializator.getMensagemStatus());
		}
		else{
			System.out.println("MSG Index Init Search Error = " + searchInitializator.getMensagemStatus());
		}
		buscaAvancada.buscarObreiros("", 1);
		List<Obreiro> listaDeObreiro = buscaAvancada.getListaDeObreirosEncontrados();

		System.out.println("Resultados = " + listaDeObreiro.size());

		for(Obreiro obreiro : listaDeObreiro){
			System.out.println("nome Obreiro = " + obreiro.getNome());
		}

	}

	public static void buscarObreiroParamTest(String PARAM_BUSCA){
		BuscaAvancada buscaAvancada = new BuscaAvancada();
		SearchInitializator searchInitializator = new SearchInitializator();
		searchInitializator.atualizarIndicesLucene();

		//String parametroNome = "Paroquia";
		//String parametroNome = "Programador";
		if(searchInitializator.isOperacaoExecutada()){

			System.out.println("MSG Index Init Search = " + searchInitializator.getMensagemStatus());
		}
		else{
			System.out.println("MSG Index Init Search Error = " + searchInitializator.getMensagemStatus());
		}
		buscaAvancada.buscarObreiros(PARAM_BUSCA, 1);
		List<Obreiro> listaDeObreiro = buscaAvancada.getListaDeObreirosEncontrados();
		System.out.println("Resultados = " + listaDeObreiro.size());

		for(Obreiro obreiro : listaDeObreiro){
			System.out.println("nome Obreiro = " + obreiro.getNome());
		}

	}
	
	public static void buscarObreiroPaginados(String parametroBusca){
		BuscaAvancada buscaAvancada = new BuscaAvancada();
		SearchInitializator searchInitializator = new SearchInitializator();
		searchInitializator.atualizarIndicesLucene();
		String parametroDeBusca = parametroBusca;
		if(searchInitializator.isOperacaoExecutada()){

			System.out.println("MSG Index Init Search = " + searchInitializator.getMensagemStatus());
		}
		else{
			System.out.println("MSG Index Init Search Error = " + searchInitializator.getMensagemStatus());
		}
		buscaAvancada.buscarObreiros(parametroDeBusca, 1);
		List<Obreiro> listaDeObreiro = buscaAvancada.getListaDeObreirosEncontrados();
		System.out.println("DADOS DA PAGINAÇÃO");
		System.out.println("NumPagina = " + buscaAvancada.getPaginaCorrente());
		System.out.println("Quantidade de Paginas: " + buscaAvancada.getQuantidadeDePagina());
		System.out.println("Quantidade de Resultados por Página: " + buscaAvancada.getQuantidadeDeResultadosPorPagina());
		System.out.println("Quantidade Total de Resultados: " + buscaAvancada.getQuantidadeTotalResultados());
		System.out.println("Mensagem Status: " + buscaAvancada.getMensagemStatus());
		System.out.println("Verificação de Ocorrencias Encontradas: " + buscaAvancada.isOcorrenciasEncontradas());

		System.out.println("Resultados = " + listaDeObreiro.size());

		for(Obreiro obreiro : listaDeObreiro){
			System.out.println("nome Obreiro = " + obreiro.getNome());
		}

	}
	
	public static void buscarCongregacoesPaginadas(String parametroBusca){
		BuscaAvancada buscaAvancada = new BuscaAvancada();
		SearchInitializator searchInitializator = new SearchInitializator();
		searchInitializator.atualizarIndicesLucene();
		String parametroDeBusca = parametroBusca;
		if(searchInitializator.isOperacaoExecutada()){

			System.out.println("MSG Index Init Search = " + searchInitializator.getMensagemStatus());
		}
		else{
			System.out.println("MSG Index Init Search Error = " + searchInitializator.getMensagemStatus());
		}
		buscaAvancada.buscarCongregacao(parametroDeBusca, 1);
		List<Congregacao> listaDeCongregacao = buscaAvancada.getListaDeCongregacoesEncontradas();
		System.out.println("DADOS DA PAGINAÇÃO");
		System.out.println("NumPagina = " + buscaAvancada.getPaginaCorrente());
		System.out.println("Quantidade de Paginas: " + buscaAvancada.getQuantidadeDePagina());
		System.out.println("Quantidade de Resultados por Página: " + buscaAvancada.getQuantidadeDeResultadosPorPagina());
		System.out.println("Quantidade Total de Resultados: " + buscaAvancada.getQuantidadeTotalResultados());
		System.out.println("Mensagem Status: " + buscaAvancada.getMensagemStatus());
		System.out.println("Verificação de Ocorrencias Encontradas: " + buscaAvancada.isOcorrenciasEncontradas());

		System.out.println("Resultados = " + listaDeCongregacao.size());

		for(Congregacao congregacao : listaDeCongregacao){
			System.out.println("nome da Congregacao = " + congregacao.getNome());
		}

	}
	
	public static void buscarCongregacoesPaginadasNoParam(){
		BuscaAvancada buscaAvancada = new BuscaAvancada();
		SearchInitializator searchInitializator = new SearchInitializator();
		searchInitializator.atualizarIndicesLucene();
		String parametroDeBusca = "";
		if(searchInitializator.isOperacaoExecutada()){

			System.out.println("MSG Index Init Search = " + searchInitializator.getMensagemStatus());
		}
		else{
			System.out.println("MSG Index Init Search Error = " + searchInitializator.getMensagemStatus());
		}
		buscaAvancada.buscarCongregacao(parametroDeBusca, 1);
		List<Congregacao> listaDeCongregacao = buscaAvancada.getListaDeCongregacoesEncontradas();
		System.out.println("DADOS DA PAGINAÇÃO");
		System.out.println("NumPagina = " + buscaAvancada.getPaginaCorrente());
		System.out.println("Quantidade de Paginas: " + buscaAvancada.getQuantidadeDePagina());
		System.out.println("Quantidade de Resultados por Página: " + buscaAvancada.getQuantidadeDeResultadosPorPagina());
		System.out.println("Quantidade Total de Resultados: " + buscaAvancada.getQuantidadeTotalResultados());
		System.out.println("Mensagem Status: " + buscaAvancada.getMensagemStatus());
		System.out.println("Verificação de Ocorrencias Encontradas: " + buscaAvancada.isOcorrenciasEncontradas());

		System.out.println("Resultados = " + listaDeCongregacao.size());

		for(Congregacao congregacao : listaDeCongregacao){
			System.out.println("nome da Congregacao = " + congregacao.getNome());
		}

	}
	
	public static void buscarReunioesPaginadasNoParam(){
		BuscaAvancada buscaAvancada = new BuscaAvancada();
		SearchInitializator searchInitializator = new SearchInitializator();
		searchInitializator.atualizarIndicesLucene();
		String parametroDeBusca = "";
		if(searchInitializator.isOperacaoExecutada()){

			System.out.println("MSG Index Init Search = " + searchInitializator.getMensagemStatus());
		}
		else{
			System.out.println("MSG Index Init Search Error = " + searchInitializator.getMensagemStatus());
		}
		buscaAvancada.buscarReuniao(parametroDeBusca, 1);
		List<Reuniao> listaDeReunioes = buscaAvancada.getListaDeReunioesEncontradas();
		System.out.println("DADOS DA PAGINAÇÃO");
		System.out.println("NumPagina = " + buscaAvancada.getPaginaCorrente());
		System.out.println("Quantidade de Paginas: " + buscaAvancada.getQuantidadeDePagina());
		System.out.println("Quantidade de Resultados por Página: " + buscaAvancada.getQuantidadeDeResultadosPorPagina());
		System.out.println("Quantidade Total de Resultados: " + buscaAvancada.getQuantidadeTotalResultados());
		System.out.println("Mensagem Status: " + buscaAvancada.getMensagemStatus());
		System.out.println("Verificação de Ocorrencias Encontradas: " + buscaAvancada.isOcorrenciasEncontradas());

		System.out.println("Resultados = " + listaDeReunioes.size());

		for(Reuniao reuniao : listaDeReunioes){
			System.out.println("nome da Congregacao = " + reuniao.getData());
		}

	}
	
	public static void buscarReunioesPaginadas(String parametroBusca){
		BuscaAvancada buscaAvancada = new BuscaAvancada();
		SearchInitializator searchInitializator = new SearchInitializator();
		searchInitializator.atualizarIndicesLucene();
		String parametroDeBusca = parametroBusca;
		if(searchInitializator.isOperacaoExecutada()){

			System.out.println("MSG Index Init Search = " + searchInitializator.getMensagemStatus());
		}
		else{
			System.out.println("MSG Index Init Search Error = " + searchInitializator.getMensagemStatus());
		}
		buscaAvancada.buscarReuniao(parametroDeBusca, 1);
		List<Reuniao> listaDeReunioes = buscaAvancada.getListaDeReunioesEncontradas();
		System.out.println("DADOS DA PAGINAÇÃO");
		System.out.println("NumPagina = " + buscaAvancada.getPaginaCorrente());
		System.out.println("Quantidade de Paginas: " + buscaAvancada.getQuantidadeDePagina());
		System.out.println("Quantidade de Resultados por Página: " + buscaAvancada.getQuantidadeDeResultadosPorPagina());
		System.out.println("Quantidade Total de Resultados: " + buscaAvancada.getQuantidadeTotalResultados());
		System.out.println("Mensagem Status: " + buscaAvancada.getMensagemStatus());
		System.out.println("Verificação de Ocorrencias Encontradas: " + buscaAvancada.isOcorrenciasEncontradas());

		System.out.println("Resultados = " + listaDeReunioes.size());

		for(Reuniao reuniao : listaDeReunioes){
			System.out.println("nome da Congregacao = " + reuniao.getData());
		}

	}
	
	

}
