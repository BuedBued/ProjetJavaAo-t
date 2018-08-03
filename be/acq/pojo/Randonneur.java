package be.acq.pojo;

public class Randonneur extends VTT {
	private String idRandonneur;
	private double taillePetitPignon;
	private double tailleGrandPignon;
	
	public String getIdRandonneur() {
		return idRandonneur;
	}
	public void setIdRandonneur(String idRandonneur) {
		this.idRandonneur = idRandonneur;
	}
	public double getTaillePetitPignon() {
		return taillePetitPignon;
	}
	public void setTaillePetitPignon(double taillePetitPignon) {
		this.taillePetitPignon = taillePetitPignon;
	}
	public double getTailleGrandPignon() {
		return tailleGrandPignon;
	}
	public void setTailleGrandPignon(double tailleGrandPignon) {
		this.tailleGrandPignon = tailleGrandPignon;
	}
}
