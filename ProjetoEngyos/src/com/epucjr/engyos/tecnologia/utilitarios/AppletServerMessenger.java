package com.epucjr.engyos.tecnologia.utilitarios;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Projeto Engyos
 */
public class AppletServerMessenger {

   Map<String, String> map;

    public AppletServerMessenger(String messageQuery) {
        this.map = new HashMap<String, String>();
        this.processMessageQueryMap(messageQuery);
    }

    private void processMessageQueryMap(String query) {
        String[] params = query.split("&");        
        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }       
    }

    public String obterValorCampo(String campo){
        if(map.containsKey(campo)){
            return map.get(campo);
        }
        else{
            return "";
        }
    }



}
