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
import org.apache.log4j.Logger;
/**
 *Classe que executa a acao solicitada de realizar o cadastro de um obreiro a 
 * partir dos dados necessários fornecidos no formulário de cadastro
 * 
 * @author Projeto Engyos Team
 *
 * @version 1.1
 *
 * @since 1.0
 */

public class ActionObreiroRegisterCommand implements Command{
    private static org.apache.log4j.Logger log = Logger.getLogger(ActionObreiroRegisterCommand.class);
    /**
     * Método que executa a requisição de cadastro de oberiro no banco de dados
     *
     * @param arg O <code>HttpServletRequest</code> e o <code>HttpServletResponse</code>     
     *
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
		
		//Obtenção de parâmetros necessários obtidos da página
		String nome = request.getParameter("Nome");
		String cpf = request.getParameter("Cpf");
		String cargo = request.getParameter("Cargo");
		String idCongregacaoEscolhido = request.getParameter("Congregacao");
		String congregacao = request.getParameter(idCongregacaoEscolhido);
		String senha = request.getParameter("Senha");
		String senhaConfirmacao = request.getParameter("SenhaConfirmacao");
                log.debug("nome: " + nome);
                log.debug("Cpf: " + cpf);
                log.debug("Cargo: " + cargo);
                log.debug("idCongregacao: " + idCongregacaoEscolhido);
                log.debug("congregacao: " + congregacao);
                log.debug("Senha: " + senha);
                log.debug("SenhaConfirmacao: " + senhaConfirmacao);
			
		long idCongregacao = 0;
		if(idCongregacaoEscolhido != null && !idCongregacaoEscolhido.equals("")){
			idCongregacao = Long.parseLong(idCongregacaoEscolhido);
		}
		
		//Obtém uma session para obtenção da digital inserida por um obreiro via applet
		//TODO - verificar soluções alternativas
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
			//TODO para teste, aós o recebimento dos parâmetros da página alterar 
			
			Identificacao identificacao = new Identificacao(senha);
			if(digitalLida != null && !digitalLida.equals("")){
				identificacao.setImpressaoDigital(digitalLida);
			}
			
			Obreiro obreiro = new Obreiro(nome, cargo, cpf, congregacaoCarregada, identificacao);
			dataAccessObjectManager.persistirObjeto(obreiro);			
			
			//Realização de passos para caso de sucesso ou fracasso por ocorrência de um erro interno
			//ex. banco de dados 
			if(dataAccessObjectManager.isOperacaoEfetuada()){
				//Instanciação e Carregar dados do obreiro registrado para apresentação
				formularioDeObreiro = new FormularioDeObreiro();
				formularioDeObreiro.definirDadosDeConfirmacaoDeCadastroObreiro(dataAccessObjectManager.getMensagemStatus(), nome, cpf, cargo, congregacao);			
				
				//Define mensagem de sucesso ao cadastrar
				formularioDeObreiro.setMensagemStatus(dataAccessObjectManager.getMensagemStatus());

                                //Encerra o atributo na session para digital
                                session.removeAttribute("DigitalObtida");
				
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

                //Fechando o EntityManager de DataAccessObjectManager após uso
                if (dataAccessObjectManager != null) {
                    dataAccessObjectManager.fecharEntityManager();
                }
		
		String respostaOperacao = formularioDeObreiro.getMensagemStatus();
		request.setAttribute("formularioDeObreiro", formularioDeObreiro);
		
		return respostaOperacao;
	}

}
