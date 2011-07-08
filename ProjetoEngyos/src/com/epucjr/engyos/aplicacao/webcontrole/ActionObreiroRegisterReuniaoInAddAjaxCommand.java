package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeObreiro;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Identificacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.dao.CongregacaoDAO;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Rogerio
 */
public class ActionObreiroRegisterReuniaoInAddAjaxCommand implements Command{
    private static org.apache.log4j.Logger log = Logger.getLogger(ActionObreiroRegisterCommand.class);
    @Override
    public Object execute(Object... arg) {
        //Instanciação de objetos e variáveis necessários para a realização do cadastro
        HttpServletRequest request = (HttpServletRequest) arg[0];
        DataAccessObjectManager dataAccessObjectManager = null;
        CongregacaoDAO congregacaoDAO = null;
        String resposta = "";
        //Obtenção de parâmetros necessários obtidos da página
        String nome = request.getParameter("Nome");
        String cpf = request.getParameter("Cpf");
        String cargo = request.getParameter("Cargo");
        String idCongregacaoEscolhido = request.getParameter("Congregacao");
        //String congregacao = request.getParameter(idCongregacaoEscolhido);
        String senha = request.getParameter("Senha");
        String senhaConfirmacao = request.getParameter("SenhaConfirmacao");
        String idReuniaoStr = request.getParameter("idReuniao");

        log.debug("nome: " + nome);
        log.debug("Cpf: " + cpf);
        log.debug("Cargo: " + cargo);
        log.debug("idCongregacao: " + idCongregacaoEscolhido);
        log.debug("idReuniao: " + idReuniaoStr);
        log.debug("Senha: " + senha);
        log.debug("SenhaConfirmacao: " + senhaConfirmacao);

        long idCongregacao = 0;
        long idReuniao = 0;
        if(idCongregacaoEscolhido != null && !idCongregacaoEscolhido.equals("")){
                idCongregacao = Long.parseLong(idCongregacaoEscolhido);
        }

        if(idReuniaoStr != null && !idReuniaoStr.equals("")){
            idReuniao = Long.parseLong(idReuniaoStr);
        }

        //Passoss para cadastrar um obreiro
        //1. Validar os dados cadastrais
        ValidadorDeFormularioDeObreiro validadorDeFormularioDeObreiro = new ValidadorDeFormularioDeObreiro();
        validadorDeFormularioDeObreiro.verificarCamposValidos(nome, cpf, cargo, idCongregacaoEscolhido, senha, senhaConfirmacao);

        if(validadorDeFormularioDeObreiro.isFormularioValido()){
            dataAccessObjectManager = new DataAccessObjectManager();
            congregacaoDAO = new CongregacaoDAO();
            Congregacao congregacaoCarregada = dataAccessObjectManager.obterCongregacao(idCongregacao);
            Reuniao reuniao = dataAccessObjectManager.obterReuniao(idReuniao);
            Identificacao identificacao = new Identificacao(senha);

            Obreiro obreiro = new Obreiro(nome, cargo, cpf, congregacaoCarregada, identificacao);
            dataAccessObjectManager.persistirObjeto(obreiro);

            reuniao.adicionarObreiroNaListaDePresenca(obreiro);
            dataAccessObjectManager.mergeDataObjeto(reuniao);

            resposta = dataAccessObjectManager.getMensagemStatus();
        }
        else{
            resposta = "Erro ao Cadastrar";
        }

        //Fechando o EntityManager de DataAccessObjectManager após uso
        if (dataAccessObjectManager != null) {
            dataAccessObjectManager.fecharEntityManager();
        }

        return resposta;

    }

}
