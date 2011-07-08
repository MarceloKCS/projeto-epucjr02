package com.epucjr.engyos.dominio.visualizacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeReuniao;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.PresencaObreiro;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.dao.CongregacaoDAO;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import com.epucjr.engyos.tecnologia.utilitarios.DateTimeUtils;
import com.epucjr.engyos.tecnologia.utilitarios.ListUtilTokenizer;
import org.apache.log4j.Logger;

public class FormularioDeReuniao {
    private static org.apache.log4j.Logger log = Logger.getLogger(FormularioDeReuniao.class);

	/******************************
	 *	ATRIBUTOS
	 ******************************/
	private String hora;
	private String minuto;
	private String dia;
	private String mes;
	private String ano;
	private HashMap<String, String> camposPreenchidos;
	private HashMap < String,List<String> > campoListaPreenchido;
	private List<Obreiro> listaDeObreiros;
	private String mensagemStatus;
	private ValidadorDeFormularioDeReuniao validadorDeFormularioDeReuniao;
        private String congregacaoPadrao;

	/******************************
	 *	CONSTRUTOR
	 ******************************/
	public FormularioDeReuniao(){
		this.camposPreenchidos = new HashMap<String, String>();
		this.campoListaPreenchido = new HashMap<String, List<String>>();
		this.dia = "";
		this.mes = "";
		this.ano = "";
		this.hora = "";
		this.minuto = "";
		this.listaDeObreiros = new ArrayList<Obreiro>();
		this.mensagemStatus = "";
		this.validadorDeFormularioDeReuniao = new ValidadorDeFormularioDeReuniao();
                this.congregacaoPadrao = "";

                this.carregarDadosDoFormulario();
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

	public void definirCampoListaPreenchido( String nomeDoCampo, List<String> valorDoCampo ) {
		this.campoListaPreenchido.put(nomeDoCampo, valorDoCampo);
	}

	public List<String> obterCampoListaPreenchido(String nomeDoCampo) {
		if(this.campoListaPreenchido.containsKey(nomeDoCampo)){
			return this.campoListaPreenchido.get(nomeDoCampo);
		}
		else{
			return null;
		}
	}

	public boolean campoListaEstaPreenchido(String nomeDoCampo){
		if (this.campoListaPreenchido.containsKey(nomeDoCampo)){
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

	private void carregarDadosDoFormulario(){
            log.info("#carregarDadosDoFormulario");
		//Carregando a lista de Obreiros utilizada para agendamento da reunião
		DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
                CongregacaoDAO congregacaoDao = new CongregacaoDAO();
		boolean congregacaoPadraoDefinido = congregacaoDao.isConregacaoPadraoDefinida();
                List<Obreiro> listaDeObreiro = dataAccessObjectManager.obterListaDeObreiros();

		this.setListaDeObreiros(listaDeObreiro);

                if(congregacaoPadraoDefinido){
                    Congregacao congregacao = congregacaoDao.obteCongregacaoPadrao();
                    log.debug("nomeCongregacao: " + congregacao.getNome());
                    log.debug("endereco: " + congregacao.getEndereco());

                    this.setCongregacaoPadrao(congregacao.getEndereco());
                }

                //Fechando o EntityManager de DataAccessObjectManager após uso
                if (dataAccessObjectManager != null) {
                    dataAccessObjectManager.fecharEntityManager();
                }

                this.setMensagemStatus("Formulario Carregado");
		
	}

	public void definirCamposPreenchidosPeloUsuario(HttpServletRequest httpServletRequest){
		//Obtenção dos campos preenchidos pelo usuário
		String local = httpServletRequest.getParameter("local");
                String dataReuniao = httpServletRequest.getParameter("dataReuniao");
		String dia = httpServletRequest.getParameter("dataReuniaoDia");
		String mes = httpServletRequest.getParameter("dataReuniaoMes");
		String ano = httpServletRequest.getParameter("dataReuniaoAno");
		String hora = httpServletRequest.getParameter("horaReuniao");
		String minuto = httpServletRequest.getParameter("minutoReuniao");
                String idReuniao = httpServletRequest.getParameter("idReuniao");
		//Os Ids de obreiros obtidos são tokenizados pelo Javascript com %
		String obreiroIdsTokenized = httpServletRequest.getParameter("obreiros");

                 if(idReuniao != null && !idReuniao.equals("")){
			this.definirCampoPreenchido("idReuniao", idReuniao);
		}

		if(local != null && !local.equals("")){
			this.definirCampoPreenchido("local", local);
		}

		if(dia != null && !dia.equals("00")){
			this.definirCampoPreenchido("dataReuniaoDia", dia);
		}

		if(mes != null && !mes.equals("00")){
			this.definirCampoPreenchido("dataReuniaoMes", mes);
		}

		if(ano != null && !ano.equals("00")){
			this.definirCampoPreenchido("dataReuniaoAno", ano);
		}

                if(dataReuniao != null && !dataReuniao.equals("")){
                    this.definirCampoPreenchido("dataReuniao", dataReuniao);
                }
		
		if(hora != null && !hora.equals("24")){
			this.definirCampoPreenchido("horaReuniao", hora);
		}
		
		if(minuto != null && !minuto.equals("60")){
			this.definirCampoPreenchido("minutoReuniao", minuto);
		}

		if(obreiroIdsTokenized != null && !obreiroIdsTokenized.equals("")){
			List<String> listaDeIds = ListUtilTokenizer.obterListaString(obreiroIdsTokenized);			
			this.definirCampoListaPreenchido("obreiros", listaDeIds);			
		}

	}

        public void definirCamposPreenchidos(Reuniao reuniao){
		//Obtenção dos campos preenchidos pelo usuário
		String local = reuniao.getLocal();
		String dia = reuniao.getDia();
		String mes = reuniao.getMes();
		String ano = reuniao.getAno();
                String dataReuniao = DateTimeUtils.converterDataBr(dia, mes, ano);
		String hora = reuniao.getHora();
		String minuto = reuniao.getMinuto();
                String idReuniao = reuniao.getIdReuniao() + "";

                //Carga de obreiros selecionados da lista de presença no modelo proposto na página
                List<PresencaObreiro> listaDePresenca =  reuniao.getListaDePresencaObreiro();
                List<String> listaDeIds = new ArrayList<String>();
                for(PresencaObreiro obreiroDaLista : listaDePresenca){
                    listaDeIds.add(obreiroDaLista.getObreiro().getCpf());
                }

                if(idReuniao != null && !idReuniao.equals("")){
			this.definirCampoPreenchido("idReuniao", idReuniao);
		}

		if(local != null && !local.equals("")){
			this.definirCampoPreenchido("local", local);
		}

		if(dia != null && !dia.equals("00")){
			this.definirCampoPreenchido("dataReuniaoDia", dia);
		}

		if(mes != null && !mes.equals("00")){
			this.definirCampoPreenchido("dataReuniaoMes", mes);
		}

		if(ano != null && !ano.equals("00")){
			this.definirCampoPreenchido("dataReuniaoAno", ano);
		}

                 if(dataReuniao != null && !dataReuniao.equals("")){
                    this.definirCampoPreenchido("dataReuniao", dataReuniao);
                }

		if(hora != null && !hora.equals("24")){
			this.definirCampoPreenchido("horaReuniao", hora);
		}

		if(minuto != null && !minuto.equals("60")){
			this.definirCampoPreenchido("minutoReuniao", minuto);
		}

		if(listaDeIds != null && !listaDeIds.isEmpty()){
			this.definirCampoListaPreenchido("obreiros", listaDeIds);
		}

	}

	public void definirDadosDeConfirmacaoDeCadastroReuniao(String confirmacaoCadastro, String local, String data, String hora){

		this.definirDadosDeConfirmacao("confirmacao_cadastro", confirmacaoCadastro);
		this.definirDadosDeConfirmacao("confirmacao_local", local);
		this.definirDadosDeConfirmacao("confirmacao_data", data);
		this.definirDadosDeConfirmacao("confirmacao_hora", hora);
	}

        public void definirDadosDeConfirmacaoDeEdicaoReuniao(String confirmacaoEdicao, String local, String data, String hora){

		this.definirDadosDeConfirmacao("confirmacao_edicao", confirmacaoEdicao);
		this.definirDadosDeConfirmacao("confirmacao_local", local);
		this.definirDadosDeConfirmacao("confirmacao_data", data);
		this.definirDadosDeConfirmacao("confirmacao_hora", hora);
	}

	/******************************
	 *	GETTERS AND SETTERS
	 ******************************/

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getMinuto() {
		return minuto;
	}

	public void setMinuto(String minuto) {
		this.minuto = minuto;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public List<Obreiro> getListaDeObreiros() {
		return listaDeObreiros;
	}

	public void setListaDeObreiros(List<Obreiro> listaDeObreiros) {
		this.listaDeObreiros = listaDeObreiros;
	}

	public String getMensagemStatus() {
		return mensagemStatus;
	}

	public void setMensagemStatus(String mensagemStatus) {
		this.mensagemStatus = mensagemStatus;
	}

	public ValidadorDeFormularioDeReuniao getValidadorDeFormularioDeReuniao() {
		return validadorDeFormularioDeReuniao;
	}

	public void setValidadorDeFormularioDeReuniao(
			ValidadorDeFormularioDeReuniao validadorDeFormularioDeReuniao) {
		this.validadorDeFormularioDeReuniao = validadorDeFormularioDeReuniao;
	}

        public String getCongregacaoPadrao() {
            return congregacaoPadrao;
        }

        public void setCongregacaoPadrao(String congregacaoPadrao) {
            this.congregacaoPadrao = congregacaoPadrao;
        }


	
	

}
