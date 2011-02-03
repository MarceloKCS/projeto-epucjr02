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
		this.adicionaView("obreiro_register", "Opera��o Realizada com Sucesso!", "CadastrarObreiro.jsp");
		this.adicionaView("obreiro_register", "Erro interno na opera��o", "CadastrarObreiro.jsp");		
		this.adicionaView("obreiro_register", "Usuario J� Existente no Registro...", "CadastrarObreiro.jsp");	
		
		this.adicionaView("congregacao_register", "Erro ao Cadastrar", "CadastrarCongregacao.jsp");
		this.adicionaView("congregacao_register", "Opera��o Realizada com Sucesso!", "CadastrarCongregacao.jsp");
		this.adicionaView("congregacao_register", "Erro interno na opera��o", "CadastrarCongregacao.jsp");		
		this.adicionaView("congregacao_register", "Congregacao J� Existente no Registro...", "CadastrarCongregacao.jsp");	
		
		this.adicionaView("reuniao_register", "Erro ao Cadastrar", "CadastrarReuniao.jsp");
		this.adicionaView("reuniao_register", "Opera��o Realizada com Sucesso!", "CadastrarReuniao.jsp");
		this.adicionaView("reuniao_register", "Erro interno na opera��o", "CadastrarReuniao.jsp");
		
		this.adicionaView("buscar_obreiro", "Busca Realizada", "BuscarObreiro.jsp");
		this.adicionaView("buscar_obreiro", "N�o existem registros no banco de dados", "BuscarObreiro.jsp");
		
		this.adicionaView("buscar_congregacao", "Busca Realizada", "BuscarCongregacao.jsp");
		this.adicionaView("buscar_congregacao", "N�o existem registros no banco de dados", "BuscarCongregacao.jsp");
		
		this.adicionaView("buscar_reuniao", "Busca Realizada", "BuscarReuniao.jsp");
		this.adicionaView("buscar_reuniao", "N�o existem registros no banco de dados", "BuscarReuniao.jsp");
		
		this.adicionaView("ActionLogin", "Sucesso Login", "Principal.jsp");
		this.adicionaView("ActionLogin", "Erro Login", "TelaLogin.jsp");
		
	}

}
