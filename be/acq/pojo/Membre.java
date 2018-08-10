package be.acq.pojo;

import java.util.ArrayList;

public class Membre extends Personne {
	private static final long serialVersionUID = 1186729938582031733L;
	private String idMembre;
	private double solde;
	private ArrayList<Categorie> listCategorie;
	public String getIdMembre() {
		return idMembre;
	}
	public void setIdMembre(String idMembre) {
		this.idMembre = idMembre;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public ArrayList<Categorie> getListCategorie() {
		return listCategorie;
	}
	public void setListCategorie(ArrayList<Categorie> listCategorie) {
		this.listCategorie = listCategorie;
	}
	public boolean addCategorie(Categorie c) {
		return listCategorie.add(c);
	}
	public boolean removeCategorie(Categorie c) {
		return listCategorie.remove(c);
	}
}
