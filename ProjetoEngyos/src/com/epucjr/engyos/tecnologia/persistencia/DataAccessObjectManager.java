package com.epucjr.engyos.tecnologia.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.Presenca;
import com.epucjr.engyos.dominio.modelo.Reuniao;

public class DataAccessObjectManager {

	/******************************
	 *	ATRIBUTOS
	 ******************************/

	private EntityManager entityManager;
	private String mensagemStatus;
	private boolean operacaoEfetuada;

	/******************************
	 *	CONSTRUTOR
	 ******************************/

	public DataAccessObjectManager() {
		this.entityManager = null;
		this.mensagemStatus = "";
		this.operacaoEfetuada = false;
	}

	/******************************
	 *	METODOS
	 ******************************/

	/**************************************************************************************
	 *  Criando um 'persistirObjeto' de modo genérico, persistindo qualquer objeto no banco
	 * e não somente um tipo especifico.
	 ***************************************************************************************/


	public void persistirObjeto(Object generico){

		boolean objetoExistente = false;

		if(entityManager == null || !entityManager.isOpen()){
			this.entityManager = EmFactory.getEntityManager();
		}		

		if(!objetoExistente){
			try{
				EntityTransaction transaction =  entityManager.getTransaction();
				if(!transaction.isActive()){
					transaction.begin();
				}
				entityManager.persist(generico);		
				transaction.commit();
				entityManager.close();
				this.setOperacaoEfetuada(true);
				this.setMensagemStatus("Operação Realizada com Sucesso!");
			}catch(Exception e){
				e.printStackTrace();
				this.setOperacaoEfetuada(false);
				this.setMensagemStatus("Erro interno na operação");			

			}
		}
		else{
			this.setOperacaoEfetuada(false);
			this.setMensagemStatus("Usuario Já Existente no Registro...");		

		}

	}	

	public void mergeDataObjeto(Object objeto){
		//TODO
		boolean usuarioExistente = false;

		if(entityManager == null || !entityManager.isOpen()){
			this.entityManager = EmFactory.getEntityManager();
		}		

		if(!usuarioExistente){
			try{
				EntityTransaction transaction =  entityManager.getTransaction();
				if(!transaction.isActive()){
					transaction.begin();
				}
				entityManager.merge(objeto);		
				transaction.commit();
				entityManager.close();
				this.setOperacaoEfetuada(true);
				this.setMensagemStatus("Item Inserido");		
			}catch(Exception e){
				e.printStackTrace();
				this.setOperacaoEfetuada(false);
				this.setMensagemStatus("Erro interno na operação");			

			}
		}
		else{
			this.setOperacaoEfetuada(false);
			this.setMensagemStatus("Usuario Já Existente no Registro...");		

		}
		//this.listaDeFuncionarios.add(funcionario);
		//this.carregarListaDeCandidatos();
	}	

	/*****************************************
	 * Criando um 'obter' para cada caso, no
	 * caso, classe. 
	 *****************************************/

	public Congregacao obterCongregacao(long idCongregacao){
		if(entityManager == null || !entityManager.isOpen()){
			this.entityManager = EmFactory.getEntityManager();
		}

		Congregacao congregacao = entityManager.find(Congregacao.class, idCongregacao);		

		if(congregacao == null){
			this.setOperacaoEfetuada(false);
			this.setMensagemStatus("Usuario não inexistente no sistema");
		}
		else{
			this.setOperacaoEfetuada(true);
			this.setMensagemStatus("Usuario encontrado");
		}		
		return congregacao;
	}

	

	public Object obterPresenca(int idDaPresenca){
		if(entityManager == null || !entityManager.isOpen()){
			this.entityManager = EmFactory.getEntityManager();
		}

		Object presenca = entityManager.find(Presenca.class, idDaPresenca);		

		if(presenca == null){
			this.setOperacaoEfetuada(false);
			this.setMensagemStatus("Usuario não inexistente no sistema");
		}
		else{
			this.setOperacaoEfetuada(true);
			this.setMensagemStatus("Usuario encontrado");
		}		
		return presenca;
	}

	public Reuniao obterReuniao(long idDaReuniao){
		if(entityManager == null || !entityManager.isOpen()){
			this.entityManager = EmFactory.getEntityManager();
		}

		Reuniao reuniao = entityManager.find(Reuniao.class, idDaReuniao);		

		if(reuniao == null){
			this.setOperacaoEfetuada(false);
			this.setMensagemStatus("Usuario não inexistente no sistema");
		}
		else{
			this.setOperacaoEfetuada(true);
			this.setMensagemStatus("Usuario encontrado");
		}		
		return reuniao;
	}
	
	
	public void deletarObjeto(Object generico){

		boolean objetoExistente = false;

		if(entityManager == null || !entityManager.isOpen()){
			this.entityManager = EmFactory.getEntityManager();
		}		

		if(!objetoExistente){
			try{
				EntityTransaction transaction =  entityManager.getTransaction();
				if(!transaction.isActive()){
					transaction.begin();
				}
				entityManager.remove(generico);		
				transaction.commit();
				entityManager.close();
				this.setOperacaoEfetuada(true);
				this.setMensagemStatus("Operação Realizada com Sucesso!");
			}catch(Exception e){
				e.printStackTrace();
				this.setOperacaoEfetuada(false);
				this.setMensagemStatus("Erro interno na operação");			

			}
		}
		else{
			this.setOperacaoEfetuada(false);
			this.setMensagemStatus("Usuario Já Existente no Registro...");		

		}

	}
	
	public Obreiro obterObreiro(String cpf){
		if(entityManager == null || !entityManager.isOpen()){
			this.entityManager = EmFactory.getEntityManager();
		}
		
		Obreiro usuario = entityManager.find(Obreiro.class, cpf);
		
		if(usuario == null){
			this.setOperacaoEfetuada(false);
			this.setMensagemStatus("Usuario não inexistente no sistema");
		}
		else{
			this.setOperacaoEfetuada(true);
			this.setMensagemStatus("Usuario encontrado");
		}		
		return usuario;
	}
	
	/******************************
	 *	GETTERS AND SETTERS
	 ******************************/

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public String getMensagemStatus() {
		return mensagemStatus;
	}

	public void setMensagemStatus(String mensagemStatus) {
		this.mensagemStatus = mensagemStatus;
	}

	public boolean isOperacaoEfetuada() {
		return operacaoEfetuada;
	}

	public void setOperacaoEfetuada(boolean operacaoEfetuada) {
		this.operacaoEfetuada = operacaoEfetuada;
	}
}
