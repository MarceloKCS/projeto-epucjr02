package com.epucjr.engyos.tecnologia.utilitarios;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

public class GeradorPDF {

	public static void gerarPDF(BufferedImage bi, OutputStream os) {
	
		try {
			Image imagem = Image.getInstance(bi,null,false);
			Document document = new Document();
			PdfWriter.getInstance(document, os);
			document.setPageSize(new Rectangle(bi.getWidth() + document.leftMargin() + document.rightMargin(), bi.getHeight() + document.bottomMargin() + document.topMargin()));
			document.open();
			document.add(imagem);
			document.close();

		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
