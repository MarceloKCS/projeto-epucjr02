package epucjr.engyos.comunicacao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Classe que tem como objetivo estabelecer uma ponte de comunicação entre
 * o Applet e o servlet, podendo fazer requisições de execução de ações
 * esperadas pelo servlet a qual ele possa executar
 *
 * @author Projeto Engyos Team
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class ServletComunication {

    //private String enderecoServlet;
    private final URL URL = new URL("http://localhost:8080/ProjetoEngyos/AppletController");
    private String mensagemStatus;
    private boolean operacaoExecutada;

    public ServletComunication() throws MalformedURLException {
        
        //getCodeBase pega o local de onde o applet veio
        //this.URL = new URL(getCodeBase(), "/ProjetoEngyos/AppletController");
        // this.URL = new URL("http://localhost:8080/ProjetoEngyos/AppletController");    
        this.mensagemStatus = "";
        this.operacaoExecutada = false;
    }

    public URLConnection conectarServlet() {
        URLConnection urlConn = null;
        try {
            urlConn = this.URL.openConnection();
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

    public String realizarRequestServlet(String acaoRequest, byte[] binaryData) {
        String requestStatus = "";
         String acaoServletCommand = acaoRequest;
        URLConnection urlConn = this.conectarServlet();//http://localhost:8080/MyServlet
        try {
             //Realiza a requisição ao servidor
            ObjectOutputStream objectOutputStream;
            objectOutputStream = new ObjectOutputStream(urlConn.getOutputStream());
            objectOutputStream.writeUTF(acaoServletCommand);
            objectOutputStream.write(binaryData);
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

        if(this.isOperacaoExecutada()){
            return requestStatus;
        }
        else{
            return this.getMensagemStatus();
        }
    }

    public String realizarRequestServlet(String acaoRequest, String dadosModoString) {
        String requestStatus = "";
        URLConnection urlConn = this.conectarServlet();//http://localhost:8080/MyServlet
        try {
            //Realiza a requisição ao servidor
            ObjectOutputStream objectOutputStream;
            objectOutputStream = new ObjectOutputStream(urlConn.getOutputStream());
            objectOutputStream.writeUTF(acaoRequest.trim());
            objectOutputStream.writeObject(dadosModoString.trim());
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

        if(this.isOperacaoExecutada()){
            return requestStatus;
        }
        else{
            return this.getMensagemStatus();
        }
        
    }

    public String realizarRequestServlet(String acaoRequest) {
        String servletResponse = "";
         String acaoServletCommand = acaoRequest;
        URLConnection urlConn = this.conectarServlet();//http://localhost:8080/MyServlet
        try {
             //Realiza a requisição ao servidor
            ObjectOutputStream objectOutputStream;
            objectOutputStream = new ObjectOutputStream(urlConn.getOutputStream());
            objectOutputStream.writeUTF(acaoServletCommand);
            objectOutputStream.flush();
            objectOutputStream.close();

            //Obtém uma resposta do status da requisição feita ao servidor


            ObjectInputStream objectInputStream = new ObjectInputStream(urlConn.getInputStream());
            servletResponse = objectInputStream.readUTF();
            System.out.println("MSG Retorno = " + servletResponse);

            objectInputStream.close();

            this.setOperacaoExecutada(true);
            this.setMensagemStatus("Digital Enviada");
        } catch (IOException ex) {
            this.setMensagemStatus("Erro de IO");
            this.setOperacaoExecutada(false);
            ex.printStackTrace();
        }

        if(this.isOperacaoExecutada()){
            return servletResponse;
        }
        else{
            return this.getMensagemStatus();
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
