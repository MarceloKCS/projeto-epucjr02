package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeAdministrador;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Rogerio
 */
public class ActionAdminRegisterPageLoaderCommand  implements Command{

    @Override
    public Object execute(Object... arg) {
        //Instancia��o de objetos e vari�veis necess�rios para a realiza��o do cadastro
        HttpServletRequest request = (HttpServletRequest) arg[0];
        FormularioDeAdministrador formularioDeAdministrador = new FormularioDeAdministrador();

        String respostaOperacao = "Formulario Carregado";
        //Solu��o tempor�ria para n�o aparecer o msg pagina carregana na p�gina
        formularioDeAdministrador.setMensagemStatus(null);

        request.setAttribute("formularioDeAdministrador", formularioDeAdministrador);

        return respostaOperacao;
    }


}
