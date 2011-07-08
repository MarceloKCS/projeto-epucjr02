/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.tecnologia.dao.CongregacaoDAO;
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
public class ActionObterListaDeCongregacaoAjaxCommand implements Command{
    private static org.apache.log4j.Logger log = Logger.getLogger(ActionObterListaDeCongregacaoAjaxCommand.class);

    @Override
    public Object execute(Object... arg) {
        log.debug("ActionObterListaDeCongregacaoAjaxCommand#execute");
        HttpServletRequest request = (HttpServletRequest) arg[0];
        HttpServletResponse response = (HttpServletResponse) arg[1];
        response.setCharacterEncoding("8859-1");
        response.setContentType("application/json; charset=8859-1");

        String resposta = "";
        CongregacaoDAO congregacaoDAO = new CongregacaoDAO();

        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        List<Congregacao> listaDeCongregacoesCadastradas = congregacaoDAO.findAll();

        if(listaDeCongregacoesCadastradas != null){           
                    
            for(Congregacao congregacao : listaDeCongregacoesCadastradas){
                jSONObject.put("nome", congregacao.getNome());
                jSONObject.put("idCongregacao", congregacao.getIdCongregacao());
                jSONArray.add(jSONObject);
            }

            resposta = jSONArray.toString();
        }
        else{
            jSONObject.put("erro", "Nenhuma congregacao carregada");
            jSONArray.add(jSONObject);
            resposta = jSONObject.toString();
        }

        log.debug("JSONArray reponse: " + resposta);
        return resposta;
    }


}
