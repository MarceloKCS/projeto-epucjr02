package com.epucjr.engyos.tecnologia.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import com.epucjr.engyos.dominio.modelo.Administrador;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.dominio.modelo.SessionStatus;

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
	 *  Criando um 'persistirObjeto' de modo gen�rico, persistindo qualquer objeto no banco
	 * e n�o somente um tipo especifico.
	 ***************************************************************************************/


	public void persistirObjeto(Object generico){

		boolean objetoExistente = this.isObjetoExistente(generico);

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
				//entityManager.close();
				this.setOperacaoEfetuada(true);
				this.setMensagemStatus("Opera��o Realizada com Sucesso!");
			}catch(Exception e){
				e.printStackTrace();
				this.setOperacaoEfetuada(false);
				this.setMensagemStatus("Erro interno na opera��o");			

			}
		}
		else{
			this.setOperacaoEfetuada(false);
					

		}

	}

	public boolean isObjetoExistente(Object generico){

		if(entityManager == null || !entityManager.isOpen()){
			this.entityManager = EmFactory.getEntityManager();
		}

		if(generico instanceof Obreiro){
			Obreiro obreiro = (Obreiro) generico;
			String cpf = obreiro.getCpf();
			try{
				Obreiro obreiroObtido = (Obreiro) entityManager.createQuery("from Obreiro o where o.cpf = '" + cpf + "'").getSingleResult();
				if(obreiroObtido.getCpf().equals(cpf)){
					this.setMensagemStatus("Usuario J� Existente no Registro...");
					return true;
					
				}
				else{
					return false;
				}
			}
			catch(NoResultException e){
				return false;
			}

		}
		return false;
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
				this.setOperacaoEfetuada(true);
				this.setMensagemStatus("Opera��o Realizada com Sucesso!");
			}catch(Exception e){
				e.printStackTrace();
				this.setOperacaoEfetuada(false);
				this.setMensagemStatus("Erro interno na opera��o");			

			}
		}
		else{
			this.setOperacaoEfetuada(false);
			this.setMensagemStatus("Usuario J� Existente no Registro...");		

		}
		//this.listaDeFuncionarios.add(funcionario);
		//this.carregarListaDeCandidatos();
	}	
	
	public void fecharEntityManager(){
		if(this.entityManager != null && this.getEntityManager().isOpen()){
			this.getEntityManager().close();
		}
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
			this.setMensagemStatus("Congrega��o inexistente no sistema");
		}
		else{
			this.setOperacaoEfetuada(true);
			this.setMensagemStatus("Congrega��o encontrada");
		}		
		return congregacao;
	}



	/*public Object obterPresenca(long idDaPresenca){
		if(entityManager == null || !entityManager.isOpen()){
			this.entityManager = EmFactory.getEntityManager();
		}

		Object presenca = entityManager.find(Presenca.class, idDaPresenca);		

		if(presenca == null){
			this.setOperacaoEfetuada(false);
			this.setMensagemStatus("Usuario n�o inexistente no sistema");
		}
		else{
			this.setOperacaoEfetuada(true);
			this.setMensagemStatus("Usuario encontrado");
		}		
		return presenca;
	}
*/
	public Reuniao obterReuniao(long idDaReuniao){
		if(entityManager == null || !entityManager.isOpen()){
			this.entityManager = EmFactory.getEntityManager();
		}

		Reuniao reuniao = entityManager.find(Reuniao.class, idDaReuniao);

		if(reuniao == null){
			this.setOperacaoEfetuada(false);
			this.setMensagemStatus("Reuni�o inexistente no sistema");
		}
		else{
			this.setOperacaoEfetuada(true);
			this.setMensagemStatus("Reuni�o encontrada");
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
				//entityManager.close();
				this.setOperacaoEfetuada(true);
				this.setMensagemStatus("Opera��o Realizada com Sucesso!");
			}catch(Exception e){
				e.printStackTrace();
				this.setOperacaoEfetuada(false);
				this.setMensagemStatus("Erro interno na opera��o");			

			}
		}
		else{
			this.setOperacaoEfetuada(false);
			this.setMensagemStatus("Usuario J� Existente no Registro...");		

		}

	}
	
	public Administrador obterAdministrador(String login){
		if(entityManager == null || !entityManager.isOpen()){
			this.entityManager = EmFactory.getEntityManager();
		}

		Administrador administrador = entityManager.find(Administrador.class, login);

		if(administrador == null){
			this.setOperacaoEfetuada(false);
			this.setMensagemStatus("Administrador inexistente no sistema");
		}
		else{
			this.setOperacaoEfetuada(true);
			this.setMensagemStatus("Administrador encontrado");
		}

		return administrador;
	}

        public SessionStatus obterSessionStatus(long idSession){
		if(entityManager == null || !entityManager.isOpen()){
			this.entityManager = EmFactory.getEntityManager();
		}

		SessionStatus sessionStatus = entityManager.find(SessionStatus.class, idSession);

		if(sessionStatus == null){
			this.setOperacaoEfetuada(false);
			this.setMensagemStatus("Sessao inexistente no sistema");
		}
		else{
			this.setOperacaoEfetuada(true);
			this.setMensagemStatus("Sessao encontrada");
		}

		return sessionStatus;
	}

	public Obreiro obterObreiro(String cpf){
		if(entityManager == null || !entityManager.isOpen()){
			this.entityManager = EmFactory.getEntityManager();
		}

		Obreiro usuario = entityManager.find(Obreiro.class, cpf);

		if(usuario == null){
			this.setOperacaoEfetuada(false);
			this.setMensagemStatus("Usuario n�o inexistente no sistema");
		}
		else{
			this.setOperacaoEfetuada(true);
			this.setMensagemStatus("Usuario encontrado");
		}		
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	public List<Obreiro> obterListaDeObreiros(){		
		if(entityManager == null || !entityManager.isOpen()){
			this.entityManager = EmFactory.getEntityManager();
		}
		
		List<Obreiro> listaDeObreiro = new ArrayList<Obreiro>();
		
		listaDeObreiro = this.getEntityManager().createQuery("from Obreiro order by nome").getResultList();
	
		return listaDeObreiro;
	}

	@SuppressWarnings("unchecked")
	public List<Congregacao> obterListaDeCongregacoes(){

		if(entityManager == null || !entityManager.isOpen()){
			this.entityManager = EmFactory.getEntityManager();
		}

		List<Congregacao> listaDeCongregacao = new ArrayList<Congregacao>();

		listaDeCongregacao =  entityManager.createQuery("from Congregacao order by nome").getResultList();

		return listaDeCongregacao;

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
