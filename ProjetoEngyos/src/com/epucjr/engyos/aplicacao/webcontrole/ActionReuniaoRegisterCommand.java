package com.epucjr.engyos.aplicacao.webcontrole;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeReuniao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.PresencaObreiro;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.dominio.modelo.ReuniaoSessionStatus;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeReuniao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import com.epucjr.engyos.tecnologia.utilitarios.DateTimeUtils;
import com.epucjr.engyos.tecnologia.utilitarios.ListUtilTokenizer;

public class ActionReuniaoRegisterCommand implements Command{

	public Object execute(Object... arg) {
		//Instancia��o de objetos e vari�veis necess�rios para a realiza��o do cadastro
		HttpServletRequest request = (HttpServletRequest) arg[0];
		DataAccessObjectManager dataAccessObjectManager = null;
		FormularioDeReuniao formularioDeReuniao = null;
		List<String> listaDeIds = null;
		List<Obreiro> listaDeObreirosSelecionados = new ArrayList<Obreiro>();
		//Obten��o de par�metros necess�rios obtidos da p�gina
		String local = request.getParameter("local");
		String dia = request.getParameter("dataReuniaoDia");
		String mes = request.getParameter("dataReuniaoMes");
		String ano = request.getParameter("dataReuniaoAno");
		String hora = request.getParameter("horaReuniao");
		String minuto = request.getParameter("minutoReuniao");
		//Os Ids de obreiros obtidos s�o tokenizados pelo Javascript com %
		String obreiroIdsTokenized = request.getParameter("obreiros");

		//Passoss para agendar uma reuni�o
		//1. Validar os dados cadastrais
		ValidadorDeFormularioDeReuniao validadorDeFormularioDeReuniao = new ValidadorDeFormularioDeReuniao();
		validadorDeFormularioDeReuniao.verificarCamposValidos(local, dia, mes, ano, hora, minuto);

		if(validadorDeFormularioDeReuniao.isFormularioValido()){
			dataAccessObjectManager = new DataAccessObjectManager();
			//2.Preparando os campos para cadastro da Reuni�o
			//2.a. Preparando a lista de presen�a de obreiros:
			if(obreiroIdsTokenized != null && !obreiroIdsTokenized.equals("")){
				listaDeIds = ListUtilTokenizer.obterListaString(obreiroIdsTokenized);			
				for(String idObreiro : listaDeIds){					
					Obreiro obreiro = dataAccessObjectManager.obterObreiro(idObreiro);
					listaDeObreirosSelecionados.add(obreiro);
				}
			}
			
			//2.b Preparando a lista de presen�a de obreiros
			List<PresencaObreiro> listaDePresenca = this.inserirObreirosNaLista(listaDeObreirosSelecionados);
			
			//2.c. Preparando a data e hora
			String data = DateTimeUtils.converterDataBr(dia, mes, ano);
			String horario = DateTimeUtils.converterHorarioHHMM(hora, minuto);
			
			//Instanciando a reuni�o para persist�ncia
			Reuniao reuniao = new Reuniao(local, data, horario);
			reuniao.setListaDePresencaObreiro(listaDePresenca);

                        //Coloca o m�dulo de controle de sess�o
                        ReuniaoSessionStatus reuniaoSessionStatus = new ReuniaoSessionStatus();
                        reuniao.setReuniaoSessionStatus(reuniaoSessionStatus);

			//Realizando a persist�ncia
			dataAccessObjectManager.persistirObjeto(reuniao);
			
			//Realiza��o de passos para caso de sucesso ou fracasso por ocorr�ncia de um erro interno
			//ex. banco de dados 
			
			if(dataAccessObjectManager.isOperacaoEfetuada()){
				//Instancia��o e Carregar dados do obreiro registrado para apresenta��o
				formularioDeReuniao = new FormularioDeReuniao();				
				formularioDeReuniao.definirDadosDeConfirmacaoDeCadastroReuniao(dataAccessObjectManager.getMensagemStatus(), local, data, horario);
				
				//Define mensagem de sucesso ao cadastrar
				formularioDeReuniao.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
			
			}
			//Ocorreu um erro de cadastro
			else{
				formularioDeReuniao = new FormularioDeReuniao();		
				formularioDeReuniao.definirCamposPreenchidosPeloUsuario(request);
				formularioDeReuniao.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
			}

		}
		else{
			formularioDeReuniao = new FormularioDeReuniao();	
			formularioDeReuniao.setValidadorDeFormularioDeReuniao(validadorDeFormularioDeReuniao);
			formularioDeReuniao.definirCamposPreenchidosPeloUsuario(request);
			formularioDeReuniao.setMensagemStatus("Erro ao Cadastrar");
		}
		
		String respostaOperacao = formularioDeReuniao.getMensagemStatus();
		request.setAttribute("formularioDeReuniao", formularioDeReuniao);

		return respostaOperacao;
	}

	public List<PresencaObreiro> inserirObreirosNaLista(List<Obreiro> listaDeObreiros){

		List<PresencaObreiro> listaDePresenca = new ArrayList<PresencaObreiro>();
		for(Obreiro obreiro : listaDeObreiros){
			listaDePresenca.add(new PresencaObreiro(obreiro));
		}		
		return listaDePresenca;
	}

}
