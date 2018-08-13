package be.acq.pojo;

public class Trialiste extends VTT {
	private static final long serialVersionUID = -8022617265610360017L;
	private double poids;
	
	public Trialiste() {
		super();
	}
	public Trialiste(double taille, int nbrVitesse, String typeAmortisseur) {
		super(taille, nbrVitesse, typeAmortisseur);
	}
	public Trialiste(double taille, int nbrVitesse, String typeAmortisseur, double poids) {
		this(taille, nbrVitesse, typeAmortisseur);
		setPoids(poids);
	}

	public double getPoids() {
		return poids;
	}
	public void setPoids(double poids) {
		this.poids = poids;
	}
}
