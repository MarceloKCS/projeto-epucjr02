package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeObreiro;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeObreiro;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *Classe que tem como finalidade executar a ação que cumpra a promessa de editar um obreiro 
 * cadastrado no sistema
 *
 * @author Projeto Engyos Team
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class ActionObreiroEditCommand implements Command {

    /**
     * Método que executa a requisição de edição do formulário de obreiro
     *
     * @param arg O <code>HttpServletRequest</code> e o <code>HttpServletResponse</code>
     * @return o Objeto com o resultado da acao requisitada, utilizada pela view
     *factory no <code>FrontControllerServlet</code>
     *
     * @see FrontControlerServlet#servico(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse
     * @see CommandFactory
     * @see ViewFactory
     * @see Obreiro
     * @see ValidadorDeFormularioDeObreiro
     * @see Identificacao
     * @see DataAccessObjectManager
     * @see FormularioDeObreiro
     */
    public Object execute(Object... arg) {
        //Instanciação de objetos e variáveis necessários para a realização do cadastro
        HttpServletRequest request = (HttpServletRequest) arg[0];
        DataAccessObjectManager dataAccessObjectManager = null;
        FormularioDeObreiro formularioDeObreiro = null;
        String msgSenha = "";
        String msgDigital = "";

        //Obtenção de parâmetros necessários obtidos da página
        String nome = request.getParameter("Nome");
        String cpf = request.getParameter("Cpf");
        String cargo = request.getParameter("Cargo");
        String idCongregacaoEscolhido = request.getParameter("Congregacao");
        String congregacao = request.getParameter(idCongregacaoEscolhido);
        String senha = request.getParameter("Senha");
        String senhaConfirmacao = request.getParameter("SenhaConfirmacao");

        System.out.println("Dados = " + cpf.trim());

        System.out.println("idCongr = " + idCongregacaoEscolhido);
        System.out.println("Nome = " + nome + " Cargo = " + cargo + " COngr = " + congregacao + " ");
        long idCongregacao = 0;
        if (idCongregacaoEscolhido != null && !idCongregacaoEscolhido.equals("")) {
            idCongregacao = Long.parseLong(idCongregacaoEscolhido);
        }

        //Obtém uma session para obtenção da digital inserida por um obreiro via applet
        //TODO - verificar soluções alternativas
        HttpSession session = request.getSession();

        String digitalLida = "";

        if (!session.isNew()) {
            digitalLida = (String) session.getAttribute("DigitalObtida");
        }

        if (cpf.equals("88436038339")) {
            System.out.println("igual");
        } else {
            System.out.println("tem coisa ai");
            System.out.println("cpf =" + cpf + "___");
        }

        //Reobtenção do obreiro a ser editado
        dataAccessObjectManager = new DataAccessObjectManager();
        Obreiro obreiro = dataAccessObjectManager.obterObreiro(cpf);
        System.out.println("ObreiroNBD = " + obreiro.getNome());
        //Reorganização dos dados para validaçao

        if (!nome.equals(obreiro.getNome())) {
            obreiro.setNome(nome);
        }
        if (!cargo.equals(obreiro.getCargo())) {
            obreiro.setCargo(cargo);
        }

        //Não adequado definir mensagens de retorno aqui, mas...
        if (!senha.equals("") && !senha.equals(obreiro.getIdentificacao().getSenha())) {
            obreiro.getIdentificacao().setSenha(senha);
            msgSenha = "Senha Alterada";
        } else {
            senha = obreiro.getIdentificacao().getSenha();
            senhaConfirmacao = obreiro.getIdentificacao().getSenha();
            msgSenha = "Senha Não Alterada";
        }

        //Não adequado definir mensagens de retorno aqui, mas...
        if (digitalLida != null && !digitalLida.equals("")) {
            obreiro.getIdentificacao().setImpressaoDigital(digitalLida);
            msgDigital = "Digital Alterada";
        } else {
            digitalLida = obreiro.getIdentificacao().getImpressaoDigital();
            msgDigital = "Digital Não Alterada";
        }

        if (idCongregacao != 0 && idCongregacao != obreiro.getCongregacao().getIdCongregacao()) {
            Congregacao congregacaoNova = dataAccessObjectManager.obterCongregacao(idCongregacao);
            obreiro.setCongregacao(congregacaoNova);
        }

        //1. Validar os dados cadastrais
        ValidadorDeFormularioDeObreiro validadorDeFormularioDeObreiro = new ValidadorDeFormularioDeObreiro();
        validadorDeFormularioDeObreiro.verificarCamposValidos(nome, cpf, cargo, congregacao, senha, senhaConfirmacao);


        if (validadorDeFormularioDeObreiro.isFormularioValido()) {

            //Identificacao identificacao = new Identificacao(impressaoDigital, senha);
            //TODO para teste, aós o recebimento dos parâmetros da página alterar  

            dataAccessObjectManager.mergeDataObjeto(obreiro);

            //Realização de passos para caso de sucesso ou fracasso por ocorrência de um erro interno
            //ex. banco de dados
            if (dataAccessObjectManager.isOperacaoEfetuada()) {
                //Instanciação e Carregar dados do obreiro registrado para apresentação
                formularioDeObreiro = new FormularioDeObreiro();
                formularioDeObreiro.definirDadosDeConfirmacaoDeEdicaoObreiro(dataAccessObjectManager.getMensagemStatus(), nome, cpf, cargo, congregacao, msgSenha, msgDigital);

                //Define mensagem de sucesso ao cadastrar
                formularioDeObreiro.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());

                //Encerra o atributo na session para digital
                session.removeAttribute("DigitalObtida");

            } //Ocorreu um erro de edição
            else {
                formularioDeObreiro = new FormularioDeObreiro();
                formularioDeObreiro.definirCamposPreenchidosPeloUsuario(request);
                formularioDeObreiro.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());

            }

        } else {
            formularioDeObreiro = new FormularioDeObreiro();
            formularioDeObreiro.setValidadorDeFormularioDeObreiro(validadorDeFormularioDeObreiro);
            formularioDeObreiro.definirCamposPreenchidosPeloUsuario(request);
            formularioDeObreiro.setMensagemStatus("Erro ao Editar");
        }

        String respostaOperacao = formularioDeObreiro.getMensagemStatus();
        request.setAttribute("formularioDeObreiro", formularioDeObreiro);
        System.out.println("value!= " + respostaOperacao);
        return respostaOperacao;

    }
}
