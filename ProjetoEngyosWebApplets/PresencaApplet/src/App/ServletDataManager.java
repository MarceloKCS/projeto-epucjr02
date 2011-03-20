package App;

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

    URL r;
    boolean status;
    public ServletDataManager(URL r) {
        this.r = r;
        status = false;
    }

    public URLConnection conectar() {
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
        URLConnection urlConn = conectar();//http://localhost:8080/MyServlet

        try {
            ObjectOutputStream objectOutputStream;
            objectOutputStream = new ObjectOutputStream(urlConn.getOutputStream());
            objectOutputStream.writeUTF("marcarPresencaSenha");
            objectOutputStream.writeLong(idReuniao);
            objectOutputStream.writeUTF(senha);
            objectOutputStream.flush();
            objectOutputStream.close();

            String nome;
            
            ObjectInputStream objectInputStream = new ObjectInputStream(urlConn.getInputStream());
            status = objectInputStream.readBoolean();
            nome = objectInputStream.readUTF();

            objectInputStream.close();

            return nome;//"" caso n tenha encontrado
        } catch (IOException ex) {
            ex.printStackTrace();
            status = false;
            return "Erro no servidor !!!";
        }
    }

    public String marcarPresencaDigital(long idReuniao, String digital) {
        URLConnection urlConn = conectar();//http://localhost:8080/MyServlet

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
            status = objectInputStream.readBoolean();
            nome = objectInputStream.readUTF();

            objectInputStream.close();

            return nome;//"" caso n tenha encontrado
        } catch (IOException ex) {
            ex.printStackTrace();
            status = false;
            return "Erro no servidor !!!";
        }
    }
}
