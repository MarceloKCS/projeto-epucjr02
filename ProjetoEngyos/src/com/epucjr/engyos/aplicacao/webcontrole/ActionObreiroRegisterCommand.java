package com.epucjr.engyos.aplicacao.webcontrole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeObreiro;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Identificacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeObreiro;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

public class ActionObreiroRegisterCommand implements Command{
	
	public Object execute(Object... arg) {
		
		//Instancia��o de objetos e vari�veis necess�rios para a realiza��o do cadastro
		HttpServletRequest request = (HttpServletRequest) arg[0];
		DataAccessObjectManager dataAccessObjectManager = null;
		FormularioDeObreiro formularioDeObreiro = null;
		
		//Obten��o de par�metros necess�rios obtidos da p�gina
		String nome = request.getParameter("Nome");
		String cpf = request.getParameter("Cpf");
		String cargo = request.getParameter("Cargo");
		String idCongregacaoEscolhido = request.getParameter("Congregacao");
		String congregacao = request.getParameter(idCongregacaoEscolhido);
		String senha = request.getParameter("Senha");
		String senhaConfirmacao = request.getParameter("SenhaConfirmacao");
			
		long idCongregacao = 0;
		if(idCongregacaoEscolhido != null && idCongregacaoEscolhido != ""){
			idCongregacao = Long.parseLong(idCongregacaoEscolhido);
		}
		
		//Obt�m uma session para obten��o da digital inserida por um obreiro via applet
		//TODO - verificar solu��es alternativas
		HttpSession session = request.getSession();			 
		
		String digitalLida = "";
		
		if(!session.isNew()){
			digitalLida = (String) session.getAttribute("DigitalObtida");
		}
		
		
		//Passoss para cadastrar um obreiro
		//1. Validar os dados cadastrais
		ValidadorDeFormularioDeObreiro validadorDeFormularioDeObreiro = new ValidadorDeFormularioDeObreiro();
		validadorDeFormularioDeObreiro.verificarCamposValidos(nome, cpf, cargo, congregacao, senha, senhaConfirmacao);
		
		if(validadorDeFormularioDeObreiro.isFormularioValido()){
			dataAccessObjectManager = new DataAccessObjectManager();
			Congregacao congregacaoCarregada = dataAccessObjectManager.obterCongregacao(idCongregacao);
			
			//Identificacao identificacao = new Identificacao(impressaoDigital, senha);
			//TODO para teste, a�s o recebimento dos par�metros da p�gina alterar 
			
			Identificacao identificacao = new Identificacao(senha);
			if(digitalLida != null && !digitalLida.equals("")){
				identificacao.setImpressaoDigital(digitalLida);
			}
			
			Obreiro obreiro = new Obreiro(nome, cargo, cpf, congregacaoCarregada, identificacao);
			dataAccessObjectManager.persistirObjeto(obreiro);			
			
			//Realiza��o de passos para caso de sucesso ou fracasso por ocorr�ncia de um erro interno
			//ex. banco de dados 
			if(dataAccessObjectManager.isOperacaoEfetuada()){
				//Instancia��o e Carregar dados do obreiro registrado para apresenta��o
				formularioDeObreiro = new FormularioDeObreiro();
				formularioDeObreiro.definirDadosDeConfirmacaoDeCadastroObreiro(dataAccessObjectManager.getMensagemStatus(), nome, cpf, cargo, congregacao);			
				
				//Define mensagem de sucesso ao cadastrar
				formularioDeObreiro.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
				
			}
			//Ocorreu um erro de cadastro
			else{
				formularioDeObreiro = new FormularioDeObreiro();
				formularioDeObreiro.definirCamposPreenchidosPeloUsuario(request);
				formularioDeObreiro.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
				
			}
			
		}
		else{
			formularioDeObreiro = new FormularioDeObreiro();
			formularioDeObreiro.setValidadorDeFormularioDeObreiro(validadorDeFormularioDeObreiro);
			formularioDeObreiro.definirCamposPreenchidosPeloUsuario(request);
			formularioDeObreiro.setMensagemStatus("Erro ao Cadastrar");
		}
		
		String respostaOperacao = formularioDeObreiro.getMensagemStatus();
		request.setAttribute("formularioDeObreiro", formularioDeObreiro);
		
		return respostaOperacao;
	}

}
