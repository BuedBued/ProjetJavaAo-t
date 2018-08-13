package be.acq.pojo;

public class Tresorier extends Personne {
	private static final long serialVersionUID = 2220704978800452838L;
	private int idTresorier;
	
	public Tresorier() {
		super();
	}
	public Tresorier(String nom, String prenom, String tel, String mail, String mdp) {
		super(nom,prenom,tel,mail,mdp);
	}

	public int getIDTresorier() {
		return idTresorier;
	}
	public void setIDTresorier(int idTresorier) {
		this.idTresorier = idTresorier;
	}
	
	
}
