package fr.isika.cda18.projet1Annuaire.model;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Noeud implements InterfaceTaille{

	private Stagiaire stagiaire;
	private int filsGauche;
	private int filsDroit;



	// constructor
	public Noeud(Stagiaire stagiaire) {
		super();
		this.stagiaire = stagiaire;
		this.filsGauche = -1;
		this.filsDroit = -1;
	}

	// autre constructeur
	public Noeud(Stagiaire stagiaire, int filsGauche, int filsDroit) {
		super();
		this.stagiaire = stagiaire;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
	}
	// Setter and Getter

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public int getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(int filsGauche) {
		this.filsGauche = filsGauche;
	}

	public int getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(int filsDroit) {
		this.filsDroit = filsDroit;
	}


	@Override
	public String toString() {
		return "Noeud [stagiaire=" + stagiaire + ", filsGauche=" + filsGauche + ", filsDroit=" + filsDroit + "]";
	}

	public static Noeud lireNoeud(RandomAccessFile raf) {

		String nom = "";
		String prenom = "";
		String departement = "";
		String nomPromo = "";
		String anneePromo = "";
		int filsGauche = -1;
		int filsDroit = -1;

		try {

			for (int i = 0; i < TAILLE_NOM; i++) {

				nom += raf.readChar();
			}
			for (int i = 0; i < TAILLE_PRENOM; i++) {

				prenom += raf.readChar();
			}

			for (int i = 0; i < TAILLE_DEPARTEMENT; i++) {

				departement += raf.readChar();
			}

			for (int i = 0; i < TALLE_NOMPROMO; i++) {
				nomPromo += raf.readChar();
			}

			for (int i = 0; i < TAILLE_ANNEEPROMO; i++) {
				anneePromo += raf.readChar();
			}

			filsGauche = raf.readInt();
			filsDroit = raf.readInt();

		} catch (Exception e) {

		}
		Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, nomPromo, anneePromo);

		return new Noeud(stagiaire, filsGauche, filsDroit);

	}

	public static void ecrireNoeud(RandomAccessFile raf, Stagiaire stagiaire) {

		try {

			raf.writeChars(stagiaire.agrandirNom());
			raf.writeChars(stagiaire.agrandirPrenom());
			raf.writeChars(stagiaire.agrandirDepartement());
			raf.writeChars(stagiaire.agrandirNomPromo());
			raf.writeChars(stagiaire.agrandirAnneePromo());
			raf.writeInt(-1);
			raf.writeInt(-1);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void ecrireNoeudComplet(RandomAccessFile raf, Noeud stagiaire) {

		try {

			raf.writeChars(stagiaire.getStagiaire().agrandirNom());
			raf.writeChars(stagiaire.getStagiaire().agrandirPrenom());
			raf.writeChars(stagiaire.getStagiaire().agrandirDepartement());
			raf.writeChars(stagiaire.getStagiaire().agrandirNomPromo());
			raf.writeChars(stagiaire.getStagiaire().agrandirAnneePromo());
			raf.writeInt(stagiaire.filsGauche);
			raf.writeInt(stagiaire.filsDroit);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void ajouterNoeud(RandomAccessFile raf, Stagiaire noeudAAjouter) throws IOException {

		if (raf.length() == 0) {
			raf.seek(0);
			ecrireNoeud(raf, noeudAAjouter);
		} else {
			raf.seek(0);
			Noeud noeudCourant = lireNoeud(raf);
			noeudCourant.AjouterNoeudCourant(raf, noeudAAjouter);
		}
		
	}

	public void AjouterNoeudCourant(RandomAccessFile raf, Stagiaire noeudAAjouter) throws IOException {

		if (this.stagiaire.getNom().compareTo(noeudAAjouter.getNom()) > 0) {

			if (this.filsGauche == -1) {

				raf.seek(raf.getFilePointer() - 8);// position of pointer
				raf.writeInt((int) (raf.length() / TAILLE_NOEUD));
				raf.seek(raf.length());
				ecrireNoeud(raf, noeudAAjouter);
			} else {
				raf.seek(this.filsGauche * TAILLE_NOEUD);
				/*
				 * je me remets à la position prêt à lire le noeud. indice du nom correspond au
				 * nombre d'incide qui le précède. * ex : 5ème noeud mais indice 4.
				 */
				Noeud noeudFilsGauche = lireNoeud(raf);
				noeudFilsGauche.AjouterNoeudCourant(raf, noeudAAjouter);
			}
		} else {
			if (this.filsDroit == -1) {
				raf.seek(raf.getFilePointer() - 4);
				raf.writeInt((int) (raf.length() / TAILLE_NOEUD));
				raf.seek(raf.length());
				ecrireNoeud(raf, noeudAAjouter);
			} else {
				raf.seek(this.filsDroit * TAILLE_NOEUD);
				Noeud noeudFilsDroit = lireNoeud(raf);
				noeudFilsDroit.AjouterNoeudCourant(raf, noeudAAjouter);
			}
		}
	}

	public static String parcoursInfixe(RandomAccessFile raf) {

		try {
			String result = "";
			Noeud noeud = lireNoeud(raf);
			if (noeud.filsGauche != -1) {
				raf.seek(noeud.filsGauche * TAILLE_NOEUD);
				result += parcoursInfixe(raf);
			}
			result += " " + noeud.stagiaire;

			if (noeud.filsDroit != -1) {
				raf.seek(noeud.filsDroit * TAILLE_NOEUD);
				result += parcoursInfixe(raf);
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static void rechercheNom(RandomAccessFile raf, String nomRecherche) {
		try {
			long position = raf.getFilePointer();
			if (raf.length() == 0) {
				System.out.println("Il n'existe pas");
				// return false;
			}
			Noeud noeud = lireNoeud(raf);
			raf.seek(position);
			if (noeud.getStagiaire().getNom().trim().equalsIgnoreCase(nomRecherche)) {
				System.out.println(noeud.getStagiaire());
				// return true;
			} else if (noeud.getStagiaire().getNom().trim().compareToIgnoreCase(nomRecherche) > 0) {
				if (noeud.filsGauche == -1) {
					System.out.println("Il n'existe pas");
					// return false;
				} else {
					raf.seek(noeud.filsGauche * TAILLE_NOEUD);
					rechercheNom(raf, nomRecherche);
				}
			} else {
				if (noeud.filsDroit == -1) {
					System.out.println("Il n'existe pas");
					// return false;
				} else {
					raf.seek(noeud.filsDroit * TAILLE_NOEUD);
					rechercheNom(raf, nomRecherche);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		// return false;

	}

	public static Noeud supprimer(RandomAccessFile raf, String nomSupprime, int positionParent) {
		try {
			System.out.println("supprimer début");
			int position = (int) (raf.getFilePointer());
			if (raf.length() == 0) {
				return null;
			}
			Noeud noeudSupprime = lireNoeud(raf);
			raf.seek(position);
			if (nomSupprime.trim().equals(noeudSupprime.getStagiaire().getNom().trim())) {
				System.out.println("suppression trouvée");
				return supprimerRacine(raf, noeudSupprime, positionParent);
			}
			if (noeudSupprime.getStagiaire().getNom().trim().compareTo(nomSupprime) > 0) {
				System.out.println("cherche à gauche");
				if(noeudSupprime.filsGauche!=-1) {
				raf.seek(noeudSupprime.filsGauche * TAILLE_NOEUD);
				 supprimer(raf, nomSupprime, position);
				}//else
			} else {
				System.out.println("cherche à droite");
				if(noeudSupprime.filsDroit!=-1) {
				raf.seek(noeudSupprime.filsDroit * TAILLE_NOEUD);
				supprimer(raf, nomSupprime, position);
				}//else
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Noeud supprimerRacine(RandomAccessFile raf, Noeud noeudSupprime, int positionParent) {
		try {
			if (noeudSupprime.filsGauche == -1 && noeudSupprime.filsDroit == -1) {
				raf.seek(positionParent);
				Noeud noeudParent = lireNoeud(raf);
				if (noeudParent.getStagiaire().getNom().trim().compareTo(noeudSupprime.getStagiaire().getNom().trim()) > 0) {
					raf.seek(raf.getFilePointer() - 8);
					raf.writeInt(-1);
				} else {
					raf.seek(raf.getFilePointer() - 4);
					raf.writeInt(-1);
				}

			}else if (noeudSupprime.filsDroit == -1 || noeudSupprime.filsGauche==-1) {
				int position=(int)raf.getFilePointer();
				if(noeudSupprime.filsGauche!=-1) {
				raf.seek(noeudSupprime.filsGauche*TAILLE_NOEUD);
				System.out.println(raf.getFilePointer());
				Noeud noeudSupprimeFilsGauche=lireNoeud(raf);
				raf.seek(position);
				ecrireNoeudComplet(raf,noeudSupprimeFilsGauche);
				}else {
					raf.seek(noeudSupprime.filsDroit*TAILLE_NOEUD);
					Noeud noeudSupprimeFilsDroit=lireNoeud(raf);
					raf.seek(position);
					ecrireNoeudComplet(raf,noeudSupprimeFilsDroit);
				}
			}else {
				int position=(int)raf.getFilePointer();
				raf.seek(noeudSupprime.filsGauche*TAILLE_NOEUD);
				Noeud noeudSupprimeFilsGauche=lireNoeud(raf);
			Noeud f = dernierDescendant(raf, noeudSupprimeFilsGauche);
			noeudSupprime.stagiaire = f.stagiaire;
			raf.seek(position);
			ecrireNoeudComplet(raf, noeudSupprime);
			System.out.println(position);
			raf.seek(noeudSupprime.filsGauche*TAILLE_NOEUD);
			System.out.println(raf.getFilePointer());
			noeudSupprimeFilsGauche = supprimer(raf, f.stagiaire.nom,position);
//			raf.seek(noeudSupprime.filsGauche*TAILLE_NOEUD);
//			ecrireNoeudComplet(raf, noeudSupprimeFilsGauche);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Noeud dernierDescendant(RandomAccessFile raf, Noeud noeudSupprime) {
		try {
			if (noeudSupprime.filsDroit == -1) {
				return noeudSupprime;
			}
			raf.seek(noeudSupprime.filsDroit * TAILLE_NOEUD);
			Noeud noeudFilsDroit = lireNoeud(raf);
			return dernierDescendant(raf, noeudFilsDroit);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static void remplirListe(RandomAccessFile raf) {

		try {
			
			Noeud noeud = lireNoeud(raf);
			if (noeud.filsGauche != -1) {
				raf.seek(noeud.filsGauche * TAILLE_NOEUD);
				remplirListe(raf);
			}
			Main.stagiaire.add(noeud.getStagiaire());

			if (noeud.filsDroit != -1) {
				raf.seek(noeud.filsDroit * TAILLE_NOEUD);
				remplirListe(raf);
			}
			 
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void remplirFichierBinaire(RandomAccessFile raf) {
		try {
			
			ListeStagiaireRecup lb = new ListeStagiaireRecup();

			List<Stagiaire> listeStagiaire = lb.recupListStagiaires();
			Noeud noeud = new Noeud(null); // put a intern in a node - pour chaque stagiaire
			for (Stagiaire stagiaire : listeStagiaire) {
				raf.seek(0);
				noeud.ajouterNoeud(raf, stagiaire);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ObservableList<Stagiaire> rechercheStagiaire(RandomAccessFile raf, Stagiaire stagiaireRecherche) {
		try {
			ObservableList<Stagiaire> stagiaire = FXCollections.observableArrayList();
			Noeud noeud = lireNoeud(raf);
			if (noeud.filsGauche != -1) {
				raf.seek(noeud.filsGauche * TAILLE_NOEUD);
				stagiaire.addAll(rechercheStagiaire(raf, stagiaireRecherche));
			}
			if ((stagiaireRecherche.getNom().isBlank() || noeud.getStagiaire().getNom().trim().equalsIgnoreCase(stagiaireRecherche.getNom().trim()))
			&& (stagiaireRecherche.getPrenom().isBlank() || noeud.getStagiaire().getPrenom().trim().equalsIgnoreCase(stagiaireRecherche.getPrenom().trim()))
			&& (stagiaireRecherche.getDepartement().isBlank() || noeud.getStagiaire().getDepartement().trim().equalsIgnoreCase(stagiaireRecherche.getDepartement().trim()))
			&& (stagiaireRecherche.getNomPromo().isBlank() || noeud.getStagiaire().getNomPromo().trim().equalsIgnoreCase(stagiaireRecherche.getNomPromo().trim()))
			&& (stagiaireRecherche.getAnneePromo().isBlank() || noeud.getStagiaire().getAnneePromo().trim().equalsIgnoreCase(stagiaireRecherche.getAnneePromo().trim()))){
				
				stagiaire.add(noeud.getStagiaire());
			}

			if (noeud.filsDroit != -1) {
				raf.seek(noeud.filsDroit * TAILLE_NOEUD);
				stagiaire.addAll(rechercheStagiaire(raf, stagiaireRecherche));
			}
			return stagiaire;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	
	
}
