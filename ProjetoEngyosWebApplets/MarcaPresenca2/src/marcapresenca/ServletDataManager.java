package marcapresenca;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Diego
 */
public class ServletDataManager implements InterfaceDataManager {

    URL url;
    /**
     * Construtor
     * @param url url do servlet
     */
    public ServletDataManager(URL r) {
        this.url = r;
    }

    /**
     * inicia conexao
     * @param urlServlet localizaçao do servlet
     * @return
     */
    public URLConnection conectar() {
        URLConnection urlConn = null;

        try {
            urlConn = url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setDefaultUseCaches(false);
            urlConn.setRequestProperty("content-type", "application/octet-stream");
        } catch (MalformedURLException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }

        return urlConn;
    }

    public String marcarPresencaDigital(long idReuniao, String digital) {
        URLConnection urlConn = conectar();

        try {
            //inicia o buffer out
            ObjectOutputStream objectOutputStream;
            objectOutputStream = new ObjectOutputStream(urlConn.getOutputStream());

            //escreve o comando
            objectOutputStream.writeUTF("marcarPresencaDigital");

            //escreve o id da reuniao q foi selecionada, isso deve ser passado via jsp/param
            objectOutputStream.writeLong(idReuniao);

            //escreve a digital em utf
            objectOutputStream.writeUTF(digital);

            objectOutputStream.flush();
            objectOutputStream.close();

            String nome;
            //inicia o buffer in
            ObjectInputStream objectInputStream = new ObjectInputStream(urlConn.getInputStream());

            //pega o nome do obreiro da digital, "" caso n tenha encontrado
            nome = objectInputStream.readUTF();

            objectInputStream.close();

            return nome;//"" caso n tenha encontrado
        } catch (IOException ex) {
            return null;
        }
    }
}
