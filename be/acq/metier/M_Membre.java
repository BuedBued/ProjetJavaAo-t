package be.acq.metier;

import java.util.ArrayList;

import be.acq.clavier.Clavier;
import be.acq.dao.DAO_Balade;
import be.acq.dao.DAO_Categorie;
import be.acq.dao.DAO_Cyclo;
import be.acq.dao.DAO_Descendeur;
import be.acq.dao.DAO_Membre;
import be.acq.dao.DAO_Randonneur;
import be.acq.dao.DAO_Trialiste;
import be.acq.dao.DAO_Vehicule;
import be.acq.dao.DBConnection;
import be.acq.pojo.Balade;
import be.acq.pojo.Calendrier;
import be.acq.pojo.Categorie;
import be.acq.pojo.Membre;
import be.acq.pojo.Vehicule;

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
			System.out.println("3. S'inscrire a une nouvelle catégorie");
			System.out.println("4. Executer les payements");
			System.out.println("0. Quitter");
			System.out.print("Votre choix : ");
			choix = Clavier.lireInt();
			while(choix<0 || choix >4) {
				System.out.println("Choix non valide");
				System.out.print("Votre choix : ");
				choix = Clavier.lireInt();
			}
			switch(choix) {
			case 1:
				proposerDisponibilite();
				break;
			case 2:
				reserverPlace();
				break;
			case 3:
				inscrireCategorie();
				break;
			case 4:
				payement();
				break;
			case 0:
				//Retour à la classe M_Personne
				break;
			}
		}
		while(choix != 0);
	}
	
	//CHOIX 1 : PROPOSER UNE DISPONIBILITE
	public void proposerDisponibilite() {
		System.out.println("**PROPOSER DISPONIBILITE**");
		Categorie c = choisirCategorie();
		DAO_Balade daoB = new DAO_Balade(DBConnection.getInstance());
		c.setCalendrier(daoB.selectList(c));
		if(c.getCalendrier().getListBalade().size()!=0) {
			if(creerCovoiturage(c.getCalendrier()))
				System.out.println("Votre véhicule est enregistré pour cette balade");
			else
				System.out.println("Une erreur est survenue, votre véhicule n'a pas été enregistré");
			}
		else
			System.out.println("Pas de balade disponible");
	}
	
	public boolean creerCovoiturage(Calendrier cal) {
		Balade b = choisirBalade(cal);
		System.out.print("Nombre de places disponibles dans votre véhicule : ");
		int maxPlace = Clavier.lireInt();
		while(maxPlace<1) {
			System.out.println("Le nombre de places maximal doit être positif");
			System.out.print("Nombre de places disponibles dans votre véhicule : ");
			maxPlace = Clavier.lireInt();
		}
		DAO_Balade daoB = new DAO_Balade(DBConnection.getInstance());
		return daoB.createCovoiturage(b.getIDBalade(), m, maxPlace);
	}
	
	//CHOIX 2 : RESERVER UNE PLACE
	public void reserverPlace() {
		System.out.println("**RESERVER UNE PLACE**");
		Categorie c = choisirCategorie();
		DAO_Balade daoB = new DAO_Balade(DBConnection.getInstance());
		c.setCalendrier(daoB.selectList(c));
		if(c.getCalendrier().getListBalade().size()!=0) {
			if(reserverCovoiturage(c.getCalendrier()))
				System.out.println("Votre place est réservée");
			else
				System.out.println("Une erreur est survenue, votre place n'a pas été enregistrée");
			}
		else
			System.out.println("Pas de balade disponible");
		
	}
	
	public boolean reserverCovoiturage(Calendrier cal) {
		Balade b = choisirBalade(cal);
		DAO_Vehicule daoV = new DAO_Vehicule(DBConnection.getInstance());
		b.setListCovoiturage(daoV.selectCovoiturages(b.getIDBalade()));
		Vehicule v = choisirCovoit(b);
		//Ajout du passager dans la base de données
		boolean etape1 = daoV.ajouterPassager(v, m.getIDMembre());
		boolean etape2 = false;
		//Ajout du forfait au membre
		if(etape1) {
			m.setSolde(m.getSolde()+b.getForfait());
			DAO_Membre daoM = new DAO_Membre(DBConnection.getInstance());
			etape2 = daoM.update(m);
		}
		return etape1 && etape2;
	}
	
	//CHOIX 3 : S'INSCRIRE A UNE NOUVELLE CATEGORIE
	public void inscrireCategorie() {
		System.out.println("**INSCRIPTION A UNE NOUVELLE CATEGORIE**");
		Categorie c = choixNouvelleCategorie(m);
		if(c!=null) {
			DAO_Categorie daoC = new DAO_Categorie(DBConnection.getInstance());
			if(daoC.ajouterCategorie(c, m)) {
				m.addCategorie(c);
				System.out.println("Vous êtes bien inscrit à une nouvelle catégorie");
			}
		}
		else 
			System.out.println("Vous êtes déjà inscrit dans cette catégorie");
	}
	
	//CHOIX 4 : PAYEMENT
	public void payement() {
		System.out.println("**PAYEMENT**");
		System.out.println("Votre solde : " + m.getSolde());
		System.out.println("Désirez-vous payer maintenant : ");
		System.out.println("1. OUI");
		System.out.println("2. NON");
		System.out.print("Votre choix : ");
		int choix = Clavier.lireInt();
		while(choix<1 || choix >2) {
			System.out.println("Choix non valide");
			System.out.print("Votre choix : ");
			choix = Clavier.lireInt();
		}
		if(choix==1) {
			m.setSolde(0);
			DAO_Membre daoM = new DAO_Membre(DBConnection.getInstance());
			if(daoM.update(m))
				System.out.println("Votre payement a été effectué avec succès");
			else
				System.out.println("Une erreur est survenue lors du payement");
		}
	}
	
	//Méthodes des choix
	public Categorie choixNouvelleCategorie(Membre m) {
		ArrayList<Categorie> listCategorie = m.getListCategorie();
		Categorie c = null;
		System.out.println("**Choix de votre catégorie**");
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
		boolean present = false;
		for(Categorie cat : listCategorie) {
			if(cat.getIDCategorie()==choix) {
				present = true;
				break;
			}
		}
		if(!present) {
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
		}
		return c;
	}
	
	public Categorie choisirCategorie() {
		System.out.println("Choisissez la catégorie");
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
		return c;
	}
	
	public Balade choisirBalade(Calendrier cal) {
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
		Balade b = cal.getListBalade().get(choix-1);
		return b;
	}
	
	public Vehicule choisirCovoit(Balade b) {
		int i = 1;
		System.out.println("Choisissez votre véhicule");
		for(Vehicule covoit : b.getListCovoiturage()) {
			System.out.println(i + ". " + covoit.display());
			i++;
		}
		Vehicule v;
		do {
			System.out.print("Votre choix : ");
			int choix = Clavier.lireInt();
			while(choix>i) {
				System.out.println("Choix non valide");
				System.out.print("Votre choix : ");
				choix = Clavier.lireInt();
			}
			v = b.getListCovoiturage().get(choix-1);
			if(v.getNbrPlaceActuel()==v.getNbrPlaceMax())
				System.out.println("Il n'y a plus de place pour ce covoiturage-ci. Veuillez en choisir un nouveau");
		}
		while(v.getNbrPlaceActuel()==v.getNbrPlaceMax());
		return v;
	}
}
