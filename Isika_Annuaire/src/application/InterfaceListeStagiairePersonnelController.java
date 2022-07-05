package application;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fr.isika.cda18.projet1Annuaire.model.Noeud;
import fr.isika.cda18.projet1Annuaire.model.Stagiaire;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.print.Printer.MarginType;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InterfaceListeStagiairePersonnelController implements Initializable {
	@FXML
	private Button btnAjouter;

	@FXML
	private Button btnImpression;

	@FXML
	private Button btnDeconnexion;

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
	private TextField txtNomRecherche;

	@FXML
	private TextField txtPrenomRecherche;

	@FXML
	private TextField txtDepartementRecherche;

	@FXML
	private TextField txtNomPromoRecherche;

	@FXML
	private TextField txtAnneePromoRecherche;

	@FXML
	private TableColumn<Stagiaire, String> nom;

	@FXML
	private TableColumn<Stagiaire, String> prenom;

	@FXML
	private TableColumn<Stagiaire, String> departement;

	@FXML
	private TableColumn<Stagiaire, String> nomPromo;

	@FXML
	private TableColumn<Stagiaire, String> anneePromo;

	@FXML
	private TableView<Stagiaire> tblStagiaire;

	@FXML
	private void btnAjouter(Event e) throws IOException {
		Main m = new Main();
		m.changeScene("InterfaceAjoutStagiaire.fxml");

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("Nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("Prenom"));
		departement.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("Departement"));
		nomPromo.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nomPromo"));
		anneePromo.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("anneePromo"));

		tblStagiaire.setItems(Main.stagiaire);

	}

	@FXML
	private void btnImpression(Event e) {

		Printer myPrinter = Printer.getDefaultPrinter();
		myPrinter.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, MarginType.HARDWARE_MINIMUM);
		PrinterJob printerJob = PrinterJob.createPrinterJob(myPrinter);
		ObservableList<Stagiaire> stagiaires = tblStagiaire.getItems();
		Label printed = new Label();
		for (Stagiaire stagiaire : stagiaires) {
			printed.setText(printed.getText() + stagiaire.toString() + "\n");
		}
		while (printerJob.getJobStatus() != PrinterJob.JobStatus.CANCELED && printerJob.printPage(printed)) {
			if (printed.getText().length() > 2752) {
				printed.setText(printed.getText().substring(2752));
			} else {
				break;
			}
		}
		if (printerJob.getJobStatus() == PrinterJob.JobStatus.PRINTING) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Impression");
			alert.setHeaderText("");
			alert.setContentText("Impression terminée");
			alert.showAndWait();
		}
		printerJob.endJob();
	}

	@FXML
	private void btnRechecher(Event e) throws IOException {
		String nomRecherche = txtNomRecherche.getText();
		String prenomRecherche = txtPrenomRecherche.getText();
		String departementRecherche = txtDepartementRecherche.getText();
		String nomPromoRecherche = txtNomPromoRecherche.getText();
		String anneePromoRecherche = txtAnneePromoRecherche.getText();
		Stagiaire stagiaire = new Stagiaire(nomRecherche, prenomRecherche, departementRecherche, nomPromoRecherche,
				anneePromoRecherche);
		try {
			RandomAccessFile raf = new RandomAccessFile("src/mesFichiers/listeStagiaire.bin", "rw");
			ObservableList<Stagiaire> listeDeRecherche = Noeud.rechercheStagiaire(raf, stagiaire);
			tblStagiaire.setItems(listeDeRecherche);
		} catch (Exception e1) {
		}
		reinitialiserFom();
	}

	public void reinitialiserFom() {
		txtNomStagiaire.clear();
		txtPrenomStagiaire.clear();
		txtDepartement.clear();
		txtNomPromo.clear();
		txtAnneePromo.clear();
	}

	@FXML
	private void btnDeconnexion(Event e) throws IOException {
		Main m = new Main();
		m.changeScene("InterfaceLogin.fxml");

	}

}
