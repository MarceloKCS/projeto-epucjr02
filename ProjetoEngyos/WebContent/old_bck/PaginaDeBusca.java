package com.epucjr.engyos.dominio.modelo;

import java.util.ArrayList;

public class PaginaDeBusca {
	private int paginaTotal;
	private int quebraPorBusca;
	private int paginaAtual;
	private ArrayList<ArrayList> paginaListaPaginaBusca; //armazena listaDePaginaDeBusca
    private ArrayList listaDePaginaDeBusca; //armazena objeto buscado
    private String tipoDeBusca;

    ///////////////
    //CONSTRUTOR //
    ///////////////
	public PaginaDeBusca(){
		this.quebraPorBusca = 8;
	}
		
	/////////////////////////
	// GETTERS AND SETTERS //
	/////////////////////////
	public void setTipoDeBusca(String tipoDeBusca){
		this.tipoDeBusca = tipoDeBusca;
	}
	public String getTipoDeBusca(){
		return this.tipoDeBusca;
	}
	public int getPaginaTotal(){
		return(this.paginaTotal);
	}
	
	public ArrayList<ArrayList> getPaginaListaPaginaBusca(){
		return(this.paginaListaPaginaBusca);
	}
	
	public ArrayList getListaDePaginaDeBusca(){
		return(this.listaDePaginaDeBusca);
	}
	
	public int getPaginaAtual(){
		return(this.paginaAtual);
	}
	
	/////////////
	// METODOS //
	/////////////
    public void paginarBusca(ArrayList listaDePaginaDeBusca){
    	int contadorDeBusca = -1;
		this.paginaListaPaginaBusca = new ArrayList<ArrayList>();
		this.listaDePaginaDeBusca = new ArrayList();
		
    	for(int indiceFor = 1; indiceFor <= listaDePaginaDeBusca.size(); indiceFor++){
    		contadorDeBusca++;
    		this.listaDePaginaDeBusca.add(listaDePaginaDeBusca.get(contadorDeBusca));
    		if(this.listaDePaginaDeBusca.size()==this.quebraPorBusca){
    			this.paginaListaPaginaBusca.add(this.listaDePaginaDeBusca);
    			this.listaDePaginaDeBusca = new ArrayList();
    		}
    		else{
    			if((indiceFor == listaDePaginaDeBusca.size())&&(this.listaDePaginaDeBusca.size() < this.quebraPorBusca)){
    				this.paginaListaPaginaBusca.add(this.listaDePaginaDeBusca);
    			}
    		}
    	}
    	this.paginaTotal = this.paginaListaPaginaBusca.size();
    }
}
