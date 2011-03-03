/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManager;

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
import presencabiometrica.InterfaceDataManager;

/**
 *
 * @author Diego
 */
public class ServletDataManager implements InterfaceDataManager {

    String servlet;
    public ServletDataManager() {
        servlet = "http://localhost:8080/ProjetoBiometria/ServletControler";
    }

    public URLConnection conectar(String urlServlet) {
        URLConnection urlConn = null;

        try {
            URL url;
            url = new URL(urlServlet);
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

    public String[] getReunioes(String data0, String data1) {
        URLConnection urlConn = conectar(servlet);//http://localhost:8080/MyServlet
        
        try {
            ObjectOutputStream objectOutputStream;
            objectOutputStream = new ObjectOutputStream(urlConn.getOutputStream());
            objectOutputStream.writeUTF("getReunioes");
            objectOutputStream.writeUTF(data0);
            objectOutputStream.writeUTF(data1);
            objectOutputStream.flush();
            objectOutputStream.close();

            ArrayList<String> lista = new ArrayList<String>();
            ObjectInputStream objectInputStream = new ObjectInputStream(urlConn.getInputStream());
            while (objectInputStream.available() > 0) {
                String aux = objectInputStream.readUTF();
                if (aux.equals(data0) || aux.equals(data1)) {
                    System.out.println("data0 ou data1 estao no buffer");
                } else {
                    lista.add(aux);
                }
            }

            objectInputStream.close();

            String[] ret = new String[lista.size()];
            for (int i = 0; i < ret.length; i++) {
                ret[i] = lista.get(i);
            }

            return ret;
        } catch (IOException ex) {
            return null;
        }
    }

    public long getReuniao(String data) {
         URLConnection urlConn = conectar(servlet);//http://localhost:8080/MyServlet

        try {
            ObjectOutputStream objectOutputStream;
            objectOutputStream = new ObjectOutputStream(urlConn.getOutputStream());
            objectOutputStream.writeUTF("getReuniao");
            objectOutputStream.writeUTF(data);
            objectOutputStream.flush();
            objectOutputStream.close();

            long id;
            ObjectInputStream objectInputStream = new ObjectInputStream(urlConn.getInputStream());
            id = objectInputStream.readLong();

            objectInputStream.close();

            return id;
        } catch (IOException ex) {
            return -1;
        }
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
