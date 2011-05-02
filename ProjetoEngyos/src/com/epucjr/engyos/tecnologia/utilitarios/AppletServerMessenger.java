package com.epucjr.engyos.tecnologia.utilitarios;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Projeto Engyos
 */
public class AppletServerMessenger {

    private Map<String, String> map;
    private String valorStringGet;

    public AppletServerMessenger(String messageQuery) {
        this.valorStringGet = "";
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

     /**
     * Aqui é definido o valor a ser armazenado, sendo fornecido no modelo
     * chave-definição com o nome do campo e valor do campo enviados, tokenizadas
     * no modelo GET:
     * <p>Exempo: reuniao=idReuniao&digital=asdasd4654646546</p>
     *
     * @param campo Nome do campo
     * @param valor Valor do campo que deseja armazenar
     */
    public void setParameterGET(String campo, String valor){
        if(this.valorStringGet.length() == 0 &&  this.valorStringGet.equals("")){
            this.valorStringGet = campo + "=" + valor;
        }
        else{
            this.valorStringGet = this.valorStringGet + "&" + campo + "=" + valor;
        }
    }

    /**
     * Obter a String Tokenizada no modelo GET para ser utilizada
     *
     * @return A String com as mensagem de requisição no modelo GET
     */
    public String obterRequestMessageParameters(){
        return this.valorStringGet;
    }



}
