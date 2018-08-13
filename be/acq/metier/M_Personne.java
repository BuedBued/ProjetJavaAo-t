package be.acq.metier;

import be.acq.pojo.*;
import java.util.ArrayList;
import be.acq.clavier.*;
import be.acq.dao.*;

public class M_Personne {
	
	public static void main(String[] args) {
		System.out.println("**PROGRAMME DE COVOITURAGE**");
		System.out.println("1. Connexion");
		System.out.println("2. Inscription");
		System.out.print("Votre choix : ");
		int choix = Clavier.lireInt();
		while(choix !=1 && choix !=2) {
			System.out.println("Choix non valide");
			System.out.print("Votre choix : ");
			choix = Clavier.lireInt();
		}
		M_Personne p = new M_Personne();
		switch(choix) {
		case 1:
			p.connexion();
			break;
		case 2:
			p.inscription();
			break;
		}
	}
	public void connexion() {
		System.out.println("**CONNEXION**");
		System.out.print("Mail : ");
		String mail = Clavier.lireString();
		System.out.print("Mot de passe : ");
		String mdp = Clavier.lireString();
		DAO_Personne daoP = new DAO_Personne(DBConnection.getInstance());
		Object p = daoP.select(mail, mdp);
		if(p!=null) {
			if(p.getClass().equals(Membre.class)) {
				//Action Membre
				System.out.println("Debug Membre");
			}
			else if(p.getClass().equals(Responsable.class)) {
				//Action Responsable
				System.out.println("Debug Responsable");
			}
			else if(p.getClass().equals(Tresorier.class)) {
				//Action Tresorier
				System.out.println("Debug Tresorier");
			}
			else
				System.out.println("Connexion echouee");
		}
		else {
			System.out.println("Connexion echouee");
		}
	}
	public void inscription() {
		Membre m = encodage();
		if(m!=null) {
			m.setListCategorie(new ArrayList<Categorie>());
			m.addCategorie(choixCategorie());
			m.setSolde(20);
			DAO_Membre daoM = new DAO_Membre(DBConnection.getInstance());
			if(daoM.create(m)) {
				//Partie Membre
			}
			else {
				System.out.println("Votre inscription a echouee");
			}
		}
		else {
			System.out.println("Une erreur est survenue, veillez ressayer");
		}
	}
	//Méthode d'encodage des informations de la personne
	public Membre encodage() {
		DAO_Personne daoP = new DAO_Personne(DBConnection.getInstance());
		Membre m = null;
		System.out.print("Veuillez saisir votre nom : ");
		String nom = Clavier.lireString();
		while(nom.length()<2) {
			System.out.println("Nom incorrect : il fait moins de 2 lettres");
			System.out.print("Veuillez saisir votre nom : ");
			nom = Clavier.lireString();
		}
		System.out.print("Veuillez saisir votre prenom : ");
		String prenom = Clavier.lireString();
		while(prenom.length() < 2) {
			System.out.println("Prenom incorrect : il fait moins de 2 lettres");
			System.out.print("Veuillez saisir votre prenom : ");
			prenom = Clavier.lireString();
		}
		System.out.print("Veuillez saisir votre numero de telephone (format : 000/000000) : ");
		String tel = Clavier.lireString();
		while(tel.charAt(3)!='/') {
			System.out.println("Format non correct");
			System.out.print("Veuillez saisir votre numero de telephone (format : 000/000000) : ");
			tel = Clavier.lireString();
		}
		System.out.print("Veuillez saisir votre adresse mail : ");
		String mail = Clavier.lireString();
		while(!mail.contains("@")) {
			System.out.println("Adresse non valable");
			System.out.print("Veuillez saisir votre adresse mail : ");
			mail = Clavier.lireString();
		}
		System.out.print("Veuillez saisir votre mot de passe : ");
		String mdp = Clavier.lireString();
		while(mdp.length()<5) {
			System.out.println("Votre mot de passe est trop petit");
			System.out.print("Veuillez saisir votre mot de passe : ");
			mdp = Clavier.lireString();
		}
		Personne p = new Personne(nom, prenom, tel, mail, mdp);
		//Création de la personne dans la base de donnée
		if(daoP.create(p)) {
			m = new Membre(nom, prenom, tel, mail, mdp);
			m.setIDPersonne(p.getIDPersonne());
		}
		return m;
	}
	//Choix de la catégorie pour les Responsables et les membres (à l'inscription)
	public Categorie choixCategorie() {
		Categorie c = null;
		System.out.println("**Choix de votre categorie**");
		System.out.println("1. Cyclo");
		System.out.println("2. Randonneur");
		System.out.println("3. Trialiste");
		System.out.println("4. Descendeur");
		System.out.print("Votre choix : ");
		int choix = Clavier.lireInt();
		while(choix<1 || choix>4) {
			System.out.println("Choix non valide");
			System.out.print("Votre choix : ");
			choix = Clavier.lireInt();
		}
		switch(choix) {
		case 1:
			DAO_Cyclo daoCyclo = new DAO_Cyclo(DBConnection.getInstance());
			c = daoCyclo.select(choix);
			break;
		case 2:
			DAO_Randonneur daoRand = new DAO_Randonneur(DBConnection.getInstance());
			c = daoRand.select(choix);
			break;
		case 3:
			DAO_Trialiste daoTrial = new DAO_Trialiste(DBConnection.getInstance());
			c = daoTrial.select(choix);
			break;
		case 4:
			DAO_Descendeur daoDesc = new DAO_Descendeur(DBConnection.getInstance());
			c = daoDesc.select(choix);
			break;
		}
		return c;
	}
}
