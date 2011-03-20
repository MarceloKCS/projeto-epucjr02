package com.epucjr.engyos.dominio.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Projeto Engyos Team
 *
 * @version 1.0
 *
 * @since 1.0
 */
@Entity
public class Administrador implements IUsuario{

    /******************************
     *	ATRIBUTOS
     ******************************/

    @Id
    private String cpf;
    private String nome;
    private String sobrenome;    
    private String email;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn
    private Identificacao identificacao;




    /******************************
     *	CONSTRUTOR
     ******************************/
    public Administrador() {
        this.cpf = "";
        this.nome = "";
        this.sobrenome = "";
        this.email = "";
        this.identificacao = new Identificacao();
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
}
