package com.epucjr.engyos.aplicacao.controle;


public class TestCommand implements Command{
	
	public Object execute(Object... arg) {
		
		return "TESTE";
		/*HttpServletRequest request = (HttpServletRequest) arg[0];
		
		String valor =  request.getParameter("teste");
		valor = (valor != null ) ? valor : "****";
		request.setAttribute("nomeDoHomem", valor);
		request.setAttribute("nome", valor);
		System.out.println("Valor = " + valor);
		valor = "TESTE";
		return valor;*/
	}

}
