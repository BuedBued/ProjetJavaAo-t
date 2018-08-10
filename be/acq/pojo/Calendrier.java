package be.acq.pojo;

import java.io.Serializable;

public class Calendrier implements Serializable {
	private static final long serialVersionUID = 956401323849234267L;
	private String idCalendrier;

	public String getIdCalendrier() {
		return idCalendrier;
	}
	public void setIdCalendrier(String idCalendrier) {
		this.idCalendrier = idCalendrier;
	}
}
