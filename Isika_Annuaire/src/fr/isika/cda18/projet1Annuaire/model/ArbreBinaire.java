package fr.isika.cda18.projet1Annuaire.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArbreBinaire {

	// Attributes
	private Noeud racine;

	// Constructor
	public ArbreBinaire() {

	}

//	public static Noeud lireNoeud(RandomAccessFile raf) {
//
//		String nom = "";
//		String prenom = "";
//		String departement = "";
//		String nomPromo = "";
//		String anneePromo = "";
//		int filsGauche = -1;
//		int filsDroit = -1;
//
//		try {
//
//			for (int i = 0; i < Stagiaire.TAILLE_NOM; i++) {
//
//				nom += raf.readChar();
//			}
//			for (int i = 0; i < Stagiaire.TAILLE_PRENOM; i++) {
//
//				prenom += raf.readChar();
//			}
//
//			for (int i = 0; i < Stagiaire.TAILLE_DEPARTEMENT; i++) {
//
//				departement += raf.readChar();
//			}
//
//			for (int i = 0; i < Stagiaire.TALLE_NOMPROMO; i++) {
//				nomPromo += raf.readChar();
//			}
//
//			for (int i = 0; i < Stagiaire.TAILLE_ANNEEPROMO; i++) {
//				anneePromo += raf.readChar();
//			}
//
//			filsGauche = raf.readInt();
//			filsDroit = raf.readInt();
//
//		} catch (Exception e) {
//
//		}
//		Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, nomPromo, anneePromo);
//
//		return new Noeud(stagiaire, filsGauche, filsDroit);
//
//	}
//	
//	public void ecrireNoeud(RandomAccessFile raf, Stagiaire stagiaire) {
//
//		try {
//
//			raf.writeChars(stagiaire.agrandirNom());
//			raf.writeChars(stagiaire.agrandirPrenom());
//			raf.writeChars(stagiaire.agrandirDepartement());
//			raf.writeChars(stagiaire.agrandirNomPromo());
//			raf.writeChars(stagiaire.agrandirAnneePromo());
//			raf.writeInt(-1);
//			raf.writeInt(-1);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}


	// Methods
	public void ajouterNoeud(Noeud ajouterNoeud) {

		// Créer une liste de stagaire
		// List<Stagiaire> listStagiaire = new ArrayList<>();

		Noeud noeud = new Noeud(null);

//		
//		try {
//			 //lecture du fichier Stagiaire.DON
//			FileReader fr = new FileReader("src/mesFichiers/STAGIAIRES.DON");
//			BufferedReader br = new BufferedReader(fr);
//			// Création du fichier bin
//			RandomAccessFile raf = new RandomAccessFile("src/mesFichiers/listeStagiaire.bin", "rw");
//				
//			
//			// Si la longueur de mon RAF est vide
//			if (raf.length() == 0) {
//
//				// Je pars du curseur à zéro
//				// Curseur, pointeur pour se mettre à 0
//				raf.seek(0);
//					
//				for (Stagiaire stagiaire : listStagiaire) {
//					
//				
//				// J'écris dans l'ajout du noeud le nom, prenom etc LONG
//				raf.writeChars(ajouterNoeud.getStagiaire().agrandirNom()); // partir de l'obj que je veux rajouter et ajout// d'argument.
//																			
//				raf.writeChars(ajouterNoeud.getStagiaire().agrandirPrenom());
//				raf.writeChars(ajouterNoeud.getStagiaire().agrandirDepartement());
//				raf.writeChars(ajouterNoeud.getStagiaire().agrandirNomPromo());
//				raf.writeChars(ajouterNoeud.getStagiaire().agrandirAnneePromo());
//				raf.writeInt(-1);// pour le filsGauche indice
//				raf.writeInt(-1);// pour le filsDroit indice
//				}
//				
//			} else { // attribuer taille à chaque attribut dans 1 1Er temps
//				raf.seek(0); // always start -
//
//					String nom = " ";
//
//					// Je lis les x caractères de l'attribut String
//					for (int i = 0; i < Stagiaire.TAILLE_NOM; i++) {
//
//						nom += raf.readChar();
//
//					}
//					System.out.println(  nom.trim());// la méthode trim enlève les espaces avant
//																				// et après
//																				// la chaine de caratère
//
//					String prenom = "";
//					for (int i = 0; i < Stagiaire.TAILLE_PRENOM; i++) {
//						prenom += raf.readChar();
//					}
//					System.out.println(prenom.trim());
//
//					String departement = "";
//					for (int i = 0; i < Stagiaire.TAILLE_DEPARTEMENT; i++) {
//						departement += raf.readChar();
//					}
//					System.out.println(departement);
//
//					String nomPromo = "";
//					for (int i = 0; i < Stagiaire.TAILLE_NOMPROMO; i++) {
//						nomPromo += raf.readChar();
//					}
//					System.out.println(nomPromo);
//
//					String anneePromo = "";
//					for (int i = 0; i < Stagiaire.TAILLE_ANNEEPROMO; i++) {
//						anneePromo += raf.readChar();
//					}
//					System.out.println(anneePromo);
//				
//			}
//			// raf.lireStagiaireDansAjoutNoeud();
//			raf.close();
//		} catch (IOException e) {
//
//			e.printStackTrace();
//
//		}

	}
//	public Stagiaire lireNoeud(RandomAccessFile raf) {
//
//		String nom = "";
//		String prenom = "";
//		String departement = "";
//		String nomPromo = "";
//		String anneePromo = "";
//
//		
//
//		try {
//
//			for (int i = 0; i < Stagiaire.TAILLE_NOM; i++) {
//
//				nom += raf.readChar();
//
//			}
//
//			for (int i = 0; i < Stagiaire.TAILLE_PRENOM; i++) {
//				prenom += raf.readChar();
//			}
//
//			for (int i = 0; i < Stagiaire.TAILLE_DEPARTEMENT; i++) {
//				departement += raf.readChar();
//			}
//
//			for (int i = 0; i < Stagiaire.TAILLE_NOMPROMO; i++) {
//				nomPromo += raf.readChar();
//			}
//
//			for (int i = 0; i < Stagiaire.TAILLE_ANNEEPROMO; i++) {
//				anneePromo += raf.readChar();
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, nomPromo, anneePromo);
//
//		return stagiaire;
//	}

}
