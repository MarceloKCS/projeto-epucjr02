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
 *Classe que tem como finalidade executar a a��o que cumpra a promessa de editar uma reuni�o
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
     * M�todo que executa a requisi��o de edi��o do formul�rio de reuniao
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
        
        //Obtendo o id da reuni�o para a edi��o
        String idReuniaoString = request.getParameter("idReuniao");
        long idReuniao = 0;

        if(idReuniaoString != null && !idReuniaoString.equals("")){
            idReuniao = Long.parseLong(idReuniaoString.trim());
        }
        //Os Ids de obreiros obtidos s�o tokenizados pelo Javascript com %
        String obreiroIdsTokenized = request.getParameter("obreiros");

        //Reobten��o da Reuniao a ser editada
        dataAccessObjectManager = new DataAccessObjectManager();
        Reuniao reuniao = dataAccessObjectManager.obterReuniao(idReuniao);

       if (dataAccessObjectManager.isOperacaoEfetuada()) {

            //Reorganiza��o dos dados para valida�ao
            if (!local.equals(reuniao.getLocal())) {
                reuniao.setLocal(local);
            }

            //Verificando se houve alguma altera��o na data
            if (!dia.equals(reuniao.getDia()) || !mes.equals(reuniao.getMes()) || !ano.equals(reuniao.getAno())) {
                //Preparando a data
                String data = DateTimeUtils.converterDataBr(dia, mes, ano);
                //Alterando a data da reuni�o
                reuniao.setData(data);
            }
            //Verificando se houve alguma altera��o no hor�rio
            if (!hora.equals(reuniao.getHora()) || !minuto.equals(reuniao.getMinuto())) {
                //Preparando a hora
                String horario = DateTimeUtils.converterHorarioHHMM(hora, minuto);
                //Alterando o hor�rio da reuni�o
                reuniao.setHorario(horario);
            }

            //Preparando a lista de presen�a de obreiros:
            if (obreiroIdsTokenized != null && !obreiroIdsTokenized.equals("")) {
                listaDeIds = ListUtilTokenizer.obterListaString(obreiroIdsTokenized);
                for (String idObreiro : listaDeIds) {
                    Obreiro obreiro = dataAccessObjectManager.obterObreiro(idObreiro);
                    listaDeObreirosSelecionados.add(obreiro);
                }
            }

            //Preparando a lista de presen�a de obreiros
            List<PresencaObreiro> listaDePresenca = this.inserirObreirosNaLista(listaDeObreirosSelecionados);
            reuniao.setListaDePresencaObreiro(listaDePresenca);

            //1. Validar os dados cadastrais
            ValidadorDeFormularioDeReuniao validadorDeFormularioDeReuniao = new ValidadorDeFormularioDeReuniao();
            validadorDeFormularioDeReuniao.verificarCamposValidos(local, dia, mes, ano, hora, minuto);

            if (validadorDeFormularioDeReuniao.isFormularioValido()) {
                dataAccessObjectManager.mergeDataObjeto(reuniao);

                //Realiza��o de passos para caso de sucesso ou fracasso por ocorr�ncia de um erro interno
                //ex. banco de dados
                if (dataAccessObjectManager.isOperacaoEfetuada()) {
                    //Instancia��o e Carregar dados do obreiro registrado para apresenta��o
                    formularioDeReuniao = new FormularioDeReuniao();
                    formularioDeReuniao.definirDadosDeConfirmacaoDeEdicaoReuniao(dataAccessObjectManager.getMensagemStatus(), local, DateTimeUtils.converterDataBr(dia, mes, ano), DateTimeUtils.converterHorarioHHMM(hora, minuto));

                    //Define mensagem de sucesso ao editar
                    formularioDeReuniao.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());
                } //Ocorreu um erro de edi��o
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
