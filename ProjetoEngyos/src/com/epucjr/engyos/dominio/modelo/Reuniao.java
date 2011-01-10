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
import javax.persistence.OneToMany;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class Reuniao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idReuniao;
	@Field
	private String local;
	@Field
	private String data;
	@Field
	private String hora;
	@OneToMany (fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@JoinColumn
	private List<Obreiro> listaDePresenca;
	
	////////////////
	// CONSTRUTOR //
	////////////////
	public Reuniao(){
		
	}
	
	public Reuniao(String data, String hora) {
		setLocal();
		setData(data);
		setHora(hora);
		this.listaDePresenca = new ArrayList<Obreiro>();
	}
	
	/////////////////////////
	// GETTERS AND SETTERS //
	/////////////////////////
	public String  getLocal(){
		return this.local;
	}
	public void setLocal(){
		this.local = "Parque São Joaquim";
	}
	public String getData() {
		return this.data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDia() {
		return this.data.substring(0, 2);
	}
	public String getMes() {
		return this.data.substring(2, 4);
	}
	public String getAno() {
		return this.data.substring(4);
	}
	public String getHoraReuniao(){
		return this.hora;
	}
	public String getHora(){
		return this.hora.substring(0, 2);
	}
	public String getMinuto(){
		return this.hora.substring(2);
	}
	public void setHora(String hora){
		this.hora = hora;
	}
	public List<Obreiro> getListaDePresenca() {
		return this.listaDePresenca;
	}
	public void setListaDePresenca(List<Obreiro> listaDePresenca) {
		this.listaDePresenca = listaDePresenca;
	}
	public void addObreiroListaDePresenca(Obreiro obreiro) {
		this.listaDePresenca.add(obreiro);
	}
	public void removeObreiroListaDePresenca(Obreiro obreiro) {
		this.listaDePresenca.remove(obreiro);
	}
	public void removeObreiroListaDePresenca(int indice) {
		this.listaDePresenca.remove(indice);
	}
	public Obreiro getObreirosListaDePresenca(int indice) {
		return this.listaDePresenca.get(indice);
	}
	public int getQtdObreirosListaDePresenca() {
		return listaDePresenca.size();
	}
	public long getIdReuniao(){
		return this.idReuniao;
	}
	public void setIdReuniao(long idReuniao){
		this.idReuniao = idReuniao;
	}

}
