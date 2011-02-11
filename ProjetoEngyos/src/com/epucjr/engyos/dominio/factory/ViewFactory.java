package com.epucjr.engyos.dominio.factory;

import java.util.HashMap;
import java.util.Map;

public class ViewFactory {

	private Map<String, String> viewMaps;

	public ViewFactory() {
		viewMaps = new HashMap<String, String>();
		//viewMaps.put("teste" + "TESTE", "/teste.jsp");
		this.carregarMapListView();
	}
	
	
	public void adicionaView(String acao, String resposta, String jspPagePath){
		viewMaps.put(acao + resposta, jspPagePath);
	}
	
	public String getView(String acao, String resposta){
		if(viewMaps.containsKey(acao + resposta)){
			return viewMaps.get(acao + resposta);
		}
		else return viewMaps.get("null" + "default");
	}
	
	private void carregarMapListView(){         
		this.adicionaView("null", "default", "teste.jsp");
		
		this.adicionaView("obreiro_formload", "Formulario Carregado", "CadastrarObreiro.jsp");
		this.adicionaView("reuniao_formload", "Formulario Carregado", "CadastrarReuniao.jsp");
		this.adicionaView("congregacao_formload", "Formulario Carregado", "CadastrarCongregacao.jsp");
		
		this.adicionaView("obreiro_register", "Erro ao Cadastrar", "CadastrarObreiro.jsp");
		this.adicionaView("obreiro_register", "Operação Realizada com Sucesso!", "CadastrarObreiro.jsp");
		this.adicionaView("obreiro_register", "Erro interno na operação", "CadastrarObreiro.jsp");		
		this.adicionaView("obreiro_register", "Usuario Já Existente no Registro...", "CadastrarObreiro.jsp");	
		
		this.adicionaView("congregacao_register", "Erro ao Cadastrar", "CadastrarCongregacao.jsp");
		this.adicionaView("congregacao_register", "Operação Realizada com Sucesso!", "CadastrarCongregacao.jsp");
		this.adicionaView("congregacao_register", "Erro interno na operação", "CadastrarCongregacao.jsp");		
		this.adicionaView("congregacao_register", "Congregacao Já Existente no Registro...", "CadastrarCongregacao.jsp");	
		
		this.adicionaView("reuniao_register", "Erro ao Cadastrar", "CadastrarReuniao.jsp");
		this.adicionaView("reuniao_register", "Operação Realizada com Sucesso!", "CadastrarReuniao.jsp");
		this.adicionaView("reuniao_register", "Erro interno na operação", "CadastrarReuniao.jsp");
		
		this.adicionaView("buscar_obreiro", "Busca Realizada", "BuscarObreiro.jsp");
		this.adicionaView("buscar_obreiro", "Não existem registros no banco de dados", "BuscarObreiro.jsp");
		
		this.adicionaView("buscar_congregacao", "Busca Realizada", "BuscarCongregacao.jsp");
		this.adicionaView("buscar_congregacao", "Não existem registros no banco de dados", "BuscarCongregacao.jsp");
		
		this.adicionaView("buscar_reuniao", "Busca Realizada", "BuscarReuniao.jsp");
		this.adicionaView("buscar_reuniao", "Não existem registros no banco de dados", "BuscarReuniao.jsp");
		
		this.adicionaView("ActionLogin", "Sucesso Login", "Principal.jsp");
		this.adicionaView("ActionLogin", "Erro Login", "TelaLogin.jsp");
		
	}

}
