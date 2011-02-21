package com.epucjr.engyos.tecnologia.ferramentas;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Projeto Engyos Team
 */
public class ControleBioDeviceHardwareTest {

    DispositivoBiometricoHardware controleBioDeviceHardware;

    @Test
    public void verificarSeDispositivoBiometricoInicializou() {

       controleBioDeviceHardware = new ControleBioDeviceHardware();
       controleBioDeviceHardware.inicializaHardware();
       controleBioDeviceHardware.abrirDispositivo();

       assertFalse(controleBioDeviceHardware.isHardwareInicializado());

    }


}
