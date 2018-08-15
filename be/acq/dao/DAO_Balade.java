package be.acq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.acq.pojo.Balade;
import be.acq.pojo.Calendrier;
import be.acq.pojo.Categorie;
import be.acq.pojo.Membre;


public class DAO_Balade extends DAO<Balade> {
	public DAO_Balade(Connection con) {
		super(con);
	}

	@Override
	public boolean create(Balade obj) { //Non nécessaire
		return false;
	}
	
	public boolean create(Balade obj, int idCategorie) {
		boolean b = false;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = connect.prepareStatement("INSERT INTO Balade (dateBalade, rueBalade, localiteBalade, numBalade,"
					+ "cpBalade, forfait, idCategorie) VALUES (?,?,?,?,?,?,?)");
			stmt.setString(1, obj.getDate());
			stmt.setString(2, obj.getRue());
			stmt.setString(3, obj.getLocalite());
			stmt.setString(4, obj.getNum());
			stmt.setString(5, obj.getCP());
			stmt.setDouble(6, obj.getForfait());
			stmt.setInt(7, idCategorie);
			//Execution de la commande SQL
			stmt.executeUpdate();
			res = stmt.getGeneratedKeys();
			if(res.next()) {
				obj.setIDBalade(res.getInt(1));
				b = true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public boolean createCovoiturage(int id, Membre m, int maxPlace) {
		boolean b = false;
		PreparedStatement stmt = null;
		try {
			stmt = connect.prepareStatement("INSERT INTO Covoiturage (idBalade, idConducteur, maxPlace) VALUES (?,?,?)");
			stmt.setInt(1, id);
			stmt.setInt(2, m.getIDMembre());
			stmt.setInt(3, maxPlace);
			stmt.executeUpdate();
			b = true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean delete(Balade obj) {
		boolean b = false;
		PreparedStatement stmt = null;
		try {
			//Preparation de la commande SQL
			stmt = connect.prepareStatement("DELETE FROM Balade WHERE idBalade = ?");
			stmt.setInt(1, obj.getIDBalade());
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
	public boolean update(Balade obj) {
		boolean b = false;
		PreparedStatement stmt = null;
		try {
			//Preparation de la commande SQL
			stmt = connect.prepareStatement("UPDATE Responsable SET dateBalade = ?, rueBalade = ?, localiteBalade = ?, "
					+ "numBalade = ?, cpBalade = ?, forfait = ? WHERE idBalade = ?");
			stmt.setString(1, obj.getDate());
			stmt.setString(2, obj.getRue());
			stmt.setString(3, obj.getLocalite());
			stmt.setString(4, obj.getNum());
			stmt.setString(5, obj.getCP());
			stmt.setDouble(6, obj.getForfait());
			stmt.setInt(7, obj.getIDBalade());
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
	public Balade select(int id) {
		Balade b = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			stmt = connect.prepareStatement("SELECT * FROM Balade WHERE idBalade = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			if(res.first()){
				b = new Balade(res.getString("localiteBalade"), res.getString("cpBalade"),res.getString("rueBalade"), 
						res.getString("numBalade"), res.getString("dateBalade"), res.getDouble("forfait"));
				b.setIDBalade(id);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return b;
	}
	
	//Liste des balades en fonction de la catégorie
	public Calendrier selectList(Categorie cat) {
		Calendrier c = null;
		ArrayList<Balade> listBalade = new ArrayList<Balade>();
		Balade b = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			stmt = connect.prepareStatement("SELECT * FROM Balade WHERE idCategorie = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, cat.getIDCategorie());
			res = stmt.executeQuery();
			while(res.next()){
				b = new Balade(res.getString("localiteBalade"), res.getString("cpBalade"),res.getString("rueBalade"), 
						res.getString("numBalade"), res.getString("dateBalade"), res.getDouble("forfait"));
				b.setIDBalade(res.getInt("idBalade"));
				listBalade.add(b);
			}
			c = new Calendrier();
			c.setCategorie(cat);
			c.setListBalade(listBalade);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return c;
	}
}
