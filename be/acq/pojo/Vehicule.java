package be.acq.pojo;
import java.io.Serializable;
import java.util.ArrayList;

public class Vehicule implements Serializable{
	private static final long serialVersionUID = -3837083086725031272L;
	private int idVehicule;
	private int nbrPlaceMax;
	private int nbrPlaceActuel;
	private Membre conducteur;
	private ArrayList<Membre> listPassager;
	
	public Vehicule() {}
	public Vehicule(int nbrPlaceMax) {
		setNbrPlaceMax(nbrPlaceMax);
	}
	public Vehicule(int nbrPlaceMax, Membre conducteur) {
		this(nbrPlaceMax);
		setConducteur(conducteur);
	}
	public Vehicule(int nbrPlaceMax, Membre conducteur, ArrayList<Membre> listPassager) {
		this(nbrPlaceMax,conducteur);
		setListPassager(listPassager);
	}
	
	public int getNbrPlaceMax() {
		return nbrPlaceMax;
	}
	public void setNbrPlaceMax(int nbrPlaceMax) {
		this.nbrPlaceMax = nbrPlaceMax;
	}
	public int getIDVehicule() {
		return idVehicule;
	}
	public void setIDVehicule(int idVehicule) {
		this.idVehicule = idVehicule;
	}
	public int getNbrPlaceActuel() {
		return nbrPlaceActuel;
	}
	public void setNbrPlaceActuel(int nbrPlaceActuel) {
		this.nbrPlaceActuel = nbrPlaceActuel;
	}
	public Membre getConducteur() {
		return conducteur;
	}
	public void setConducteur(Membre conducteur) {
		this.conducteur = conducteur;
	}
	public ArrayList<Membre> getListPassager() {
		return listPassager;
	}
	public void setListPassager(ArrayList<Membre> listPassager) {
		this.listPassager = listPassager;
	}
	public boolean addCategorie(Membre m) {
		return listPassager.add(m);
	}
	public boolean removeCategorie(Membre m) {
		return listPassager.remove(m);
	}
}
