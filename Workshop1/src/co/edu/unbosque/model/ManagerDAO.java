package co.edu.unbosque.model;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import co.edu.unbosque.model.Pet;

public class ManagerDAO {

	private FileReader archivoCSV;
	private CSVReader csvReader;

	ArrayList<Pet> petsList = new ArrayList<Pet>();
	private Pet pet;

	public ManagerDAO() {
		this.pet = new Pet();
	}

	public String uploadData(String ruta) {
		int datosNoLeidos = 0;
		String mensaje = "";
		try {
			archivoCSV = new FileReader(ruta);
			CSVParser conPyC = new CSVParserBuilder().withSeparator(';').build();
			csvReader = new CSVReaderBuilder(archivoCSV).withCSVParser(conPyC).build();
			String[] f = null;
			while ((f = csvReader.readNext()) != null) {
				try {
					long micro = Long.parseLong(f[0]);
					this.pet.setMicrochip(micro);
					if (!f[1].equals("NO IDENTIFICADO") && f[1].equals("CANINO")) {
						this.pet.setSpecies("C");
					} else if (!f[1].equals("NO IDENTIFICADO") && f[1].equals("FELINO")) {
						this.pet.setSpecies("F");
					} else {
						throw new SpeciesUnknow();
					}
					if (f[2].equals("MACHO")) {
						this.pet.setSex("M");
					} else {
						this.pet.setSex("H");
					}
					if (f[3].equals("MINIATURA")) {
						this.pet.setSize("MI");
					} else if (f[3].equals("PEQUE�O")) {
						this.pet.setSize("P");
					} else if (f[3].equals("MEDIANO")) {
						this.pet.setSize("M");
					} else if (f[3].equals("GRANDE") || f[3].equals("MUY GRANDE") || f[3].equals("GIGANTE")) {
						this.pet.setSize("G");
					}
					Boolean dangerous = false;
					if (f[4].equals("SI")) {
						dangerous = true;
						this.pet.setPotentDangerous(dangerous);
					} else {
						this.pet.setPotentDangerous(dangerous);
					}
					if (!f[5].equals("")) {
						this.pet.setNeighborhood(f[5]);
					} else {
						throw new EmptyAttributeException();
					}
					this.petsList.add(
							new Pet("No ASIGNADO", this.pet.getMicrochip(), this.pet.getSpecies(), this.pet.getSex(),
									this.pet.getSize(), this.pet.isPotentDangerous(), this.pet.getNeighborhood()));
				} catch (SpeciesUnknow e) {
					datosNoLeidos++;
				} catch (NumberFormatException e) {
					datosNoLeidos++;
				} catch (EmptyAttributeException e) {
					datosNoLeidos++;
				}
			}
			archivoCSV.close();
			csvReader.close();
			mensaje = "El proceso de carga del archivo ha finalizado";
		} catch (IOException e) {
			mensaje = "El proceso de carga del archivo no se ha realizado correctamente";
		} catch (CsvValidationException e) {
			mensaje = "Hubo un error en el cvs";
		}
		return mensaje + "\n# de datos excluidos: " + datosNoLeidos;
	}
	/**
	 * Constructor Manager class
	 */

	public String findByMicrochip(long microchip){
		String m="";
		for(int i=0;i<petsList.size();i++) {
			if(microchip==(petsList.get(i).getMicrochip())) {
				m = "ID: " + this.petsList.get(i).getId() + "\nSpecies: " + this.petsList.get(i).getSpecies() + 
						"\nGender: " + this.petsList.get(i).getSex() + "\nSize: " + this.petsList.get(i).getSize() + 
						"\nPotentially Dangerous: " + this.petsList.get(i).isPotentDangerous() + "\nNeighborhood: " + 
						this.petsList.get(i).getNeighborhood();
				break;
			}else {
				m= microchip+"Mascota no encontrada ";
			}
		}
		return m;
	}


	public String countBySpecies(String species) {
		int cont=0;
		int contador=0;
		String m= "";
		for(int i=0; i<petsList.size();i++) {
			if (species.equals(this.petsList.get(i).getSpecies())){
				cont ++;
			}else{
				for (int j=0;j<petsList.size();i++) {
					if (("CANINO").equals(this.petsList.get(i).getSpecies())) {
						cont ++;
					}
					if (("FELINO").equals(this.petsList.get(i).getSpecies())) {
						contador ++;
					}
				}
				m= "Total de CANINOS: "+cont+ "\nTotal de Felinos: "+contador;
			}
		}
		return "Total de "+ species + ": "+cont;
	}

	
	public String countByNeighborhood(String neighborhood,String species) {
		int cont=0;
		int contador=0;
		String m= "";
		for(int i=0; i<petsList.size();i++) {
			if (neighborhood.equals(this.petsList.get(i).getNeighborhood())&& species.equals(this.petsList.get(i).getSpecies())){
				cont ++;
			}else{
			}
		}
		return "Total de " +species +" en la localidad de: "+ neighborhood+ "es: "+cont;
	}
}



