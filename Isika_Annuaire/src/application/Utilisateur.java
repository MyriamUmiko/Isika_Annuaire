package application;

public class Utilisateur {
	/*ajout d'un bouléen pour pouvoir définir pour la partie login. Afin de savoir où retourner.
	objectif : se déconnecter d'une interface et se reconnecter sur une autre sans fermer la fenêtre.
		*/
	
private boolean admin;
	
	
	public boolean isAdmin() {
		return admin;
	}
	
	//Setter
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	//Constructeur
	public Utilisateur(boolean admin) {
		this.admin = admin;
	}
}
