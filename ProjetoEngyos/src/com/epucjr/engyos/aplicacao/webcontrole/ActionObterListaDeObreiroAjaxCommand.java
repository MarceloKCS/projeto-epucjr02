package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

/**
 *
 * @author Rogerio
 */
public class ActionObterListaDeObreiroAjaxCommand implements Command{
    private static org.apache.log4j.Logger log = Logger.getLogger(ActionObterListaDeCongregacaoAjaxCommand.class);
    @Override
    public Object execute(Object... arg) {
        log.debug("ActionObterListaDeObreiroAjaxCommand#execute");
        DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
        HttpServletResponse response = (HttpServletResponse) arg[1];
        response.setCharacterEncoding("8859-1");
        response.setContentType("application/json; charset=8859-1");
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        String resposta = "";

        List<Obreiro> listaDeObreiro = dataAccessObjectManager.obterListaDeObreiros();

        if(listaDeObreiro != null){
            for(Obreiro obreiro : listaDeObreiro){
                jSONObject.put("nome", obreiro.getNome());
                jSONObject.put("cpf", obreiro.getCpf());
                jSONArray.add(jSONObject);
            }

            resposta = jSONArray.toString();
        }
        else{
            jSONObject.put("erro", "Nenhum obreiro carregado.");
            jSONArray.add(jSONObject);
            resposta = jSONObject.toString();
        }

        log.debug("JSONArray reponse: " + resposta);
        return resposta;

    }


}
