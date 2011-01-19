/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

import java.util.HashMap;

/**
 *
 * @author Diego
 */
public class FabricaComandos {

    HashMap<String, Comando> comandos;

    public FabricaComandos() {
        comandos = new HashMap<String, Comando>();
        comandos.put("getReunioes", new ComandoGetReunioes());
        comandos.put("getReuniao", new ComandoGetReuniao());
        comandos.put("marcarPresenca", new ComandoMarcarPresenca());
        comandos.put("marcarPresencaDigital", new ComandoMarcarPresencaDigital());
    }

    public Comando getComando(String acao) {
        return comandos.get(acao);
    }

 }
