package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.modelo.Administrador;
import com.epucjr.engyos.dominio.visualizacao.FormularioDeAdministrador;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Rogerio
 */
public class ActionAdministradorEditPageLoaderCommand implements Command{
    private static org.apache.log4j.Logger log = Logger.getLogger(ActionAdministradorEditPageLoaderCommand.class);
    @Override
    public Object execute(Object... arg) {
        //Instanciação de objetos necessários para carregar a página
        HttpServletRequest request = (HttpServletRequest) arg[0];
        FormularioDeAdministrador formularioDeAdministrador = new FormularioDeAdministrador();
        DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
        //Obtendo o id do administrador requisitado para a edição
        String cpfAdministrador = request.getParameter("cpfAdministrador");
        log.debug("cpfAdministrador : " + cpfAdministrador);

        //Carregando o administrador requisitado
        Administrador administrador = dataAccessObjectManager.obterAdministrador(cpfAdministrador);

        //Carregando os dados necessários para a edição de obreiro
        if(dataAccessObjectManager.isOperacaoEfetuada()){
            formularioDeAdministrador.definirCamposPreenchidos(administrador);
        }
        //Fechando o EntityManager de DataAccessObjectManager após uso
        if (dataAccessObjectManager != null) {
            dataAccessObjectManager.fecharEntityManager();
        }

        String respostaOperacao = dataAccessObjectManager.getMensagemStatus();

        //Solução temporária para não aparecer o msg pagina carregana na página
        formularioDeAdministrador.setMensagemStatus(null);

        request.setAttribute("formularioDeAdministrador", formularioDeAdministrador);

        return respostaOperacao;
    }
}
