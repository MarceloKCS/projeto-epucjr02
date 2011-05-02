package com.epucjr.engyos.teste;

import java.util.List;

import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.dominio.modelo.PresencaObreiro;
import com.epucjr.engyos.dominio.modelo.Reuniao;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

public class ObreiroReuniaoPresencaTest {

	public static void main(String[] args){
		ObreiroReuniaoPresencaTest.adicionarObreiroNaLista_Novo();
	}

	public static void insertReuniaoObreiroTest(){
		DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();

		Reuniao reuniao = dataAccessObjectManager.obterReuniao(1);

		Obreiro obreiro = dataAccessObjectManager.obterObreiro("31273800893");

		//Adiciona Presença
		//Presenca listaDePresenca = reuniao.getListaDePresenca();

		//persiste a lista de presença, caso a reunião não tenha lista de presença		
		//listaDePresenca.adicionarObreiroNaLista(obreiro);	
		//dataAccessObjectManager.persistirObjeto(listaDePresenca);

		//Adiciona a lista de presença na reunião e da update
		//reuniao.setListaDePresenca(listaDePresenca);		
		//dataAccessObjectManager.mergeDataObjeto(reuniao);		

		//long idPresenca = reuniao.getListaDePresenca().getIdPresenca();


		//Monta a tabela de relacionamento na mão
		//PresencaObreiroRel presencaObreiro = new PresencaObreiroRel();
		//presencaObreiro.setCpfObreiroFk(obreiro.getCpf());
		//presencaObreiro.setIdPresencaFk(idPresenca);


		//dataAccessObjectManager.persistirObjeto(presencaObreiro);

		

		System.out.println("Status = " + dataAccessObjectManager.getMensagemStatus());
		System.out.println("Obreiro = " + obreiro.getNome());

		System.out.println("Reunião = " + reuniao.getData());

	}
	
	public static void inserirObreiroNaLista_Novo(){
		Reuniao reuniao = new Reuniao("Casa do Cadu", "25/01/2010", "18:07");
		DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
		
		Obreiro obreiro = dataAccessObjectManager.obterObreiro("31273800893");
		Obreiro obreiro2 = dataAccessObjectManager.obterObreiro("45261946972");
		
		reuniao.adicionarObreiroNaListaDePresenca(obreiro);
		reuniao.adicionarObreiroNaListaDePresenca(obreiro2);
		
		dataAccessObjectManager.persistirObjeto(reuniao);
		
		if(dataAccessObjectManager.isOperacaoEfetuada()){
			System.out.println("SucessoMSG = " + dataAccessObjectManager.getMensagemStatus());
		}
		else{
			System.out.println("FracassoMSG = " + dataAccessObjectManager.getMensagemStatus());
		}
		
		
	}
	
	public static void adicionarObreiroNaLista_Novo(){
		
		DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
		Reuniao reuniao = dataAccessObjectManager.obterReuniao(1);
		Obreiro obreiro = dataAccessObjectManager.obterObreiro("29675850507");
		reuniao.adicionarObreiroNaListaDePresenca(obreiro);
		
		
		dataAccessObjectManager.mergeDataObjeto(reuniao);
		
		if(dataAccessObjectManager.isOperacaoEfetuada()){
			System.out.println("SucessoMSG = " + dataAccessObjectManager.getMensagemStatus());
		}
		else{
			System.out.println("FracassoMSG = " + dataAccessObjectManager.getMensagemStatus());
		}
		
		
	}
	
public static void removerObreiroNaLista_Novo(){
		
		DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
		Reuniao reuniao = dataAccessObjectManager.obterReuniao(2);
		//Obreiro obreiro = dataAccessObjectManager.obterObreiro("29675850507");
		//reuniao.adicionarObreiroNaLista(obreiro);
		
		List<PresencaObreiro> listaPresencaObreiro = reuniao.getListaDePresencaObreiro(); 
		int posicao = 0;
		for(PresencaObreiro presencaObreiro : listaPresencaObreiro){
			System.out.println("ObreiroCPF = " + presencaObreiro.getObreiro().getCpf());
			if(presencaObreiro.getObreiro().getCpf().equals("29675850507")){
				System.out.println("Item Encontrado");
				listaPresencaObreiro.remove(posicao);
				break;
			}
			posicao++;
		}
		reuniao.setListaDePresencaObreiro(listaPresencaObreiro);
		
		dataAccessObjectManager.mergeDataObjeto(reuniao);
		
		if(dataAccessObjectManager.isOperacaoEfetuada()){
			System.out.println("SucessoMSG = " + dataAccessObjectManager.getMensagemStatus());
		}
		else{
			System.out.println("FracassoMSG = " + dataAccessObjectManager.getMensagemStatus());
		}
		
		
	}

}
