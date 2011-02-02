/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presencabiometrica;

/**
 *
 * @author Diego
 */
public interface InterfaceDataManager {

    /**
     *
     * @param data1
     * @param data0
     * @return String[0]... data String[1]... hora
     */
    public String[] getReunioes(String data0, String data1);

    public long getReuniao(String data);

    public String marcarPresenca(long idReuniao, String senha);

    public String marcarPresencaDigital(long idReuniao, String digital);

}
