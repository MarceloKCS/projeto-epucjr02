/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presencabiometrica;

import java.awt.Container;
import telas.TelaPrincipal;

/**
 *
 * @author Diego
 */
public class Controler {

    AplicacaoBiometria aplicacaoBiometria;
    TelaPrincipal telaPrincipal;

    public Controler(InterfaceDataManager idm, String reuniao) {
        aplicacaoBiometria = new AplicacaoBiometria(this, idm);
        telaPrincipal = new TelaPrincipal(this, reuniao);
    }

    public String[] getReunioes(String data0, String data1) {
        return aplicacaoBiometria.getRunioes(data0, data1);
    }

    public boolean selecionarReuniao(String text) {
        return aplicacaoBiometria.selecionarReuniao(text);
    }

    public String marcarPresenca(String senha) {
        return aplicacaoBiometria.marcarPresenca(senha);
    }

    public String marcarPresencaDigital() {
        return aplicacaoBiometria.marcarPresencaDigital();
    }

    public Container getVisual() {
        return telaPrincipal;
    }
}
