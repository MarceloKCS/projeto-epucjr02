package com.epucjr.engyos.aplicacao.webcontrole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.administrativo.ValidadorLogin;
import com.epucjr.engyos.dominio.modelo.Administrador;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

public class ActionLogin implements Command {

	// TODO mudar senha e login mestre
	public static String senhaMestre = "admin";
	public static String loginMestre = "admin";

	public Object execute(Object... arg) {
		HttpServletRequest request = (HttpServletRequest) arg[0];
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		ValidadorLogin vl = new ValidadorLogin();

		// pega o admin
		DataAccessObjectManager dao = new DataAccessObjectManager();
		Administrador admin = dao.obterAdministrador(login);

		if (!dao.isOperacaoEfetuada()) {
			admin = new Administrador("Acesso sem login", "", senhaMestre,
					loginMestre, senhaMestre);
		}

		boolean ok = vl.validarLogin(admin, senha);

		HttpSession session = request.getSession();
		session.removeAttribute("senha");

		if (ok) {
			request.setAttribute("Administrador", admin);
			request.setAttribute("nomeDoAdministrador", admin.obterNome());
			request.setAttribute("MensagemLogin", "Sucesso ao efetuar login");
			return "Sucesso Login";
		} else {
			request.setAttribute("MensagemLogin", "Erro ao efetuar login");
			return "Erro Login";
		}

	}

}
