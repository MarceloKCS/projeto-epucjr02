package com.epucjr.engyos.aplicacao.handler;

import com.epucjr.engyos.aplicacao.estrutura.GUIMessageTypes;
import java.sql.SQLException;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Rogerio
 */
public class EngyosExceptionHandler {

    private String message;


    public EngyosExceptionHandler() {
        this.message = "";
    }

    public void handleException(Exception exception){
        if(exception instanceof ClassNotFoundException){
            this.setMessage(GUIMessageTypes.ERR_RE_01.getGUIMessage() + "\n" + exception.getMessage());
            return;
        }
        else if(exception instanceof SQLException){
            this.setMessage(GUIMessageTypes.ERR_RE_01.getGUIMessage() + "\n" + exception.getMessage());
            return;
        }
        else if(exception instanceof JRException){
            this.setMessage(GUIMessageTypes.ERR_RE_01.getGUIMessage() + "\n" + exception.getMessage());
            return;
        }
        else{
            this.setMessage(GUIMessageTypes.ERR_RE_01.getGUIMessage() + "\n" + exception.getMessage());
        }
    }

    public Object handleTreatException(Exception exception){
        return null;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }





}
