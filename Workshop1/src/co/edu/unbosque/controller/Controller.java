package co.edu.unbosque.controller;

import co.edu.unbosque.model.ManagerDAO;
import co.edu.unbosque.view.View;

public class Controller {

	private ManagerDAO mDAO;
	private View v;
	
	public Controller() {
		this.mDAO = new ManagerDAO();
		this.v = new View();
		funcionar();
	}
	
	public void funcionar() {
		this.v.optionMenu();
	}
}
