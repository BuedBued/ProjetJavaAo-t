package be.acq.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class Calendrier implements Serializable {
	private static final long serialVersionUID = 956401323849234267L; //[DEBUG] Nécessaire? 
	private String idCalendrier;
	private ArrayList<Balade> listBalade;
	public String getIdCalendrier() {
		return idCalendrier;
	}
	public void setIdCalendrier(String idCalendrier) {
		this.idCalendrier = idCalendrier;
	}
	public ArrayList<Balade> getListBalade() {
		return listBalade;
	}
	public void setListBalade(ArrayList<Balade> listBalade) {
		this.listBalade = listBalade;
	}
	public boolean addBalade(Balade b) {
		return listBalade.add(b);
	}
	public boolean removeBalade(Balade b) {
		return listBalade.remove(b);
	}
}
