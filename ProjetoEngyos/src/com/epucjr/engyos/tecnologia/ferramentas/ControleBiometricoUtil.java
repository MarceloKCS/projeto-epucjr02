package com.epucjr.engyos.tecnologia.ferramentas;

/**
 *
 * @author Projeto Engyos Team
 */
public interface ControleBiometricoUtil {

    public void testeVerificarDigitalStringValida(Object digitalTextMode);

    public void testeVerificarDigitalValidaBinData(Object digitalTextMode);

    public void verificarMatchDigital(Object digitalSavedTextMode, Object digitalCapturedTextMode);

    public void verificarMatchDigitalString(String digitalSavedTextMode, String digitalCapturedTextMode);

    public String getMensagemStatus();

    public boolean isOperacaoExecutada();

    public boolean isUsuarioValido();

}