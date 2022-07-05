package application;

import java.io.IOException;
import java.io.RandomAccessFile;

import fr.isika.cda18.projet1Annuaire.model.Noeud;
import fr.isika.cda18.projet1Annuaire.model.Stagiaire;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InterfaceAjoutStagiaireController {
	
	public static RandomAccessFile raf;
	
	@FXML
	private Button btnEnregistrer;

	@FXML
	private Button btnVoirListe;

	@FXML
	private TextField txtNomStagiaire;

	@FXML
	private TextField txtPrenomStagiaire;

	@FXML
	private TextField txtDepartement;

	@FXML
	private TextField txtNomPromo;

	@FXML
	private TextField txtAnneePromo;
	

	@FXML
	public Label loginMessageLabel;

	@FXML
	public Label loginMessageLabel2;


	@FXML
	private void btnVoirListe(Event e) throws IOException {
		Main m = new Main();
		if (Main.user.isAdmin()) {
			m.changeScene("InterfaceListeStagiaireAdmin.fxml");
		} else {
			m.changeScene("InterfaceListeStagiairePersonnel.fxml");
		}
		}

	@FXML
	private void btnEnregistrer(Event e) throws IOException {
		String nom = txtNomStagiaire.getText();
		String prenom = txtPrenomStagiaire.getText();
		String departement = txtDepartement.getText();
		String nomPromo = txtNomPromo.getText();
		String anneePromo = txtAnneePromo.getText();

		if ((nom.equalsIgnoreCase("") || (prenom.equalsIgnoreCase("")) || (departement.equalsIgnoreCase(""))
				|| (nomPromo.equalsIgnoreCase("")) || (anneePromo.equalsIgnoreCase("")))) {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Complétion des informations");
			alert.setContentText("Veuillez saisir tous les champs *");
			alert.showAndWait();
			/// fin partie alert*/
			reinitialisationForm();
		} else {
			System.out.println("Nom : " + nom + " \nPrenom : " + prenom + " \nDepartement : " + departement
					+ " \nNom de la Promo : " + nomPromo + " \nAnnee de la Promo : " + anneePromo);
			
			Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, nomPromo, anneePromo);
			
			RandomAccessFile raf = new RandomAccessFile("src/mesFichiers/listeStagiaire.bin", "rw");
			Noeud.ajouterNoeud(raf, stagiaire);
			int l=Main.stagiaire.size();
			for (int i = 0; i < l; i++) {
				if(stagiaire.getNom().compareTo(Main.stagiaire.get(i).getNom())<0) {
					Main.stagiaire.add(i,stagiaire);
					break;
				}
				
			}
			//Main.stagiaire.Noeud.ajouterNoeud;
			loginMessageLabel.setText("L'enregistrement a bien été effectué...");
		}
		
		reinitialisationForm();
	}

	
	@FXML
	private void reinitialisationForm() {
		txtNomStagiaire.clear();
		txtPrenomStagiaire.clear();
		txtDepartement.clear();
		txtNomPromo.clear();
		txtAnneePromo.clear();

	}

}
