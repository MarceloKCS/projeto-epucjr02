package com.epucjr.engyos.tecnologia.ferramentas;

import com.nitgen.SDK.BSP.NBioBSPJNI;

/**
 *
 * @author Projeto Engyos Team
 */
public class ControleBiometricoNBioBSPUtil implements ControleBiometricoUtil {

    private NBioBSPJNI nBioBSPJNI;
    private boolean operacaoExecutada;
    private String mensagemStatus;
    private boolean usuarioValido;

    public ControleBiometricoNBioBSPUtil() {
        //Inicializações e instancianciações
        this.nBioBSPJNI = new NBioBSPJNI();
        this.operacaoExecutada = false;
        this.mensagemStatus = "";
        this.usuarioValido = false;

    }


     public void testeVerificarDigitalStringValida(Object digitalTextMode) {
        //Inicializações e instancianciações
        NBioBSPJNI.INPUT_FIR inputFir = this.nBioBSPJNI.new INPUT_FIR();
        Boolean verificacaoValida = new Boolean(false);
        NBioBSPJNI.FIR_PAYLOAD firPayload = this.nBioBSPJNI.new FIR_PAYLOAD();
        NBioBSPJNI.FIR_TEXTENCODE textSavedFIR = (NBioBSPJNI.FIR_TEXTENCODE) digitalTextMode;

        inputFir.SetTextFIR(textSavedFIR);

        this.nBioBSPJNI.Verify(inputFir, verificacaoValida, firPayload);

        if (!this.nBioBSPJNI.IsErrorOccured()) {

            if (verificacaoValida) {
                this.setMensagemStatus(firPayload.GetText());
                if (this.getMensagemStatus() == null) {
                    this.setMensagemStatus("Usuário válido");
                }
                this.setUsuarioValido(true);
            } else {
                this.setMensagemStatus("Verificação Falhou...");
                this.setUsuarioValido(false);
            }

            this.setOperacaoExecutada(true);

        } else {
            this.setOperacaoExecutada(false);
            this.setMensagemStatus("Erro na leitura e/ou dispositivo");
        }
    }

    public void testeVerificarDigitalValidaBinData(Object digitalTextMode) {
        //Inicializações e instancianciações
        NBioBSPJNI.INPUT_FIR inputFir = this.nBioBSPJNI.new INPUT_FIR();
        Boolean verificacaoValida = new Boolean(false);
        NBioBSPJNI.FIR_PAYLOAD firPayload = this.nBioBSPJNI.new FIR_PAYLOAD();
        NBioBSPJNI.FIR binSavedFIR = (NBioBSPJNI.FIR) digitalTextMode;

        inputFir.SetFullFIR(binSavedFIR);

        this.nBioBSPJNI.Verify(inputFir, verificacaoValida, firPayload);

        if (!this.nBioBSPJNI.IsErrorOccured()) {

            if (verificacaoValida) {
                this.setMensagemStatus(firPayload.GetText());
                if (this.getMensagemStatus() == null) {
                    this.setMensagemStatus("Usuário válido");
                }
                this.setUsuarioValido(true);
            } else {
                this.setMensagemStatus("Verificacao Falhou...");
                this.setUsuarioValido(false);
            }

            this.setOperacaoExecutada(true);

        } else {
            this.setOperacaoExecutada(false);
            this.setMensagemStatus("Erro na leitura e/ou dispositivo");
        }
    }

    public void verificarMatchDigital(Object digitalSavedTextMode, Object digitalCapturedTextMode) {
        NBioBSPJNI.INPUT_FIR inputFIR = this.nBioBSPJNI.new INPUT_FIR();
        NBioBSPJNI.INPUT_FIR inputFIR2 = this.nBioBSPJNI.new INPUT_FIR();

        Boolean verificacaoValida = new Boolean(false);
        NBioBSPJNI.FIR_PAYLOAD firPayload = this.nBioBSPJNI.new FIR_PAYLOAD();

        NBioBSPJNI.FIR_TEXTENCODE textSavedFIR = (NBioBSPJNI.FIR_TEXTENCODE) digitalSavedTextMode;
        NBioBSPJNI.FIR_TEXTENCODE textCapturedFIR = (NBioBSPJNI.FIR_TEXTENCODE) digitalCapturedTextMode;


        inputFIR.SetTextFIR(textSavedFIR);
        inputFIR2.SetTextFIR(textCapturedFIR);

        this.nBioBSPJNI.VerifyMatch(inputFIR, inputFIR2, verificacaoValida, firPayload);

        if (!this.nBioBSPJNI.IsErrorOccured()) {

            if (verificacaoValida) {
                this.setMensagemStatus(firPayload.GetText());
                if (this.getMensagemStatus() == null) {
                    this.setMensagemStatus("Usuário válido");
                }
                this.setUsuarioValido(true);
            } else {
                this.setMensagemStatus("Usuário Inválido no sistema");
                this.setUsuarioValido(false);
            }

            this.setOperacaoExecutada(true);

        } else {
            this.setOperacaoExecutada(false);
            this.setMensagemStatus("Erro na leitura e/ou dispositivo");
        }


    }

     public void verificarMatchDigitalString(String digitalSavedTextMode, String digitalCapturedTextMode) {
        NBioBSPJNI.INPUT_FIR inputFIR = this.nBioBSPJNI.new INPUT_FIR();
        NBioBSPJNI.INPUT_FIR inputFIR2 = this.nBioBSPJNI.new INPUT_FIR();

        Boolean verificacaoValida = new Boolean(false);
        NBioBSPJNI.FIR_PAYLOAD firPayload = this.nBioBSPJNI.new FIR_PAYLOAD();

        NBioBSPJNI.FIR_TEXTENCODE textSavedFIR = this.nBioBSPJNI.new FIR_TEXTENCODE();
        NBioBSPJNI.FIR_TEXTENCODE textCapturedFIR = this.nBioBSPJNI.new FIR_TEXTENCODE();

        textSavedFIR.TextFIR = digitalSavedTextMode;
        textCapturedFIR.TextFIR = digitalCapturedTextMode;

        inputFIR.SetTextFIR(textSavedFIR);
        inputFIR2.SetTextFIR(textCapturedFIR);

        this.nBioBSPJNI.VerifyMatch(inputFIR, inputFIR2, verificacaoValida, firPayload);

        if (!this.nBioBSPJNI.IsErrorOccured()) {

            if (verificacaoValida) {
                this.setMensagemStatus(firPayload.GetText());
                if (this.getMensagemStatus() == null) {
                    this.setMensagemStatus("Usuário válido");
                }
                this.setUsuarioValido(true);
            } else {
                this.setMensagemStatus("Usuário Inválido no sistema");
                this.setUsuarioValido(false);
            }

            this.setOperacaoExecutada(true);

        } else {
            this.setOperacaoExecutada(false);
            this.setMensagemStatus("Erro na leitura e/ou dispositivo");
        }
    }

    public String getMensagemStatus() {
        return mensagemStatus;
    }

    public void setMensagemStatus(String mensagemStatus) {
        this.mensagemStatus = mensagemStatus;
    }   

    public boolean isOperacaoExecutada() {
        return operacaoExecutada;
    }

    public void setOperacaoExecutada(boolean operacaoExecutada) {
        this.operacaoExecutada = operacaoExecutada;
    }

    public boolean isUsuarioValido() {
        return usuarioValido;
    }

    public void setUsuarioValido(boolean usuarioValido) {
        this.usuarioValido = usuarioValido;
    }


}
