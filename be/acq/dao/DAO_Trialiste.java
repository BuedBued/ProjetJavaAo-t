package be.acq.dao;
import be.acq.pojo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_Trialiste extends DAO<Trialiste> {
	public DAO_Trialiste(Connection con) {
		super(con);
	}

	@Override
	public boolean create(Trialiste obj) { //Non utilisé
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Trialiste obj) { //Non utilisé
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Trialiste obj) { //Non utilisé
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Trialiste select(int id) {
		Trialiste t = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			stmt = connect.prepareStatement("SELECT * FROM Categorie c INNER JOIN VTT v ON c.idCategorie = v.idCategorie "
					+ "INNER JOIN Trialiste t ON v.idCategorie = t.idCategorie WHERE idCategorie = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			if(res.first()){
				t = new Trialiste(res.getDouble("taille"), res.getInt("nbrVitesse"), res.getString("typeAmortisseur"),
						res.getDouble("poids"));
				t.setIDCategorie(res.getInt("idCategorie"));
				t.setLibelle("Trialiste");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return t;
	}

}
