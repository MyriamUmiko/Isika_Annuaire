package fr.isika.cda18.projet1Annuaire.model;

public class Stagiaire implements InterfaceTaille{

	// Attributes
	public String nom;
	public String prenom;
	public String departement;
	public String nomPromo;
	public String anneePromo;

	// constructor
	public Stagiaire(String nom, String prenom, String departement, String nomPromo, String anneePromo) {
		this.nom=nom;
		this.prenom=prenom;
		this.departement = departement;
		this.nomPromo = nomPromo;
		this.anneePromo = anneePromo;
	}

	// Getters & Setters
	
	public String getDepartement() {
		return departement;
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

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getNomPromo() {
		return nomPromo;
	}

	public void setNomPromo(String nomPromo) {
		this.nomPromo = nomPromo;
	}

	public String getAnneePromo() {
		return anneePromo;
	}

	public void setAnneePromo(String anneePromo) {
		this.anneePromo = anneePromo;
	}

	// methods
	@Override
	public String toString() {
		return super.toString() + departement + nomPromo + anneePromo;
	}

	// specifics methods

	// agrandir le nom :

	public String agrandirNom() {
		String nomLong = this.nom;
		for (int i = nom.length(); i <TAILLE_NOM; i++) { // pour remplir l'espace entre le nom et la value max name.
			nomLong += " ";
		}
		return nomLong;
	}

	public String agrandirPrenom() {
		String prenomLong = this.prenom;
		for (int i = prenom.length(); i < TAILLE_PRENOM; i++) {
			prenomLong += " ";
		}
		return prenomLong;
	}

	public String agrandirDepartement() {
		String departementLong = this.departement;
		for (int i = departement.length(); i < TAILLE_DEPARTEMENT; i++) {
			departementLong += " ";
		}
		return departementLong;
	}

	public String agrandirNomPromo() {
		String nomPromoLong = this.nomPromo;
		for (int i = nomPromo.length(); i < TALLE_NOMPROMO; i++) {
			nomPromoLong += " ";
		}
		return nomPromoLong;
	}

	public String agrandirAnneePromo() {
		String anneePromoLong = this.anneePromo;
		for (int i = anneePromo.length(); i < TAILLE_ANNEEPROMO; i++) {
			anneePromoLong += " ";
		}
		return anneePromoLong;
	}

}
