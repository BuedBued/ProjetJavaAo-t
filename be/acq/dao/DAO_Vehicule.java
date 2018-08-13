package be.acq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import be.acq.pojo.Membre;
import be.acq.pojo.Vehicule;

public class DAO_Vehicule extends DAO<Vehicule> {
	public DAO_Vehicule(Connection con) {
		super(con);
	}

	@Override
	public boolean create(Vehicule obj) { //Non utilisé
		return false;
	}
	
	public boolean create(Vehicule obj, int idBalade) {
		boolean b = false;
		PreparedStatement stmt = null;
		try {
			stmt = connect.prepareStatement("INSERT INTO Covoiturage (idConducteur, idBalade, maxPlace) VALUES (?,?,?)");
			stmt.setInt(1, obj.getConducteur().getIDPersonne());
			stmt.setInt(2, idBalade);
			stmt.setInt(3, obj.getNbrPlaceMax());
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
	public boolean delete(Vehicule obj) {
		boolean b = false;
		PreparedStatement stmt = null;
		try {
			//Preparation de la commande SQL
			stmt = connect.prepareStatement("DELETE FROM Covoiturage WHERE idCovoiturage = ?");
			stmt.setInt(1, obj.getIDVehicule());
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
	public boolean update(Vehicule obj) {
		boolean b = false;
		PreparedStatement stmt = null;
		try {
			//Preparation de la commande SQL
			stmt = connect.prepareStatement("UPDATE Covoiturage SET maxPlace = ? WHERE idCovoiturage = ?");
			stmt.setInt(1, obj.getNbrPlaceMax());
			stmt.setInt(2, obj.getIDVehicule());
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
	public Vehicule select(int id) {
		Vehicule v = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			stmt = connect.prepareStatement("SELECT * FROM Covoiturage c INNER JOIN Membre m ON c.idConducteur = "
					+ "m.idMembre INNER JOIN Personne p ON m.idPersonne = p.idPersonne WHERE idCovoiturage = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			if(res.first()){
				Membre m = new Membre(res.getString("nomPersonne"), res.getString("prenomPersonne"), 
						res.getDate("dateNaissancePersonne"), res.getString("telephonePersonne"), 
						res.getString("mailPersonne"), res.getString("mdpPersonne"), res.getDouble("solde"));
				m.setIDMembre(res.getInt("idMembre"));
				v = new Vehicule(res.getInt("maxPlace"),m);
				
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return v;
	}
	

}
