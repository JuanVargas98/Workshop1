package co.edu.unbosque.model;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class ManagerDAO {

	private FileReader archivoCSV;
	private CSVReader csvReader;

	ArrayList<Pet> petsList = new ArrayList<Pet>();
	private Pet pet;

	public ManagerDAO() {
		this.pet = new Pet();
	}

	/**
	 * This method is responsible for uploading all the information in the file to a
	 * pet type arrayList
	 */
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
					} else if (f[3].equals("PEQUEï¿½O")) {
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
	 * This method is responsible for assigning the id to each pet in the list,
	 * taking into account all the parameters of their respective data
	 */

	public String assingID() {
		String mensaje = "";
		String mensaje2 = "";
		int contIDRepetidos = 0;
		String d = "";
		for (int i = 0; i < petsList.size(); i++) {
			int numDigitosID = 2;
			String id = String.valueOf(this.petsList.get(i).getMicrochip());
			id = id.substring(id.length() - numDigitosID);
			if (this.petsList.get(i).isPotentDangerous() == true) {
				d = String.valueOf(this.petsList.get(i).isPotentDangerous());
				d = d.substring(0, 1).toUpperCase();
				mensaje = id + "-" + this.petsList.get(i).getSpecies() + this.petsList.get(i).getSex()
						+ this.petsList.get(i).getSize() + d + "\n";
			} else {
				d = String.valueOf(this.petsList.get(i).isPotentDangerous());
				d = d.substring(0, 1).toUpperCase();
				mensaje = id + "-" + this.petsList.get(i).getSpecies() + this.petsList.get(i).getSex()
						+ this.petsList.get(i).getSize() + d + "\n";
			}
			boolean cicloW = false;
			while (!cicloW) {
				try {
					for (int j = i; j > 0; j--) {
						if (mensaje.equals(this.petsList.get(j).getId())) {
							throw new IdentifierExistsException();
						}
					}
					cicloW = true;
					mensaje2 = "El proceso de asignacion de IDs ha terminado";
				} catch (IdentifierExistsException e) {
					numDigitosID++;
					id = String.valueOf(this.petsList.get(i).getMicrochip());
					id = id.substring(id.length() - numDigitosID);
					mensaje = id + "-" + this.petsList.get(i).getSpecies() + this.petsList.get(i).getSex()
							+ this.petsList.get(i).getSize() + d + "\n";
				}
			}
			this.petsList.get(i).setId(mensaje);
			this.pet.setId(mensaje);
			mensaje2 = "El proceso de asignacion de IDs ha terminado";
		}
		return mensaje2;
	}

	/**
	 * Este metodo busca la mascota por su microchip en la lista de mascotas 
	 * @param microchip
	 * @return La mascota con su informacion
	 */
	public String findByMicrochip(long microchip) {
		String m = "";
		for (int i = 0; i < petsList.size(); i++) {
			if (microchip == (petsList.get(i).getMicrochip())) {
				m = this.pet.toString();
				break;
			} else {
				m = microchip + " Mascota no encontrada ";
			}
		}
		return m;
	}

	/**
	 * Este método cuenta las mascotas del arraylist segun la especie
	 * @param species
	 * @return El numero de mascotas por especie, segun el usuario ingrese 
	 */
	public String countBySpecies(String species) {
		int contF = 0;
		int contC = 0;
		String m = "";
		
		for (int i = 0; i < petsList.size(); i++) {
			if(species.equals("1")) {
				if(this.petsList.get(i).getSpecies().equals("F")) {
					contF++;
				}else {
					contC++;
				}
				m = "El numero de Felinos es: " + contF + "y El numero de Caninos es: " + contC;
			}else if(species.equals("2")) {
				if(this.petsList.get(i).getSpecies().equals("F")) {
					contF++;
				}
				m = "El numero de Felinos es: " + contF;
			}else if(species.equals("3")){
				if(this.petsList.get(i).getSpecies().equals("C")) {
					contC++;
				}
				m = "El numero de Caninos es: " + contC;
			}else {
				m = "Debes seleccionar una opcion valida ('1','2' o '3')";
			}
		}
		return m;
	}
	
	/**
	 * Este metodo cuenta las mascotas del arrayList segun la localidad en que viven
	 * @param neighborhood
	 * @return El número de mascotas que hay en la localidad que el usuario ingreso.
	 */

	public String countByNeighborhood(String neighborhood) {
		int contN = 0;
		String m = "";
		for (int i = 0; i < petsList.size(); i++) {
			if (neighborhood.equals(this.petsList.get(i).getNeighborhood())) {
				contN++;
				m = "Total de mascotas registradas en la localidad de " + neighborhood.toUpperCase() + " es: " + contN;
			}
		}
		return m;
	}

	/**
	 * Este metodo busca una mascota por diferentes atributos segun el usuario ingrese
	 * @param species
	 * @param sex
	 * @param size
	 * @param potentDangerous
	 * @return la mascota 
	 */
	public String findByMultipleFields(String species, String sex, String size, String potentDangerous) {
		String mensaje = "";
		String sex2 = sex.substring(0, 1);
		String species2 = species.substring(0, 1);
		String size2 = "";
		String potentDangerous2 = "";
		if (size.equals("MINIATURA")) {
			size2 = size.substring(0, 2);
		} else {
			size2 = size.substring(0, 1);
		}
		if (potentDangerous.equals("SI")) {
			potentDangerous2 = "T";
		} else if (potentDangerous.equals("NO")) {
			potentDangerous2 = "F";
		}
		String id = species2 + sex2 + size2 + potentDangerous2;
		for (int i = 0; i < this.petsList.size(); i++) {
			String id2 = this.petsList.get(i).getId();
			String id3 = "";
			String[] cadena = id2.split("-");
			id3 = cadena[1];
			if (id3.equals(id)) {
				mensaje += this.petsList.get(i).getId() + "\n";
			}
		}
		return mensaje;
	}

	/**
	 * 
	 * @return la lista de mascotas
	 */
	public ArrayList<Pet> getPetsList() {
		return petsList;
	}
	/**
	 * 
	 * @param El arraylist petsList
	 */
	public void setPetsList(ArrayList<Pet> petsList) {
		this.petsList = petsList;
	}
	
	/**
	 * 
	 * @return una mascota
	 */

	public Pet getPet() {
		return pet;
	}

	/**
	 * 
	 * @param pet
	 */
	public void setPet(Pet pet) {
		this.pet = pet;
	}
}