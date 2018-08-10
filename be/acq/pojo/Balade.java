package be.acq.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Balade implements Serializable {
	private static final long serialVersionUID = -2663266308936877512L;
	private String idBalade;
	private String localite;
	private String CP;
	private String rue;
	private String num;
	private Date date;
	private ArrayList<Vehicule> listCovoiturage;
	
	public String getIdBalade() { 
		return idBalade;
	}
	public void setIdBalade(String idBalade) {
		this.idBalade = idBalade;
	}
	public String getLocalite() {
		return localite;
	}
	public void setLocalite(String localite) {
		this.localite = localite;
	}
	public String getCP() {
		return CP;
	}
	public void setCP(String cP) {
		CP = cP;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public ArrayList<Vehicule> getListCovoiturage() {
		return listCovoiturage;
	}
	public void setListCovoiturage(ArrayList<Vehicule> listCovoiturage) {
		this.listCovoiturage = listCovoiturage;
	}
	public boolean addCovoiturage(Vehicule v) {
		return listCovoiturage.add(v);
	}
	public boolean removeCovoiturage(Vehicule v) {
		return listCovoiturage.remove(v);
	}
}
