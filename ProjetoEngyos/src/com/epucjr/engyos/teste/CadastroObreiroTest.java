package com.epucjr.engyos.teste;

import javax.swing.JOptionPane;

import com.epucjr.engyos.dominio.crud.ValidadorDeFormularioDeObreiro;
import com.epucjr.engyos.dominio.modelo.Congregacao;
import com.epucjr.engyos.dominio.modelo.Identificacao;
import com.epucjr.engyos.dominio.modelo.Obreiro;
import com.epucjr.engyos.tecnologia.persistencia.DataAccessObjectManager;

public class CadastroObreiroTest {
	
	public static void main(String[] args){
		
		int opcao = 1;
		
		opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite uma opção"));
		
		switch(opcao){
					case 1:
						JOptionPane.showMessageDialog(null, "Cadastro Congregação");
						CadastroObreiroTest.cadastrarCongregacaoTest();
						break;
						
					case 2:
						JOptionPane.showMessageDialog(null, "Cadastro Obreiro");
						CadastroObreiroTest.cadastrarObreiroTest();
						break;
					case 3:
						JOptionPane.showMessageDialog(null, "Validador Cadastro Obreiro");
						int opcaoErro = Integer.parseInt(JOptionPane.showInputDialog("Digite uma opção de 1 a 5 para verificar erros"));
						CadastroObreiroTest.validadorObreiroTest(opcaoErro);
		}				
		
		//Encerra o Programa
		System.exit(0);
	}
	
	public static void cadastrarCongregacaoTest(){
		Congregacao congregacao = new Congregacao("Congregação de Santo Amaro", "Avenida Santo Amaro nº. 548");
		DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
		dataAccessObjectManager.persistirObjeto(congregacao);
	}
	
	public static void cadastrarObreiroTest(){
		
		DataAccessObjectManager dataAccessObjectManager = new DataAccessObjectManager();
		
		Congregacao congregacao = dataAccessObjectManager.obterCongregacao(1);
		
		Identificacao identificacao = new Identificacao("0x0123456789ABCDEFG", "mano");
		
		Obreiro obreiro = new Obreiro("Michel Alves Silva", "Faxineiro", "51427777276", congregacao, identificacao);
		
		dataAccessObjectManager.persistirObjeto(obreiro);
		
		if(dataAccessObjectManager.isOperacaoEfetuada()){
			System.out.println("msg Register = " + dataAccessObjectManager.getMensagemStatus());
		}
		
		
	}
	
	public static void validadorObreiroTest(int opcaoErro){
		ValidadorDeFormularioDeObreiro validadorDeFormularioDeObreiro = new ValidadorDeFormularioDeObreiro();
		
		String nome = "Michel Alves Silva";
		String cargo = "Faxineiro";
		String cpf = "51427777276";
		String congregacao = "Congregação de Santo Amaro";
		//String senha = "";
		//String digital = "";
		
		
		switch(opcaoErro){
						case 1:
							 nome = "";
							 cargo = "Faxineiro";
							 cpf = "51427777276";
							 congregacao = "Congregação de Santo Amaro";
							break;
						case 2:
							 nome = "Michel Alves Silva";
							 cargo = "";
							 cpf = "51427777276";
							 congregacao = "Congregação de Santo Amaro";
							break;
						case 3:
							 nome = "Michel Alves Silva";
							 cargo = "Faxineiro";
							 cpf = "";
							 congregacao = "Congregação de Santo Amaro";
							break;
						case 4: 
							 nome = "Michel Alves Silva";
							 cargo = "Faxineiro";
							 cpf = "51427777276";
							 congregacao = "";
							break;
							
						case 5:
							 nome = "";
							 cargo = "";
							 cpf = "";
							 congregacao = "";
							//String senha = "";
							//String digital = "";
							 break;
		
		}
		
		
		
		validadorDeFormularioDeObreiro.verificarCamposValidos(nome, cpf, cargo, congregacao);
		if(validadorDeFormularioDeObreiro.verificarCampoComErro("Nome")){
			System.out.println(validadorDeFormularioDeObreiro.obterCampoComErro("Nome"));
		}
		
		if(validadorDeFormularioDeObreiro.verificarCampoComErro("Cpf")){
			System.out.println(validadorDeFormularioDeObreiro.obterCampoComErro("Cpf"));
		}
		
		if(validadorDeFormularioDeObreiro.verificarCampoComErro("Cargo")){
			System.out.println(validadorDeFormularioDeObreiro.obterCampoComErro("Cargo"));
		}
		
		if(validadorDeFormularioDeObreiro.verificarCampoComErro("Congregacao")){
			System.out.println(validadorDeFormularioDeObreiro.obterCampoComErro("Congregacao"));
		}
		
		if(validadorDeFormularioDeObreiro.isFormularioValido()){
			System.out.println("Formulário Válido");
		}
		else{
			System.out.println("Foram Encontrados erros no preenchimento do formulário");
		}
		
	}

}
