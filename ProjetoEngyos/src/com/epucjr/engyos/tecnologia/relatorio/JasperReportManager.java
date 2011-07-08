package com.epucjr.engyos.tecnologia.relatorio;

import com.epucjr.engyos.aplicacao.estrutura.GUIMessageTypes;
import com.epucjr.engyos.aplicacao.handler.EngyosExceptionHandler;
import com.epucjr.engyos.tecnologia.persistencia.MySQLDatabaseManager;
import java.sql.Connection;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.log4j.Logger;

/**
 *
 * @author Rogerio
 */
public class JasperReportManager {
    private static org.apache.log4j.Logger log = Logger.getLogger(JasperReportManager.class);
    private boolean operacaoExecutada;
    private String mensagemStatus;
    private MySQLDatabaseManager mySQLDatabaseManager;
    private EngyosExceptionHandler engyosExceptionHandler;

    public JasperReportManager() {
        this.operacaoExecutada = false;
        this.mensagemStatus = "";
        this.mySQLDatabaseManager = new MySQLDatabaseManager();
        this.engyosExceptionHandler = new EngyosExceptionHandler();
    }

    public void gerarRelatorio(String jasperFilePath, OutputStream outputStream, HashMap<String, Object> parametros){
        Object relatorioCarregado = null;
        Connection mysqlConnection = null;

        try {

            mysqlConnection = mySQLDatabaseManager.getConnection();
            relatorioCarregado = JRLoader.loadObject(jasperFilePath);
            JasperReport relatorioCarregadoJR = (JasperReport) relatorioCarregado;
            JasperPrint relatorioPreenchido = JasperFillManager.fillReport(relatorioCarregadoJR, parametros, mysqlConnection);
            JasperExportManager.exportReportToPdfStream(relatorioPreenchido, outputStream);
            this.setOperacaoExecutada(true);
            this.setMensagemStatus(GUIMessageTypes.ME_RE_01.getGUIMessage());
            
        } catch (JRException ex) {
            this.engyosExceptionHandler.handleException(ex);
            this.setMensagemStatus(this.engyosExceptionHandler.getMessage());
            log.debug("JRException : " + ex.getMessage());
            this.setOperacaoExecutada(false);
        } catch (ClassNotFoundException ex) {
            this.engyosExceptionHandler.handleException(ex);
            this.setMensagemStatus(this.engyosExceptionHandler.getMessage());
            log.debug("ClassNotFoundException : " + ex.getMessage());
            this.setOperacaoExecutada(false);
        } catch (SQLException ex) {
            this.engyosExceptionHandler.handleException(ex);
            this.setMensagemStatus(this.engyosExceptionHandler.getMessage());
            log.debug("SQLException : " + ex.getMessage());
            this.setOperacaoExecutada(false);
        }finally {
            try {
                mySQLDatabaseManager.closeConnection();
            } catch (SQLException ex) {
                this.engyosExceptionHandler.handleException(ex);
                this.setMensagemStatus(this.engyosExceptionHandler.getMessage());
                this.setOperacaoExecutada(false);
                log.debug("SQLException : " + ex.getMessage());
            }
        }

    }

    public String getMensagemStatus() {
        return mensagemStatus;
    }

    public void setMensagemStatus(String mensagemStatus) {
        this.mensagemStatus = mensagemStatus;
    }

    public boolean isOperacaoExecutada() {
        return operacaoExecutada;
    }

    public void setOperacaoExecutada(boolean operacaoExecutada) {
        this.operacaoExecutada = operacaoExecutada;
    }

    

}
