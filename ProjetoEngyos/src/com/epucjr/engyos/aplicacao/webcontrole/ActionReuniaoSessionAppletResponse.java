/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.controle.ReuniaoSessionControl;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rogerio
 */
public class ActionReuniaoSessionAppletResponse implements Command{

    @Override
    public Object execute(Object... arg) {
        //Instanciação de Objetos e declarações de variáveis utilizadas
        HttpServletRequest request = (HttpServletRequest) arg[0];

        String mensagemResposta = "";

        ObjectInputStream objectInputStream = (ObjectInputStream) arg[2];
        ObjectOutputStream objectOutputStream = (ObjectOutputStream) arg[3];

        ReuniaoSessionControl reuniaoSessionControl = new ReuniaoSessionControl(request.getSession());
        //HttpSession session = request.getSession();
        reuniaoSessionControl.abrirSessaoReuniao();

        //String value = (String) session.getAttribute("reuniao");

        //System.out.println("value = " + value);
        if(reuniaoSessionControl.verificarSessaoReuniaoAberta()){
            mensagemResposta = "ativa";
        }
        else{
            mensagemResposta = "inativa";
        }
        try {
            objectOutputStream.writeUTF(mensagemResposta);
            objectOutputStream.flush();
            objectOutputStream.close();
            objectInputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ActionReuniaoSessionAppletResponse.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mensagemResposta;

    }



}
