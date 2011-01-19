package com.epucjr.engyos.teste;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.utilitarios.DataSet;
import com.epucjr.engyos.tecnologia.utilitarios.Grafico;

public class TesteGrafico {

	private DataSet dataset0, dataset1;
	private JLabel label0, label1;
	private JFrame tela0, tela1;
	
	public TesteGrafico() {
		super();
		dataset0 = new DataSet(0);
		dataset1 = new DataSet(1);
	}
	
	/**
	 * Janela teste
	 */
	public void initComponentsTest() {
		//Gera os graficos
		Color c = Color.getHSBColor((float)Math.random(), 1f,1f);//gera cor aleatorio
		label0 = new JLabel(new ImageIcon(gerarGrafico3D(0, c)));
		label1 = new JLabel(new ImageIcon(gerarGrafico3D(1, null)));
		
		//Instancia as janelas
		tela0 = new JFrame("modo 0");
		tela1 = new JFrame("modo 1");
		
		//adiciona o fechar as janelas
		tela0.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tela1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//adiciona os graficos na janela
		tela0.getContentPane().add(label0);
		tela1.getContentPane().add(label1);
		
		//prepara para exibir
		tela0.pack();
		tela1.pack();
		
		//exibe
		tela0.setVisible(true);
		tela1.setVisible(true);
	}

	/**
	 * Adiciona reuniao no DataSet
	 * @param r
	 */
	public void addReuniao(Reuniao r) {
		dataset0.addReuniao(r);
		dataset1.addReuniao(r);
	}
	
	/**
	 * Retorna a imagem do grafico
	 * @param modo
	 * @param cor
	 * @return
	 */
	public BufferedImage gerarGrafico3D(int modo, Color cor) {
		if (modo == 0) return Grafico.gerarGrafico3D(null, "Presenca 2010", "mês", "presença", 800, 500, 800, 500, dataset0.getDcd(), 0.75f, cor, modo);
		if (modo == 1) return Grafico.gerarGrafico3D(null, "Presenca 2010", "mês", "presença", 800, 500, 800, 500, dataset1.getDcd(), 0.75f, null, modo);
		return null;
	}
	
	public static void main(String[] args) {
		//instancia os DataSet
		TesteGrafico tg = new TesteGrafico();
		
		//add as reunioes
		Reuniao r;
		String mes;
		for (int k = 1; k <= 12; k++) {
			
			mes = "" + k;
			if (mes.length() == 1) mes = "0" + mes;
			r = new Reuniao("14" + mes + "2010","5:40");
			
			for (float i = 0; i < Math.random(); i = i + 0.02f) r.addObreiroListaDePresenca(new Obreiro());
			
			tg.addReuniao(r);
		}
		
		tg.initComponentsTest();
		
	}
}
