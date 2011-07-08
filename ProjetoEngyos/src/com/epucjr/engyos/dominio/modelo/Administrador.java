package com.epucjr.engyos.dominio.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.Index;

/**
 *
 * @author Projeto Engyos Team
 *
 * @version 1.0
 *
 * @since 1.0
 */
@Entity
@Indexed
public class Administrador implements IUsuario{

    /******************************
     *	ATRIBUTOS
     ******************************/

    @Id
    @DocumentId
    @Field
    private String cpf;

    //Essas marcação são necessárias ao hibernate search e cumprem duas funções:
    // - O primeiro é para indexar para tornar possivel a realização de buscas
    //no campo referido
    //-A segunda, a qual foi atribuido um nome nomexxx_sort é utilizado pelo
    //hibernate search para realizar uma ordenação com base no campo nome
    //Veja a classe BuscaAvancada no método de sort para verificar a implementação
    @Fields(
            {@Field(index=Index.TOKENIZED, store=Store.YES),
            @Field(name="nomeadm_sort",
            index=Index.UN_TOKENIZED)
            })
    private String nome;
    private String sobrenome;
    @Field
    private String email;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn
    private Identificacao identificacao;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn
    private SessionStatus sessionStatus;




    /******************************
     *	CONSTRUTOR
     ******************************/
    public Administrador() {
        this.cpf = "";
        this.nome = "";
        this.sobrenome = "";
        this.email = "";
        this.identificacao = new Identificacao();
        this.sessionStatus = new SessionStatus();
    }


    /**
     * Class constructor
     *
     * <p>O construtor que define um usuário do tipo administrador, porém identificado apenas
     * pela senha
     * </p>
     *
     * @param nome O nome do usuário
     * @param sobrenome O sobrenome do administrador
     * @param cpf O cpf do administrador
     * @param email O e-mail do administrador
     * @param senha A senha do administrador
     */

    public Administrador(String nome, String sobrenome, String cpf, String email, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.identificacao = new Identificacao(senha);
        this.sessionStatus = new SessionStatus();
    }

    /**
     * Class constructor
     *
     * <p>O construtor que define um usuário do tipo administrador, identificado pela
     * pela senha e impressão digital definidos na <code>Identificacao</code>
     * </p>
     *
     * @param nome O nome do usuário
     * @param sobrenome O sobrenome do administrador
     * @param cpf O cpf do administrador
     * @param email O e-mail do administrador
     * @param identificacao A identificação completa do administrador com senha e impressão digital
     *
     * @see Identificacao
     */

    public Administrador(String nome, String sobrenome, String cpf, String email, Identificacao identificacao) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.identificacao = identificacao;
         this.sessionStatus = new SessionStatus();
    }
    
   /**
    * Administrador Simplificado
    *
    * @param nome
    * @param cpf
    * @param identificacao
    */
     public Administrador(String nome, String cpf, Identificacao identificacao) {
        this.nome = nome;
        this.sobrenome = "";
        this.cpf = cpf;
        this.email = "";
        this.identificacao = identificacao;
         this.sessionStatus = new SessionStatus();
    }

    
    /******************************
     *	METODOS
     ******************************/


    /******************************
     *	GETTERS AND SETTERS
     ******************************/
    @Override
    public String obterNome() {
        return nome;
    }

    @Override
    public String obterNomeCompleto() {
        return nome + ' ' + sobrenome;
    }

    @Override
    public String obterSenha() {
        return identificacao.getSenha();
    }

    @Override
    public String obterSobrenome() {
        return sobrenome;
    }

    public String obterEmail() {
    	return email;
    }
    
    public String obterCPF() {
        return cpf;
    }
    
    public String obterLogin() {
        return email;
    }

    public Identificacao getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(Identificacao identificacao) {
        this.identificacao = identificacao;
    }

    public String getSenha(){
        if(this.identificacao != null){
            return this.identificacao.getSenha();
        }
        else{
            return "";
        }
    }

    public String getImpressaoDigital(){
        if(this.identificacao != null){
            return this.identificacao.getImpressaoDigital();
        }
        else{
            return "";
        }
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public void definirNome(String nome){
        this.nome = nome;
    }

    public void definirCPF(String cpf){
        this.cpf = cpf;
    }
    
}
