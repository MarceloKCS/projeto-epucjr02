/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.crud.BuscaAvancada;
import com.epucjr.engyos.dominio.modelo.Administrador;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

/**
 *
 * @author Rogerio
 */
public class ActionAdministradorFindAjaxCommand implements Command{
    private static org.apache.log4j.Logger log = Logger.getLogger(ActionObterListaDeCongregacaoAjaxCommand.class);

    @Override
    public Object execute(Object... arg) {
        HttpServletRequest request = (HttpServletRequest) arg[0];
        HttpServletResponse response = (HttpServletResponse) arg[1];
        BuscaAvancada buscaAvancada = new BuscaAvancada();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        List<Administrador> listaDeAdministrador = null;
        String resposta = "";
        response.setCharacterEncoding("8859-1");
        response.setContentType("application/json; charset=8859-1");

        //Vari�veis a serem obtidas na busca
        String parametroBusca = request.getParameter("parametroBusca");
        int paginaSelecionada = 0;
        String paginaSelecionadaStr = request.getParameter("paginaCorrente");

        //Prepara��o dos campos de busca respeitando as especifica��es dos m�todos
        if(parametroBusca == null || parametroBusca.equals("null")){
                parametroBusca = "";
        }
        log.debug("parametroBusca : " + parametroBusca);
        log.debug("paginaCorrenteSelecionada : " + paginaSelecionadaStr);
        
        if(paginaSelecionadaStr == null && paginaSelecionadaStr.equals("")){
                paginaSelecionada = 1;
        }
        else{
                paginaSelecionada = Integer.parseInt(request.getParameter("paginaCorrente"));
        }
        log.debug("paginaCorrente : " + paginaSelecionada);

        buscaAvancada.buscarAdministrador(parametroBusca, paginaSelecionada);
        listaDeAdministrador = buscaAvancada.getListaDeAdministradoresEncontrados();

        //Posicao 0 do JSONArray ser� preenchido com dados de paginacao e exibicao
        jSONObject.put("parametroDeBusca", parametroBusca);
        jSONObject.put("paginaCorrente", paginaSelecionada);
        jSONObject.put("quantidadeTotalDeResultados", buscaAvancada.getQuantidadeTotalResultados());
        jSONObject.put("quantidadeTotalDePaginas", buscaAvancada.getQuantidadeDePagina());
        jSONObject.put("mensagemStatus", buscaAvancada.getMensagemStatus());
        jSONArray.add(jSONObject);

        //A partir da posicao 1 do JSONArray sera preenchido com dados de resultado de busca
        if(listaDeAdministrador != null && !listaDeAdministrador.isEmpty()){
            for(Administrador administrador : listaDeAdministrador){
                jSONObject.put("nome", administrador.obterNome());
                jSONObject.put("cpf", administrador.obterCPF());
                jSONArray.add(jSONObject);
            }

        }

        resposta = jSONArray.toString();

        log.debug("JSONArray reponse: " + resposta);

        return resposta;
    }


}
