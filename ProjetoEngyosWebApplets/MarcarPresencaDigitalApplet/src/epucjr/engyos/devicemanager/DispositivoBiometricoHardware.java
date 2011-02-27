package epucjr.engyos.devicemanager;

/**
 * Esta interface definir� um conjunto de opera��es a ser seguido na
 * conec��o de um dispositivo biom�trico no sistema, de modo que seja
 * possivel trocar de dispositivo com uma implementa��o diferente e esta n�o
 * impacte no sistema
 *
 * @author Project Engyos Team
 */
public interface DispositivoBiometricoHardware {
    public void inicializaHardware();

    public void encerrarHardware();

    public void abrirDispositivo();

    public void fecharDispositivo();

    public Object capturarDigitalModoString();

    public String capturarDigitalModoTextString();

    public Object capturarDigitalModoBinario();

    public void verificarMatchDigital(Object digitalSavedTextMode, Object digitalCapturedTextMode);

    public void verificarMatchDigitalString(String digitalSavedTextMode, String digitalCapturedTextMode);

    public boolean isHardwareInicializado();

    public String getMensagemStatus();

    public boolean isOperacaoExecutada();

    public boolean isUsuarioValido();

    public byte[] getDigitalModoBinario();

    public String getDigitalModoTexto();

}
