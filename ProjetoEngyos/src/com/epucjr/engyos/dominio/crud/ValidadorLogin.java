package com.epucjr.engyos.dominio.crud;


public class ValidadorLogin {
	public boolean validarLogin(String login, String senha){
		return login.equals("admin") && senha.equals("admin");
	}
}
