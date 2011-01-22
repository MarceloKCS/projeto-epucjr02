/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package epucjr.engyos.comunicacao;

import epucjr.engyos.test.Progress;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rogerio
 */
public class ServletComunication {

    //private String enderecoServlet;
    private URL url;
    private String mensagemStatus;
    private boolean operacaoExecutada;

    public ServletComunication() {
        try {
            this.url = new URL("http://localhost:8080/ProjetoEngyos/AppletController");
           // this.url = new URL("http://localhost:8080/ProjetoBiometria/TestServlet");

        } catch (MalformedURLException ex) {
            Logger.getLogger(ServletComunication.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.mensagemStatus = "";
        this.operacaoExecutada = false;
    }

    public URLConnection conectarServlet() {
        URLConnection urlConn = null;
        System.out.println("url = " + this.url.getPath());
        try {
            urlConn = this.url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setDefaultUseCaches(false);
            // urlConn.setRequestProperty("Content-type", "application/x-java-serialized-object");
            urlConn.setRequestProperty("Content-Type", "application/octet-stream");

            this.setOperacaoExecutada(true);
            this.setMensagemStatus("Conectado ao Servlet");

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            this.setMensagemStatus("URL de conexão especificada é inválida");
            this.setOperacaoExecutada(false);
        } catch (IOException ex) {
            ex.printStackTrace();
            this.setMensagemStatus("Erro de IO");
            this.setOperacaoExecutada(false);
        }       

        return urlConn;
    }

    public String marcarDigitalObject(Object digitalModoString) {
        String requestStatus = "";
        String acaoServletCommand = "RegistrarDigital";
        URLConnection urlConn = this.conectarServlet();//http://localhost:8080/MyServlet
        // System.out.println("conn = " + urlConn.getContentEncoding());
        try {
            //Realiza a requisição ao servidor
            ObjectOutputStream objectOutputStream;
            objectOutputStream = new ObjectOutputStream(urlConn.getOutputStream());
            objectOutputStream.writeUTF(acaoServletCommand);
            objectOutputStream.writeObject(digitalModoString);
            objectOutputStream.flush();
            objectOutputStream.close();

            //Obtém uma resposta do status da requisição feita ao servidor


            ObjectInputStream objectInputStream = new ObjectInputStream(urlConn.getInputStream());
            requestStatus = objectInputStream.readUTF();
            System.out.println("MSG Retorno = " + requestStatus);

            objectInputStream.close();

            this.setOperacaoExecutada(true);
            this.setMensagemStatus("Digital Enviada");
        } catch (IOException ex) {
            this.setMensagemStatus("Erro de IO");
            this.setOperacaoExecutada(false);
            ex.printStackTrace();
        }

        return requestStatus;
    }

    public String marcarDigitalString(String digitalModoString) {
        String requestStatus = "";
        String acaoServletCommand = "RegistrarDigital";
        URLConnection urlConn = this.conectarServlet();//http://localhost:8080/MyServlet
        // System.out.println("conn = " + urlConn.getContentEncoding());
        try {
            //Realiza a requisição ao servidor
            ObjectOutputStream objectOutputStream;
            objectOutputStream = new ObjectOutputStream(urlConn.getOutputStream());
            objectOutputStream.writeUTF(acaoServletCommand);
            objectOutputStream.writeObject(digitalModoString);
            objectOutputStream.flush();
            objectOutputStream.close();

            //Obtém uma resposta do status da requisição feita ao servidor

            ObjectInputStream objectInputStream = new ObjectInputStream(urlConn.getInputStream());
            requestStatus = objectInputStream.readUTF();
            System.out.println("MSG Retorno = " + requestStatus);

            objectInputStream.close();

            this.setOperacaoExecutada(true);
            this.setMensagemStatus("Digital Enviada");
        } catch (IOException ex) {
            this.setMensagemStatus("Erro de IO");
            this.setOperacaoExecutada(false);
            ex.printStackTrace();
        }
        

        return requestStatus;
    }

    public void testConexaoStandAloneDespairModeON() {
        //  Listing 2
        //
        //  Applet client-side code to send a student object
        //  to a servlet in a serialized fashion.
        //
        //  A POST method is sent to the servlet.
        //
        String webServerStr = "http://localhost:8080/ProjetoBiometria/TestServlet";
        URL studentDBservlet = null;
        try {
            studentDBservlet = new URL(webServerStr);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        URLConnection servletConnection = null;
        try {
            servletConnection = studentDBservlet.openConnection();
        } catch (IOException ex) {
            Logger.getLogger(ServletComunication.class.getName()).log(Level.SEVERE, null, ex);
        }

        // inform the connection that we will send output and accept input
        servletConnection.setDoInput(true);
        servletConnection.setDoOutput(true);

        // Don't use a cached version of URL connection.
        servletConnection.setUseCaches(false);
        servletConnection.setDefaultUseCaches(false);

        // Specify the content type that we will send binary data
        servletConnection.setRequestProperty("Content-Type", "application/octet-stream");

        // send the student object to the servlet using serialization
        ObjectOutputStream outputToServlet;
        try {
            outputToServlet = new ObjectOutputStream(servletConnection.getOutputStream());
            Progress progress = new Progress("Vamo lá");

            // serialize the object
            outputToServlet.writeUTF("marcarPresenca");
            outputToServlet.writeObject(progress);

            outputToServlet.flush();
            outputToServlet.close();

            String nome;
            ObjectInputStream objectInputStream = new ObjectInputStream(servletConnection.getInputStream());
            nome = objectInputStream.readUTF();

            objectInputStream.close();

            this.setOperacaoExecutada(true);
            this.setMensagemStatus("Mensagem Enviada");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getMensagemStatus() {
        return mensagemStatus;
    }

    public void setMensagemStatus(String mensagemStatus) {
        this.mensagemStatus = mensagemStatus;
    }

    public boolean isOperacaoExecutada() {
        return operacaoExecutada;
    }

    public void setOperacaoExecutada(boolean operacaoExecutada) {
        this.operacaoExecutada = operacaoExecutada;
    }
}
