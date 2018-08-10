package be.acq.pojo;

public class Trialiste extends VTT {
	private static final long serialVersionUID = -8022617265610360017L;
	private String idTrialiste;
	private double poids;
	public String getIdTrialiste() {
		return idTrialiste;
	}
	public void setIdTrialiste(String idTrialiste) {
		this.idTrialiste = idTrialiste;
	}
	public double getPoids() {
		return poids;
	}
	public void setPoids(double poids) {
		this.poids = poids;
	}
}
