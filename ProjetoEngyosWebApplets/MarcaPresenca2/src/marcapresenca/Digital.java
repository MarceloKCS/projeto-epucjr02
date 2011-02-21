/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package marcapresenca;

import com.nitgen.SDK.BSP.NBioBSPJNI;

/**
 *
 * @author Diego
 */
public class Digital {

    public static String getNome(long idReuniao, InterfaceDataManager idm) {
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
            capt = 1;
        } catch(java.lang.NoClassDefFoundError e) {
            capt = 1;
        }

        if (capt == 513) r = "-1";
        else if(capt == 0) r = idm.marcarPresencaDigital(idReuniao, digital);
        else r = "-2";

        return r;
    }
}
