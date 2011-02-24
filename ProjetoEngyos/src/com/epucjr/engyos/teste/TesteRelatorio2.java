package com.epucjr.engyos.teste;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.epucjr.engyos.aplicacao.webcontrole.ActionGeradorDeEstatisticaCongregacao;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Identificacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.PresencaObreiro;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.utilitarios.GeradorPDF;

public class TesteRelatorio2 {

	private JLabel label0;
	private JFrame tela0;
	private BufferedImage bi;
	
	public BufferedImage getBi() {
		return bi;
	}

	public void setBi(BufferedImage bi) {
		this.bi = bi;
	}

	public TesteRelatorio2() {
		super();
	}

	/**
	 * Janela teste
	 */
	public void initComponentsTest() {
		// Gera os graficos
		Color c = Color.getHSBColor((float) Math.random(), 1f, 1f);// gera cor
																	// aleatorio
		label0 = new JLabel(new ImageIcon(bi));

		try {
			FileOutputStream fos0 = new FileOutputStream(new File(
					"c:\\arquiv\\testRelatorio2.pdf"));
			GeradorPDF.gerarPDF(bi, fos0);
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

	public static void main(String[] args) {
		// instancia os DataSet
		TesteRelatorio2 tg = new TesteRelatorio2();

		// cria situacao
		Reuniao reuniao = new Reuniao("Aki", "20/20/2020", "20:20:20");
		Obreiro[] obreiros = new Obreiro[20];
		ArrayList<Congregacao> congregacaos = new ArrayList<Congregacao>(2);

		// instancia congergracoes
		congregacaos.add(new Congregacao("Cong 0", ""));
		congregacaos.add(new Congregacao("Cong 1", ""));
		congregacaos.get(0).setIdCongregacao(0);
		congregacaos.get(1).setIdCongregacao(1);

		// instancia obreiros, insere na congregacao e na lista de presenca
		for (int i = 0; i < obreiros.length; i++) {
			int idc = i < 10 ? 0 : 1;
			obreiros[i] = new Obreiro("Ob" + i, "Carg" + i, "" + i,
					congregacaos.get(idc), new Identificacao("" + i));
			congregacaos.get(idc).addObreiro(obreiros[i]);
			reuniao.adicionarObreiroNaListaDePresenca(obreiros[i]);
		}

		// pega a lista de presenca
		ArrayList<PresencaObreiro> listaPresencaObreiros = new ArrayList<PresencaObreiro>(
				reuniao.getListaDePresencaObreiro());

		//gera aleatorio, cong 1 tem maior chance de presenca
		for (int i = 0; i < obreiros.length; i++) {
			double x = Math.random();
			listaPresencaObreiros.get(i).setObreiroPresente(x < (0.5d + (double)obreiros[i].getCongregacao().getIdCongregacao()/4d));
		}

		// lista de reuniao
		LinkedList<Reuniao> r = new LinkedList<Reuniao>();
		r.add(reuniao);
		
		// Action command
		ActionGeradorDeEstatisticaCongregacao actionGeradorDeEstatisticaCongregacao = new ActionGeradorDeEstatisticaCongregacao();
		BufferedImage bi = actionGeradorDeEstatisticaCongregacao.criarGrafico(r, congregacaos, "Teste");
		tg.setBi(bi);

		tg.initComponentsTest();

	}

}
