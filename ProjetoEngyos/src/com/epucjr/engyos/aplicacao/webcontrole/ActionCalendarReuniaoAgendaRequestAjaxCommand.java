/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.controle.ReuniaoSessionControl;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.dao.ReuniaoDAO;
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
public class ActionCalendarReuniaoAgendaRequestAjaxCommand implements Command{
    private static org.apache.log4j.Logger log = Logger.getLogger(ActionCalendarReuniaoAgendaRequestAjaxCommand.class);

    @Override
    public Object execute(Object... arg) {
        HttpServletRequest request = (HttpServletRequest) arg[0];
        HttpServletResponse response = (HttpServletResponse) arg[1];
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        ReuniaoDAO reuniaoDAO = new ReuniaoDAO();
        ReuniaoSessionControl reuniaoSessionControl = new ReuniaoSessionControl();
        List<Reuniao> listaDeReuniao = null;
        String startTimeRequest = request.getParameter("start");
        String endTimeRequest = request.getParameter("end");
        String resposta = "";
        response.setCharacterEncoding("8859-1");
        response.setContentType("application/json; charset=8859-1");
        long startTime = 0;
        long endTime = 0;

        log.debug("startTime: " + startTimeRequest);
        log.debug("endTime: " + endTimeRequest);


        if(startTimeRequest != null && !startTimeRequest.equals("")){
            startTime = Long.parseLong(startTimeRequest);
        }

        if(endTimeRequest != null && !endTimeRequest.equals("")){
            endTime = Long.parseLong(endTimeRequest);
        }
        
        listaDeReuniao = reuniaoDAO.findByTimeInterval(startTime * 1000L, endTime * 1000L);
        log.debug("list size: " + listaDeReuniao.size());

        for(Reuniao reuniao : listaDeReuniao){
            if(reuniaoSessionControl.verificarReuniaoValida(reuniao.getIdReuniao())){
                String dia = reuniao.getDia();
                String mes = reuniao.getMes();
                String ano = reuniao.getAno();
                String hora = reuniao.getHora();
                String minuto = reuniao.getMinuto();
                String segundo = "00"; //The Engyos Project dosen't care with seconds
                log.debug("data: " + ano + "-" + mes + "-" + dia + "T" + hora + ":" + minuto + ":" + segundo);
                jSONObject.put("id", reuniao.getIdReuniao());
                jSONObject.put("title", "Reunião.\nLocal: " + reuniao.getLocal());
                jSONObject.put("start", reuniao.getMomentoReuniaoMarcada().getTime() / 1000);
                //jSONObject.put("url", "Controller?acao=reuniao_editformload&idReuniao=" + reuniao.getIdReuniao());
                //jSONObject.put("url", "Controller?acao=reuniao_editformload&idReuniao=" + reuniao.getIdReuniao());
                jSONObject.put("allDay", false);
                jSONArray.add(jSONObject);
            }
        }

        resposta = jSONArray.toString();
        log.debug("resposta: " + resposta);

        return resposta;

    }


}
