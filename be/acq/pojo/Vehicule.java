package be.acq.pojo;
import java.io.Serializable;

public class Vehicule implements Serializable{
	private static final long serialVersionUID = -3837083086725031272L;
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
