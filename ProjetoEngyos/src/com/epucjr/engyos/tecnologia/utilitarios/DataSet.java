package com.epucjr.engyos.tecnologia.utilitarios;

import org.jfree.data.category.DefaultCategoryDataset;

import com.epucjr.engyos.dominio.modelo.Reuniao;

/*
 * O DataSet cria uma tabela para o grafico
 */
public class DataSet {
	
	private DefaultCategoryDataset dcd;
	private int modo; //modo, 0 sem legenda com uma cor, 1 com legenda multicores
	private static final String[] mes = {"Janeiro", "Fevereiro", "Mar"+(char)0x00e7+"o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outobro", "Novembro", "Dezembro"};
	
	public DataSet(int modo) {
		dcd = new DefaultCategoryDataset();
		this.modo = modo;
		
		if (modo == 1) {
			for (int i = 1; i < 13; i++) {
				dcd.addValue(0, mes[i-1], "");
			}
		} else if (modo == 0) {
			for (int i = 1; i < 13; i++) {
				dcd.addValue(0, "", mes[i-1]);
			}
		}
	}

	public void addReuniao(Reuniao r) {
		String mes;
		try {
			int i = Integer.parseInt(r.getMes());
			mes = this.mes[i-1];
		} catch (NumberFormatException ex) {
			mes = r.getMes();
		}
		
		if (modo == 1) {
			dcd.addValue(r.getQtdObreirosListaDePresenca(), mes, "");
		} else if (modo == 0) {
			dcd.addValue(r.getQtdObreirosListaDePresenca(), "", mes);
		}
	}
		
	public DefaultCategoryDataset getDcd() {
		return dcd;
	}

	protected void setDcd(DefaultCategoryDataset dcd) {
		this.dcd = dcd;
	}

}
