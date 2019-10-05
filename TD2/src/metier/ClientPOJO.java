package metier;


public class ClientPOJO {
	
		// champs
	
		private int id_client;
		private String nom;
		private String prenom;
		private String no_rue;
		private String voie;
		private String code_postal;
		private String ville;
		private String pays;
		
		// getters & setters
		
		public int getId_client() {
			return id_client;
		}
		public void setId_client(int id_client) {
			this.id_client = id_client;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		public String getNo_rue() {
			return no_rue;
		}
		public void setNo_rue(String no_rue) {
			this.no_rue = no_rue;
		}
		public String getVoie() {
			return voie;
		}
		public void setVoie(String voie) {
			this.voie = voie;
		}
		public String getCode_postal() {
			return code_postal;
		}
		public void setCode_postal(String code_postal) {
			this.code_postal = code_postal;
		}
		public String getVille() {
			return ville;
		}
		public void setVille(String ville) {
			this.ville = ville;
		}
		public String getPays() {
			return pays;
		}
		public void setPays(String pays) {
			this.pays = pays;
		}
		
		// ToString
		@Override
		public String toString() {
			String newLine = System.getProperty("line.separator");

			return "ClientPOJO [id_client=" + id_client + ", nom=" + nom + ", prenom=" + prenom + ", no_rue=" + no_rue
					+ ", voie=" + voie + ", code_postal=" + code_postal + ", ville=" + ville + ", pays=" + pays + "]"+newLine;
		}
		
		// Constructeurs
		
		public ClientPOJO(int id_client, String nom, String prenom, String no_rue, String voie, String code_postal,
				String ville, String pays) {
			super();
			this.id_client = id_client;
			this.nom = nom;
			this.prenom = prenom;
			this.no_rue = no_rue;
			this.voie = voie;
			this.code_postal = code_postal;
			this.ville = ville;
			this.pays = pays;
		}
	
		
}
