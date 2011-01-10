package com.epucjr.engyos.tecnologia.utilitarios;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.table.TableModel;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

public class Grafico {

	/**
	 * @param titleFont, Fonte do titulo(null default)
	 * @param titulo, Titulo
	 * @param textox, Texto em x ("")
	 * @param textoy, Texto em y ("")
	 * @param width, Tamanho horizontal
	 * @param height, Tamanho vertical
	 * @param scaledWidth, Escala horizontal( = width)
	 * @param scaledHeight, Escala vertical( = height)
	 * @param dataset, Tabela de dados
	 * @param alpha, Transparencia das barras(0.75f default)
	 * @param cor, Cor para grafico modo 0(null default = RED)
	 * @param modo, 0 sem legenda com uma cor, 1 com legenda multicores
	 * @return BufferedImage
	 */
	public static BufferedImage gerarGrafico3D(Font titleFont, String titulo, String textox, String textoy,int width, int height, int scaledWidth, int scaledHeight, DefaultCategoryDataset dataset, float alpha, Color cor, int modo) {
		CategoryAxis categoryAxis = new CategoryAxis3D(textox);
		ValueAxis valueAxis = new NumberAxis3D(textoy);
		valueAxis.setStandardTickUnits(NumberAxis3D.createIntegerTickUnits());

		BarRenderer3D renderer = new BarRenderer3D();

		CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
		plot.setOrientation(PlotOrientation.VERTICAL);
		plot.setForegroundAlpha(alpha);
		
		Font f = titleFont;
		
		if (f == null) {
			f = JFreeChart.DEFAULT_TITLE_FONT;
		}
		JFreeChart chart = null;
		if (modo == 0) chart = new JFreeChart(titulo, f, plot, false);
		if (modo == 1) {
			chart = new JFreeChart(titulo, f, plot, true);
			chart.getLegend().setPosition(RectangleEdge.RIGHT);
		}
		new StandardChartTheme("JFree").apply(chart);
		
		//cor
		if (cor != null) renderer.setSeriesPaint(0, cor);

		//rotate
		if (modo == 0) {
			CategoryAxis domainAxis = plot.getDomainAxis();
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		}
		
		BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics2D = scaledImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(chart.createBufferedImage(width, height), 0, 0, scaledWidth, scaledHeight, null);
		
		graphics2D.dispose();
		
		return scaledImage;
	}
	
	/**
	 * @param titleFont, Fonte do titulo(null default)
	 * @param titulo, Titulo
	 * @param textox, Texto em x
	 * @param textoy, Texto em y
	 * @param width, Tamanho horizontal
	 * @param height, Tamanho vertical
	 * @param scaledWidth, Escala horizontal( = width)
	 * @param scaledHeight, Escala vertical( = height)
	 * @param dataset, Tabela de dados
	 * @param alpha, Transparencia das barras(0.75f default)
	 * @param cor, Cor para grafico modo 0(null default = RED)
	 * @param modo, 0 sem legenda com uma cor, 1 com legenda multicores
	 * @return BufferedImage
	 */
	public static BufferedImage gerarGrafico2D(Font titleFont, String titulo, String textox, String textoy,int width, int height, int scaledWidth, int scaledHeight, DefaultCategoryDataset dataset, float alpha, Color cor, int modo) {
		CategoryAxis categoryAxis = new CategoryAxis(textox);
		ValueAxis valueAxis = new NumberAxis(textoy);
		valueAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		BarRenderer renderer = new BarRenderer();

		CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
		plot.setOrientation(PlotOrientation.VERTICAL);
		plot.setForegroundAlpha(alpha);
		
		Font f = titleFont;
		
		if (f == null) {
			f = JFreeChart.DEFAULT_TITLE_FONT;
		}
		JFreeChart chart = null;
		if (modo == 0) chart = new JFreeChart(titulo, f, plot, false);
		if (modo == 1) chart = new JFreeChart(titulo, f, plot, true);
		new StandardChartTheme("JFree").apply(chart);
		
		//cor
		if (cor != null) renderer.setSeriesPaint(0, cor);

		//rotate
		if (modo == 0) {
			CategoryAxis domainAxis = plot.getDomainAxis();
			domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		}
		
		BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics2D = scaledImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(chart.createBufferedImage(width, height), 0, 0, scaledWidth, scaledHeight, null);
		
		graphics2D.dispose();
		
		return scaledImage;
	}
}
