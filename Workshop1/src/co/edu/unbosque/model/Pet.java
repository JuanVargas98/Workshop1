package co.edu.unbosque.model;

/**
 * Pet class
 */
public class Pet {
    private String id;
    private long microchip;
    private String species;
    private String sex;
    private String size;
    private boolean potentDangerous;
    private String neighborhood;
    
    
    /**
     * Constructor Pet class
     */
    
    public Pet(){

    }
    /**
     * specific Constructor
     * @param id
     * @param microchip
     * @param species
     * @param sex
     * @param size
     * @param potentDangerous
     * @param neighborhood
     */
    public Pet(String id, long microchip, String species, String sex, String size, boolean potentDangerous, String neighborhood) {
        this.id = id;
        this.microchip = microchip;
        this.species = species;
        this.sex = sex;
        this.size = size;
        this.potentDangerous = potentDangerous;
        this.neighborhood = neighborhood;
    }
    
    
    
    @Override
	public String toString() {
		return "Pet [id=" + id + ", microchip=" + microchip + ", species=" + species + ", sex=" + sex + ", size=" + size
				+ ", potentDangerous=" + potentDangerous + ", neighborhood=" + neighborhood + "]";
	}
	/**
     * @return the id
     */
	public String getId() {
		return id;
	}
    /**
     * @param id the id to set
     */
	public void setId(String id) {
		this.id = id;
	}
	/**
     * @return the microchip
     */
	public long getMicrochip() {
		return microchip;
	/**
	 * @param microchip the microchip to set
	 */
	}
	public void setMicrochip(long microchip) {
		this.microchip = microchip;
	}
	/**
     * @return the species
     */
	public String getSpecies() {
		return species;
	}
	/**
     * @param species the species to set
     */
	public void setSpecies(String species) {
		this.species = species;
	}
	/**
     * @return the sex
     */
	public String getSex() {
		return sex;
	}
	/**
     * @param sex the sex to set
     */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
     * @return the size
     */
	public String getSize() {
		return size;
	}
	/**
     * @param size the size to set
     */
	public void setSize(String size) {
		this.size = size;
	}
	/**
     * @return the potentDangerous
     */
	public boolean isPotentDangerous() {
		return potentDangerous;
	}
	/**
     * @param potentDangerous the potentDangerous to set
     */
	public void setPotentDangerous(boolean potentDangerous) {
		this.potentDangerous = potentDangerous;
	}
	/**
     * @return the neighborhood
     */
	public String getNeighborhood() {
		return neighborhood;
	}
	/**
     * @param neighborhood the neighborhood to set
     */
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
}
