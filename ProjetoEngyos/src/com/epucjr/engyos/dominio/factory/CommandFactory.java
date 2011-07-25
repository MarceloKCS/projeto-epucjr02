package com.epucjr.engyos.dominio.factory;

import java.util.HashMap;

import com.epucjr.engyos.aplicacao.controle.Command;
import com.epucjr.engyos.aplicacao.controle.TestCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionAdminRegisterPageLoaderCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionAdministradorEditCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionAdministradorEditPageLoaderCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionAdministradorFindAjaxCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionAdministradorFindCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionAdministradorRegisterCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionAdministradorRemoverCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionBuscaPageLoaderCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionCalendarReuniaoAgendaRequestAjaxCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionCommandCapturarDigital;
import com.epucjr.engyos.aplicacao.webcontrole.ActionCongregacaoEditCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionCongregacaoEditPageLoaderCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionCongregacaoFindAjaxCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionCongregacaoFindCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionCongregacaoRegisterCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionCongregacaoRegisterPageLoaderCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionCongregacaoRemoverCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionGeradorDeRelatorioCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionLogin;
import com.epucjr.engyos.aplicacao.webcontrole.ActionLogoutCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionMainPageLoader;
import com.epucjr.engyos.aplicacao.webcontrole.ActionMarcarPresencaDigital;
import com.epucjr.engyos.aplicacao.webcontrole.ActionMarcarPresencaPeloCPFCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionMarcarPresencaSenha;
import com.epucjr.engyos.aplicacao.webcontrole.ActionObreiroEditCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionObreiroEditPageLoaderCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionObreiroFindAjaxCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionObreiroFindCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionObreiroRegisterReuniaoInAddAjaxCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionObreiroRegisterCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionObreiroRegisterPageLoader;
import com.epucjr.engyos.aplicacao.webcontrole.ActionObreiroRemoverCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionObterListaDeCongregacaoAjaxCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionObterListaDeObreiroAjaxCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionReuniaoEditCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionReuniaoEditPageLoaderCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionReuniaoFindAjaxCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionReuniaoFindCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionReuniaoRegisterCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionReuniaoRegisterPageLoader;
import com.epucjr.engyos.aplicacao.webcontrole.ActionReuniaoRemoverCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionReuniaoSessionAppletResponse;
import com.epucjr.engyos.aplicacao.webcontrole.ActionReuniaoSessionFinishCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionReuniaoSessionStartCommand;
import com.epucjr.engyos.aplicacao.webcontrole.ActionReuniaoStartPageLoader;


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
		mapaCommand.put("reuniao_formload", new ActionReuniaoRegisterPageLoader());
		mapaCommand.put("congregacao_formload", new ActionCongregacaoRegisterPageLoaderCommand());
                mapaCommand.put("administrador_formload", new ActionAdminRegisterPageLoaderCommand());
                mapaCommand.put("administrador_register", new ActionAdministradorRegisterCommand());
		mapaCommand.put("reuniao_register", new ActionReuniaoRegisterCommand());
		mapaCommand.put("obreiro_register", new ActionObreiroRegisterCommand());
		mapaCommand.put("congregacao_register", new ActionCongregacaoRegisterCommand());
                mapaCommand.put("obreiro_editformload", new ActionObreiroEditPageLoaderCommand());
                mapaCommand.put("obreiro_editer", new ActionObreiroEditCommand());
                mapaCommand.put("obreiro_editformload", new ActionObreiroEditPageLoaderCommand());
                mapaCommand.put("administrador_editer", new ActionAdministradorEditCommand());
                mapaCommand.put("administrador_editformload", new ActionAdministradorEditPageLoaderCommand());
                mapaCommand.put("congregacao_editformload", new ActionCongregacaoEditPageLoaderCommand());
                mapaCommand.put("congregacao_editer", new ActionCongregacaoEditCommand());
                mapaCommand.put("reuniao_editformload", new ActionReuniaoEditPageLoaderCommand());
                mapaCommand.put("reuniao_editer", new ActionReuniaoEditCommand());
                mapaCommand.put("buscar_administrador", new ActionAdministradorFindCommand());
		mapaCommand.put("buscar_obreiro", new ActionObreiroFindCommand());
		mapaCommand.put("buscar_congregacao", new ActionCongregacaoFindCommand());
		mapaCommand.put("buscar_reuniao", new ActionReuniaoFindCommand());
		mapaCommand.put("RegistrarDigital", new ActionCommandCapturarDigital());
                mapaCommand.put("iniciar_reuniao", new ActionReuniaoStartPageLoader());
                mapaCommand.put("gerar_relatorio", new ActionGeradorDeRelatorioCommand());
                mapaCommand.put("action_login", new ActionLogin());
                mapaCommand.put("action_logout", new ActionLogoutCommand());
                
                //Commands usados pelo servlet que trata requisisoes ajax
                mapaCommand.put("marcar_presenca_cpf", new ActionMarcarPresencaPeloCPFCommand());
                mapaCommand.put("iniciar_session_reuniao", new ActionReuniaoSessionStartCommand());
                mapaCommand.put("encerrar_session_reuniao", new ActionReuniaoSessionFinishCommand());
                mapaCommand.put("marcar_presenca", new ActionMarcarPresencaDigital());
                mapaCommand.put("verificar_reuniao_status", new ActionReuniaoSessionAppletResponse());
                mapaCommand.put("ajax_get_listacongregacao", new ActionObterListaDeCongregacaoAjaxCommand());
                mapaCommand.put("ajax_get_administradorsearch", new ActionAdministradorFindAjaxCommand());
                mapaCommand.put("ajax_get_obreirosearch", new ActionObreiroFindAjaxCommand());
                mapaCommand.put("ajax_administrador_remover", new ActionAdministradorRemoverCommand());
                mapaCommand.put("ajax_obreiro_remover", new ActionObreiroRemoverCommand());
                mapaCommand.put("ajax_obreiro_register", new ActionObreiroRegisterReuniaoInAddAjaxCommand());
                mapaCommand.put("ajax_get_listaobreiro", new ActionObterListaDeObreiroAjaxCommand());
                mapaCommand.put("ajax_congregacao_remover", new ActionCongregacaoRemoverCommand());
                mapaCommand.put("ajax_get_congregacaosearch", new ActionCongregacaoFindAjaxCommand());
                mapaCommand.put("ajax_reuniao_remover", new ActionReuniaoRemoverCommand());
                mapaCommand.put("ajax_get_reuniaosearch", new ActionReuniaoFindAjaxCommand());
                mapaCommand.put("ajax_get_reuniaocalendar", new ActionCalendarReuniaoAgendaRequestAjaxCommand());

                //Page Loader
                mapaCommand.put("busca_loader", new ActionBuscaPageLoaderCommand());
                mapaCommand.put("page_loader", new ActionMainPageLoader());
		//marcar presenca
		//mapaCommand.put("marcarPresencaDigital", new ActionMarcarPresencaDigital());
		mapaCommand.put("marcarPresencaSenha", new ActionMarcarPresencaSenha());
	}
}