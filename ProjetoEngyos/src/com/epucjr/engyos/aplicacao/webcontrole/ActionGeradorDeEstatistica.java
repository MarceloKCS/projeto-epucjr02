package com.epucjr.engyos.aplicacao.webcontrole;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.tecnologia.utilitarios.DataSet;
import com.epucjr.engyos.tecnologia.utilitarios.GeradorPDF;
import com.epucjr.engyos.tecnologia.utilitarios.Grafico;

public class ActionGeradorDeEstatistica implements Command {

	public Object execute(Object... arg) {
		// TODO Auto-generated method stub
		
		//inicializacao
		int modo = 0;
		Color cor = Color.RED;
		HttpServletResponse response = (HttpServletResponse) arg[1];
		
		//mostra pdf no browser, n sei se isso deve estar antes ou depois
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition",
			 " attachment; filename=\"Estatistica.pdf\"");//usado para salvar
		
		//pega os dados do bd
		
		//cria o dataset
		DataSet dataSet = new DataSet(modo);
		
		//insere os dados do bd no dataset
		
		//cria o grafico
		BufferedImage bi = null;
		if (modo == 0) bi = Grafico.gerarGrafico3D(null, "Presenca 2010", "Mês", "Presença", 800, 500, 800, 500, dataSet.getDcd(), 0.75f, cor, modo);
		if (modo == 1) bi =  Grafico.gerarGrafico3D(null, "Presenca 2010", "Mês", "Presença", 800, 500, 800, 500, dataSet.getDcd(), 0.75f, null, modo);
		
		//gera o pdf
		try {
			GeradorPDF.gerarPDF(bi, response.getOutputStream());
			return "Sucesso gerou pdf";
		} catch (IOException e) {
			return "Falhou na geracao do pdf";
		}

	}

}
