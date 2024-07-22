package pharma;

public class Mouvement {
	int id,prix,quantite;
	String code,nom,date,nature,jour;
	public Mouvement(){
		
	}
	public Mouvement(int id, int prix, int quantite, String code, String nom, String date, String nature,String jour) {
		super();
		this.id = id;
		this.prix = prix;
		this.quantite = quantite;
		this.code = code;
		this.nom = nom;
		this.date = date;
		this.nature = nature;
		this.jour=jour;
	}
	public int getId() {
		return id;
	}
	public int getPrix() {
		return prix;
	}
	public int getQuantite() {
		return quantite;
	}
	public String getCode() {
		return code;
	}
	public String getNom() {
		return nom;
	}
	public String getDate() {
		return date;
	}
	public String getNature() {
		return nature;
	}
	public String getJour() {
		return jour;
	}
	


}

