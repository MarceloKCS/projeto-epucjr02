package com.epucjr.engyos.dominio.visualizacao;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeCongregacao;
import com.epucjr.engyos.dominio.modelo.Congregacao;

public class FormularioDeCongregacao {
	
	/******************************
	 *	ATRIBUTOS
	 ******************************/
	private Map<String, String> camposPreenchidos;
	private String mensagemStatus;
	private ValidadorDeFormularioDeCongregacao validadorDeFormularioDeCongregacao;

	/******************************
	 *	CONSTRUTOR
	 ******************************/
	public FormularioDeCongregacao(){
		this.mensagemStatus = "";
		this.validadorDeFormularioDeCongregacao = new ValidadorDeFormularioDeCongregacao();	
		this.camposPreenchidos = new HashMap<String, String>();
	}
	
	/******************************
	 *	METODOS
	 ******************************/
    public void definirCampoPreenchido(String nomeDoCampo, String valorDoCampo ) {
		this.camposPreenchidos.put(nomeDoCampo, valorDoCampo);
    }
    
    public String obterCampoPreenchido(String nomeDoCampo) {
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
    
    public void definirDadosDeConfirmacao(String nomeDoCampo, String valorDoCampo ) {
		this.camposPreenchidos.put(nomeDoCampo, valorDoCampo);
    }
    
    public String obterDadoDeConfirmacao(String nomeDoCampo) {
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
	
    //Formulario não possui dados com requisição de carga previa ao formulário
	/*private void carregarDadosDoFormulario(Congregacao congregacao){
		
	}*/
	
	public void definirCamposPreenchidosPeloUsuario(HttpServletRequest httpServletRequest){
		//Recebendo os campos que podem ter sido preenchidos pelo usuário
		String nomeDaCongregacao = httpServletRequest.getParameter("Nome");
		String endereco = httpServletRequest.getParameter("Endereco");
		String idCongregacao = httpServletRequest.getParameter("idCongregacao");


                if (idCongregacao != null && !idCongregacao.equals("")) {
                    this.definirCampoPreenchido("idCongregacao", idCongregacao);
                }
		
		if(nomeDaCongregacao != null && !nomeDaCongregacao.equals("")){
			this.definirCampoPreenchido("Nome", nomeDaCongregacao);
		}
		
		if(endereco != null && !endereco.equals("")){
			this.definirCampoPreenchido("Endereco", endereco);
		}
	}

      public void definirCamposPreenchidos(Congregacao congregacao) {
        //Recebendo os campos que podem ter sido preenchidos pelo usuário
        String nomeDaCongregacao = congregacao.getNome();
        String endereco = congregacao.getEndereco();
        String idCongregacao = congregacao.getIdCongregacao() + "";


        if (idCongregacao != null && !idCongregacao.equals("")) {
            this.definirCampoPreenchido("idCongregacao", idCongregacao);
        }

        if (nomeDaCongregacao != null && !nomeDaCongregacao.equals("")) {
            this.definirCampoPreenchido("Nome", nomeDaCongregacao);
        }

        if (endereco != null && !endereco.equals("")) {
            this.definirCampoPreenchido("Endereco", endereco);
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
	
	
	
}
