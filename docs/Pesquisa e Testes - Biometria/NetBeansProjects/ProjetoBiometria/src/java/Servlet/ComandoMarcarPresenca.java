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
public class ComandoMarcarPresenca implements Comando{

    public String execute(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
        try {
           // long idReuniao = objectInputStream.readLong();
            //String senha = objectInputStream.readUTF();
            //TODO

            //System.out.println("senha = " + senha);

            //Teste
            objectOutputStream.writeUTF("Nome de Alguem via Senha");
            return "ok";
        } catch (IOException ex) {
            ex.printStackTrace();
            return "erro";
        }
    }
}
