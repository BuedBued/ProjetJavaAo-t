package be.acq.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.acq.pojo.*;

public class DAO_Cyclo extends DAO<Cyclo>{
	public DAO_Cyclo(Connection con) {
		super(con);
	}

	@Override
	public boolean create(Cyclo obj) { //Non utilisé
		return false;
	}

	@Override
	public boolean delete(Cyclo obj) { //Non utilisé
		return false;
	}

	@Override
	public boolean update(Cyclo obj) { //Non utilisé
		return false;
	}

	@Override
	public Cyclo select(int id) {
		Cyclo c = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			stmt = connect.prepareStatement("SELECT * FROM Categorie c INNER JOIN Cyclo cy ON c.idCategorie = cy.idCategorie WHERE idCategorie = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			if(res.first()){
				c = new Cyclo(res.getDouble("taille"),res.getInt("nbrVitesse"),res.getDouble("diametreRoue"));
				c.setIDCategorie(res.getInt("idCategorie"));
				c.setLibelle("Cyclo");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return c;
	}
}
