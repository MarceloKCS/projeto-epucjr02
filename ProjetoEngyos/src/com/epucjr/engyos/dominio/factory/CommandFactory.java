package com.epucjr.engyos.dominio.factory;

import java.util.HashMap;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.controle.TestCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionCommandCapturarDigital;
import com.epucjr.engyos.aplicacao.webcontrole.ActionObreiroRegisterCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionObreiroRegisterPageLoader;


public class CommandFactory {

	private HashMap<String, Command> mapaCommand;

	public CommandFactory() {
		mapaCommand = new HashMap<String, Command>();
		//Colocar comandos para teste...
		//Ao inves de um arquivo de config carrego aqui os comandos
		this.carregarCommandList();
	}

	public Command getCommand(String comando) {
		if(mapaCommand.containsKey(comando)){
			return mapaCommand.get(comando);
		}
		else return mapaCommand.get("default");
		
	}

	public void addComando(String comando, Command command){
		mapaCommand.put(comando, command);
	}

	public void carregarCommandList(){		
		mapaCommand.put("default", new TestCommand());
		mapaCommand.put("teste", new TestCommand());
		mapaCommand.put("obreiro_formload", new ActionObreiroRegisterPageLoader());
		mapaCommand.put("obreiro_register", new ActionObreiroRegisterCommand());
		mapaCommand.put("RegistrarDigital", new ActionCommandCapturarDigital());
	}

}
