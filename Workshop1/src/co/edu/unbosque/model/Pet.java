package co.edu.unbosque.model;

public class Pet {
    private String id;
    private long microchip;
    private String species;
    private String sex;
    private String size;
    private boolean potentDangerous;
    private String neighborhood;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getMicrochip() {
		return microchip;
	}
	public void setMicrochip(long microchip) {
		this.microchip = microchip;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public boolean isPotentDangerous() {
		return potentDangerous;
	}
	public void setPotentDangerous(boolean potentDangerous) {
		this.potentDangerous = potentDangerous;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
    
    

}
