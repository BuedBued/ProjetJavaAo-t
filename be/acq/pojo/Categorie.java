package be.acq.pojo;

import java.io.Serializable;

public class Categorie implements Serializable{
	private static final long serialVersionUID = -2791452639944428954L;
	private String idCategorie;
	private String nom;
	private String taille;
	private String nbrVitesse;
	
	public String getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(String idCategorie) {
		this.idCategorie = idCategorie;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
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
