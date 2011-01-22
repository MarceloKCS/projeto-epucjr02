/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epucjr.engyos.tecnologia.ferramentas;

import com.nitgen.SDK.BSP.NBioBSPJNI;

/**
 *
 * @author Rogerio
 */
public class ControleBioDeviceHardware {

    private NBioBSPJNI nBioBSPJNI;
    private NBioBSPJNI.DEVICE_ENUM_INFO device_enum_info;
    private boolean hardwareInicializado;
    private boolean operacaoExecutada;
    private String mensagemStatus;
    private boolean usuarioValido;
    private int digitalCapturadaStatusDispositivo; // se valor for igual a zero quer dizer OK
    private String digitalModoTexto;
    private byte[] digitalModoBinario;

    public ControleBioDeviceHardware() {
        this.nBioBSPJNI = null;
        this.device_enum_info = null;
        this.hardwareInicializado = false;
        this.operacaoExecutada = false;
        this.mensagemStatus = "";
        this.usuarioValido = false;
        this.digitalCapturadaStatusDispositivo = 0;
    }

    public void inicializaHardware() {
        //Inicializações e instancianciações
        this.nBioBSPJNI = new NBioBSPJNI();

        //Enumera o dispositivo
        this.device_enum_info = this.nBioBSPJNI.new DEVICE_ENUM_INFO();
        nBioBSPJNI.EnumerateDevice(this.device_enum_info);

        this.setHardwareInicializado(true);
    }

    public void encerrarHardware(NBioBSPJNI nBioBSPJNI) {
        nBioBSPJNI.dispose();
    }

    public void abrirDispositivo() {

        //Verifica se as apis necessárias ao acesso do hardware estão inicializadas

        if (isHardwareInicializado()) {
            //Abre o dispositivo
            nBioBSPJNI.OpenDevice(device_enum_info.DeviceInfo[0].NameID, device_enum_info.DeviceInfo[0].Instance);
        } else {
            this.setMensagemStatus("Ocorreu erro de hardware ou hardware nÃ£o inicializado");
        }

    }

    public void fecharDispositivo() {
        //Fecha Dispositivo
        nBioBSPJNI.CloseDevice(device_enum_info.DeviceInfo[0].NameID, device_enum_info.DeviceInfo[0].Instance);
    }

    //TODO - captura 0 (Zero) e porque a captura diz texto mas devolve int?
    public Object capturarDigitalModoString() {
        //Inicializações e instancianciações
        NBioBSPJNI.FIR_HANDLE fir_handle = this.nBioBSPJNI.new FIR_HANDLE();
        NBioBSPJNI.FIR_TEXTENCODE textSavedFIR = null;

        //Captura a digital
        this.nBioBSPJNI.Capture(fir_handle);
       
        //Obtémm a digital capturada em modo texto
        if (!this.nBioBSPJNI.IsErrorOccured()) {
            textSavedFIR = this.nBioBSPJNI.new FIR_TEXTENCODE();

            this.setDigitalCapturadaStatusDispositivo(this.nBioBSPJNI.GetTextFIRFromHandle(fir_handle, textSavedFIR));
            this.setDigitalModoTexto(textSavedFIR.TextFIR);

            this.setOperacaoExecutada(true);
            this.setMensagemStatus("Digital capturada");
        } else {
            this.setOperacaoExecutada(false);
            this.setMensagemStatus("Erro na captura e/ou dispositivo");
        }


        return textSavedFIR;

    }

    public Object capturarDigitalModoBinario() {
        //Inicializações e instancianciações
        NBioBSPJNI.FIR_HANDLE fir_handle = this.nBioBSPJNI.new FIR_HANDLE();
        NBioBSPJNI.FIR binSavedFIR = null;
        

        //Captura a digital
        this.nBioBSPJNI.Capture(fir_handle);

        //Obtém a digital capturada em modo binário
        if (!this.nBioBSPJNI.IsErrorOccured()) {
            binSavedFIR = this.nBioBSPJNI.new FIR();
           
            this.setDigitalCapturadaStatusDispositivo(this.nBioBSPJNI.GetFIRFromHandle(fir_handle, binSavedFIR));
            this.setDigitalModoBinario(binSavedFIR.Data);
            this.setOperacaoExecutada(true);
            this.setMensagemStatus("Digital capturada");
        } else {
            this.setOperacaoExecutada(false);
            this.setMensagemStatus("Erro na captura e/ou dispositivo");
        }

        return binSavedFIR;

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

     public void verificarMatchDigitalStringTest(Object digitalSavedTextMode, Object digitalCapturedTextMode) {
        NBioBSPJNI.INPUT_FIR inputFIR = this.nBioBSPJNI.new INPUT_FIR();
        NBioBSPJNI.INPUT_FIR inputFIR2 = this.nBioBSPJNI.new INPUT_FIR();

        Boolean verificacaoValida = new Boolean(false);
        NBioBSPJNI.FIR_PAYLOAD firPayload = this.nBioBSPJNI.new FIR_PAYLOAD();

        NBioBSPJNI.FIR_TEXTENCODE textSavedFIR = (NBioBSPJNI.FIR_TEXTENCODE) digitalSavedTextMode;
        NBioBSPJNI.FIR_TEXTENCODE textCapturedFIR = (NBioBSPJNI.FIR_TEXTENCODE) digitalCapturedTextMode;
        NBioBSPJNI.FIR_TEXTENCODE textCapturedFIR3 = this.nBioBSPJNI.new FIR_TEXTENCODE();
        String aux = textCapturedFIR.TextFIR;
        textCapturedFIR3.TextFIR = aux;
        inputFIR.SetTextFIR(textSavedFIR);
        inputFIR2.SetTextFIR(textCapturedFIR3);

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

    public boolean isHardwareInicializado() {
        return hardwareInicializado;
    }

    public void setHardwareInicializado(boolean hardwareInicializado) {
        this.hardwareInicializado = hardwareInicializado;
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

    public int getDigitalCapturadaStatusDispositivo() {
        return digitalCapturadaStatusDispositivo;
    }

    public void setDigitalCapturadaStatusDispositivo(int digitalCapturadaStatusDispositivo) {
        this.digitalCapturadaStatusDispositivo = digitalCapturadaStatusDispositivo;
    }
    
    public byte[] getDigitalModoBinario() {
		return digitalModoBinario;
	}

	public void setDigitalModoBinario(byte[] digitalModoBinario) {
		this.digitalModoBinario = digitalModoBinario;
	}

	public String getDigitalModoTexto() {
        return digitalModoTexto;
    }

    public void setDigitalModoTexto(String digitalModoTexto) {
        this.digitalModoTexto = digitalModoTexto;
    }
}
