package be.acq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.acq.pojo.Descendeur;

public class DAO_Descendeur extends DAO<Descendeur> {
	public DAO_Descendeur(Connection con) {
		super(con);
	}

	@Override
	public boolean create(Descendeur obj) { //Non utilisé
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Descendeur obj) { //Non utilisé
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Descendeur obj) { //Non utilisé
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Descendeur select(int id) {
		Descendeur d = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			stmt = connect.prepareStatement("SELECT * FROM Categorie c INNER JOIN VTT v ON c.idCategorie = v.idCategorie "
					+ "INNER JOIN Descendeur d ON v.idCategorie = d.idCategorie WHERE idCategorie = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			if(res.first()){
				d = new Descendeur(res.getDouble("taille"), res.getInt("nbrVitesse"), res.getString("typeAmortisseur"),
						res.getString("typeFrein"));
				d.setIDCategorie(res.getInt("idCategorie"));
				d.setLibelle("Descendeur");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return d;
	}

}
