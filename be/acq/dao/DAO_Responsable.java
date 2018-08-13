package be.acq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.acq.pojo.*;

public class DAO_Responsable extends DAO<Responsable> {

	public DAO_Responsable(Connection con) {
		super(con);
	}
	@Override
	public boolean create(Responsable obj) {
		boolean b = false;
		PreparedStatement stmt = null;
		try {
			stmt = connect.prepareStatement("INSERT INTO Responsable (idPersonne,idCategorie) VALUES (?,?)");
			stmt.setInt(1, obj.getIDPersonne());
			stmt.setInt(2, obj.getCategorie().getIDCategorie());
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
	public boolean delete(Responsable obj) {
		boolean b = false;
		PreparedStatement stmt = null;
		try {
			//Preparation de la commande SQL
			stmt = connect.prepareStatement("DELETE FROM Responsable WHERE idResponsable = ?");
			stmt.setInt(1, obj.getIDResponsable());
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
	public boolean update(Responsable obj) { //Pas utilisée normalement
		boolean b = false;
		PreparedStatement stmt = null;
		try {
			//Preparation de la commande SQL
			stmt = connect.prepareStatement("UPDATE Responsable SET idCategorie = ? WHERE idResponsable = ?");
			stmt.setInt(1, obj.getCategorie().getIDCategorie());
			stmt.setInt(2, obj.getIDResponsable());
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
	public Responsable select(int id) {
		Responsable r = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			stmt = connect.prepareStatement("SELECT * FROM Responsable WHERE idResponsable = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			if(res.first()){
				r = new Responsable();
				r.setIDResponsable(id);
				r.setIDPersonne(res.getInt("idPersonne"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return r;
	}

}
