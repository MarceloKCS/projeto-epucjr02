/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epucjr.engyos.aplicacao.estrutura;

/**
 *
 * @author Rogerio
 */
public enum UserReportType {
    PARAM_NOME("nome"),
    PARAM_CPF("cpf"),
    PARAM_CONGREGACAO("congregacao");

     private String reportType;
     
    private UserReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportType(){
        return this.reportType;
    }    



}
