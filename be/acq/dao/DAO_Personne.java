package be.acq.dao;

import be.acq.pojo.Personne;
import java.sql.*;

public class DAO_Personne extends DAO<Personne> {
	public DAO_Personne(Connection con) {
		super(con);
	}

	@Override
	public boolean create(Personne obj) {
		boolean b = false;
		PreparedStatement stmt = null;
		try {
			stmt = connect.prepareStatement("INSERT INTO Personne (nomPersonne,prenomPersonne,dateNaissancePersonne,"
					+ "telephonePersonne,mailPersonne,mdpPersonne) "
					+ "VALUES (?,?,?,?,?,?)");
			stmt.setString(1, obj.getNom());
			stmt.setString(2, obj.getPrenom());
			stmt.setDate(3, (Date)obj.getDateNaissance()); //Convertion java.util.Date en java.sql.Date
			stmt.setString(4, obj.getTel());
			stmt.setString(5, obj.getMail());
			stmt.setString(6, obj.getMdp());
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
					+ "dateNaissancePersonne = ?, telephonePersonne = ?, mailPersonne = ?, mdpPersonne = ? "
					+ "WHERE idPersonne = ?");
			stmt.setString(1, obj.getNom());
			stmt.setString(2, obj.getPrenom());
			stmt.setDate(3,(Date)obj.getDateNaissance());
			stmt.setString(4, obj.getTel());
			stmt.setString(5, obj.getMail());
			stmt.setString(6, obj.getMdp());
			stmt.setInt(7, obj.getIDPersonne());
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
						res.getDate("dateNaissancePersonne"), res.getString("telephonePersonne"), res.getString("mailPersonne"),
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
	public Personne select(Personne obj) {
		Personne p = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try{
			stmt = connect.prepareStatement("SELECT * FROM Personne WHERE nomPersonne = ? AND prenomPersonne = ? "
					+ "AND mdpPersonne = ? ",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, obj.getNom());
			stmt.setString(2, obj.getPrenom());
			stmt.setString(3, obj.getMdp());
			res = stmt.executeQuery();
			if(res.first()){
				p = new Personne(res.getString("nomPersonne"), res.getString("prenomPersonne"), 
						res.getDate("dateNaissancePersonne"), res.getString("telephonePersonne"), res.getString("mailPersonne"),
						res.getString("mdpPersonne"));
				p.setIDPersonne(res.getInt("idPersonne"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return p;
	}
	
}
