package application;

import java.io.IOException;
import java.io.RandomAccessFile;

import fr.isika.cda18.projet1Annuaire.model.Noeud;
import fr.isika.cda18.projet1Annuaire.model.Stagiaire;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static ObservableList<Stagiaire> stagiaire = FXCollections.observableArrayList();
	
	static Utilisateur user = new Utilisateur(false);
	
	private static Stage stg;
	
	@Override
	public void start(Stage primaryStage) {
		stg=primaryStage;
		primaryStage.setResizable(false); //impossible d'agrendir la fenêtre
		try {
			RandomAccessFile raf = new RandomAccessFile("src/mesFichiers/listeStagiaire.bin", "rw");
			if (raf.length() == 0) {
				Noeud.remplirFichierBinaire(raf);
				raf.seek(0);
				Noeud.remplirListe(raf);
			} else {
				raf.seek(0);
				Noeud.remplirListe(raf);
			}
//			 raf.setLength(0); 
//			 Noeud.remplirFichierBinaire(raf);
//			 raf.seek(0);
//			Noeud.remplirListe(raf);
			System.out.println(stagiaire.size());
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("InterfaceLogin.fxml"));
			Scene scene = new Scene(root, 1368, 768);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeScene (String fxml) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
		stg.getScene().setRoot(pane);
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
