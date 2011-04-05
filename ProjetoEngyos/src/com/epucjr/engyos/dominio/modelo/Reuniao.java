package com.epucjr.engyos.dominio.modelo;

import com.epucjr.engyos.tecnologia.ferramentas.ControleBioDeviceHardware;
import com.epucjr.engyos.tecnologia.ferramentas.ControleBiometricoNBioBSPUtil;
import com.epucjr.engyos.tecnologia.ferramentas.ControleBiometricoUtil;
import com.epucjr.engyos.tecnologia.ferramentas.DispositivoBiometricoHardware;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.epucjr.engyos.tecnologia.utilitarios.DateTimeUtils;
import com.epucjr.engyos.tecnologia.utilitarios.HoraUtil;
import javax.persistence.OneToOne;

@Entity
@Indexed
public class Reuniao implements IReuniao{
	
	/******************************
	 *	ATRIBUTOS
	 ******************************/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idReuniao;
	
	@Field
	private String local;
	
	@Fields(
			{@Field(index=Index.TOKENIZED, store=Store.YES),
			@Field(name="datareun_sort",
			index=Index.UN_TOKENIZED)
			})
	private String data;
	
	@Field
	private String horario;	
	
	@ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@JoinColumn 
	private List<PresencaObreiro> listaDePresencaObreiro;
	
	private String reuniaoStatus;  //ATIVO-INATIVO

        private String horárioInicioEfetivo;

        private String horárioDeEncerramentoEsperado;
	
	@Transient
	private int quantidadeMaxObreirosReuniao;

        @Transient
	private final int tempoEmMinutosDeTolerânciaContagemPresenca = 30;

        @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinColumn
        ReuniaoSessionStatus sessionStatus;
	
		
	/******************************
	 *	CONSTRUTOR
	 ******************************/
	public Reuniao(){
		this.local = "";
		this.data = "";
		this.horario = "";		
		this.listaDePresencaObreiro = new ArrayList<PresencaObreiro>();
		this.reuniaoStatus = "ATIVO";
		this.quantidadeMaxObreirosReuniao = 0;
                this.horárioInicioEfetivo = "";
                this.horárioDeEncerramentoEsperado = "";
                this.sessionStatus = new ReuniaoSessionStatus();
	}
	
	public Reuniao(String local, String data, String hora) {
		this.local = local;
		this.data = data;
		this.horario = hora;		
		this.listaDePresencaObreiro = new ArrayList<PresencaObreiro>();
		this.reuniaoStatus = "ATIVO";
		this.quantidadeMaxObreirosReuniao = 0;
                this.horárioInicioEfetivo = "";
                this.horárioDeEncerramentoEsperado = "";
                this.sessionStatus = new ReuniaoSessionStatus();
	}
	
	/******************************
	 *	METODOS
	 ******************************/

        /**
         * Adiciona um obreiro na lista de presença em uma reunião
         *
         * @param obreiro O obreiro a ser adicionado na lista de presença
         */
        public void adicionarObreiroNaListaDePresenca(Obreiro obreiro){
		PresencaObreiro presencaObreiro = new PresencaObreiro(obreiro);
		if(!this.verificaObreiroNaLista(obreiro)){
			this.getListaDePresencaObreiro().add(presencaObreiro);
		}		
	}

        /**
         * Verifica se o obreiro está na lista de presença
         * @param obreiro O obreiro a ser verificado se está na lista
         * @return O resultado da verificação, false se o obeiro não estiver na lista
         */
	public boolean verificaObreiroNaLista(Obreiro obreiro){
		boolean obreiroNaLista = false;
		for(PresencaObreiro presencaObreiro : this.getListaDePresencaObreiro()){
			if(presencaObreiro.getObreiro().getCpf().equals(obreiro.getCpf())){
				obreiroNaLista = true;
				break;
			}
		}
		return obreiroNaLista;
	}

    public boolean verificaObreiroNaListaPelaDigital(String digitalObreiro) {
        Obreiro obreiro = this.buscarObreiroNaListaDePresenca(digitalObreiro);

        if (obreiro != null) {
            return true;
        }

        return false;
    }

