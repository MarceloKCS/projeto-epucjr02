/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class ComandoGetReunioes implements Comando {

    public ComandoGetReunioes() {
    }

    public String execute(ObjectInputStream ois, ObjectOutputStream oos) {
        try {
            String data0 = ois.readUTF();
            String data1 = ois.readUTF();
            //Todo

            //Teste
            oos.writeUTF("11/04/2010 15:40");
            oos.writeUTF("11/05/2010 20:50");
            oos.writeUTF("11/07/2010 10:30");
            return "ok";
        } catch (IOException ex) {
            return "erro";
        }
    }


}
