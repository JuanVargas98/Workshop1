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
	
}
