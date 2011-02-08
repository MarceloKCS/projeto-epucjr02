package com.epucjr.engyos.dominio.crud;

import com.epucjr.engyos.dominio.modelo.Obreiro;

public class ValidadorSenha {

		public boolean validarSenha(String senha, Obreiro obreiro){
			return senha.equals(obreiro.getIdentificacao().getSenha());
		}
}
