package com.epucjr.engyos.dominio.modelo;

import com.epucjr.engyos.tecnologia.ferramentas.ControleBioDeviceHardware;
import com.epucjr.engyos.tecnologia.ferramentas.DispositivoBioDeviceInterface;
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

import com.epucjr.engyos.tecnologia.utilitarios.DateUtils;

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
	
	@Transient
	private int quantidadeMaxObreirosReuniao;
	
		
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
	}
	
	public Reuniao(String local, String data, String hora) {
		this.local = local;
		this.data = data;
		this.horario = hora;		
		this.listaDePresencaObreiro = new ArrayList<PresencaObreiro>();
		this.reuniaoStatus = "ATIVO";
		this.quantidadeMaxObreirosReuniao = 0;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean verificaObreiroNaListaPelaSenha(String senhaObreiro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void marcarPresencaDeObreiroNaListaPelaDigital(String digitalObreiro) {
        throw new UnsupportedOperationException("Not supported yet.");
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean verificarObreiroEstevePresenteNaReuniao(String cpf) {
        PresencaObreiro presencaObreiro = this.obterPresencaDeObreiroDaLista(this.listaDePresencaObreiro, cpf, 0, this.listaDePresencaObreiro.size() - 1);

        return presencaObreiro.isObreiroPresente();
    }

    public boolean verificarObreiroEstevePresenteNaReuniaoPelaSenha(String senha) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void marcarPresencaDeObreiroNaListaPeloCPF(String cpfObreiro) {
         PresencaObreiro presencaObreiro = this.obterPresencaDeObreiroDaLista(this.listaDePresencaObreiro, cpfObreiro, 0, this.listaDePresencaObreiro.size() - 1);

         presencaObreiro.setObreiroPresente(true);
    }

    @Override
    public boolean verificaObreiroNaListaPeloCPF(String cpfObreiro) {

        Obreiro obreiro = this.buscarObreiroNaListaDePresenca(this.listaDePresencaObreiro, cpfObreiro, 0, this.listaDePresencaObreiro.size() - 1);

        if(obreiro != null){
            return true;
        }

        return false;
    }

     /**
     * Busca por um obreiro utilizando um algoritmo de busca binária
     *
     * @param listaDePresencaObreiro A lista de presença de obreiros da reunião
     * @param valorCPFProcurado O CPF do pbreiro que estamos procurando na lista
     * @param left O indice do limite da lista à esquerda
     * @param right O indice do limite da lista à direira
      *
     * @return o valor procurado, se presenta na lista ou null, se
     *  não for encontrado
     */
    @Override
    public Obreiro buscarObreiroNaListaDePresenca(List<PresencaObreiro> listaDePresencaObreiro, String valorCPFProcurado, int left, int right) {

        if (left > right) {
            return null;
        }

        int middle = (left + right) / 2;

        if (listaDePresencaObreiro.get(middle).getObreiro().getCpf().equals(valorCPFProcurado)){
            return listaDePresencaObreiro.get(middle).getObreiro();
        } else if (listaDePresencaObreiro.get(middle).getObreiro().getCpf().compareTo(valorCPFProcurado) > 0) {
            return buscarObreiroNaListaDePresenca(listaDePresencaObreiro, valorCPFProcurado, left, middle - 1);
        } else {
            return buscarObreiroNaListaDePresenca(listaDePresencaObreiro, valorCPFProcurado, middle + 1, right);
        }

    }

    public PresencaObreiro obterPresencaDeObreiroDaLista(List<PresencaObreiro> listaDePresencaObreiro, String valorCPFProcurado, int left, int right) {

        if (left > right) {
            return null;
        }

        int middle = (left + right) / 2;

        if (listaDePresencaObreiro.get(middle).getObreiro().getCpf().equals(valorCPFProcurado)){
            return listaDePresencaObreiro.get(middle);
        } else if (listaDePresencaObreiro.get(middle).getObreiro().getCpf().compareTo(valorCPFProcurado) > 0) {
            return obterPresencaDeObreiroDaLista(listaDePresencaObreiro, valorCPFProcurado, left, middle - 1);
        } else {
            return obterPresencaDeObreiroDaLista(listaDePresencaObreiro, valorCPFProcurado, middle + 1, right);
        }

    }

    public Obreiro buscarObreiroNaListaDePresenca(String digitalObreiro) {
        //Utiliza o algoritmo de comparacao da Implementaçaõ da Interface ControleBioDeviceHardware
        //Busca sequencial
        DispositivoBioDeviceInterface dispositivoBioApi = new ControleBioDeviceHardware();
        //TODO Refatorar - Não inicializar hardware e dispositivo aqui uma vez que estão execução nem precisa do hardware

        dispositivoBioApi.inicializaHardware();
        dispositivoBioApi.abrirDispositivo();
        boolean obreiroEncontrado = false;

        for (PresencaObreiro presencaObreiro : listaDePresencaObreiro) {
            System.out.println("Presenca = " + presencaObreiro.getObreiro().getNome());
            System.out.println("Presenca = " + presencaObreiro.getObreiro().getIdentificacao().getImpressaoDigital());
            dispositivoBioApi.verificarMatchDigitalString(presencaObreiro.getObreiro().getIdentificacao().getImpressaoDigital(), digitalObreiro);
            obreiroEncontrado = dispositivoBioApi.isUsuarioValido();
            if (obreiroEncontrado) {
                return presencaObreiro.getObreiro();

            }
        }

        return null;

    }

	
	/******************************
	 *	GETTERS AND SETTERS
	 ******************************/
	
	public String getDia(){
		if(this.getData() != null && !this.getData().equals("") ){
			return DateUtils.obterDiaDeDataBrasileira(this.getData());
		}
		else{
			return "";
		}
	}
	
	public String getMes(){
		if(this.getData() != null && !this.getData().equals("") ){
			return DateUtils.obterMesDeDataBrasileira(this.getData());
		}
		else{
			return "";
		}
	}
	
	public String getAno(){
		if(this.getData() != null && !this.getData().equals("") ){
			return DateUtils.obterAnoDeDataBrasileira(this.getData());
		}
		else{
			return "";
		}
	}
	
	public String getHora(){
		if(this.getHorario() != null && !this.getHorario().equals("") ){
			return DateUtils.obterHora(this.getHorario());
		}
		else{
			return "";
		}
	}
	
	public String getMinuto(){
		if(this.getHorario() != null && !this.getHorario().equals("") ){
			return DateUtils.obterMinuto(this.getHorario());
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

}
