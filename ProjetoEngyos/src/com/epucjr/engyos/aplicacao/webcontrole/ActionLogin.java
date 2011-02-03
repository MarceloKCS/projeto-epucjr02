package com.epucjr.engyos.aplicacao.webcontrole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.crud.ValidadorLogin;

public class ActionLogin implements Command {

	public Object execute(Object... arg) {
		HttpServletRequest request = (HttpServletRequest) arg[0];
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		ValidadorLogin vl = new ValidadorLogin();
		
		boolean ok = vl.validarLogin(login, senha);
		HttpSession session = request.getSession();
		session.removeAttribute("senha");
		
		if (ok) {
			request.setAttribute("MensagemLogin", "Sucesso ao efetuar login");
			return "Sucesso Login";
		} else {
			request.setAttribute("MensagemLogin", "Erro ao efetuar login");
			return "Erro Login";
		}
	}
	
}
