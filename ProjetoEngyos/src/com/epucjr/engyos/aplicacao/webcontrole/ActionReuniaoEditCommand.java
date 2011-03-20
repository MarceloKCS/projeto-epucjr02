package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.web.FrontControlerServlet;
import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeReuniao;
import com.epucjr.engyos.dominio.factory.CommandFactory;
import com.epucjr.engyos.dominio.factory.ViewFactory;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.PresencaObreiro;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeReuniao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import com.epucjr.engyos.tecnologia.utilitarios.DateTimeUtils;
import com.epucjr.engyos.tecnologia.utilitarios.ListUtilTokenizer;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *Classe que tem como finalidade executar a ação que cumpra a promessa de editar uma reunião
 * cadastrada no sistema
 *
 * @author Projeto Engyos Team
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class ActionReuniaoEditCommand implements Command{


     /**
     * Método que executa a requisição de edição do formulário de reuniao
     *
     * @param arg O <code>HttpServletRequest</code> e o <code>HttpServletResponse</code>
     * @return o Objeto com o resultado da acao requisitada, utilizada pela view
     *factory no <code>FrontControllerServlet</code>
     *
     * @see FrontControlerServlet#servico(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse
     * @see CommandFactory
     * @see ViewFactory
     * @see Reuniao
     * @see ValidadorDeFormularioDeReuniao
     * @see DataAccessObjectManager
     * @see FormularioDeReuniao
     */
    @Override
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
        
        //Obtendo o id da reunião para a edição
        String idReuniaoString = request.getParameter("idReuniao");
        long idReuniao = 0;

        if(idReuniaoString != null && !idReuniaoString.equals("")){
            idReuniao = Long.parseLong(idReuniaoString.trim());
        }
        //Os Ids de obreiros obtidos são tokenizados pelo Javascript com %
        String obreiroIdsTokenized = request.getParameter("obreiros");

        //Reobtenção da Reuniao a ser editada
        dataAccessObjectManager = new DataAccessObjectManager();
        Reuniao reuniao = dataAccessObjectManager.obterReuniao(idReuniao);

       if (dataAccessObjectManager.isOperacaoEfetuada()) {

            //Reorganização dos dados para validaçao
            if (!local.equals(reuniao.getLocal())) {
                reuniao.setLocal(local);
            }

            //Verificando se houve alguma alteração na data
            if (!dia.equals(reuniao.getDia()) || !mes.equals(reuniao.getMes()) || !ano.equals(reuniao.getAno())) {
                //Preparando a data
                String data = DateTimeUtils.converterDataBr(dia, mes, ano);
                //Alterando a data da reunião
                reuniao.setData(data);
            }
            //Verificando se houve alguma alteração no horário
            if (!hora.equals(reuniao.getHora()) || !minuto.equals(reuniao.getMinuto())) {
                //Preparando a hora
                String horario = DateTimeUtils.converterHorarioHHMM(hora, minuto);
                //Alterando o horário da reunião
                reuniao.setHorario(horario);
            }

            //Preparando a lista de presença de obreiros:
            if (obreiroIdsTokenized != null && !obreiroIdsTokenized.equals("")) {
                listaDeIds = ListUtilTokenizer.obterListaString(obreiroIdsTokenized);
                for (String idObreiro : listaDeIds) {
                    Obreiro obreiro = dataAccessObjectManager.obterObreiro(idObreiro);
                    listaDeObreirosSelecionados.add(obreiro);
                }
            }

            //Preparando a lista de presença de obreiros
            List<PresencaObreiro> listaDePresenca = this.inserirObreirosNaLista(listaDeObreirosSelecionados);
            reuniao.setListaDePresencaObreiro(listaDePresenca);

            //1. Validar os dados cadastrais
            ValidadorDeFormularioDeReuniao validadorDeFormularioDeReuniao = new ValidadorDeFormularioDeReuniao();
            validadorDeFormularioDeReuniao.verificarCamposValidos(local, dia, mes, ano, hora, minuto);

            if (validadorDeFormularioDeReuniao.isFormularioValido()) {
                dataAccessObjectManager.mergeDataObjeto(reuniao);

                //Realização de passos para caso de sucesso ou fracasso por ocorrência de um erro interno
                //ex. banco de dados
                if (dataAccessObjectManager.isOperacaoEfetuada()) {
                    //Instanciação e Carregar dados do obreiro registrado para apresentação
                    formularioDeReuniao = new FormularioDeReuniao();
                    formularioDeReuniao.definirDadosDeConfirmacaoDeEdicaoReuniao(dataAccessObjectManager.getMensagemStatus(), local, DateTimeUtils.converterDataBr(dia, mes, ano), DateTimeUtils.converterHorarioHHMM(hora, minuto));

                    //Define mensagem de sucesso ao editar
                    formularioDeReuniao.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
                } //Ocorreu um erro de edição
                else {
                    formularioDeReuniao = new FormularioDeReuniao();
                    formularioDeReuniao.definirCamposPreenchidosPeloUsuario(request);
                    formularioDeReuniao.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
                }

            } else {
                formularioDeReuniao = new FormularioDeReuniao();
                formularioDeReuniao.setValidadorDeFormularioDeReuniao(validadorDeFormularioDeReuniao);
                formularioDeReuniao.definirCamposPreenchidosPeloUsuario(request);
                formularioDeReuniao.setMensagemStatus("Erro ao Editar");
            }

        } else {
            formularioDeReuniao = new FormularioDeReuniao();
            formularioDeReuniao.definirCamposPreenchidosPeloUsuario(request);
            formularioDeReuniao.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
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
