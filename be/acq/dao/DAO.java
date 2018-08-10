package be.acq.dao;

import java.sql.Connection;

public abstract class DAO<T> {
	protected Connection connect = null;
	
	public DAO(Connection con) {
		this.connect = con;
	}
	
	public abstract boolean create(T obj);
	public abstract boolean delete(T obj);
	public abstract boolean update(T obj);
	public abstract T select(int id);
	public abstract T select(T obj); //Nécessaire pour récupérer un objet dans la base de données sans connaissance de l'ID
}
