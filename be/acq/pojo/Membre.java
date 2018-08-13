package be.acq.pojo;

import java.util.ArrayList;
import java.util.Date;

public class Membre extends Personne {
	private static final long serialVersionUID = 1186729938582031733L;
	private int idMembre;
	private double solde;
	private ArrayList<Categorie> listCategorie;
	
	public Membre() {
		super();
	}
	public Membre(String nom, String prenom, Date naissance, String tel, String mail, String mdp) {
		super(nom, prenom, naissance, tel, mail, mdp);
	}
	public Membre(String nom, String prenom, Date naissance, String tel, String mail, String mdp, double solde) {
		this(nom, prenom, naissance, tel, mail, mdp);
		setSolde(solde);
	}
	
	public int getIDMembre() {
		return idMembre;
	}
	public void setIDMembre(int idMembre) {
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
