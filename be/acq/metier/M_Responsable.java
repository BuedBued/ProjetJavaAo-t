package be.acq.metier;

import be.acq.clavier.Clavier;
import be.acq.dao.DAO_Balade;
import be.acq.dao.DBConnection;
import be.acq.pojo.Balade;
import be.acq.pojo.Responsable;

public class M_Responsable {
	public Responsable r;
	public M_Responsable(Responsable r) {
		this.r = r;
	}
	
	public void menuResponsable() {
		int choix;
		do {
			System.out.println("**MENU RESPONSABLE**");
			System.out.println("Que voulez-vous faire?");
			System.out.println("1. Ajouter une balade");
			System.out.println("0. Quitter");
			System.out.print("Votre choix : ");
			choix = Clavier.lireInt();
			while (choix != 0 && choix != 1) {
				System.out.println("Choix non valide");
				System.out.print("Votre choix : ");
				choix = Clavier.lireInt();
			}
			switch(choix) {
				case 1:
					if(createBalade())
						System.out.println("Balade ajoutée avec succès");
					else
						System.out.println("L'ajout de la balade a échoué");
					break;
				case 0:
					//Retour à la classe M_Personne
					break;
			}
		}
		while(choix!=0);
	}
	
	public boolean createBalade() {
		boolean res = false;
		System.out.println("**AJOUT D'UNE BALADE**");
		System.out.print("Date de la balade : ");
		String date = Clavier.lireString();
		System.out.print("Rue : ");
		String rue = Clavier.lireString();
		System.out.print("Localité : ");
		String localite = Clavier.lireString();
		System.out.print("Code Postal : ");
		String cp = Clavier.lireString();
		System.out.print("Numero : ");
		String num = Clavier.lireString();
		System.out.print("Forfait : ");
		double forfait = Clavier.lireDouble();
		Balade b = new Balade(localite,cp,rue,num,date, forfait);
		DAO_Balade daoB = new DAO_Balade(DBConnection.getInstance());
		if(daoB.create(b,r.getCategorie().getIDCategorie()))
			res = true;
		return res;
	}
}
