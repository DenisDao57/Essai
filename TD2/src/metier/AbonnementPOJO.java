package metier;

public class AbonnementPOJO {
	private ClientPOJO client;
	private RevuePOJO revue;
	private String date_debut;
	private String date_fin;
	
	public ClientPOJO getClient() {
		return client;
	}
	public void setClient(ClientPOJO client) {
		this.client = client;
	}
	public RevuePOJO getRevue() {
		return revue;
	}
	public void setRevue(RevuePOJO revue) {
		this.revue = revue;
	}
	public String getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(String date_debut) {
		this.date_debut = date_debut;
	}
	public String getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(String date_fin) {
		this.date_fin = date_fin;
	}
	
	public AbonnementPOJO(ClientPOJO client, RevuePOJO revue, String date_debut, String date_fin) {
		super();
		this.client = client;
		this.revue = revue;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
	}
	
	@Override
	public String toString() {
		String newLine = System.getProperty("line.separator");
		return "AbonnementPOJO [client=" + client + ", revue=" + revue + ", date_debut=" + date_debut + ", date_fin="
				+ date_fin + "]"+newLine;
	}
	

}
