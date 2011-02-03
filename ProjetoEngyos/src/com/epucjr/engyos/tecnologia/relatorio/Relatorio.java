package com.epucjr.engyos.tecnologia.relatorio;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;

import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperRunManager;



public class Relatorio{





/* Gera Relatorio e visualiza-o */
public void geraRelatorioPDF() throws JRException, Exception{

 /*
=======
 //
>>>>>>> /tmp/Relatorio.java~other.0OJXU-
  
  
  BufferedImage imagem = ImageIO.read(new File("/home/notroot/Desktop/grafico_exemplo.bmp"));
  
  JasperReport jr;
  
  //JasperDesign jasperDesign = JasperManager.loadXmlDesign(G:\TesteJasper\relatorioCh); 
  
jr = JasperCompileManager.compileReport("/home/notroot/Desktop/Calebe.jrxml");
  
  
  

 
  Map parameters = new HashMap();
 parameters.put("imagem",imagem);
  

 
 

  //String arquivoJasper = "/home/notroot/Desktop/Calebe.jasper";
JasperPrint impressao = JasperFillManager.fillReport(jr, parameters);




JasperExportManager.exportReportToPdfFile(impressao, "/home/notroot/Desktop/Teste");

System.out.println("salci-fufu");

//JasperRunManager.runReportToPdfFile(arquivoJasper,parameters,jrRS);

//JasperViewer.viewReport( impressao, false );
 * */
}
}

