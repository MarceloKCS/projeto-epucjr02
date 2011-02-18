package com.epucjr.engyos.dominio.administrativo;

import com.epucjr.engyos.dominio.modelo.Administrador;


public class ValidadorLogin {
	public boolean validarLogin(Administrador admin, String senha){
		return senha.equals(admin.obterSenha());
	}
}
