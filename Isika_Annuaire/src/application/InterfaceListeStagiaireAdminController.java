package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import fr.isika.cda18.projet1Annuaire.model.Noeud;
import fr.isika.cda18.projet1Annuaire.model.Stagiaire;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InterfaceListeStagiaireAdminController implements Initializable {

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
	private Button btnAjouter;

	@FXML
	private Button btnSupprimer;

	@FXML
	private Button btnUpdate;

	@FXML
	private Button btnImpression;

	@FXML
	private Button btnRechercher;

	@FXML
	private Button btnDeconnexion;
	
	@FXML
	private Button btnModifier;

	@FXML
	private void btnAjouter(Event e) throws IOException {
		Main m = new Main();
		m.changeScene("InterfaceAjoutStagiaire.fxml");
	}

	@FXML
	private void btnDeconnexion(Event e) throws IOException {
		Main m = new Main();
		m.changeScene("InterfaceLogin.fxml");
		
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
    public void btnModifier(ActionEvent actionEvent) {

        //set data
        Stagiaire singleStagiaire = tblStagiaire.getSelectionModel().getSelectedItem();
        String nom = singleStagiaire.getNom();
        String prenom = singleStagiaire.getPrenom();
        String departement = singleStagiaire.getDepartement();
        String nomPromo = singleStagiaire.getNomPromo();
        String anneePromo = singleStagiaire.getAnneePromo();

        //set textfield
        txtNomRecherche.setText(nom);
        txtPrenomRecherche.setText(prenom);
        txtDepartementRecherche.setText(departement);
        txtNomPromoRecherche.setText(nomPromo);
        txtAnneePromoRecherche.setText(anneePromo);
//    
    }
      @FXML
    public void btnUpdate(ActionEvent actionEvent) throws FileNotFoundException {
        
    	RandomAccessFile raf = new RandomAccessFile("src/mesFichiers/listeStagiaire.bin", "rw"); 
        Stagiaire singleStagiaire = tblStagiaire.getSelectionModel().getSelectedItem();
          
         
         //une ligne selectionné 
        String nom = txtNomRecherche.getText();
        String prenom = txtPrenomRecherche.getText();
        String departement = txtDepartementRecherche.getText();
        String nomPromo = txtNomPromoRecherche.getText();
        String anneePromo = txtAnneePromoRecherche.getText();
        
         
        //set update value
        singleStagiaire.setNom(nom);
        singleStagiaire.setPrenom(prenom);
        singleStagiaire.setDepartement(departement);
        singleStagiaire.setNomPromo(nomPromo);
        singleStagiaire.setAnneePromo(anneePromo);
        
        ObservableList<Stagiaire> listeDeRecherche=Noeud.rechercheStagiaire(raf, singleStagiaire);
		tblStagiaire.setItems(listeDeRecherche);
        
    }

	@FXML
	private void btnRechecher(Event e) throws IOException {
		String nomRecherche = txtNomRecherche.getText();
		String prenomRecherche = txtPrenomRecherche.getText();
		String departementRecherche = txtDepartementRecherche.getText();
		String nomPromoRecherche = txtNomPromoRecherche.getText();
		String anneePromoRecherche = txtAnneePromoRecherche.getText();
		Stagiaire stagiaire=new Stagiaire(nomRecherche, prenomRecherche, departementRecherche, nomPromoRecherche, anneePromoRecherche);
		try {
			RandomAccessFile raf = new RandomAccessFile("src/mesFichiers/listeStagiaire.bin", "rw");
			ObservableList<Stagiaire> listeDeRecherche=Noeud.rechercheStagiaire(raf, stagiaire);
			tblStagiaire.setItems(listeDeRecherche);
		}catch (Exception e1) {
		}
		
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
	private void btnSupprmier(ActionEvent actionEvent) throws IOException {
		Main m = new Main();
		//ObservableList<Stagiaire> allStagiaire, singleStagiaire;
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Suppression des données");
		alert.setHeaderText("Etes-vous sûr de vouloir supprimer cette ligne ?");
		Optional<ButtonType> option = alert.showAndWait();
		
		if(option.get()==ButtonType.OK) {
			Stagiaire singleStagiaire = tblStagiaire.getSelectionModel().getSelectedItem();
			
			
				RandomAccessFile raf = new RandomAccessFile("src/mesFichiers/listeStagiaire.bin", "rw");
				raf.seek(0);
				Noeud.supprimer(raf,singleStagiaire.getNom(),0);
		
			Main.stagiaire.remove(tblStagiaire.getSelectionModel().getSelectedItem());
			ObservableList<Stagiaire> listeDeRecherche=Noeud.rechercheStagiaire(raf, singleStagiaire);
			tblStagiaire.setItems(listeDeRecherche);
		} else if (option.get() == ButtonType.CANCEL){
			
		}
		
//		ObservableList<Stagiaire> allStagiaire, singleStagiaire;
		//allStagiaire = tblStagiaire.getItems();
//		singleStagiaire = tblStagiaire.getSelectionModel().getSelectedItems();
	//	singleStagiaire.forEach(allStagiaire::remove);
		
	}
	
}
