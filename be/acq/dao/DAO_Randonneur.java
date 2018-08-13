package be.acq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.acq.pojo.*;

public class DAO_Randonneur extends DAO<Randonneur> {
	public DAO_Randonneur(Connection con) {
		super(con);
	}

	@Override
	public boolean create(Randonneur obj) { //Non utilisé
		return false;
	}

	@Override
	public boolean delete(Randonneur obj) { //Non utilisé
		return false;
	}

	@Override
	public boolean update(Randonneur obj) { //Non utilisé
		return false;
	}

	@Override
	public Randonneur select(int id) {
		Randonneur r = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			stmt = connect.prepareStatement("SELECT * FROM Categorie c INNER JOIN VTT v ON c.idCategorie = v.idCategorie "
					+ "INNER JOIN Randonneur r ON v.idCategorie = r.idCategorie WHERE idCategorie = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			if(res.first()){
				r = new Randonneur(res.getDouble("taille"), res.getInt("nbrVitesse"), res.getString("typeAmortisseur"),
						res.getDouble("taillePetitPignon"), res.getDouble("tailleGrandPignon"));
				r.setIDCategorie(res.getInt("idCategorie"));
				r.setLibelle("Randonneur");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return r;
	}
}
