/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Diego
 */
public class ComandoGetReuniao implements Comando {

    public ComandoGetReuniao() {
    }

    public String execute(ObjectInputStream ois, ObjectOutputStream oos) {
        try {
            String data = ois.readUTF();
            //Todo

            //Teste
            oos.writeUTF(data);
            return "ok";
        } catch (IOException ex) {
            return "erro";
        }
    }


}
