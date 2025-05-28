package com.meuprojeto;

import com.meuprojeto.repository.InRepositorio;
import com.meuprojeto.repository.Repositorio;
import com.meuprojeto.service.ColetarDados;
import com.meuprojeto.service.ConsultarDados;
import com.meuprojeto.view.MenuPrincipal;

public class Main {
	public static void main(String[] args) {
		InRepositorio irepo = new Repositorio();

		ColetarDados coletarService = new ColetarDados(irepo);
		ConsultarDados consultarService = new ConsultarDados(irepo);

		MenuPrincipal menuP = new MenuPrincipal(coletarService, consultarService);
		menuP.infoOpcoes();
	}
}