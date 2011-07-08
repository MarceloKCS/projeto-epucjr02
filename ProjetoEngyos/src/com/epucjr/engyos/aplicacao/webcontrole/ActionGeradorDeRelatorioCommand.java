package com.epucjr.engyos.aplicacao.webcontrole;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.tecnologia.relatorio.JasperReportManager;
import com.epucjr.engyos.tecnologia.utilitarios.DateTimeUtils;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rogerio
 */
public class ActionGeradorDeRelatorioCommand implements Command{

    private static org.apache.log4j.Logger log = Logger.getLogger(ActionGeradorDeRelatorioCommand.class);
    private enum ReportType{OBREIRO_NOMESORT,
                            OBREIRO_CPFSORT,
                            OBREIRO_CARGOSORT,
                            OBREIRO_CONGREGACAO_NOMESORT,
                            OBREIRO_CONGREGACAO_CPFSORT,
                            OBREIRO_CONGREGACAO_CARGOSORT,
                            CONGREGACAO_NOMESORT,
                            CONGREGACAO_ENDERECOSORT,
                            RELATORIO_GRAFICO
    };

    @Override
    public Object execute(Object... arg) {
        //1. Instanciações e inicializações
        HttpServletRequest request = (HttpServletRequest) arg[0];
        HttpServletResponse response = (HttpServletResponse) arg[1];
        response.setHeader("Content-Encoding", "pdf");
        JasperReportManager jasperReportManager = new JasperReportManager();
        String campoDigitado = request.getParameter("campo_digitar");
        log.debug("Page: " + campoDigitado);
        String jasperPath = "";
        String nomeDoArquivo = "";

        //1.1 - Para testes
//        long periodoInicio = 1304218800;
//        long periodoFim = 1306551600;

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
//        hashMap.put("periodo_inicio", periodoInicio);
//        hashMap.put("periodo_fim", periodoFim);

        String tipoDeRelatorio = request.getParameter("tipoFormulario");
        String congregacaoId = request.getParameter("congregacaoId");
        String periodoInicioForm = request.getParameter("dataInicioForm");
        String periodoFimForm = request.getParameter("dataFimForm");
        long periodoInicio = 0;
        long periodoFim = 0;

        if(periodoInicioForm != null && !periodoInicioForm.equals("") && periodoFimForm != null && !periodoFimForm.equals("")){
            String horario = DateTimeUtils.converterHorarioHHMM("00", "00");
            periodoInicio = DateTimeUtils.converterDateTimeToMilissegundos(periodoInicioForm, horario) / 1000;
            periodoFim = DateTimeUtils.converterDateTimeToMilissegundos(periodoFimForm, horario) / 1000;
            System.out.println("periodoInicio = " + periodoInicio);
            System.out.println("periodoFim = " + periodoFim);
        }
        log.debug("tipoFormulario: " + tipoDeRelatorio);
        log.debug("congregacaoId: " + congregacaoId);
        log.debug("dataInicioForm: " + periodoInicio);
        log.debug("dataFimForm: " + periodoFim);

        //2. Definindo o tipo de relatório a ser entregue

        ReportType reportType = ReportType.valueOf(tipoDeRelatorio);

        try {

            switch (reportType) {
                case OBREIRO_NOMESORT:
                    log.debug("CASE: OBREIRO_NOMESORT" );
                    jasperPath = request.getRealPath("../../src/jasperfiles/ObreirosCadastradosNameOrdered.jasper");
                    log.debug("contextPath: " + jasperPath);
                    nomeDoArquivo = "RelatorioObreirosCadastrados.pdf";
                    response.setHeader("Content-disposition", "attachment;filename=" + nomeDoArquivo);
                    jasperReportManager.gerarRelatorio(jasperPath, response.getOutputStream(), hashMap);
                    break;
                case OBREIRO_CPFSORT:
                    log.debug("CASE: OBREIRO_CPFSORT" );
                    jasperPath = request.getRealPath("../../src/jasperfiles/ObreirosCadastradosCPFOrdered.jasper");
                    log.debug("contextPath: " + jasperPath);
                    nomeDoArquivo = "RelatorioObreirosCadastradosCPFOrder.pdf";
                    response.setHeader("Content-disposition", "attachment;filename=" + nomeDoArquivo);
                    jasperReportManager.gerarRelatorio(jasperPath, response.getOutputStream(), hashMap);
                    break;
                case OBREIRO_CARGOSORT:
                    log.debug("CASE: OBREIRO_CARGOSORT" );
                    jasperPath = request.getRealPath("../../src/jasperfiles/ObreirosCadastradosCargoOrdered.jasper");
                    log.debug("contextPath: " + jasperPath);
                    nomeDoArquivo = "RelatorioObreirosCadastradosCargoOrder.pdf";
                    response.setHeader("Content-disposition", "attachment;filename=" + nomeDoArquivo);
                    jasperReportManager.gerarRelatorio(jasperPath, response.getOutputStream(), hashMap);
                    break;
                case OBREIRO_CONGREGACAO_NOMESORT:
                    log.debug("CASE: OBREIRO_CONGREGACAO_NOMESORT" );
                    jasperPath = request.getRealPath("../../src/jasperfiles/ObreirosCadastradosCongregacaoOrderedByNome.jasper");
                    nomeDoArquivo = "RelatorioObreirosCadastradosCongregacao.pdf";
                    response.setHeader("Content-disposition", "attachment;filename=" + nomeDoArquivo);
                    hashMap.put("congregacao_id", congregacaoId);
                    jasperReportManager.gerarRelatorio(jasperPath, response.getOutputStream(), hashMap);
                    log.debug("contextPath: " + jasperPath);
                    break;
                case OBREIRO_CONGREGACAO_CPFSORT:
                    log.debug("CASE: OBREIRO_CONGREGACAO_CPFSORT" );
                    jasperPath = request.getRealPath("../../src/jasperfiles/ObreirosCadastradosCongregacaoOrderedByCpf.jasper");
                    nomeDoArquivo = "RelatorioObreirosCadastradosCongregacao.pdf";
                    response.setHeader("Content-disposition", "attachment;filename=" + nomeDoArquivo);
                    hashMap.put("congregacao_id", congregacaoId);
                    jasperReportManager.gerarRelatorio(jasperPath, response.getOutputStream(), hashMap);
                    log.debug("contextPath: " + jasperPath);
                    break;
                case OBREIRO_CONGREGACAO_CARGOSORT:
                    log.debug("CASE: OBREIRO_CONGREGACAO_CARGOSORT" );
                    jasperPath = request.getRealPath("../../src/jasperfiles/ObreirosCadastradosCongregacaoOrderedByCargo.jasper");
                    nomeDoArquivo = "RelatorioObreirosCadastradosCongregacao.pdf";
                    response.setHeader("Content-disposition", "attachment;filename=" + nomeDoArquivo);
                    hashMap.put("congregacao_id", congregacaoId);
                    jasperReportManager.gerarRelatorio(jasperPath, response.getOutputStream(), hashMap);
                    log.debug("contextPath: " + jasperPath);
                    break;
                case CONGREGACAO_NOMESORT:
                    log.debug("CASE: CONGREGACAO_NOMESORT" );
                    jasperPath = request.getRealPath("../../src/jasperfiles/CongregacoesCadastradasOrderByNome.jasper");
                    log.debug("contextPath: " + jasperPath);
                    nomeDoArquivo = "RelatorioCongregacoes.pdf";
                    response.setHeader("Content-disposition", "attachment;filename=" + nomeDoArquivo);
                    jasperReportManager.gerarRelatorio(jasperPath, response.getOutputStream(), hashMap);
                    break;
                case CONGREGACAO_ENDERECOSORT:
                    log.debug("CASE: CONGREGACAO_ENDERECOSORT" );
                    jasperPath = request.getRealPath("../../src/jasperfiles/CongregacoesCadastradasOrderByEndereco.jasper");
                    log.debug("contextPath: " + jasperPath);
                    nomeDoArquivo = "RelatorioCongregacoes.pdf";
                    response.setHeader("Content-disposition", "attachment;filename=" + nomeDoArquivo);
                    jasperReportManager.gerarRelatorio(jasperPath, response.getOutputStream(), hashMap);
                    break;
                case RELATORIO_GRAFICO:
                    log.debug("CASE: RELATORIO_GRAFICO" );
                    jasperPath = request.getRealPath("../../src/jasperfiles/PresencaPorCongregacaoChart.jasper");
                    nomeDoArquivo = "PersencaPorCongregacaoGrafico.pdf";
                    response.setHeader("Content-disposition", "attachment;filename=" + nomeDoArquivo);
                    hashMap.put("PERIODO_INICIO", periodoInicio);
                    hashMap.put("PERIODO_FIM", periodoFim);
                    jasperReportManager.gerarRelatorio(jasperPath, response.getOutputStream(), hashMap);
                    log.debug("contextPath: " + jasperPath);
                    break;
                default:
                    break;

            }

        } catch (IOException ex) {
            log.debug("IOException : " + ex.getMessage());
        }

        return jasperReportManager.getMensagemStatus();
    }


}
