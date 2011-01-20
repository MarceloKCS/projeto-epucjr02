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
		this.adicionaView("obreiro_register", "Erro ao Cadastrar", "CadastrarObreiro.jsp");
		this.adicionaView("obreiro_register", "Operação Realizada com Sucesso!", "CadastrarObreiro.jsp");
		this.adicionaView("obreiro_register", "Erro interno na operação", "CadastrarObreiro.jsp");
		this.adicionaView("obreiro_register", "Usuario Já Existente no Registro...", "CadastrarObreiro.jsp");
		
	}

}
