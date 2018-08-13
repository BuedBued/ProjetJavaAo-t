package be.acq.pojo;

import java.io.Serializable;

public abstract class Categorie implements Serializable{
	private static final long serialVersionUID = -2791452639944428954L;
	private int idCategorie;
	private String libelle;
	private double taille;
	private int nbrVitesse;
	private Calendrier calendrier;
	
	//Constructeurs
	public Categorie() {}
	public Categorie(double taille, int nbrVitesse) {
		setTaille(taille);
		setNbrVitesse(nbrVitesse);
	}
	
	public int getIDCategorie() {
		return idCategorie;
	}
	public void setIDCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public double getTaille() {
		return taille;
	}
	public void setTaille(double taille) {
		this.taille = taille;
	}
	public int getNbrVitesse() {
		return nbrVitesse;
	}
	public void setNbrVitesse(int nbrVitesse) {
		this.nbrVitesse = nbrVitesse;
	}
	public Calendrier getCalendrier() {
		return calendrier;
	}
	public void setCalendrier(Calendrier calendrier) {
		this.calendrier = calendrier;
	}
}
