package be.acq.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class Balade implements Serializable {
	private static final long serialVersionUID = -2663266308936877512L;
	private int idBalade;
	private String localite;
	private String CP;
	private String rue;
	private String num;
	private String date;
	private double forfait;
	private ArrayList<Vehicule> listCovoiturage;
	
	public Balade() {}
	public Balade(String localite, String CP, String rue, String num, String date, double forfait) {
		setLocalite(localite);
		setCP(CP);
		setRue(rue);
		setNum(num);
		setDate(date);
		setForfait(forfait);
	}
	
	public int getIDBalade() { 
		return idBalade;
	}
	public void setIDBalade(int idBalade) {
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
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
	public double getForfait() {
		return forfait;
	}
	public void setForfait(double forfait) {
		this.forfait = forfait;
	}
	
	public String display() {
		return date + " " + rue + " " + num + " " + CP + " " + localite;
	}
}
