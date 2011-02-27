package epucjr.engyos.devicemanager;

/**
 * Esta interface definirá um conjunto de operações a ser seguido na
 * conecção de um dispositivo biométrico no sistema, de modo que seja
 * possivel trocar de dispositivo com uma implementação diferente e esta não
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
