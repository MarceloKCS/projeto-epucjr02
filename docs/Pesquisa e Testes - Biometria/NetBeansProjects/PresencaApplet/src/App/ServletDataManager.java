package App;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class ServletDataManager implements InterfaceDataManager {

    String servlet;
    URL r;
    public ServletDataManager(URL r) {
        this.r = r;
    }

    public URLConnection conectar(String urlServlet) {
        URLConnection urlConn = null;

        try {
            urlConn = r.openConnection();
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

    public String marcarPresenca(long idReuniao, String senha) {
        URLConnection urlConn = conectar(servlet);//http://localhost:8080/MyServlet

        try {
            ObjectOutputStream objectOutputStream;
            objectOutputStream = new ObjectOutputStream(urlConn.getOutputStream());
            objectOutputStream.writeUTF("marcarPresenca");
            objectOutputStream.writeLong(idReuniao);
            objectOutputStream.writeUTF(senha);
            objectOutputStream.flush();
            objectOutputStream.close();

            String nome;
            ObjectInputStream objectInputStream = new ObjectInputStream(urlConn.getInputStream());
            nome = objectInputStream.readUTF();

            objectInputStream.close();

            return nome;//"" caso n tenha encontrado
        } catch (IOException ex) {
            return null;
        }
    }

    public String marcarPresencaDigital(long idReuniao, String digital) {
        URLConnection urlConn = conectar(servlet);//http://localhost:8080/MyServlet

        try {
            ObjectOutputStream objectOutputStream;
            objectOutputStream = new ObjectOutputStream(urlConn.getOutputStream());
            objectOutputStream.writeUTF("marcarPresencaDigital");
            objectOutputStream.writeLong(idReuniao);
            objectOutputStream.writeUTF(digital);
            objectOutputStream.flush();
            objectOutputStream.close();

            String nome;
            ObjectInputStream objectInputStream = new ObjectInputStream(urlConn.getInputStream());
            nome = objectInputStream.readUTF();

            objectInputStream.close();

            return nome;//"" caso n tenha encontrado
        } catch (IOException ex) {
            return null;
        }
    }
}
