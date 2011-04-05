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
		//Instanciação de objetos e variáveis necessários para a realização do cadastro
		HttpServletRequest request = (HttpServletRequest) arg[0];
		DataAccessObjectManager dataAccessObjectManager = null;
		FormularioDeReuniao formularioDeReuniao = null;
		List<String> listaDeIds = null;
		List<Obreiro> listaDeObreirosSelecionados = new ArrayList<Obreiro>();
		//Obtenção de parâmetros necessários obtidos da página
		String local = request.getParameter("local");
		String dia = request.getParameter("dataReuniaoDia");
		String mes = request.getParameter("dataReuniaoMes");
		String ano = request.getParameter("dataReuniaoAno");
		String hora = request.getParameter("horaReuniao");
		String minuto = request.getParameter("minutoReuniao");
		//Os Ids de obreiros obtidos são tokenizados pelo Javascript com %
		String obreiroIdsTokenized = request.getParameter("obreiros");

		//Passoss para agendar uma reunião
		//1. Validar os dados cadastrais
		ValidadorDeFormularioDeReuniao validadorDeFormularioDeReuniao = new ValidadorDeFormularioDeReuniao();
		validadorDeFormularioDeReuniao.verificarCamposValidos(local, dia, mes, ano, hora, minuto);

		if(validadorDeFormularioDeReuniao.isFormularioValido()){
			dataAccessObjectManager = new DataAccessObjectManager();
			//2.Preparando os campos para cadastro da Reunião
			//2.a. Preparando a lista de presença de obreiros:
			if(obreiroIdsTokenized != null && !obreiroIdsTokenized.equals("")){
				listaDeIds = ListUtilTokenizer.obterListaString(obreiroIdsTokenized);			
				for(String idObreiro : listaDeIds){					
					Obreiro obreiro = dataAccessObjectManager.obterObreiro(idObreiro);
					listaDeObreirosSelecionados.add(obreiro);
				}
			}
			
			//2.b Preparando a lista de presença de obreiros
			List<PresencaObreiro> listaDePresenca = this.inserirObreirosNaLista(listaDeObreirosSelecionados);
			
			//2.c. Preparando a data e hora
			String data = DateTimeUtils.converterDataBr(dia, mes, ano);
			String horario = DateTimeUtils.converterHorarioHHMM(hora, minuto);
			
			//Instanciando a reunião para persistência
			Reuniao reuniao = new Reuniao(local, data, horario);
			reuniao.setListaDePresencaObreiro(listaDePresenca);

                        //Coloca o módulo de controle de sessão
                        ReuniaoSessionStatus reuniaoSessionStatus = new ReuniaoSessionStatus();
                        reuniao.setReuniaoSessionStatus(reuniaoSessionStatus);

			//Realizando a persistência
			dataAccessObjectManager.persistirObjeto(reuniao);
			
			//Realização de passos para caso de sucesso ou fracasso por ocorrência de um erro interno
			//ex. banco de dados 
			
			if(dataAccessObjectManager.isOperacaoEfetuada()){
				//Instanciação e Carregar dados do obreiro registrado para apresentação
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
