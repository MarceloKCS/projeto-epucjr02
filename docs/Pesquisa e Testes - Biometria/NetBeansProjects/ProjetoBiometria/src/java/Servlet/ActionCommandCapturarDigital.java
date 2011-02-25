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
 * @author Rogerio
 */
public class ActionCommandCapturarDigital implements Comando {

    public String execute(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
        String mensagemResposta = "";
        try {

            try {
                String digitalCapturada =  (String) objectInputStream.readObject();
                System.out.println("ValorConfere = " + digitalCapturada);
                mensagemResposta = "Digital capturada com sucesso";
            } catch (IOException ex) {
                ex.printStackTrace();
                mensagemResposta = "Erro na Operacao de Leitura do Cliente";
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                mensagemResposta = "Erro de Instanciação no Objeto Cliente";
            }

            objectOutputStream.writeUTF(mensagemResposta);

        } catch (IOException ex) {
            ex.printStackTrace();
            mensagemResposta = "Erro de IO no retorno ao cliente";
        }

        return mensagemResposta;
    }
}
