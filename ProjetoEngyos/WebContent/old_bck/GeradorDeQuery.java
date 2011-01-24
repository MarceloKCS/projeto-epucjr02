package com.epucjr.engyos.tecnologia.ferramentas;


public class GeradorDeQuery {


	public String verificaNome(String nome){
		String retorno;
		if(nome.equals("")){
			retorno="";
		}
		else{

			retorno = "+ AND nome:"+nome+"*";
		}
		return retorno;
	}


	public String verificaCargo(String cargo){
		String retorno;
		if(cargo.equals("")){
			retorno="";
		}
		else{
			retorno = "+ AND cargo:"+cargo ;
		}
		return retorno;
	}
	public String verificaCpf(String cpf){
		String retorno;
		if(cpf.equals("")){
			retorno="";
		}
		else{
			retorno = "+ AND cpf:" + cpf;
		}
		return retorno;
	}

	public String verificaCongregacaoNome(String congragacaoNome){
		String retorno;
		if(congragacaoNome.equals("")){
			retorno="";
		}
		else{
			retorno = "+ AND nome:" + congragacaoNome+ "*" ;
		}
		return retorno;
	}
	
	public String verificaEndereco(String endereco ){
		String retorno;
		if(endereco.equals("")){
			retorno="";
		}
		else{

			retorno = "+ AND endereco:"+endereco ;
		}
		return retorno;
	}


	
	public String verificaCongregacao(String congragacao){
		String retorno;
		if(congragacao.equals("")){
			retorno="";
		}
		else{
			retorno = "+ AND congregacao:" + congragacao ;
		}
		return retorno;
	}
	
	public String verificaData(String data){
		String retorno;
		if(data.equals("")){
			retorno="";
		}
		else{
			retorno = "+ AND data:" + data ;
		}
		return retorno;
	}
	
	public String verificaHora(String hora ){
		String retorno;
		if(hora.equals("")){
			retorno="";
		}
		else{

			retorno = "+ AND hora:"+hora ;
		}
		return retorno;
	}


	
	public String verificaLocal(String local){
		String retorno;
		if(local.equals("")){
			retorno="";
		}
		else{
			retorno = "+ AND local:" + local ;
		}
		return retorno;
	}
	
	







	//o metodo abaixo elimina o ultimo AND para que a String final fique com a cara de um comando de SQL

	public String removerAndFinal(String queryFinal){
		String retorno = "Funciona legal";
		if(queryFinal.equals("")){
		}
		else{
			int tamanho;
			tamanho = queryFinal.length();

			retorno = queryFinal.substring(5, tamanho);
		}

		return retorno;
	}

}