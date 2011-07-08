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
	 *  Criando um 'persistirObjeto' de modo genérico, persistindo qualquer objeto no banco
	 * e não somente um tipo especifico.
	 ***************************************************************************************/

        public void persistir(Object generico){

                if(entityManager == null || !entityManager.isOpen()){
                        this.entityManager = EmFactory.getEntityManager();
                }

                EntityTransaction transaction =  entityManager.getTransaction();
                if(!transaction.isActive()){
                        transaction.begin();
                }
                entityManager.persist(generico);
                transaction.commit();
                //entityManager.close();
                //this.setOperacaoEfetuada(true);
                //this.setMensagemStatus("Operação Realizada com Sucesso!");
	}

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
                        this.setMensagemStatus("Operação Realizada com Sucesso!");
                    }catch(Exception e){
                        e.printStackTrace();
                        this.setOperacaoEfetuada(false);
                        this.setMensagemStatus("Erro interno na operação");

                    }
		}
		else{
                    this.setOperacaoEfetuada(false);
					

		}

	}

        public void persistirAdmnistrador(Administrador administrador){

		boolean objetoExistente = this.isAdmnistradorExistente(administrador.obterCPF());

		if(entityManager == null || !entityManager.isOpen()){
			this.entityManager = EmFactory.getEntityManager();
		}

		if(!objetoExistente){
                    try{
                        EntityTransaction transaction =  entityManager.getTransaction();
                        if(!transaction.isActive()){
                                transaction.begin();
                        }
                        entityManager.persist(administrador);
                        transaction.commit();
                        //entityManager.close();
                        this.setOperacaoEfetuada(true);
                        this.setMensagemStatus("Operação Realizada com Sucesso!");
                    }catch(Exception e){
                        e.printStackTrace();
                        this.setOperacaoEfetuada(false);
                        this.setMensagemStatus("Erro interno na operação");
                    }
		}
		else{
                    this.setMensagemStatus("Já existe Admnistrador registrado com este cpf.");
                    this.setOperacaoEfetuada(false);
		}
	}

        public void persistirObreiro(Obreiro obreiro){

		boolean objetoExistente = this.isObreiroExistente(obreiro.getCpf());

		if(entityManager == null || !entityManager.isOpen()){
			this.entityManager = EmFactory.getEntityManager();
		}

		if(!objetoExistente){
                    try{
                        EntityTransaction transaction =  entityManager.getTransaction();
                        if(!transaction.isActive()){
                                transaction.begin();
                        }
                        entityManager.persist(obreiro);
                        transaction.commit();
                        //entityManager.close();
                        this.setOperacaoEfetuada(true);
                        this.setMensagemStatus("Operação Realizada com Sucesso!");
                    }catch(Exception e){
                        e.printStackTrace();
                        this.setOperacaoEfetuada(false);
                        this.setMensagemStatus("Erro interno na operação");
                    }
		}
		else{
                    this.setMensagemStatus("Já existe obreiro registrado com este cpf.");
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
					this.setMensagemStatus("Usuario Já Existente no Registro...");
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

            if(entityManager == null || !entityManager.isOpen()){
                    this.entityManager = EmFactory.getEntityManager();
            }

            try{
                    EntityTransaction transaction =  entityManager.getTransaction();
                    if(!transaction.isActive()){
                            transaction.begin();
                    }
                    entityManager.merge(objeto);
                    transaction.commit();
                    this.setOperacaoEfetuada(true);
                    this.setMensagemStatus("Operação Realizada com Sucesso!");
            }catch(Exception e){
                    e.printStackTrace();
                    this.setOperacaoEfetuada(false);
                    this.setMensagemStatus("Erro interno na operação");

            }
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
			this.setMensagemStatus("Congregação inexistente no sistema");
		}
		else{
			this.setOperacaoEfetuada(true);
			this.setMensagemStatus("Congregação encontrada");
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
			this.setMensagemStatus("Usuario não inexistente no sistema");
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
			this.setMensagemStatus("Reunião inexistente no sistema");
		}
		else{
			this.setOperacaoEfetuada(true);
			this.setMensagemStatus("Reunião encontrada");
		}		
		return reuniao;
	}


       public void deletarObjeto(Object generico) {

            if (entityManager == null || !entityManager.isOpen()) {
                this.entityManager = EmFactory.getEntityManager();
            }

            try{
                EntityTransaction transaction =  entityManager.getTransaction();
                if(!transaction.isActive()){
                        transaction.begin();
                }
                entityManager.remove(generico);
                transaction.commit();
                //entityManager.close();
                this.setOperacaoEfetuada(true);
                this.setMensagemStatus("Operação Realizada com Sucesso!");
            }catch(Exception e){
                e.printStackTrace();
                this.setOperacaoEfetuada(false);
                this.setMensagemStatus("Erro interno na operação");

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
			this.setMensagemStatus("Usuario não inexistente no sistema");
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

                if(listaDeObreiro != null){
                    this.setOperacaoEfetuada(true);
                }
                else{
                    this.setOperacaoEfetuada(false);
                }
               
		return listaDeObreiro;
	}

	@SuppressWarnings("unchecked")
	public List<Congregacao> obterListaDeCongregacoes(){

		if(entityManager == null || !entityManager.isOpen()){
			this.entityManager = EmFactory.getEntityManager();
		}

		List<Congregacao> listaDeCongregacao = new ArrayList<Congregacao>();

		listaDeCongregacao =  entityManager.createQuery("from Congregacao order by nome").getResultList();

                if(listaDeCongregacao != null){
                    this.setOperacaoEfetuada(true);
                }
                else{
                    this.setOperacaoEfetuada(false);
                }
		return listaDeCongregacao;

	}

        public boolean isAdmnistradorExistente(String cpfAdmnistrador){
            if (entityManager == null || !entityManager.isOpen()) {
                this.entityManager = EmFactory.getEntityManager();
            }

            Administrador administrador = this.entityManager.find(Administrador.class, cpfAdmnistrador);

            if(administrador == null){
                return false;
            }
            else{
                return true;
            }
        }

        public boolean isCongregacaoExistente(long congregacaoId){
             if (entityManager == null || !entityManager.isOpen()) {
                this.entityManager = EmFactory.getEntityManager();
            }

            Congregacao congregacao = this.entityManager.find(Congregacao.class, congregacaoId);

            if(congregacao == null){
                return false;
            }
            else{
                return true;
            }
        }

        public boolean isObreiroExistente(String cpfOBreiro) {
            if (entityManager == null || !entityManager.isOpen()) {
                this.entityManager = EmFactory.getEntityManager();
            }

            Obreiro obreiro = entityManager.find(Obreiro.class, cpfOBreiro);

            if(obreiro == null){
                return false;
            }
            else{
                return true;
            }

        }

        public boolean isReuniaoExistente(long reuniaoId){
            if (entityManager == null || !entityManager.isOpen()) {
                this.entityManager = EmFactory.getEntityManager();
            }

            Reuniao reuniao = this.entityManager.find(Reuniao.class, reuniaoId);

            if(reuniao == null){
                return false;
            }
            else{
                return true;
            }
        }

	/******************************
	 *	GETTERS AND SETTERS
	 ******************************/

	public EntityManager getEntityManager() {
                if (this.entityManager == null || !this.entityManager.isOpen()) {
                    this.entityManager = EmFactory.getEntityManager();
                }
		return this.entityManager;
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
