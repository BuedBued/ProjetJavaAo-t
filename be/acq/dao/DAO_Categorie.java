package be.acq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.acq.pojo.*;

public class DAO_Categorie extends DAO<Categorie> {

	public DAO_Categorie(Connection con) {
		super(con);
	}

	@Override
	public boolean create(Categorie obj) { //Non utilisé
		return false;
	}
	
	public boolean ajouterCategorie(Categorie obj, Membre m) {
		boolean b = false;
		PreparedStatement stmt = null;
		try {
			stmt = connect.prepareStatement("INSERT INTO LigneCategorie (idCategorie,idMembre) VALUES (?,?)");
			stmt.setInt(1, obj.getIDCategorie());
			stmt.setInt(2, m.getIDMembre());
			stmt.executeUpdate();
			
			//Ajout du supplément + mise en DB
			m.setSolde(m.getSolde()+5);
			DAO_Membre daoM = new DAO_Membre(DBConnection.getInstance());
			if(daoM.update(m))
				b = true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean delete(Categorie obj) { //Non utilisé
		return false;
	}

	@Override
	public boolean update(Categorie obj) { //Non utilisé
		return false;
	}

	@Override
	public Categorie select(int id) {
		Categorie c = null;
		switch(id) {
		case 1:
			DAO_Cyclo daoC = new DAO_Cyclo(DBConnection.getInstance());
			c = daoC.select(1);
			break;
		case 2:
			DAO_Randonneur daoR = new DAO_Randonneur(DBConnection.getInstance());
			c = daoR.select(2);
			break;
		case 3:
			DAO_Trialiste daoT = new DAO_Trialiste(DBConnection.getInstance());
			c = daoT.select(3);
			break;
		case 4:
			DAO_Descendeur daoD = new DAO_Descendeur(DBConnection.getInstance());
			c = daoD.select(4);
			break;
		}
		return c;
	}
	
	public ArrayList<Categorie> selectAllCategories(int idMembre){
		ArrayList<Categorie> listCat = new ArrayList<Categorie>();
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			stmt = connect.prepareStatement("SELECT * FROM LigneCategorie WHERE idMembre = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, idMembre);
			res = stmt.executeQuery();
			while(res.next()) {
				Categorie c = null;
				switch(res.getInt("idCategorie")) {
				case 1:
					DAO_Cyclo daoC = new DAO_Cyclo(DBConnection.getInstance());
					c = daoC.select(1);
					break;
				case 2:
					DAO_Randonneur daoR = new DAO_Randonneur(DBConnection.getInstance());
					c = daoR.select(2);
					break;
				case 3:
					DAO_Trialiste daoT = new DAO_Trialiste(DBConnection.getInstance());
					c = daoT.select(3);
					break;
				case 4:
					DAO_Descendeur daoD = new DAO_Descendeur(DBConnection.getInstance());
					c = daoD.select(4);
					break;
				}
				listCat.add(c);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return listCat;
	}
}
