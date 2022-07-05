package fr.isika.cda18.projet1Annuaire.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ListeStagiaireRecup {// test

	public List<Stagiaire> recupListStagiaires() {

		List<Stagiaire> listeStagiaire = new ArrayList<>();

		Noeud noeud = new Noeud(null);

		ArbreBinaire arbreBinaire = new ArbreBinaire();

		// stocker les données dans la liste - pour la lecture du fichier STAGIAIRE.DON

		try {
			// LECTURE DU FICHIER .DON
			// ouvre un flux du fichier vers le programme
			FileReader fr = new FileReader("src/mesFichiers/STAGIAIRES.DON");
			BufferedReader br = new BufferedReader(fr);

			// tant qu'il y a quelque chose à lire, lit la ligne
			while (br.ready()) {
				Stagiaire stagiaire = new Stagiaire(null, null, null, null, null);
				stagiaire.setNom(br.readLine());
				stagiaire.setPrenom(br.readLine());
				stagiaire.setDepartement(br.readLine());
				stagiaire.setNomPromo(br.readLine());
				stagiaire.setAnneePromo(br.readLine());
				br.readLine();
				listeStagiaire.add(stagiaire);

			}

			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listeStagiaire;

	}

}
