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
		boolean bandera = false;
		while (!bandera) {
			try {
				int menu = this.v.readInt(this.v.optionMenu());
				System.out.println(menu);
				switch (menu) {
				case 1:
					this.v.printMessage(this.mDAO.uploadData(this.v.chooserFile()));
					break;
				case 2:
					if (this.mDAO.getPetsList().isEmpty()) {
						this.v.printMessage("Debes seleccionar la opcion '1' primero");
					} else {
						this.v.printMessage(this.mDAO.assingID());
					}
					break;
				case 3:
					if (this.mDAO.getPetsList().isEmpty()) {
						this.v.printMessage("Debes seleccionar la opcion '1' primero");
					} else {
						long micro = Long.parseLong(this.v.readString("Ingrese el microchip que quiere ver"));
						this.v.printMessage(this.mDAO.findByMicrochip(micro));
					}
					break;
				case 4:
					if (this.mDAO.getPetsList().isEmpty()) {
						this.v.printMessage("Debes seleccionar la opcion '1' primero");
					} else {
						String opciosS = this.v.readString("Selecciona el numero de la opcion que quieras elegir\n"
								+ "1)Felinos y Caninos\n2)Felinos\n3)Caninos");
						this.v.printMessage(this.mDAO.countBySpecies(opciosS));
					}
					break;
				case 5:
					if (this.mDAO.getPetsList().isEmpty()) {
						this.v.printMessage("Debes seleccionar la opcion '1' primero");
					} else {
						String n = this.v.readString(
								"Escriba 1 de los siguientes vecindarios\n" + "A. NARINO\n" + "B. UNIDOS\n" + "BOSA\n"
										+ "C. BOLIVAR\n" + "CHAPINERO\n" + "ENGATIVA\n" + "FONTIBON\n" + "KENNEDY\n"
										+ "LA CANDELARIA\n" + "LOS MARTIRES\n" + "MUNICIPIOS ALEDAÑOS A BOGOTA D.C.\n"
										+ "P. ARANDA\n" + "R. URIBE\n" + "SAN CRISTOBAL\n" + "SANTA FE\n" + "SUBA\n"
										+ "SUMAPAZ\n" + "TEUSAQUILLO\n" + "TUNJUELITO\n" + "USAQUEN\n" + "USME");
						this.v.printMessage(this.mDAO.countByNeighborhood(n.toUpperCase()));
					}
					break;
				case 6:
					// falta el requerimiento 6, tengo sueño jajaja
					break;
				case 7:
					bandera = true;
					this.v.printMessage("Gracias por consultar CIUDADANOS DE 4 PATAS\nADIOS :)");
					break;
				default:
					this.v.printMessage("INGRESASTE UNA OPCION INVALIDA ('1','2','3','4','5','6' o '7')");
				}
			} catch (NumberFormatException e) {
				this.v.printMessage("Debes ingresar un numero");
			}

		}
	}
}
