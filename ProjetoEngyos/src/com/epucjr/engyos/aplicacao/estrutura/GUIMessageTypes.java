package com.epucjr.engyos.aplicacao.estrutura;

/**
 *
 * @author Rogerio
 */
public enum GUIMessageTypes {

    ME_CO_01("nome"), //Atualmente como exemplo, ME - significa tipo "mensagem" CO significa pertence ao cadastro Obreiro #01 c�digo da mensagem apresentada
    ME_RE_01("Relat�rio gerado com sucesso!"),
    ME_RE_02(""),
    ERR_RE_01("Ocorreu uma excess�o na gera��o do relat�rio"),
    PARAM_CONGREGACAO("congregacao");

     private String guiMessage;

    private GUIMessageTypes(String guiMessage) {
        this.guiMessage = guiMessage;
    }

    public String getGUIMessage(){
        return this.guiMessage;
    }

}
