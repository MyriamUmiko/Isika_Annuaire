package fr.isika.cda18.projet1Annuaire.files;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import fr.isika.cda18.projet1Annuaire.model.ArbreBinaire;
import fr.isika.cda18.projet1Annuaire.model.ListeStagiaireRecup;
import fr.isika.cda18.projet1Annuaire.model.Noeud;
import fr.isika.cda18.projet1Annuaire.model.Stagiaire;

public class LanceurTestAnnuaireEcriture {

	// DECLARATION DU FICHIER BINAIRE RANDOM ACCESS
	public static RandomAccessFile raf;

	public static void main(String[] args) {

		// RECUP LISTE STAGIAIRE :

		try {
			// INITIALISATION DU FICHIER BIN AVEC NEW RANDOM
			raf = new RandomAccessFile("src/mesFichiers/listeStagiaire.bin", "rw");
			raf.setLength(0);
			ListeStagiaireRecup lb = new ListeStagiaireRecup();

			List<Stagiaire> listeStagiaire = lb.recupListStagiaires();


			
			Noeud noeud = new Noeud(null); // put a intern in a node - pour chaque stagiaire
			for (Stagiaire stagiaire : listeStagiaire) {
				
				noeud.ajouterNoeud(raf, stagiaire);
			}
			raf.seek(0); // comparaison de la taille du stagiaire e tla taille du noeud staigiare.

			for (int j = 0; j < raf.length() / Noeud.TAILLE_NOEUD; j++) {
				Noeud stagiaire = Noeud.lireNoeud(raf); 
														
			}
			raf.seek(0);
			System.out.println(Noeud.parcoursInfixe(raf));
			raf.seek(0);
			Noeud.rechercheNom(raf,"augereau");
			raf.seek(0);
			Noeud.supprimer(raf,"CHAVENEAU",0);
			raf.seek(0);
			System.out.println(Noeud.parcoursInfixe(raf));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
}
