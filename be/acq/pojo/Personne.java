package be.acq.pojo;
import java.io.Serializable;
import java.util.Date;

public class Personne implements Serializable {
	private static final long serialVersionUID = 1072609382965770964L;
	//Attributs
	private int idPersonne;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String tel;
	private String mail;
	private String mdp;
	
	//Constructeurs
	public Personne() {}
	public Personne(String nom, String prenom, Date naissance, String tel, String mail, String mdp) {
		setNom(nom);
		setPrenom(prenom);
		setDateNaissance(naissance);
		setTel(tel);
		setMail(mail);
		setMdp(mdp);
	}
	
	//Getters & Setters
	public int getIDPersonne() {
		return idPersonne;
	}
	public void setIDPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
	
}
