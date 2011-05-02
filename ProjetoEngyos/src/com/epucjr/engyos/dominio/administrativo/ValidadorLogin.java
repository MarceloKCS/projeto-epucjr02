package com.epucjr.engyos.dominio.administrativo;

import com.epucjr.engyos.dominio.modelo.Administrador;

public class ValidadorLogin {

   private String mensagemStatus;
   private boolean usuarioValido;
   public static String STATUS_ERROR = "ERROR";
   public static String STATUS_WARNING = "WARNING";
   public static String STATUS_SUCCESS = "SUCCESS";

   private String statusCondition;

    public ValidadorLogin() {
        this.mensagemStatus = "";
        this.usuarioValido = false;
        this.statusCondition = "";
    }

    public void verificarUsuariovalido(Administrador admin, String senha) {
        if (admin != null) {
            if (admin.getSenha().equals(senha)) {
                this.setMensagemStatus("Usuário válido");
                this.setUsuarioValido(true);
                this.setStatusCondition(STATUS_SUCCESS);
            } else {
                this.setMensagemStatus("Senha Inválida");
                this.setUsuarioValido(false);
                this.setStatusCondition(STATUS_WARNING);
            }

        } else {
            this.setMensagemStatus("Usuário inexistente no sistema");
            this.setUsuarioValido(false);
            this.setStatusCondition(STATUS_ERROR);
        }
    }

    public boolean verifyStatusCondition(String statusCondition){
        if(statusCondition.equals(this.statusCondition)){
            return true;
        }
        else{
            return false;
        }
    }

    public void setStatusCondition(String statusCondition){
        this.statusCondition = statusCondition;
    }

    public String getMensagemStatus() {
        return mensagemStatus;
    }

    public void setMensagemStatus(String mensagemStatus) {
        this.mensagemStatus = mensagemStatus;
    }

    public boolean isUsuarioValido() {
        return usuarioValido;
    }

    public void setUsuarioValido(boolean usuarioValido) {
        this.usuarioValido = usuarioValido;
    }
}
