package be.acq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.acq.pojo.Membre;

public class DAO_Membre extends DAO<Membre>{
	public DAO_Membre(Connection con) {
		super(con);
	}

	@Override
	public boolean create(Membre obj) {
		boolean b = false;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = connect.prepareStatement("INSERT INTO Membre (idPersonne,solde) VALUES (?,?)");
			stmt.setInt(1, obj.getIDPersonne());
			stmt.setDouble(2, obj.getSolde());
			//Execution de la commande SQL
			stmt.executeUpdate();
			res = stmt.getGeneratedKeys();
			if(res.next()) {
				obj.setIDMembre(res.getInt(1));
				
				stmt = connect.prepareStatement("INSERT INTO LigneCategorie (idCategorie,idMembre) VALUES (?,?)");
				stmt.setInt(1, obj.getListCategorie().get(0).getIDCategorie());
				stmt.setInt(2,obj.getIDMembre());
				stmt.executeUpdate();
				b = true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean delete(Membre obj) {
		boolean b = false;
		PreparedStatement stmt = null;
		try {
			//Preparation de la commande SQL
			stmt = connect.prepareStatement("DELETE FROM Membre WHERE idMembre = ?");
			stmt.setInt(1, obj.getIDMembre());
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
	public boolean update(Membre obj) {
		boolean b = false;
		PreparedStatement stmt = null;
		try {
			//Preparation de la commande SQL
			stmt = connect.prepareStatement("UPDATE Membre SET solde = ? WHERE idMembre = ?");
			stmt.setDouble(1, obj.getSolde());
			stmt.setInt(2, obj.getIDMembre());
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
	public Membre select(int id) {
		Membre m = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			stmt = connect.prepareStatement("SELECT * FROM Membre WHERE idPersonne = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			if(res.first()){
				m = new Membre();
				m.setSolde(res.getDouble("solde"));
				m.setIDMembre(res.getInt("idMembre"));
				m.setIDPersonne(id);
				DAO_Categorie daoC = new DAO_Categorie(DBConnection.getInstance());
				m.setListCategorie(daoC.selectAllCategories(m.getIDMembre()));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return m;
	}
	
	public ArrayList<Membre> selectAll(){
		ArrayList<Membre> listMembre = new ArrayList<Membre>();
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			stmt = connect.prepareStatement("SELECT * FROM Membre m INNER JOIN Personne p ON m.idPersonne = p.idPersonne",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			res = stmt.executeQuery();
			if(res.next()){
				Membre m = new Membre(res.getString("nomPersonne"),res.getString("prenomPersonne"), 
						res.getString("telephonePersonne"),res.getString("mailPersonne"), 
						res.getString("mdpPersonne"),res.getDouble("solde"));
				m.setIDMembre(res.getInt("idMembre"));
				m.setIDPersonne(res.getInt("idPersonne"));
				listMembre.add(m);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return listMembre;
	}

}
