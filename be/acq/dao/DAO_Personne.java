package be.acq.dao;

import be.acq.pojo.Membre;
import be.acq.pojo.Personne;
import be.acq.pojo.Responsable;
import be.acq.pojo.Tresorier;

import java.sql.*;

public class DAO_Personne extends DAO<Personne> {
	public DAO_Personne(Connection con) {
		super(con);
	}

	@Override
	public boolean create(Personne obj) {
		boolean b = false;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = connect.prepareStatement("INSERT INTO Personne (nomPersonne,prenomPersonne,"
					+ "telephonePersonne,mailPersonne,mdpPersonne) "
					+ "VALUES (?,?,?,?,?)");
			stmt.setString(1, obj.getNom());
			stmt.setString(2, obj.getPrenom());
			stmt.setString(3, obj.getTel());
			stmt.setString(4, obj.getMail());
			stmt.setString(5, obj.getMdp());
			//Execution de la commande SQL
			stmt.executeUpdate();
			res = stmt.getGeneratedKeys();
			if(res.next()) {
				obj.setIDPersonne(res.getInt(1));
			}
			b = true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean delete(Personne obj) {
		boolean b = false;
		PreparedStatement stmt = null;
		try {
			//Preparation de la commande SQL
			stmt = connect.prepareStatement("DELETE FROM Personne WHERE idPersonne = ?");
			stmt.setInt(1, obj.getIDPersonne());
			//Execution de la commande SQL
			stmt.executeUpdate();
			b = true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean update(Personne obj) {
		boolean b = false;
		PreparedStatement stmt = null;
		try {
			//Preparation de la commande SQL
			stmt = connect.prepareStatement("UPDATE Personne SET nomPersonne = ?, prenomPersonne = ?, "
					+ "telephonePersonne = ?, mailPersonne = ?, mdpPersonne = ? "
					+ "WHERE idPersonne = ?");
			stmt.setString(1, obj.getNom());
			stmt.setString(2, obj.getPrenom());
			stmt.setString(3, obj.getTel());
			stmt.setString(4, obj.getMail());
			stmt.setString(5, obj.getMdp());
			stmt.setInt(6, obj.getIDPersonne());
			//Execution de la commande SQL
			stmt.executeUpdate();
			b = true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public Personne select(int id) {
		Personne p = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			stmt = connect.prepareStatement("SELECT * FROM Personne WHERE idPersonne = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			if(res.first()){
				p = new Personne(res.getString("nomPersonne"), res.getString("prenomPersonne"), 
						res.getString("telephonePersonne"), res.getString("mailPersonne"),
						res.getString("mdpPersonne"));
				p.setIDPersonne(id);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return p;
	}

	//Select complémentaire pour récupérer une personne si on ne connait pas son ID (par exemple à la connexion
	//au programme)
	public Object select(String mail, String mdp) {
		Object retour = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			stmt = connect.prepareStatement("SELECT * FROM Personne WHERE mailPersonne = ? AND mdpPersonne = ? ",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, mail);
			stmt.setString(2, mdp);
			res = stmt.executeQuery();
			if(res.first()){
				String nom = res.getString("nomPersonne");
				String prenom = res.getString("prenomPersonne");
				String telephone = res.getString("telephonePersonne");
				int idPersonne = res.getInt("idPersonne");
				
				//Recherche dans la table Membre
				DAO_Membre daoM = new DAO_Membre(DBConnection.getInstance());
				Membre m = daoM.select(idPersonne);
				if(m!=null) {
					m.setNom(nom);
					m.setPrenom(prenom);
					m.setTel(telephone);
					m.setMail(mail);
					m.setMdp(mdp);
					retour = m;
				}
				else {
					DAO_Responsable daoR = new DAO_Responsable(DBConnection.getInstance());
					Responsable r = daoR.select(idPersonne);
					if(r!=null) {
						r.setNom(nom);
						r.setPrenom(prenom);
						r.setTel(telephone);
						r.setMail(mail);
						r.setMdp(mdp);
						retour = r;
					}
					else {
						//Recherche dans la table Tresorier
						DAO_Tresorier daoT = new DAO_Tresorier(DBConnection.getInstance());
						Tresorier t = daoT.select(idPersonne);
						if(t!=null) {
							t.setNom(nom);
							t.setPrenom(prenom);
							t.setTel(telephone);
							t.setMail(mail);
							t.setMdp(mdp);
							retour = t;
						}
					}
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return retour;
	}
	
}
