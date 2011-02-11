package com.epucjr.engyos.teste;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Identificacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.PresencaObreiro;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.utilitarios.DataSet;
import com.epucjr.engyos.tecnologia.utilitarios.Grafico;
import com.epucjr.engyos.tecnologia.utilitarios.GeradorPDF;

public class TesteRelatorio2 {

	private DataSet dataset0;
	private JLabel label0;
	private JFrame tela0;

	public TesteRelatorio2() {
		super();
		dataset0 = new DataSet(0, DataSet.DATASET_VAZIO);
	}

	/**
	 * Janela teste
	 */
	public void initComponentsTest() {
		// Gera os graficos
		Color c = Color.getHSBColor((float) Math.random(), 1f, 1f);// gera cor
																	// aleatorio

		BufferedImage bi0 = gerarGrafico3D(0, c);
		label0 = new JLabel(new ImageIcon(bi0));

		try {
			FileOutputStream fos0 = new FileOutputStream(new File(
					"c:\\arquiv\\testRelatorio2.pdf"));
			GeradorPDF.gerarPDF(bi0, fos0);
			fos0.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Instancia as janelas
		tela0 = new JFrame("modo 0");

		// adiciona o fechar as janelas
		tela0.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// adiciona os graficos na janela
		tela0.getContentPane().add(label0);

		// prepara para exibir
		tela0.pack();

		// exibe
		tela0.setVisible(true);
	}

	/**
	 * Adiciona reuniao no DataSet
	 * 
	 * @param r
	 */
	public void addValor(String elemento, double valor) {
		dataset0.addValor(elemento, valor);
	}

	/**
	 * Retorna a imagem do grafico
	 * 
	 * @param modo
	 * @param cor
	 * @return
	 */
	public BufferedImage gerarGrafico3D(int modo, Color cor) {
		if (modo == 0)
			return Grafico.gerarGrafico3D(null, "Presenca 2010", "mês",
					"presença", 800, 500, 800, 500, dataset0.getDcd(), 0.75f,
					cor, modo, false);
		return null;
	}

	public static void main(String[] args) {
		// instancia os DataSet
		TesteRelatorio2 tg = new TesteRelatorio2();

		// cria situacao
		Reuniao reuniao = new Reuniao("Aki", "20/20/2020", "20:20:20");
		Obreiro[] obreiros = new Obreiro[20];
		Congregacao[] congregacaos = new Congregacao[2];

		congregacaos[0] = new Congregacao("Cong 0", "");
		congregacaos[1] = new Congregacao("Cong 1", "");
		congregacaos[0].setIdCongregacao(0);
		congregacaos[1].setIdCongregacao(1);

		for (int i = 0; i < obreiros.length; i++) {
			int idc = i < 10 ? 0 : 1;
			obreiros[i] = new Obreiro("Ob" + i, "Carg" + i, "" + i,
					congregacaos[idc], new Identificacao("" + i));
			congregacaos[idc].addObreiro(obreiros[i]);
			reuniao.adicionarObreiroNaLista(obreiros[i]);
		}

		ArrayList<PresencaObreiro> listaPresencaObreiros = new ArrayList<PresencaObreiro>(
				reuniao.getListaDePresencaObreiro());

		//gera aleatorio, cong 1 tem maior chance de presenca
		for (int i = 0; i < obreiros.length; i++) {
			double x = Math.random();
			listaPresencaObreiros.get(i).setObreiroPresente(x < (0.5d + (double)obreiros[i].getCongregacao().getIdCongregacao()/4d));
		}

		// copia do command
		HashMap<Long, Integer> mapPresenca = new HashMap<Long, Integer>();
		
		for (Congregacao c:congregacaos) {
			mapPresenca.put(c.getIdCongregacao(), 0);
		}
		
		// List<PresencaObreiro> listaPresencaObreiros =
		// reuniao.getListaDePresencaObreiro();
		for (PresencaObreiro po : listaPresencaObreiros) {
			if (po.isObreiroPresente()) { // ???? TODO
				long id = po.getObreiro().getCongregacao().getIdCongregacao();
				int valor = mapPresenca.get(id) + 1;
				mapPresenca.put(id, valor);
			}
		}

		for (Congregacao c : congregacaos) {
			long id = c.getIdCongregacao();
			double valor = mapPresenca.get(id);
			double porcentagem = valor / c.getQtdObreiros();
			
			//seta de 0-100, remova para gerar de 0-1
			porcentagem = porcentagem * 100;
			tg.addValor(c.getNome(), porcentagem);
		}

		tg.initComponentsTest();

	}

}
