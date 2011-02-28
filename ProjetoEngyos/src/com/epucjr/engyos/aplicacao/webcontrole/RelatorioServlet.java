package com.epucjr.engyos.aplicacao.webcontrole;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epucjr.engyos.dominio.crud.BuscaAvancada;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.relatorio.Relatorio;
import com.epucjr.engyos.tecnologia.utilitarios.DataSet;
import com.epucjr.engyos.tecnologia.utilitarios.Grafico;

/**
 * Servlet implementation class RelatorioServlet
 */
public class RelatorioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BuscaAvancada busca;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RelatorioServlet() {
        super();
        busca =  new BuscaAvancada();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String ano = request.getParameter("ano");
//
//		//TODO
//		List<Reuniao> listaReuniao = busca.buscarReuniao("0000"+ano, "", "");
//		//(intervalo)
//
//		//TODO
//		int modo = 0;
//		DataSet dataset = new DataSet(modo);
//		//modo 0 ou 1 (ver teste)
//
//		for (Reuniao r: listaReuniao) {
//			dataset.addReuniao(r);
//		}
//
//		BufferedImage bi = Grafico.gerarGrafico3D(null, "Gráfico "+ano, "", "", 800, 600, 800, 600, dataset.getDcd(), 0.75f, null, modo);
//
//		//TODO
//		Relatorio relatorio = new Relatorio();
//		relatorio.geraRelatorioPDF(bi);
//		//n implementado
	}

}
