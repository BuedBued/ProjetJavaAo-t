package be.acq.pojo;

public class Membre extends Personne {
	private static final long serialVersionUID = 1186729938582031733L;
	private String idMembre;
	private double solde;
	public String getIdMembre() {
		return idMembre;
	}
	public void setIdMembre(String idMembre) {
		this.idMembre = idMembre;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
}
