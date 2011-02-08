package com.epucjr.engyos.aplicacao.webcontrole;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeCongregacao;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeCongregacao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

public class ActionCongregacaoRegisterCommand implements Command{

	public Object execute(Object... arg) {
		//Instanciação de objetos e variáveis necessários para a realização do cadastro
		HttpServletRequest request = (HttpServletRequest) arg[0];
		DataAccessObjectManager dataAccessObjectManager = null;
		FormularioDeCongregacao formularioDeCongregacao = null;
		
		//Obtenção de parâmetros necessários obtidos da página
		String nomeDaCongregacao = request.getParameter("Nome");
		String endereco = request.getParameter("Endereco");
		
		//1. Validar os dados cadastrais
		ValidadorDeFormularioDeCongregacao validadorDeFormularioDeCongregacao = new ValidadorDeFormularioDeCongregacao();
		validadorDeFormularioDeCongregacao.verificarCamposValidos(nomeDaCongregacao, endereco);
		
		if(validadorDeFormularioDeCongregacao.isFormularioValido()){
			dataAccessObjectManager = new DataAccessObjectManager();
			
			//Como os datos são validos, persistir a Congregacao
			Congregacao congregacao = new Congregacao(nomeDaCongregacao, endereco);
			dataAccessObjectManager.persistirObjeto(congregacao);
			
			//Realização de passos para caso de sucesso ou fracasso por ocorrência de um erro interno
			//ex. banco de dados 
			if(dataAccessObjectManager.isOperacaoEfetuada()){
				//Instanciação e Carregar dados da congregacao registrada para apresentação
				 formularioDeCongregacao = new FormularioDeCongregacao();
				 formularioDeCongregacao.definirDadosDeConfirmacaoDeCadastro(dataAccessObjectManager.getMensagemStatus(), nomeDaCongregacao, endereco);
				 
				//Define mensagem de sucesso ao cadastrar				 
				 formularioDeCongregacao.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
				 
			}
			//Ocorreu um erro de cadastro
			else{
				formularioDeCongregacao = new FormularioDeCongregacao();
				formularioDeCongregacao.definirCamposPreenchidosPeloUsuario(request);
				formularioDeCongregacao.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
			}
			
		}
		else{
			formularioDeCongregacao = new FormularioDeCongregacao();
			formularioDeCongregacao.setValidadorDeFormularioDeCongregacao(validadorDeFormularioDeCongregacao);
			formularioDeCongregacao.definirCamposPreenchidosPeloUsuario(request);
			formularioDeCongregacao.setMensagemStatus("Erro ao Cadastrar");
		}
		
		String respostaOperacao = formularioDeCongregacao.getMensagemStatus();
		request.setAttribute("formularioDeCongregacao", formularioDeCongregacao);
		
		System.out.println("Resposta = " + respostaOperacao);
		return respostaOperacao;
		
	}
	
}
