package com.epucjr.engyos.tecnologia.utilitarios;

import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class HoraUtil {

    public static final String 	TIME_FORMAT = "HH:mm:ss";

    public static String obterHora(String horaReuniao) {
        String hora = "";
        if (horaReuniao != null) {
            /*int posicao = 0;
            while(horaReuniao.charAt(posicao) != ':'){
            hora = hora + horaReuniao.charAt(posicao);
            posicao++;
            }*/
            hora = ListUtilTokenizer.obterArrayString(horaReuniao, ":")[0];
        }
        return hora;
    }

    public static String obterMinuto(String horaReuniao) {
        String minuto = "";
        if (horaReuniao != null) {
            /*int posicao = 0;
            while(horaReuniao.charAt(posicao) != ':'){
            posicao++;
            }
            while(horaReuniao.length() > posicao){
            minuto = minuto + horaReuniao.charAt(posicao);
            posicao++;
            }*/
            minuto = ListUtilTokenizer.obterArrayString(horaReuniao, ":")[1];
        }
        return minuto;
    }

    public static String obterSegundo(String horaReuniao) {
        String segundo = "";
        if (horaReuniao != null) {
            segundo = ListUtilTokenizer.obterArrayString(horaReuniao, ":")[2];
        }
        return segundo;
    }

    public static String obterTempoCorrente(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
		return sdf.format(cal.getTime());
	}

    //Método de teste para uso do calendar
    public static String obterHoraCorrente() {
        Calendar calendar = new GregorianCalendar();
        Integer hora = calendar.get(Calendar.HOUR_OF_DAY);
        Integer minuto = calendar.get(Calendar.MINUTE);
        Integer segundo = calendar.get(Calendar.SECOND);

        return converterHorarioHHMMSS(hora.toString(), minuto.toString(), segundo.toString());
    }

    public static String converterHorarioHHMMSS(String hora, String minuto, String segundo) {
        String horario = hora + ":" + minuto + ":" + segundo;
        return horario;
    }

    /**
     * Exemplo de Timer da sun
     */

    private  Toolkit toolkit;
    private  Timer timer;

    public void ReminderBeep(int seconds) {
        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds * 1000);
    }

    private class RemindTask extends TimerTask {

        public void run() {
            System.out.println("Time's up!");
            toolkit.beep();
            //timer.cancel(); //Not necessary because we call System.exit
            System.exit(0); //Stops the AWT thread (and everything else)
        }
    }
}
