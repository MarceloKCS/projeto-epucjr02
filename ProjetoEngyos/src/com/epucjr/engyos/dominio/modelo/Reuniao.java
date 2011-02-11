package com.epucjr.engyos.dominio.modelo;

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
public class Reuniao {
	
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
	
	public void adicionarObreiroNaLista(Obreiro obreiro){
		PresencaObreiro presencaObreiro = new PresencaObreiro(obreiro);
		if(!this.isObreiroNaLista(obreiro)){
			this.getListaDePresencaObreiro().add(presencaObreiro);
		}		
	}
	
	public boolean isObreiroNaLista(Obreiro obreiro){
		boolean obreiroNaLista = false;
		for(PresencaObreiro presencaObreiro : this.getListaDePresencaObreiro()){
			if(presencaObreiro.getObreiro().getCpf().equals(obreiro.getCpf())){
				obreiroNaLista = true;
				break;
			}
		}
		return obreiroNaLista;
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
