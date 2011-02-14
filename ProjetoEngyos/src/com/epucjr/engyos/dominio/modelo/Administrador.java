package com.epucjr.engyos.dominio.modelo;

/**
 *
 * @author Projeto Engyos Team
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class Administrador implements IUsuario{

    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private Identificacao identificacao;

    /**
     * Class constructor
     *
     * <p>O construtor que define um usu�rio do tipo administrador, por�m identificado apenas
     * pela senha
     * </p>
     *
     * @param nome O nome do usu�rio
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
     * <p>O construtor que define um usu�rio do tipo administrador, identificado pela
     * pela senha e impress�o digital definidos na <code>Identificacao</code>
     * </p>
     *
     * @param nome O nome do usu�rio
     * @param sobrenome O sobrenome do administrador
     * @param cpf O cpf do administrador
     * @param email O e-mail do administrador
     * @param identificacao A identifica��o completa do administrador com senha e impress�o digital
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

    
    //TODO


    @Override
    public String obterNome() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String obterNomeCompleto() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String obterSenha() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String obterSobrenome() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