    public boolean verificaObreiroNaListaPelaSenha(String senhaObreiro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void marcarPresencaDeObreiroNaListaPelaDigital(String digitalObreiro) {
        PresencaObreiro presencaObreiro = this.obterPresencaDeObreiroDaLista(digitalObreiro);
        presencaObreiro.setMomentoPresenca(HoraUtil.obterTempoCorrente());
        presencaObreiro.setDataPresenca(DateTimeUtils.obterDataCorrenteBr());
        presencaObreiro.setObreiroPresente(true);
    }

    @Override
    public void desmarcarPresencaDeObreiroNaListaPelaDigital(String digitalObreiro) {
        PresencaObreiro presencaObreiro = this.obterPresencaDeObreiroDaLista(digitalObreiro);
        presencaObreiro.setMomentoPresenca("");
        presencaObreiro.setDataPresenca("");
        presencaObreiro.setObreiroPresente(false);
    }

    public void marcarPresencaDeObreiroNaListaPelaSenha(String senhaObreiro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void definirNovaListaDePresenca(List<PresencaObreiro> listaDePresencaObreiro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int obterQuantidadeTotalDeObreirosNaLista() {

        return this.getListaDePresencaObreiro().size();
    }

    public int obterTotalDePresentesNaReunião() {
        int quantidadeDeObreirosPresente = 0;

        for(PresencaObreiro presencaObreiro : listaDePresencaObreiro){
            if(presencaObreiro.isObreiroPresente()){
                quantidadeDeObreirosPresente++;
            }
        }

        return quantidadeDeObreirosPresente;
    }

    //TODO
    @Override
    public int obterTempoDeDuracaoReuniao() {
        throw new UnsupportedOperationException("Not supported yet.");
    }



    public boolean verificarObreiroEstevePresenteNaReuniao(String cpf) {
        PresencaObreiro presencaObreiro = this.obterPresencaDeObreiroDaLista(this.listaDePresencaObreiro, cpf);

        return presencaObreiro.isObreiroPresente();
    }

    public boolean verificarObreiroEstevePresenteNaReuniaoPelaSenha(String senha) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean verificarObreiroEstevePresenteNaReuniaoPelaDigital(String digitalObreiro) {
        PresencaObreiro presencaObreiro = this.obterPresencaDeObreiroDaLista(digitalObreiro);

        return presencaObreiro.isObreiroPresente();
    }

    @Override
    public void marcarPresencaDeObreiroNaListaPeloCPF(String cpfObreiro) {
        PresencaObreiro presencaObreiro = this.obterPresencaDeObreiroDaLista(this.listaDePresencaObreiro, cpfObreiro);
        presencaObreiro.setMomentoPresenca(HoraUtil.obterTempoCorrente());
        presencaObreiro.setDataPresenca(DateTimeUtils.obterDataCorrenteBr());
        presencaObreiro.setObreiroPresente(true);
    }

    @Override
    public void desmarcarPresencaDeObreiroNaLista(String cpf) {
       PresencaObreiro presencaObreiro = this.obterPresencaDeObreiroDaLista(this.listaDePresencaObreiro, cpf);
       presencaObreiro.setMomentoPresenca("");
       presencaObreiro.setDataPresenca("");
       presencaObreiro.setObreiroPresente(false);
    }

    @Override
    public boolean verificaObreiroNaListaPeloCPF(String cpfObreiro) {

        Obreiro obreiro = this.buscarObreiroNaListaDePresenca(this.listaDePresencaObreiro, cpfObreiro);

        if(obreiro != null){
            return true;
        }

        return false;
    }

     /**
     * Busca por um obreiro utilizando um algoritmo de busca sequêncial
     *
     * @param listaDePresencaObreiro A lista de presença de obreiros da reunião
     * @param valorCPFProcurado O CPF do pbreiro que estamos procurando na lista
     *
     * @return o valor procurado, se presenta na lista ou null, se
     *  não for encontrado
     */
    @Override
    public Obreiro buscarObreiroNaListaDePresenca(List<PresencaObreiro> listaDePresencaObreiro, String valorCPFProcurado) {

        for(PresencaObreiro presencaObreiro : listaDePresencaObreiro){
            if(presencaObreiro.getObreiro().getCpf().equals(valorCPFProcurado)){
                return presencaObreiro.getObreiro();
            }
        }

        return null;
    }

    public PresencaObreiro obterPresencaDeObreiroDaLista(List<PresencaObreiro> listaDePresencaObreiro, String valorCPFProcurado) {

         for(PresencaObreiro presencaObreiro : listaDePresencaObreiro){
            if(presencaObreiro.getObreiro().getCpf().equals(valorCPFProcurado)){
                return presencaObreiro;
            }
        }

        return null;
    }

    public Obreiro buscarObreiroNaListaDePresenca(String digitalObreiro) {
        //Utiliza o algoritmo de comparacao da Implementaçaõ da Interface ControleBioDeviceHardware
        //Busca sequencial
        DispositivoBiometricoHardware dispositivoBioApi = new ControleBioDeviceHardware();
        //TODO Refatorar - Não inicializar hardware e dispositivo aqui uma vez que estão execução nem precisa do hardware

        dispositivoBioApi.inicializaHardware();
        dispositivoBioApi.abrirDispositivo();
        boolean obreiroEncontrado = false;

        for (PresencaObreiro presencaObreiro : listaDePresencaObreiro) {
            dispositivoBioApi.verificarMatchDigitalString(presencaObreiro.getObreiro().getIdentificacao().getImpressaoDigital(), digitalObreiro);
            obreiroEncontrado = dispositivoBioApi.isUsuarioValido();
            if (obreiroEncontrado) {
                return presencaObreiro.getObreiro();
            }
        }

        return null;
    }

      public PresencaObreiro obterPresencaDeObreiroDaLista(String digitalObreiro) {
        //Utiliza o algoritmo de comparacao da Implementaçaõ da Interface ControleBioDeviceHardware
        //Busca sequencial
        //DispositivoBiometricoHardware dispositivoBioApi = new ControleBioDeviceHardware();

        ControleBiometricoUtil controleBiometricoUtil = new ControleBiometricoNBioBSPUtil();
        //TODO Refatorar - Não inicializar hardware e dispositivo aqui uma vez que estão execução nem precisa do hardware

//        dispositivoBioApi.inicializaHardware();
//        dispositivoBioApi.abrirDispositivo();
        boolean obreiroEncontrado = false;

        for (PresencaObreiro presencaObreiro : listaDePresencaObreiro) {
            controleBiometricoUtil.verificarMatchDigitalString(presencaObreiro.getObreiro().getIdentificacao().getImpressaoDigital(), digitalObreiro);
            obreiroEncontrado = controleBiometricoUtil.isUsuarioValido();
            if (obreiroEncontrado) {
                return presencaObreiro;
            }
        }

        return null;
    }

    /**
     * Obtém um obreiro da lista de presença
     *
     * @param impressaoDigital A impressão digital do obreiro
     * @return O obreiro da lista, devolve null se o obreiro não está na lista
     */
    @Override
    public Obreiro obterObreiroDaListaPelaDigital(String impressaoDigital) {
        if(this.verificaObreiroNaListaPelaDigital(impressaoDigital)){
            return this.buscarObreiroNaListaDePresenca(impressaoDigital);
        }
        else{
            return null;
        }
    }

    /**
     * Obtém um obreiro da lista de presença
     *
     * @param cpfObreiro O CPF do obreiro
     * @return O obreiro da lista, devolve null se o obreiro não está na lista
     */
    @Override
    public Obreiro obterObreiroDaListaPeloCPF(String cpfObreiro) {
        if(this.verificaObreiroNaListaPeloCPF(cpfObreiro)){
            return this.buscarObreiroNaListaDePresenca(this.listaDePresencaObreiro, cpfObreiro);
        }
        else{
            return null;
        }
    }

	
	/******************************
	 *	GETTERS AND SETTERS
	 ******************************/


	
	public String getDia(){
		if(this.getData() != null && !this.getData().equals("") ){
			return DateTimeUtils.obterDiaDeDataBrasileira(this.getData());
		}
		else{
			return "";
		}
	}
	
	public String getMes(){
		if(this.getData() != null && !this.getData().equals("") ){
			return DateTimeUtils.obterMesDeDataBrasileira(this.getData());
		}
		else{
			return "";
		}
	}
	
	public String getAno(){
		if(this.getData() != null && !this.getData().equals("") ){
			return DateTimeUtils.obterAnoDeDataBrasileira(this.getData());
		}
		else{
			return "";
		}
	}
	
	public String getHora(){
		if(this.getHorario() != null && !this.getHorario().equals("") ){
			return DateTimeUtils.obterHora(this.getHorario());
		}
		else{
			return "";
		}
	}
	
	public String getMinuto(){
		if(this.getHorario() != null && !this.getHorario().equals("") ){
			return DateTimeUtils.obterMinuto(this.getHorario());
		}
		else{
			return "";
		}
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}		

	public long getIdReuniao() {
		return idReuniao;
	}	

	public String getReuniaoStatus() {
		return reuniaoStatus;
	}

	public void setReuniaoStatus(String reuniaoStatus) {
		this.reuniaoStatus = reuniaoStatus;
	}

	public List<PresencaObreiro> getListaDePresencaObreiro() {
		return listaDePresencaObreiro;
	}

	public void setListaDePresencaObreiro(
			List<PresencaObreiro> listaDePresencaObreiro) {
		this.listaDePresencaObreiro = listaDePresencaObreiro;
	}

	public int getQuantidadeMaxObreirosReuniao() {
		return quantidadeMaxObreirosReuniao;
	}

	public void setQuantidadeMaxObreirosReuniao(int quantidadeMaxObreirosReuniao) {
		this.quantidadeMaxObreirosReuniao = quantidadeMaxObreirosReuniao;
	}

    public int getTempoEmMinutosDeTolerânciaContagemPresenca() {
        return tempoEmMinutosDeTolerânciaContagemPresenca;
    }

    public String getHorárioDeEncerramentoEsperado() {
        return horárioDeEncerramentoEsperado;
    }

    public void setHorárioDeEncerramentoEsperado(String horárioDeEncerramentoEsperado) {
        this.horárioDeEncerramentoEsperado = horárioDeEncerramentoEsperado;
    }

    public String getHorárioInicioEfetivo() {
        return horárioInicioEfetivo;
    }

    public void setHorárioInicioEfetivo(String horárioInicioEfetivo) {
        this.horárioInicioEfetivo = horárioInicioEfetivo;
    }

    public ReuniaoSessionStatus getReuniaoSessionStatus() {
        return sessionStatus;
    }

    public void setReuniaoSessionStatus(ReuniaoSessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    

}
