package com.epucjr.engyos.aplicacao.webcontrole;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import com.epucjr.engyos.tecnologia.utilitarios.DataSet;
import com.epucjr.engyos.tecnologia.utilitarios.GeradorPDF;
import com.epucjr.engyos.tecnologia.utilitarios.Grafico;

public class ActionGeradorDeEstatisticaMes implements Command {

	/**
	 * 0-request
	 * 1-response
	 */
	public Object execute(Object... arg) {
		// TODO Auto-generated method stub
		
		//inicializacao
		int modo = 0;
		Color cor = Color.RED;
		HttpServletResponse response = (HttpServletResponse) arg[1];
		HttpServletRequest request = (HttpServletRequest) arg[0];
		
		//mostra pdf no browser, n sei se isso deve estar antes ou depois
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition",
			 " attachment; filename=\"Estatistica.pdf\"");//usado para salvar
		
		//pega os dados do bd
		DataAccessObjectManager dao = new DataAccessObjectManager();
		String ano = request.getAttribute("Ano").toString();//Ano
		List<Long> listaReuniao = (List<Long>) request.getAttribute("ListaIdReuniao");//lista de reunioes seleecionada
		
		
		//cria o dataset
		DataSet dataSet = new DataSet(modo, DataSet.DATASET_MES);
		
		//insere os dados do bd no dataset
		for (long id: listaReuniao) {
			Reuniao r = dao.obterReuniao(id); //obtem a reuniao
			if (r.getAno().equals(ano)) {
				int i = Integer.parseInt(r.getMes()) - 1;
				dataSet.addValor(i, r.getListaDePresencaObreiro().size());
			}
		}
		
		//cria o grafico
		BufferedImage bi = null;
		if (modo == 0) bi = Grafico.gerarGrafico3D(null, "Presença "+ano, "Mês", "Presença", 800, 500, 800, 500, dataSet.getDcd(), 0.75f, cor, modo, true);
		if (modo == 1) bi = Grafico.gerarGrafico3D(null, "Presença "+ano, "Mês", "Presença", 800, 500, 800, 500, dataSet.getDcd(), 0.75f, null, modo, true);
		
		//gera o pdf
		try {
			GeradorPDF.gerarPDF(bi, response.getOutputStream());
			return "Sucesso gerou pdf";
		} catch (IOException e) {
			return "Falhou na geracao do pdf";
		}

	}

}
