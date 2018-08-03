package be.acq.pojo;

public class Vehicule {
	private String idVehicule;
	private int nbrPlaceMax;
	private int nbrPlaceActuel;
	
	
	public int getNbrPlaceMax() {
		return nbrPlaceMax;
	}
	public void setNbrPlaceMax(int nbrPlaceMax) {
		this.nbrPlaceMax = nbrPlaceMax;
	}
	public String getIdVehicule() {
		return idVehicule;
	}
	public void setIdVehicule(String idVehicule) {
		this.idVehicule = idVehicule;
	}
	public int getNbrPlaceActuel() {
		return nbrPlaceActuel;
	}
	public void setNbrPlaceActuel(int nbrPlaceActuel) {
		this.nbrPlaceActuel = nbrPlaceActuel;
	}
}
