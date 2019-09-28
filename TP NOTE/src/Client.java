import java.util.ArrayList;

public abstract class Client {
	private String nom;
	private ArrayList<Disque> loue = new ArrayList<>();
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public ArrayList<Disque> getLoue() {
		return loue;
	}
	public void setLoue(ArrayList<Disque> loue) {
		this.loue = loue;
	}
	public Client(String nom, ArrayList<Disque> loue) {
		super();
		this.nom = nom;
		this.loue = loue;
	}
	public void Ajouter(Disque a) {
		loue.add(a);
	}
	public void Enlever(int i) {
		loue.remove(i);
	}
	@Override
	public String toString() {
		return "Client [nom=" + nom + ", loue=" + loue + "]";
	}
	
	
}
