package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.crud.BuscaAvancada;
import com.epucjr.engyos.dominio.modelo.Administrador;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeBuscaDeAdministrador;
import com.epucjr.engyos.tecnologia.ferramentas.SearchInitializator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Rogerio
 */
public class ActionAdministradorFindCommand implements Command {
    private static org.apache.log4j.Logger log = Logger.getLogger(ActionAdministradorFindCommand.class);

    @Override
    public Object execute(Object... arg) {
        //Instanciação de objetos e variáveis necessários para a realização da busca
        HttpServletRequest request = (HttpServletRequest) arg[0];
        //Variáveis a serem obtidas na busca
        String parametroBusca = request.getParameter("busca_input");
        int paginaSelecionada = 0;
        String paginaSelecionadaStr = request.getParameter("paginaCorrente");
        log.debug("parametroBusca : " + parametroBusca);
        log.debug("paginaCorrenteSelecionada : " + paginaSelecionadaStr);

        //Preparação dos campos de busca respeitando as especificações dos métodos
        if(parametroBusca == null && parametroBusca.equals("")){
                parametroBusca = "";
        }
        if(paginaSelecionadaStr == null){
                paginaSelecionada = 1;
        }
        else{
                paginaSelecionada = Integer.parseInt(paginaSelecionadaStr);
        }

        if(parametroBusca == null){
            parametroBusca = "";
        }
        log.debug("paginaCorrente : " + paginaSelecionada);

        //TODO - Sequencia de mensagem visando teste - REFATORAR
        BuscaAvancada buscaAvancada = new BuscaAvancada();
        SearchInitializator searchInitializator = new SearchInitializator();
        searchInitializator.atualizarIndicesLucene();
        buscaAvancada.buscarAdministrador(parametroBusca, paginaSelecionada);
        List<Administrador> listaDeAdministrador = buscaAvancada.getListaDeAdministradoresEncontrados();
        FormularioDeBuscaDeAdministrador formularioDeBuscaDeAdministrador = new FormularioDeBuscaDeAdministrador(listaDeAdministrador, paginaSelecionada, buscaAvancada.getQuantidadeDePagina());
        formularioDeBuscaDeAdministrador.setParametroDeBusca(parametroBusca);
        formularioDeBuscaDeAdministrador.setMensagemStatus(buscaAvancada.getMensagemStatus());

        for(Administrador administrador : listaDeAdministrador){
            log.debug("Nome: " + administrador.obterNome());
            log.debug("CPF: " + administrador.obterCPF());
        }

        request.setAttribute("resultadoDeBuscaDeAdministrador", formularioDeBuscaDeAdministrador);

        return buscaAvancada.getMensagemStatus();

    }



}
