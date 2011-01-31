/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presencabiometrica;

import com.nitgen.SDK.BSP.NBioBSPJNI;

/**
 *
 * @author Diego
 */
public class AplicacaoBiometria {

    Controler controler;
    InterfaceDataManager dataManager;
    long idReuniao;

    public AplicacaoBiometria(Controler controler, InterfaceDataManager dataManager) {
        this.controler = controler;
        this.dataManager = dataManager;
        idReuniao = -1;
    }

    public String[] getRunioes(String data0, String data1) {
        int d1,d0;
        d0 = Integer.parseInt(data0.substring(6, 10));
        d1 = Integer.parseInt(data1.substring(6, 10));
        if (d1 != d0) {
            if (d0 > d1) {
                return dataManager.getReunioes(data1, data0);
            } else return dataManager.getReunioes(data0, data1);
        }

        d0 = Integer.parseInt(data0.substring(3, 5));
        d1 = Integer.parseInt(data1.substring(3, 5));
        if (d1 != d0) {
            if (d0 > d1) {
                return dataManager.getReunioes(data1, data0);
            } else return dataManager.getReunioes(data0, data1);
        }

        d0 = Integer.parseInt(data0.substring(0, 2));
        d1 = Integer.parseInt(data1.substring(0, 2));
        if (d1 != d0) {
            if (d0 > d1) {
                return dataManager.getReunioes(data1, data0);
            } else return dataManager.getReunioes(data0, data1);
        } else return dataManager.getReunioes(data0, data1);
    }

    public boolean selecionarReuniao(String text) {
        long id = dataManager.getReuniao(text.substring(0, 10));
        idReuniao = id;
        return id >= 0;
    }

    public String marcarPresenca(String senha) {
        return dataManager.marcarPresenca(idReuniao, senha);
    }

    public String marcarPresencaDigital() {
        String r = null;
        String digital = null;
        int capt = 1;

        try{
            NBioBSPJNI bsp = new NBioBSPJNI();

            NBioBSPJNI.DEVICE_ENUM_INFO deviceEnumInfo = bsp.new DEVICE_ENUM_INFO();
            bsp.EnumerateDevice(deviceEnumInfo);

            bsp.OpenDevice(deviceEnumInfo.DeviceInfo[0].NameID,
            deviceEnumInfo.DeviceInfo[0].Instance);

            NBioBSPJNI.FIR_HANDLE hSavedFIR;
            hSavedFIR = bsp.new FIR_HANDLE();

            capt = bsp.Capture(hSavedFIR);

            NBioBSPJNI.FIR_TEXTENCODE textSavedFIR;
            textSavedFIR = bsp.new FIR_TEXTENCODE();

            if (bsp.IsErrorOccured() == false) {
                bsp.GetTextFIRFromHandle(hSavedFIR, textSavedFIR);
            }

            digital = textSavedFIR.TextFIR;


            bsp.CloseDevice(deviceEnumInfo.DeviceInfo[0].NameID,
            deviceEnumInfo.DeviceInfo[0].Instance);
            bsp.dispose();
        } catch(java.lang.UnsatisfiedLinkError e) {
            
        } catch(java.lang.NoClassDefFoundError e) {
            
        }

        if (capt == 513) r = "-1";
        else if(capt == 0) r = dataManager.marcarPresencaDigital(idReuniao, digital);
        else r = "-2";
        
        return r;
    }
}
