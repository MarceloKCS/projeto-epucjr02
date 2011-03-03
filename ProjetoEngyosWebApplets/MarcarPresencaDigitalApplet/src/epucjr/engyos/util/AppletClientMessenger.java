package epucjr.engyos.util;

/**
 * Classe que ajuda a padronizar o modo como a applet envia as mensagens para
 * o servidor, tentando seguir um modelo de requisição das mensagens realizadas
 * pelo GET
 *
 * @author Projeto Engyos
 */
public class AppletClientMessenger {

    private String valorStringGet;

    public AppletClientMessenger() {
        this.valorStringGet = "";
    }

    /**
     * Aqui é definido o valor a ser armazenado, sendo fornecido no modelo
     * chave-definição com o nome do campo e valor do campo enviados, tokenizadas
     * no modelo GET:
     * <p>Exempo: reuniao=idReuniao&digital=asdasd4654646546</p>
     *
     * @param campo Nome do campo
     * @param valor Valor do campo que deseja armazenar
     */
    public void setParameterGET(String campo, String valor){
        if(this.valorStringGet.length() == 0 &&  this.valorStringGet.equals("")){
            this.valorStringGet = campo + "=" + valor;
        }
        else{
            this.valorStringGet = this.valorStringGet + "&" + campo + "=" + valor;
        }
    }

    /**
     * Obter a String Tokenizada no modelo GET para ser utilizada
     *
     * @return A String com as mensagem de requisição no modelo GET
     */
    public String obterRequestMessageParameters(){
        return this.valorStringGet;
    }

}
