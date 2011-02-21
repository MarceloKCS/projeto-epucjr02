package com.epucjr.engyos.dominio.crud;

/**
 * Classe auxiliar que realiza as verifica��es necess�rias para a valida��o de um
 * n�mero CPF v�lido de acordo com as regras utilizadas pelo governo federal para
 * um n�mero de CPF legal e v�lido
 *
 * @author Projeto Engyos Team
 *
 * @version 1.0
 *
 * @since 1.0
 */
public class ValidatorCpf {

    /**
     * M�todo que realiza a valida��o do CPF a partir da inser��o de um n�mero de CPF
     *
     * @param cpf Um n�mero de CPF
     *
     * @return Um valor booleano, <code>false</code> se o CPF for inv�lido
     *
     * @see ValidatorCpf#isCPFPadrao(java.lang.String)
     * @see ValidatorCpf#calcDigVerif(java.lang.String) 
     */

    public static boolean valido(String cpf) {
        if (cpf == null || !cpf.matches("([0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9])") || isCPFPadrao(cpf)) {
            return false;
        }
        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) {
            return false;
        }

        if (!calcDigVerif(cpf.substring(0, 9)).equals(cpf.substring(9, 11))) {
            return false;
        }

        return true;
    }

    /**
     * M�todo auxiliar priivado que verifica se o n~umero de CPF segue um padr�o
     * num�rico
     *
     * @param cpf Um numero de CPF
     * @return Um valoor booleano, <code>false</code> se n�o senue nenhum dos par�es verificados
     */

    private static boolean isCPFPadrao(String cpf) {
        if (cpf.equals("11111111111") || cpf.equals("22222222222")
                || cpf.equals("33333333333")
                || cpf.equals("44444444444")
                || cpf.equals("55555555555")
                || cpf.equals("66666666666")
                || cpf.equals("77777777777")
                || cpf.equals("88888888888")
                || cpf.equals("99999999999")) {

            return true;
        }
        return false;
    }

    /**
     *Faz a verifica��o dos digitos do CPF para verificar a validade dos mesmos
     *
     * @param num O digito do CPF
     *
     * @return Um digito? 
     */

    private static String calcDigVerif(String num) {
        Integer primDig, segDig;
        int soma = 0, peso = 10;
        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        if (soma % 11 == 0 | soma % 11 == 1) {
            primDig = new Integer(0);
        } else {
            primDig = new Integer(11 - (soma % 11));
        }

        soma = 0;
        peso = 11;
        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        soma += primDig.intValue() * 2;
        if (soma % 11 == 0 | soma % 11 == 1) {
            segDig = new Integer(0);
        } else {
            segDig = new Integer(11 - (soma % 11));
        }

        return primDig.toString() + segDig.toString();
    }
}
