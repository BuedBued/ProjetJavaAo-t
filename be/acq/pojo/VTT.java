package be.acq.pojo;

public abstract class VTT extends Categorie{
	private static final long serialVersionUID = 959352147911027482L;
	private String typeAmortisseur;

	public VTT() {
		super();
	}
	public VTT(double taille, int nbrVitesse) {
		super(taille, nbrVitesse);
	}
	public VTT(double taille, int nbrVitesse, String typeAmortisseur) {
		this(taille,nbrVitesse);
		setTypeAmortisseur(typeAmortisseur);
	}
	
	public String getTypeAmortisseur() {
		return typeAmortisseur;
	}
	public void setTypeAmortisseur(String typeAmortisseur) {
		this.typeAmortisseur = typeAmortisseur;
	}
}
