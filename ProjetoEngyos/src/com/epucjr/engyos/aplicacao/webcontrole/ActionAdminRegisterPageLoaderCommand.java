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
        //Instanciação de objetos e variáveis necessários para a realização do cadastro
        HttpServletRequest request = (HttpServletRequest) arg[0];
        FormularioDeAdministrador formularioDeAdministrador = new FormularioDeAdministrador();

        String respostaOperacao = "Formulario Carregado";
        //Solução temporária para não aparecer o msg pagina carregana na página
        formularioDeAdministrador.setMensagemStatus(null);

        request.setAttribute("formularioDeAdministrador", formularioDeAdministrador);

        return respostaOperacao;
    }


}
