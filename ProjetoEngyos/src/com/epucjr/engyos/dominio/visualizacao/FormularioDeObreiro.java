package com.epucjr.engyos.dominio.visualizacao;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeObreiro;
import com.epucjr.engyos.dominio.crud.ValidatorCpf;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

/**
 * Classe para manipulação dos dados que serão apresentados na interface gráfica
 *
 * @author Projeto Engyos Team
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class FormularioDeObreiro {

    /******************************
     *	ATRIBUTOS
     ******************************/
    private HashMap<String, String> camposPreenchidos;
    private List<Congregacao> listaDeCongregacoes;
    private String mensagemStatus;
    private ValidadorDeFormularioDeObreiro validadorDeFormularioDeObreiro;
    private boolean isCPFValido;

    /******************************
     *	CONSTRUTOR
     ******************************/
    /**
     * Class constructor
     */
    public FormularioDeObreiro() {
        this.camposPreenchidos = new HashMap<String, String>();
        this.mensagemStatus = "";
        this.validadorDeFormularioDeObreiro = new ValidadorDeFormularioDeObreiro();
        this.carregarDadosDoFormulario();
    }

    /******************************
     *	METODOS
     ******************************/
    /**
     * Metodo para definir o preenchimento de um campo do formulário, caso o
     * campo contenha dados, este será capturado e armazenado com basse no nome
     * do campo e os dados inseridos no campo
     *
     * @param nomeDoCampo O nome do campo no formulário
     * @param valorDoCampo Os dados inseridos no formulário
     */
    public void definirCampoPreenchido(String nomeDoCampo, String valorDoCampo) {
        this.camposPreenchidos.put(nomeDoCampo, valorDoCampo);
    }

    public String obterCampoPreenchido(String nomeDoCampo) {
        if (this.camposPreenchidos.containsKey(nomeDoCampo)) {
            return this.camposPreenchidos.get(nomeDoCampo);
        } else {
            return "";
        }
    }

    public boolean verificarCampoPreenchido(String nomeDoCampo) {
        if (this.camposPreenchidos.containsKey(nomeDoCampo)) {
            return true;
        } else {
            return false;
        }
    }

    public void definirDadosDeConfirmacao(String nomeDoCampo, String valorDoCampo) {
        this.camposPreenchidos.put(nomeDoCampo, valorDoCampo);
    }

    public String obterDadoDeConfirmacao(String nomeDoCampo) {
        if (this.camposPreenchidos.containsKey(nomeDoCampo)) {
            return this.camposPreenchidos.get(nomeDoCampo);
        } else {
            return "";
        }
    }

    public boolean verificarDadoDeConfirmacao(String nomeDoCampo) {
        if (this.camposPreenchidos.containsKey(nomeDoCampo)) {
            return true;
        } else {
            return false;
        }
    }

    public void carregarDadosDoFormulario() {

        //Carregando a lista de Congregações utilizada para cadastro de obreiros
        DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
        List<Congregacao> listaDeCongregacao = dataAccessObjectManager.obterListaDeCongregacoes();

        this.setListaDeCongregacoes(listaDeCongregacao);
        this.setMensagemStatus("Formulario Carregado");

    }

    public void definirCamposPreenchidosPeloUsuario(HttpServletRequest httpServletRequest) {

        String nome = httpServletRequest.getParameter("Nome");
        String cpf = httpServletRequest.getParameter("Cpf");
        String cargo = httpServletRequest.getParameter("Cargo");
        String idCongregacaoEscolhido = httpServletRequest.getParameter("Congregacao");
        String congregacao = httpServletRequest.getParameter(idCongregacaoEscolhido);


        //TODO talvez não seja necessário pos o validador já faz isso
        //Verifica o CPF obtido de modo a informar a validade do mesmo ao usuário

        this.definirCPFValido(cpf);

        //Define os campos preenchidos de modo que o usuário não perca os dados previamente
        //inseridos em caso de erro no preenchimento

        if (nome != null && !nome.equals("")) {
            this.definirCampoPreenchido("Nome", nome);
        }

        if (cpf != null && !cpf.equals("")) {
            this.definirCampoPreenchido("Cpf", cpf);
        }

        if (cargo != null && !cargo.equals("")) {
            this.definirCampoPreenchido("Cargo", cargo);
        }

        if (idCongregacaoEscolhido != null && !idCongregacaoEscolhido.equals("")) {
            this.definirCampoPreenchido("idCongregacaoEscolhido", idCongregacaoEscolhido);
        }

        if (congregacao != null && !congregacao.equals("")) {
            this.definirCampoPreenchido("Congregacao", congregacao);
        }
    }

    public void definirCamposPreenchidos(Obreiro obreiro) {

        String nome = obreiro.getNome();
        String cpf = obreiro.getCpf();
        String cargo = obreiro.getCargo();
        String idCongregacaoEscolhido = obreiro.getCongregacao().getIdCongregacao() + "";
        String congregacao = obreiro.getCongregacao().getNome();
        String senha = obreiro.getIdentificacao().getSenha();
        String digital = obreiro.getIdentificacao().getImpressaoDigital();


        //TODO talvez não seja necessário pos o validador já faz isso
        //Verifica o CPF obtido de modo a informar a validade do mesmo ao usuário

        //this.definirCPFValido(cpf);

        //Define os campos preenchidos de modo que o usuário não perca os dados previamente
        //inseridos em caso de erro no preenchimento

        if (nome != null && !nome.equals("")) {
            this.definirCampoPreenchido("Nome", nome);
        }

        if (cpf != null && !cpf.equals("")) {
            this.definirCampoPreenchido("Cpf", cpf);
        }

        if (cargo != null && !cargo.equals("")) {
            this.definirCampoPreenchido("Cargo", cargo);
        }

        if (idCongregacaoEscolhido != null && !idCongregacaoEscolhido.equals("")) {
            this.definirCampoPreenchido("idCongregacaoEscolhido", idCongregacaoEscolhido);
        }

        if (congregacao != null && !congregacao.equals("")) {
            this.definirCampoPreenchido("Congregacao", congregacao);
        }
        if (senha != null && !senha.equals("")) {
            this.definirCampoPreenchido("Senha", senha);
        }
        if (digital != null && !digital.equals("")) {
            this.definirCampoPreenchido("Digital", digital);
        }
    }

    public void definirDadosDeConfirmacaoDeCadastroObreiro(String confirmacaoCadastro, String nome, String cpf, String cargo, String congregacao) {

        this.definirDadosDeConfirmacao("confirmacao_cadastro", confirmacaoCadastro);
        this.definirDadosDeConfirmacao("confirmacao_nome", nome);
        this.definirDadosDeConfirmacao("confirmacao_cpf", cpf);
        this.definirDadosDeConfirmacao("confirmacao_cargo", cargo);
        this.definirDadosDeConfirmacao("confirmacao_congregacao", congregacao);

    }

    public void definirDadosDeConfirmacaoDeEdicaoObreiro(String confirmacaoEdicao, String nome, String cpf, String cargo, String congregacao, String msgSenha, String msgDigital) {

        this.definirDadosDeConfirmacao("confirmacao_edicao", confirmacaoEdicao);
        this.definirDadosDeConfirmacao("confirmacao_nome", nome);
        this.definirDadosDeConfirmacao("confirmacao_cpf", cpf);
        this.definirDadosDeConfirmacao("confirmacao_cargo", cargo);
        this.definirDadosDeConfirmacao("confirmacao_congregacao", congregacao);
        this.definirDadosDeConfirmacao("confirmacao_senha", msgSenha);
        this.definirDadosDeConfirmacao("confirmacao_digital", msgDigital);

    }

    public void definirCPFValido(String numeroCPF) {

        if (ValidatorCpf.valido(numeroCPF)) {
            this.setCPFValido(true);
            this.setMensagemStatus("CPF Inválido");
        } else {
            this.setCPFValido(false);
        }

    }

    /******************************
     *	GETTERS AND SETTERS
     ******************************/
    public HashMap<String, String> getCamposPreenchidos() {
        return camposPreenchidos;
    }

    public void setCamposPreenchidos(HashMap<String, String> camposPreenchidos) {
        this.camposPreenchidos = camposPreenchidos;
    }

    public List<Congregacao> getListaDeCongregacoes() {
        return listaDeCongregacoes;
    }

    public void setListaDeCongregacoes(List<Congregacao> listaDeCongregacoes) {
        this.listaDeCongregacoes = listaDeCongregacoes;
    }

    public String getMensagemStatus() {
        return mensagemStatus;
    }

    public void setMensagemStatus(String mensagemStatus) {
        this.mensagemStatus = mensagemStatus;
    }

    public boolean isCPFValido() {
        return isCPFValido;
    }

    public void setCPFValido(boolean isCPFValido) {
        this.isCPFValido = isCPFValido;
    }

    public ValidadorDeFormularioDeObreiro getValidadorDeFormularioDeObreiro() {
        return validadorDeFormularioDeObreiro;
    }

    public void setValidadorDeFormularioDeObreiro(
            ValidadorDeFormularioDeObreiro validadorDeFormularioDeObreiro) {
        this.validadorDeFormularioDeObreiro = validadorDeFormularioDeObreiro;
    }
}
