package be.acq.pojo;

public class Cyclo extends Categorie {
	private static final long serialVersionUID = -4923348525774721749L;
	private double diametreRoue;
	
	public Cyclo() {
		super();
	}
	public Cyclo(double taille, int nbrVitesse) {
		super(taille, nbrVitesse);
	}
	public Cyclo(double taille, int nbrVitesse, double diametreRoue) {
		this(taille,nbrVitesse);
		setDiametreRoue(diametreRoue);
	}
	
	public double getDiametreRoue() {
		return diametreRoue;
	}
	public void setDiametreRoue(double diametreRoue) {
		this.diametreRoue = diametreRoue;
	}
}
