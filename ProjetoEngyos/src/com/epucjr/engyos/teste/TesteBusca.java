package com.epucjr.engyos.teste;

import java.util.ArrayList;
import java.util.List;

import com.epucjr.engyos.dominio.crud.BuscaAvancada;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.Reuniao;

public class TesteBusca {

	public static void main(String[] args) {
		BuscaAvancada ba = new BuscaAvancada();

		ArrayList<Obreiro> buscaObreiro;
		ArrayList<Congregacao> buscaCongregacao;
		ArrayList<Reuniao>buscaReuniao;
		List<Congregacao> listarCongregacao;

		buscaObreiro = new ArrayList<Obreiro>(ba.buscarObreiros("", "", "", ""));
		//buscaCongregacao = new ArrayList<Congregacao>(ba.buscarCongregacao("Cotia" , ""));
		//buscaReuniao = new ArrayList<Reuniao>(ba.buscarReuniao("", "", ""));

		/******************************************************************************
		 *  
		 *  KOBAYASHINHU S2S2 AQUI ESTÁ A PARTE DE LISTAR AS CONGREGAÇÕES <3
		 *  ASS: CAMPOS.
		 *  
		 ******************************************************************************/

		//listarCongregacao = ba.buscarTodasCongregacoes();
		//System.out.println("TAMANHO "+ listarCongregacao.size());

		//for(Congregacao congregacao : listarCongregacao){System.out.println("congregacao de nome:" + congregacao.getNome());}


		//showList(buscaObreiro);
		//showList(buscaCongregacao);
		//showList(buscaReuniao);

		//}
		/******************************************************************************
		 *  
		 * A BUSCA POR OBREIRO COM A LETRA 'C' ESTÁ SENDO EFETUADA AQUI EM BAIXO
		 * FUNCIONANDO PERFEITAMENTE, CASO ALGUÉM QUEIRA MEXER, FAVOR COMENTAR.!! 
		 *  
		 ******************************************************************************/
		
		for(Obreiro obreiro : buscaObreiro){System.out.println("obreiro de nome:" + obreiro.getNome());}
		
		//for(Congregacao congregacao : buscaCongregacao){System.out.println("Nome da congregacao:" + congregacao.getNome());}
		
		//for(Reuniao reuniao : buscaReuniao){System.out.println("Dia da Reuniao:" + reuniao.getData());}
		/******************************************************************************
		 *  
		 * POR FAVOR ESPECIFIQUEM A UTILIDADE DESTE CÓDIGO ABAIXO!!!! 
		 *  
		 ******************************************************************************/

		/*public static void showList(ArrayList list) {
		String tipo = list.get(0).getClass().getName();
		StringTokenizer st = new StringTokenizer(tipo, ".");
		while(st.hasMoreTokens()) {
			tipo = st.nextToken();
		}
		String s = "Lista de "+tipo+" ( "+list.size()+" ):";
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof Obreiro) s = s + '\n' + ((Obreiro)list.get(i)).getNome();
			if (list.get(i) instanceof Congregacao) s = s + '\n' + ((Congregacao)list.get(i)).getEndereco();
		}
		JOptionPane.showMessageDialog(null, s);
	}*/

	}
}
