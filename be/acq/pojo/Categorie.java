package be.acq.pojo;

import java.io.Serializable;

public class Categorie implements Serializable{
	private static final long serialVersionUID = -2791452639944428954L;
	private String idCategorie;
	private String libelle;
	private String taille;
	private String nbrVitesse;
	
	public String getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(String idCategorie) {
		this.idCategorie = idCategorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getTaille() {
		return taille;
	}
	public void setTaille(String taille) {
		this.taille = taille;
	}
	public String getNbrVitesse() {
		return nbrVitesse;
	}
	public void setNbrVitesse(String nbrVitesse) {
		this.nbrVitesse = nbrVitesse;
	}
}
