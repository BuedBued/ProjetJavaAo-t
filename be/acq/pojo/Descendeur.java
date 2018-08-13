package be.acq.pojo;

public class Descendeur extends VTT {
	private static final long serialVersionUID = -9074831186630555148L;
	private String typeFrein;
	
	public Descendeur() {
		super();
	}
	public Descendeur(double taille, int nbrVitesse, String typeAmortisseur) {
		super(taille, nbrVitesse, typeAmortisseur);
	}
	public Descendeur(double taille, int nbrVitesse, String typeAmortisseur, String typeFrein) {
		this(taille, nbrVitesse, typeAmortisseur);
		setTypeFrein(typeFrein);
	}
	
	public String getTypeFrein() {
		return typeFrein;
	}
	public void setTypeFrein(String typeFrein) {
		this.typeFrein = typeFrein;
	}
}
