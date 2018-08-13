package be.acq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.acq.pojo.*;

public class DAO_Tresorier extends DAO<Tresorier> {

	public DAO_Tresorier(Connection con) {
		super(con);
	}

	@Override
	public boolean create(Tresorier obj) {
		boolean b = false;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = connect.prepareStatement("INSERT INTO Tresorier (idPersonne) VALUES (?)");
			stmt.setInt(1, obj.getIDPersonne());
			//Execution de la commande SQL
			stmt.executeUpdate();
			res = stmt.getGeneratedKeys();
			if(res.next()) {
				obj.setIDTresorier(res.getInt(1));
				b = true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean delete(Tresorier obj) {
		boolean b = false;
		PreparedStatement stmt = null;
		try {
			//Preparation de la commande SQL
			stmt = connect.prepareStatement("DELETE FROM Tresorier WHERE idTresorier = ?");
			stmt.setInt(1, obj.getIDTresorier());
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
	public boolean update(Tresorier obj) { //Non nécessaire
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tresorier select(int id) {
		Tresorier t = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			stmt = connect.prepareStatement("SELECT * FROM Tresorier WHERE idPersonne = ?",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			if(res.first()){
				t = new Tresorier();
				t.setIDPersonne(id);
				t.setIDTresorier(res.getInt("idTresorier"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return t;
	}
}
