/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package epucjr.engyos.test;

import epucjr.engyos.comunicacao.ServletComunication;

/**
 *
 * @author Rogerio
 */
public class ConnTestClass {

    public static void main(String[] args){
        //ConnTestClass.testarConexaoServlet();
        ConnTestClass.testarComunicacaoAppletServlet();

    }

    public static void testarConexaoServlet(){
        ServletComunication servletComunication = new ServletComunication();
        servletComunication.conectarServlet();

        if(servletComunication.isOperacaoExecutada()){
            System.out.println("MSG: " + servletComunication.getMensagemStatus());
        }
        else{
            System.out.println("Msg Fail = " + servletComunication.getMensagemStatus());
        }

    }

     public static void testarComunicacaoAppletServlet(){
        ServletComunication servletComunication = new ServletComunication();
        Progress progress = new Progress("Ocorreu bem a conn com string");

        servletComunication.marcarDigitalObject(progress);
        System.out.println("Stringu = " + progress.getNomeTitle());

         if(servletComunication.isOperacaoExecutada()){
            System.out.println("MSG: " + servletComunication.getMensagemStatus());
        }
        else{
            System.out.println("Msg Fail = " + servletComunication.getMensagemStatus());
        }

        System.exit(0);
    }

    public static void testarComunicacaoDespairModeAppletServlet(){
        ServletComunication servletComunication = new ServletComunication();
        String msgTest = "Ocorreu bem a conn com string";
        //servletComunication.marcarDigital(msgTest);
        servletComunication.testConexaoStandAloneDespairModeON();
        System.out.println("Stringu = " + msgTest);
         if(servletComunication.isOperacaoExecutada()){
            System.out.println("MSG: " + servletComunication.getMensagemStatus());
        }
        else{
            System.out.println("Msg Fail = " + servletComunication.getMensagemStatus());
        }

        System.exit(0);
    }

}
