package be.acq.pojo;

public class Randonneur extends VTT {
	private static final long serialVersionUID = -5620715460716844169L;
	private double taillePetitPignon;
	private double tailleGrandPignon;
	
	public Randonneur() {
		super();
	}
	public Randonneur(double taille, int nbrVitesse, String typeAmortisseur) {
		super(taille, nbrVitesse, typeAmortisseur);
	}
	public Randonneur(double taille, int nbrVitesse, String typeAmortisseur, double taillePetitPignon, 
			double tailleGrandPignon) {
		this(taille, nbrVitesse, typeAmortisseur);
		setTaillePetitPignon(taillePetitPignon);
		setTailleGrandPignon(tailleGrandPignon);
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
