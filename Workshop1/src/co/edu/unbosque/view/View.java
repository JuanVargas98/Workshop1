package co.edu.unbosque.view;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class View {

	public String chooserFile() {
		String u = "";
		try {
			JOptionPane.showMessageDialog(null, "Busque el archivo .csv, que contiene la base de datos");
			JFileChooser filechooser = new JFileChooser();
			filechooser.showSaveDialog(filechooser);
			File ubicacion = filechooser.getSelectedFile();
			u = ubicacion.toString();
		} catch (NullPointerException e) {
			u = "Hubo un problema al obtener la ruta del archivo .csv";
		}
		return u;
	}

	public void printMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public String readString(String message) {
		return JOptionPane.showInputDialog(null, message);
	}

	public int readInt(String message) {
		String datoStr = JOptionPane.showInputDialog(null, message);
		int datoNum = Integer.parseInt(datoStr);
		return datoNum;
	}

	public String optionMenu() {
		String men = "BIENVENIDO A CIUDADANOS DE 4 PATAS\n" + "Menu de Opciones a realizar. Marque el numero\n"
				+ "1) Leer el archivo\n2) Generar ID\n3)Buscar microchip\n"
				+ "4)Contar Especies\n5)Contar vecindarios\n6)Busqueda Avanzada\n7)SALIR";
		return men; 
	}
}
