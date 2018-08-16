package be.acq.metier;

import java.util.ArrayList;

import be.acq.clavier.Clavier;
import be.acq.dao.DAO_Membre;
import be.acq.dao.DBConnection;
import be.acq.pojo.Membre;
import be.acq.pojo.Tresorier;

public class M_Tresorier {
	private Tresorier t;
	public M_Tresorier(Tresorier t) {
		this.t = t;
	}
	
	public void menuTresorier() {
		int choix;
		do {
			System.out.println("**MENU TRESORIER**");
			System.out.println("1. Comptabilité");
			System.out.println("0. Quitter");
			System.out.print("Votre choix : ");
			choix = Clavier.lireInt();
			while(choix<0 || choix >1) {
				System.out.println("Choix non valide");
				System.out.print("Votre choix : ");
				choix = Clavier.lireInt();
			}
			switch(choix) {
			case 1:
				comptabilite();
				break;
			case 0:
				//Retour à M_Personne
				break;
			}
		}
		while(choix!=0);
	}
	
	public void comptabilite() {
		DAO_Membre daoM = new DAO_Membre(DBConnection.getInstance());
		ArrayList<Membre> listMembre = daoM.selectAll();
		for(Membre m : listMembre) {
			System.out.println(m.getNom() + " " + m.getPrenom() + " Solde : " + m.getSolde());
		}
	}

}
