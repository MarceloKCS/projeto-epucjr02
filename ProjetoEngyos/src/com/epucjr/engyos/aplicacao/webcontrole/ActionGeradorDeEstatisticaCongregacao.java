package com.epucjr.engyos.aplicacao.webcontrole;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.PresencaObreiro;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;
import com.epucjr.engyos.tecnologia.utilitarios.DataSet;
import com.epucjr.engyos.tecnologia.utilitarios.GeradorPDF;
import com.epucjr.engyos.tecnologia.utilitarios.Grafico;

public class ActionGeradorDeEstatisticaCongregacao implements Command {

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
		long idDaReuniao = Long.parseLong(request.getAttribute("idDaReuniao").toString());
		Reuniao reuniao = dao.obterReuniao(idDaReuniao);
		List<Congregacao> listaCongregacaos = dao.obterListaDeCongregacoes();
		HashMap<Long, Integer> mapPresenca = new HashMap<Long, Integer>();//idCong,pres
		
		//cria o dataset
		DataSet dataSet = new DataSet(modo, DataSet.DATASET_VAZIO);
		
		//inicializa map
		for (Congregacao c:listaCongregacaos) {
			mapPresenca.put(c.getIdCongregacao(), 0);
		}
		
		//insere os dados do bd no dataset
		List<PresencaObreiro> listaPresencaObreiros = reuniao.getListaDePresencaObreiro();
		for (PresencaObreiro po: listaPresencaObreiros) {
			if (po.isObreiroPresente()) { //???? TODO
				long id = po.getObreiro().getCongregacao().getIdCongregacao();
				int valor = mapPresenca.get(id) + 1;
				mapPresenca.put(id, valor);
			}
		}
		
		for (Congregacao c: listaCongregacaos) {
			long id = c.getIdCongregacao();
			double valor = mapPresenca.get(id);
			double porcentagem = valor / c.getQtdObreiros();
			porcentagem = porcentagem * 100;
			dataSet.addValor(c.getNome(), porcentagem);
		}
		
		//cria o grafico
		BufferedImage bi = null;
		if (modo == 0) bi = Grafico.gerarGrafico3D(null, "Presença "+reuniao.getData(), "Congregação", "Presença %", 800, 500, 800, 500, dataSet.getDcd(), 0.75f, cor, modo, true);
		if (modo == 1) bi = Grafico.gerarGrafico3D(null, "Presença "+reuniao.getData(), "Congregação", "Presença %", 800, 500, 800, 500, dataSet.getDcd(), 0.75f, null, modo, true);
		
		//gera o pdf
		try {
			GeradorPDF.gerarPDF(bi, response.getOutputStream());
			return "Sucesso gerou pdf";
		} catch (IOException e) {
			return "Falhou na geracao do pdf";
		}

	}

}
