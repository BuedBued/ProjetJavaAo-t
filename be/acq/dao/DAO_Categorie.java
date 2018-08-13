package be.acq.dao;

import java.sql.Connection;

import be.acq.pojo.*;

public class DAO_Categorie extends DAO<Categorie> {

	public DAO_Categorie(Connection con) {
		super(con);
	}

	@Override
	public boolean create(Categorie obj) { //Non utilisé
		return false;
	}

	@Override
	public boolean delete(Categorie obj) { //Non utilisé
		return false;
	}

	@Override
	public boolean update(Categorie obj) { //Non utilisé
		return false;
	}

	@Override
	public Categorie select(int id) { //[DEBUG] Non utilisé [Je crois]
		return null;
	}
}
