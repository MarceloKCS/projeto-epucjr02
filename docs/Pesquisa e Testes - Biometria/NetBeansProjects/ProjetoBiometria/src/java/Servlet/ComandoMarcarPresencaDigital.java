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
public class ComandoMarcarPresencaDigital implements Comando{
    public String execute(ObjectInputStream ois, ObjectOutputStream oos) {
        try {
            long idReuniao = ois.readLong();
            String digital = ois.readUTF();
            //Todo

            //Teste
            oos.writeUTF("Nome de Alguem via Digital");
            return "ok";
        } catch (IOException ex) {
            return "erro";
        }
    }
}
