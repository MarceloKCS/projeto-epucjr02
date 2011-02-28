package com.epucjr.engyos.aplicacao.web;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.factory.CommandFactory;
//import com.epucjr.engyos.dominio.factory.ViewFactory;

/**
 * Servlet implementation class Servlet
 */
public class FrontControllerAppletServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	private CommandFactory fabricaComandos;		
	//private ViewFactory viewFactory;
	
    public FrontControllerAppletServlet() {
        super();
        
        fabricaComandos = new CommandFactory();
        //viewFactory = new ViewFactory();       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servico(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servico(request, response);
	}
	
	public void servico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().print("TESTE");
		//response.setContentType("application/octet-stream");
		ObjectInputStream objectInputStream = new ObjectInputStream(request.getInputStream());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(response.getOutputStream());
        String acao = objectInputStream.readUTF();		
		
		Command comando = fabricaComandos.getCommand(acao);
		String resposta = (String) comando.execute(request, response, objectInputStream, objectOutputStream);		
		
		System.out.println("resposta = " + resposta);
		
		objectInputStream.close();
		objectOutputStream.close();
		//response.setContentType("text/plain");
		//objectOutputStream.write(resposta.getBytes());
		/*String viewJsp = viewFactory.getView(acao, resposta);
		
		RequestDispatcher view = request.getRequestDispatcher(viewJsp);
		view.forward(request, response);*/
	}

}

