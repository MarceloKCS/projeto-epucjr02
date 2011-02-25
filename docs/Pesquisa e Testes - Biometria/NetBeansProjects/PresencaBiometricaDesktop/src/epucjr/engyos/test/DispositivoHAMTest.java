/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package epucjr.engyos.test;

import epucjr.engyos.devicemanager.ControleBioDeviceHardware;

/**
 *
 * @author Rogerio
 */
public class DispositivoHAMTest {

    public static void main(String[] args) {
        // DispositivoHAMTest.captureDigitalTextTest();
        //DispositivoHAMTest.capturaEVerificaTest();
        //DispositivoHAMTest.capturaEVerificaBinDataTest();
        DispositivoHAMTest.verificarMAtchClientServerTestString();
    }

    public static void captureDigitalTextTest() {
        ControleBioDeviceHardware controleBioDeviceHardware = new ControleBioDeviceHardware();
        controleBioDeviceHardware.inicializaHardware();
        controleBioDeviceHardware.abrirDispositivo();
        Object valor = controleBioDeviceHardware.capturarDigitalModoString();
        // String valorBin = controleBioDeviceHardware.capturarDigitalModoBinario();
        controleBioDeviceHardware.fecharDispositivo();

       
    }

    public static void capturaEVerificaTest() {
        ControleBioDeviceHardware controleBioDeviceHardware = new ControleBioDeviceHardware();
        controleBioDeviceHardware.inicializaHardware();
        controleBioDeviceHardware.abrirDispositivo();
        Object valor = controleBioDeviceHardware.capturarDigitalModoString();
        controleBioDeviceHardware.testeVerificarDigitalStringValida(valor);

        System.out.println("valor = " + valor.toString());

        if (controleBioDeviceHardware.isOperacaoExecutada()) {
            if (controleBioDeviceHardware.isUsuarioValido()) {
                System.out.println("msgStatusSucesso = " + controleBioDeviceHardware.getMensagemStatus());
            } else {
                System.out.println("msgStatusErro = " + controleBioDeviceHardware.getMensagemStatus());
            }
        } else {
            System.out.println("msg Erro = " + controleBioDeviceHardware.getMensagemStatus());
        }


        controleBioDeviceHardware.fecharDispositivo();

    }

    public static void capturaEVerificaBinDataTest() {
        ControleBioDeviceHardware controleBioDeviceHardware = new ControleBioDeviceHardware();
        controleBioDeviceHardware.inicializaHardware();
        controleBioDeviceHardware.abrirDispositivo();
        Object valor = controleBioDeviceHardware.capturarDigitalModoBinario();
        controleBioDeviceHardware.testeVerificarDigitalValidaBinData(valor);

        if (controleBioDeviceHardware.isOperacaoExecutada()) {
            if (controleBioDeviceHardware.isUsuarioValido()) {
                System.out.println("msgStatusSucesso = " + controleBioDeviceHardware.getMensagemStatus());
            } else {
                System.out.println("msgStatusErro = " + controleBioDeviceHardware.getMensagemStatus());
            }
        } else {
            System.out.println("msg Erro = " + controleBioDeviceHardware.getMensagemStatus());
        }


        controleBioDeviceHardware.fecharDispositivo();

    }

    public static void verificarMAtchClientServerTest(){

        ControleBioDeviceHardware controleBioDeviceHardware = new ControleBioDeviceHardware();
        controleBioDeviceHardware.inicializaHardware();
        controleBioDeviceHardware.abrirDispositivo();
        Object digitalSalvaNoBD = controleBioDeviceHardware.capturarDigitalModoString();
        Object digitalInseridaPeloUsuario = controleBioDeviceHardware.capturarDigitalModoString();

        controleBioDeviceHardware.verificarMatchDigital(digitalSalvaNoBD, digitalInseridaPeloUsuario);

        if (controleBioDeviceHardware.isOperacaoExecutada()) {
            if (controleBioDeviceHardware.isUsuarioValido()) {
                System.out.println("msgStatusSucesso = " + controleBioDeviceHardware.getMensagemStatus());
            } else {
                System.out.println("msgStatusErro = " + controleBioDeviceHardware.getMensagemStatus());
            }
        } else {
            System.out.println("msg Erro = " + controleBioDeviceHardware.getMensagemStatus());
        }


        controleBioDeviceHardware.fecharDispositivo();

    }

     public static void verificarMAtchClientServerTestString(){

        ControleBioDeviceHardware controleBioDeviceHardware = new ControleBioDeviceHardware();
        controleBioDeviceHardware.inicializaHardware();
        controleBioDeviceHardware.abrirDispositivo();
        controleBioDeviceHardware.capturarDigitalModoString();
        String digitalSalvaNoBD = controleBioDeviceHardware.getDigitalModoTexto();

        controleBioDeviceHardware.capturarDigitalModoString();
        String digitalInseridaPeloUsuario = controleBioDeviceHardware.getDigitalModoTexto();


        controleBioDeviceHardware.verificarMatchDigitalString(digitalSalvaNoBD, digitalInseridaPeloUsuario);

        if (controleBioDeviceHardware.isOperacaoExecutada()) {
            if (controleBioDeviceHardware.isUsuarioValido()) {
                System.out.println("msgStatusSucesso = " + controleBioDeviceHardware.getMensagemStatus());
            } else {
                System.out.println("msgStatusErro = " + controleBioDeviceHardware.getMensagemStatus());
            }
        } else {
            System.out.println("msg Erro = " + controleBioDeviceHardware.getMensagemStatus());
        }


        controleBioDeviceHardware.fecharDispositivo();

    }
}
