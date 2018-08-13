package be.acq.pojo;

import java.util.Date;

public class Responsable extends Personne{
	private static final long serialVersionUID = -1913995540909085053L;
	private int idResponsable;
	private Categorie categorie;
	
	//Constructeurs
	public Responsable() {
		super();
	}
	public Responsable(String nom, String prenom, Date naissance, String tel, String mail, String mdp) {
		super(nom, prenom, naissance, tel, mail, mdp);
	}
	public Responsable(String nom, String prenom, Date naissance, String tel, String mail, String mdp, Categorie cat) {
		this(nom,prenom,naissance,tel,mail,mdp);
		setCategorie(cat);
	}

	//Getters & Setters
	public int getIDResponsable() {
		return idResponsable;
	}
	public void setIDResponsable(int idResponsable) {
		this.idResponsable = idResponsable;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
}
