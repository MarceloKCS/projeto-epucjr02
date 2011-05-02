package com.epucjr.engyos.aplicacao.webcontrole;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
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
	 * 0-request 1-response
	 */
	public Object execute(Object... arg) {

		// inicializacao
		HttpServletResponse response = (HttpServletResponse) arg[1];
		HttpServletRequest request = (HttpServletRequest) arg[0];

		// mostra pdf no browser, n sei se isso deve estar antes ou depois
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition",
				" attachment; filename=\"Estatistica.pdf\"");// usado para
																// salvar

		// pega os dados do bd
		DataAccessObjectManager dao = new DataAccessObjectManager();
		
		//pega um array de id de reunioes
		long[] idreunioes = (long[]) request.getAttribute("idReunioes");
		String titulo = request.getAttribute("titulo").toString();
		
		LinkedList<Reuniao> reuniaos = new LinkedList<Reuniao>();
		for (long r:idreunioes) {
			reuniaos.add(dao.obterReuniao(r));
		}
		
		List<Congregacao> listaCongregacaos = dao.obterListaDeCongregacoes();
		
		try {
			BufferedImage bi = criarGrafico(reuniaos, listaCongregacaos, titulo);
			GeradorPDF.gerarPDF(bi, response.getOutputStream());
			return "Sucesso gerou pdf";
		} catch (IOException e) {
			return "Falhou na geracao do pdf";
		}
	}

	public BufferedImage criarGrafico(List<Reuniao> reuniaos,
			List<Congregacao> congregacaos, String titulo) {
		// inicializacao
		int modo = 0;
		Color cor = Color.RED;

		HashMap<Long, Integer> mapPresenca = new HashMap<Long, Integer>();// idCong,pres

		// cria o dataset
		DataSet dataSet = new DataSet(modo, DataSet.DATASET_VAZIO);

		// inicializa map
		for (Congregacao c : congregacaos) {
			mapPresenca.put(c.getIdCongregacao(), 0);
		}

		// insere os dados do bd no dataset
		for (Reuniao reuniao : reuniaos) {
			List<PresencaObreiro> listaPresencaObreiros = reuniao
					.getListaDePresencaObreiro();
			for (PresencaObreiro po : listaPresencaObreiros) {
				if (po.isObreiroPresente()) { // ???? TODO n sei se é isso, se isObreiroPresente entao adiciona no grafico
					long id = po.getObreiro().getCongregacao()
							.getIdCongregacao();
					int valor = mapPresenca.get(id) + 1;
					mapPresenca.put(id, valor);
				}
			}
		}

		for (Congregacao congregacao : congregacaos) {
			long id = congregacao.getIdCongregacao();
			double valor = mapPresenca.get(id);
			double porcentagem = valor / (congregacao.getQtdObreiros() * reuniaos.size());
			porcentagem = porcentagem * 100;
			dataSet.addValor(congregacao.getNome(), porcentagem);
		}

		// cria o grafico
		if (modo == 0)
			return Grafico.gerarGrafico3D(null, "Presença " + titulo,
					"Congregação", "Presença %", 800, 500, 800, 500,
					dataSet.getDcd(), 0.75f, cor, modo, false);
		if (modo == 1)
			return Grafico.gerarGrafico3D(null, "Presença " + titulo,
					"Congregação", "Presença %", 800, 500, 800, 500,
					dataSet.getDcd(), 0.75f, null, modo, false);
		return null;
	}

}
