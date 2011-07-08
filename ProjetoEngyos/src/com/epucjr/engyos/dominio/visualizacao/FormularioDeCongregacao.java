package com.epucjr.engyos.dominio.visualizacao;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeCongregacao;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.tecnologia.dao.CongregacaoDAO;
import org.apache.log4j.Logger;

public class FormularioDeCongregacao {
	
	/******************************
	 *	ATRIBUTOS
	 ******************************/
        private static org.apache.log4j.Logger log = Logger.getLogger(FormularioDeCongregacao.class);
	private Map<String, Object> camposPreenchidos;
        private boolean congregacaoPadraoDefinida;
        private boolean formularioPertenceCongregacaoPadrao;
	private String mensagemStatus;
	private ValidadorDeFormularioDeCongregacao validadorDeFormularioDeCongregacao;

	/******************************
	 *	CONSTRUTOR
	 ******************************/
	public FormularioDeCongregacao(){
		this.mensagemStatus = "";
		this.validadorDeFormularioDeCongregacao = new ValidadorDeFormularioDeCongregacao();	
		this.camposPreenchidos = new HashMap<String, Object>();
                this.congregacaoPadraoDefinida = false;
                formularioPertenceCongregacaoPadrao = false;
                this.carregarDadosDoFormulario();
	}
	
	/******************************
	 *	METODOS
	 ******************************/
   
    public void definirCampoPreenchido(String nomeDoCampo, Object valorDoCampo ) {
		this.camposPreenchidos.put(nomeDoCampo, valorDoCampo);
    }
    
    public Object obterCampoPreenchido(String nomeDoCampo) {
    	if(this.camposPreenchidos.containsKey(nomeDoCampo)){
    		return this.camposPreenchidos.get(nomeDoCampo);
    	}
    	else{
    		return "";
    	}
    }
    
    public boolean verificarCampoPreenchido(String nomeDoCampo){
    	if (this.camposPreenchidos.containsKey(nomeDoCampo)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public void definirDadosDeConfirmacao(String nomeDoCampo, Object valorDoCampo ) {
		this.camposPreenchidos.put(nomeDoCampo, valorDoCampo);
    }
    
    public Object obterDadoDeConfirmacao(String nomeDoCampo) {
    	if(this.camposPreenchidos.containsKey(nomeDoCampo)){
    		return this.camposPreenchidos.get(nomeDoCampo);
    	}
    	else{
    		return "";
    	}
    }
    
    public boolean verificarDadoDeConfirmacao(String nomeDoCampo){
    	if (this.camposPreenchidos.containsKey(nomeDoCampo)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
	
	private void carregarDadosDoFormulario(){
                log.debug("#carregarDadosDoFormulario");
		CongregacaoDAO congregacaoDAO = new CongregacaoDAO();
                boolean congregacaoPadraoDefinida = congregacaoDAO.isConregacaoPadraoDefinida();
                log.debug("congregacaoPadraoDefinida = " + congregacaoPadraoDefinida);
                log.debug("mensagem status = " + congregacaoDAO.getMensagemStatus());
                this.setCongregacaoPadraoDefinida(congregacaoPadraoDefinida);
                this.setMensagemStatus("Formulario Carregado");
	}
	
	public void definirCamposPreenchidosPeloUsuario(HttpServletRequest httpServletRequest){
		//Recebendo os campos que podem ter sido preenchidos pelo usuário
		String nomeDaCongregacao = httpServletRequest.getParameter("Nome");
		String endereco = httpServletRequest.getParameter("Endereco");
		String idCongregacao = httpServletRequest.getParameter("idCongregacao");
                String congregacaoPadrao = httpServletRequest.getParameter("congregacao_padrao");

                if (idCongregacao != null && !idCongregacao.equals("")) {
                    this.definirCampoPreenchido("idCongregacao", idCongregacao);
                }
		
		if(nomeDaCongregacao != null && !nomeDaCongregacao.equals("")){
			this.definirCampoPreenchido("Nome", nomeDaCongregacao);
		}
		
		if(endereco != null && !endereco.equals("")){
			this.definirCampoPreenchido("Endereco", endereco);
		}

                if(congregacaoPadrao != null && !congregacaoPadrao.equals("")){
                    if(congregacaoPadrao.equals("true")){
                        this.definirCampoPreenchido("congregacao_padrao", true);
                    }
		}
	}

      public void definirCamposPreenchidos(Congregacao congregacao) {
        //Recebendo os campos que podem ter sido preenchidos pelo usuário
        String nomeDaCongregacao = congregacao.getNome();
        String endereco = congregacao.getEndereco();
        String idCongregacao = congregacao.getIdCongregacao() + "";
        boolean congregacaoPadrao = congregacao.isCongregacaoPadrao();

        if (idCongregacao != null && !idCongregacao.equals("")) {
            this.definirCampoPreenchido("idCongregacao", idCongregacao);
        }

        if (nomeDaCongregacao != null && !nomeDaCongregacao.equals("")) {
            this.definirCampoPreenchido("Nome", nomeDaCongregacao);
        }

        if (endereco != null && !endereco.equals("")) {
            this.definirCampoPreenchido("Endereco", endereco);
        }

        if(congregacaoPadrao){
            this.definirCampoPreenchido("congregacao_padrao", congregacaoPadrao);
        }
    }
	
	public void definirDadosDeConfirmacaoDeCadastro(String confirmacaoCadastro, String nomeDaCongregacao, String endereco){
		this.definirDadosDeConfirmacao("confirmacao_cadastro", confirmacaoCadastro);
		this.definirDadosDeConfirmacao("confirmacao_nomeDaCongregacao", nomeDaCongregacao);
		this.definirDadosDeConfirmacao("confirmacao_endereco", endereco);
	}

        public void definirDadosDeConfirmacaoDeEdicao(String confirmacaoEdicao, String nomeDaCongregacao, String endereco){
		this.definirDadosDeConfirmacao("confirmacao_edicao", confirmacaoEdicao);
		this.definirDadosDeConfirmacao("confirmacao_nomeDaCongregacao", nomeDaCongregacao);
		this.definirDadosDeConfirmacao("confirmacao_endereco", endereco);
	}
	
	/******************************
	 *	GETTERS AND SETTERS
	 ******************************/

	public String getMensagemStatus() {
		return mensagemStatus;
	}

	public void setMensagemStatus(String mensagemStatus) {
		this.mensagemStatus = mensagemStatus;
	}

	public ValidadorDeFormularioDeCongregacao getValidadorDeFormularioDeCongregacao() {
		return validadorDeFormularioDeCongregacao;
	}

	public void setValidadorDeFormularioDeCongregacao(
			ValidadorDeFormularioDeCongregacao validadorDeFormularioDeCongregacao) {
		this.validadorDeFormularioDeCongregacao = validadorDeFormularioDeCongregacao;
	}

        public boolean isCongregacaoPadraoDefinida() {
            return congregacaoPadraoDefinida;
        }

        public void setCongregacaoPadraoDefinida(boolean congregacaoPadraoDefinida) {
            this.congregacaoPadraoDefinida = congregacaoPadraoDefinida;
        }

        public boolean isFormularioPertenceCongregacaoPadrao() {
            return formularioPertenceCongregacaoPadrao;
        }

        public void setFormularioPertenceCongregacaoPadrao(boolean formularioPertenceCongregacaoPadrao) {
            this.formularioPertenceCongregacaoPadrao = formularioPertenceCongregacaoPadrao;
        }


	
	
	
}
