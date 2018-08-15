package be.acq.metier;

import be.acq.clavier.Clavier;
import be.acq.dao.DAO_Balade;
import be.acq.dao.DBConnection;
import be.acq.pojo.Balade;
import be.acq.pojo.Calendrier;
import be.acq.pojo.Categorie;
import be.acq.pojo.Membre;

public class M_Membre {
	public Membre m;
	public M_Membre(Membre m) {
		this.m = m;
	}
	
	public void menuMembre() {
		int choix;
		do {
			System.out.println("**MENU MEMBRE**");
			System.out.println("Que voulez-vous faire?");
			System.out.println("1. Proposer ses disponibilités");
			System.out.println("2. Réserver une place");
			System.out.println("3. Executer les payements");
			System.out.println("0. Quitter");
			System.out.print("Votre choix : ");
			choix = Clavier.lireInt();
			while(choix<0 || choix >2) {
				System.out.println("Choix non valide");
				System.out.print("Votre choix : ");
				choix = Clavier.lireInt();
			}
			switch(choix) {
			case 1:
				proposerDisponibilite();
				break;
			case 2:
				break;
			case 0:
				//Retour à la classe M_Personne
				break;
			}
		}
		while(choix != 0);
	}

	public void proposerDisponibilite() {
		System.out.println("**PROPOSER DISPONIBILITE**");
		System.out.println("Choisissez la categorie");
		int i = 1;
		for(Categorie c : m.getListCategorie()) {
			System.out.println(i + ". " + c.getLibelle());
			i++;
		}
		System.out.print("Votre choix : ");
		int choix = Clavier.lireInt();
		while(choix>=i || choix < 1) {
			System.out.println("Choix non valide");
			System.out.print("Votre choix : ");
			choix = Clavier.lireInt();
		}
		Categorie c = m.getListCategorie().get(choix-1);
		DAO_Balade daoB = new DAO_Balade(DBConnection.getInstance());
		c.setCalendrier(daoB.selectList(c));
		if(c.getCalendrier().getListBalade().size()!=0) {
			if(creerCovoiturage(c.getCalendrier()))
				System.out.println("Votre vehicule est enregistre pour cette balade");
			else
				System.out.println("Une erreur est survenue, votre vehicule n'a pas ete enregistre");
			}
		else
			System.out.println("Pas de balade disponible");
	}
	
	public boolean creerCovoiturage(Calendrier cal) {
		System.out.println("**CHOISIR BALADE**");
		System.out.println("Choisissez une balade");
		int i = 1;
		
		for(Balade b : cal.getListBalade()) {
			System.out.println(i + ". " + b.display());
			i++;
		}
		System.out.print("Votre choix : ");
		int choix = Clavier.lireInt();
		while(choix<0 || choix>=i) {
			System.out.println("Choix invalide");
			System.out.print("Votre choix : ");
			choix = Clavier.lireInt();
		}
		System.out.print("Nombre de places disponibles dans votre vehicule : ");
		int maxPlace = Clavier.lireInt();
		while(maxPlace<1) {
			System.out.println("Le nombre de places maximal doit être positif");
			System.out.print("Nombre de places disponibles dans votre vehicule : ");
			maxPlace = Clavier.lireInt();
		}
		Balade b = cal.getListBalade().get(choix-1);
		DAO_Balade daoB = new DAO_Balade(DBConnection.getInstance());
		return daoB.createCovoiturage(b.getIDBalade(), m, maxPlace);
	}
}
