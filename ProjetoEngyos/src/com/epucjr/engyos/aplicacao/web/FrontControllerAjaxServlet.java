/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.aplicacao.web;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.factory.CommandFactory;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Rogerio
 */
public class FrontControllerAjaxServlet extends HttpServlet {
    private static org.apache.log4j.Logger log = Logger.getLogger(FrontControllerAjaxServlet.class);

    private CommandFactory fabricaComandos;

    public FrontControllerAjaxServlet() {
         fabricaComandos = new CommandFactory();
    }
   // private ViewFactory viewFactory;



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        servico(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        servico(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    public void servico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.getWriter().print("TESTE");
        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("ISO-8859-1"); // You want world domination, huh?
        //InputStreamReader inputStream = new InputStreamReader(request.getInputStream());
        //log.debug("\ncharacter Encoding" + inputStream.getEncoding());
        PrintWriter printWriter = response.getWriter();
        String acao = request.getParameter("acao");
        log.debug("acao requisitada : " + acao);
        System.out.println("ACAO_RETRIEVED = " + acao);
        //Cada comando mapeado na fábrica implementa a interface Command
        System.out.println("RETRIEVING: COMMAND");
       
        Command comando = fabricaComandos.getCommand(acao);
        System.out.println("OK: COMAND RETRIEVED");
        String resposta = (String) comando.execute(request, response);

        System.out.println("RESPOSTA = " + resposta);
        log.debug("resposta da requisição : " + resposta);
        
        printWriter.write(resposta);
        printWriter.close();
        //String viewJsp = viewFactory.getView(acao, resposta);

//		RequestDispatcher view = request.getRequestDispatcher(viewJsp);
//		view.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
