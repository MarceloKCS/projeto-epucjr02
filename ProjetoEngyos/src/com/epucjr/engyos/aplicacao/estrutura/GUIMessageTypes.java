package com.epucjr.engyos.aplicacao.estrutura;

/**
 *
 * @author Rogerio
 */
public enum GUIMessageTypes {

    ME_CO_01("nome"), //Atualmente como exemplo, ME - significa tipo "mensagem" CO significa pertence ao cadastro Obreiro #01 código da mensagem apresentada
    ME_RE_01("Relatório gerado com sucesso!"),
    ME_RE_02(""),
    ERR_RE_01("Ocorreu uma excessão na geração do relatório"),
    PARAM_CONGREGACAO("congregacao");

     private String guiMessage;

    private GUIMessageTypes(String guiMessage) {
        this.guiMessage = guiMessage;
    }

    public String getGUIMessage(){
        return this.guiMessage;
    }

}
