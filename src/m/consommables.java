package m;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import c.connecteur;

public class consommables {

	/**
	* Fonction qui permet l'ajout d'un consommable a la bdd.<br/>
	*
	* @param prm_ref  La reference du consommable.
	* @param prm_quantite  La quantite a ajouter.
	* @param prm_modele  Le type de consommable (Cartouche, Toner, Tambour, etc).
	* 
	*/
	public static void ajout_conso(String prm_ref, int prm_quantite, String prm_modele) {
		connecteur bdd = new connecteur();
		String sql = "SELECT * FROM consommable WHERE reference_conso = \""+ prm_ref.toLowerCase() +"\"";
		List<String> reponse = bdd.select(sql, 3);
		Date date = new Date();
		SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
		String stringDate= DateFor.format(date);
		if(reponse.size( ) == 0 || reponse.size() > 1) {
		    sql = "INSERT INTO consommable ( reference_conso, stock, modele ) VALUES( \"" + prm_ref.toLowerCase() + "\"," + prm_quantite + ", (SELECT id_modele FROM modele WHERE modele = \"" + prm_modele.toLowerCase() + "\") )";
		    bdd.requeteSansReponse(sql);
		    
		    historique.ajouter_histo(stringDate, prm_quantite, prm_ref.toLowerCase());
		}else {
			int stock= 0;
			for (String ligne : reponse) {
				String[] tempSplit = ligne.split(";");
				stock = Integer.parseInt(tempSplit[1]);
				stock += prm_quantite;
			}
			
			
			sql = "UPDATE consommable SET stock = " + stock + "  WHERE reference_conso = \"" + prm_ref.toLowerCase() + "\"";
			bdd.requeteSansReponse(sql);
			historique.ajouter_histo(stringDate, prm_quantite, prm_ref.toLowerCase());
		}
	}
	
	/**
	 * Permet de retirer du stock a un consommable.
	 * 
	 * @param reference La reference du consommable.
	 * @param date  La date du jour.
	 * @param batiment Le batiment dans lequel est l'imprimante.
	 * @param numeroSalle Le numéro de la salle dans lequel est l'imprimante.
	 * @param nom Le nom de la personne qui demande un consommable.
	 * @param prenom Le prenom de la personne qui demande un consommable.
	 * @param qte Le nombre de consommable qui sont retirer
	 * @return </br> <code>1</code> Erreur au niveau de la référence. 
	 * </br> <code>2</code> Erreur au niveau du batiment ou de la salle.
	 * </br> <code>3</code> Erreur au niveau de l'imprimante. 
	 * </br> <code>4</code> Erreur au niveau de la personne qui demande les consommables.
	 * </br> <code>5</code> Erreur au niveau du stock de la reference.
	 * </br> <code>6</code> Seuil atteint.
	 */
	public static int retire_conso(String reference, String date, String batiment, String numeroSalle, String nom, String prenom, int qte) {
		connecteur bdd = new connecteur();
		String sql = "SELECT * FROM consommable WHERE reference_conso = \"" + reference.toLowerCase() + "\"";
		List<String> reponse = bdd.select(sql, 3);
		if(reponse.size() == 0) {
			return 1;
		} else {
			sql = "SELECT * FROM salle WHERE batiment = \"" + batiment.toUpperCase() + "\" AND designation = \"" + numeroSalle.toUpperCase() + "\"";
			reponse = bdd.select(sql, 3);
			if(reponse.size() == 0) {
				return 2;
			} else {
				sql = "SELECT * FROM modele_imprimante INNER JOIN salle ON modele_imprimante.id_salle = salle.id_salle WHERE batiment = \"" + batiment.toUpperCase() + "\" AND designation = \"" + numeroSalle.toUpperCase() + "\"";
				reponse = bdd.select(sql, 3);
				if(reponse.size() == 0) {
					return 3;
				} else {
					sql = "SELECT * FROM utilisateur WHERE prenom = \""+ prenom.toLowerCase() +"\" AND nom = \""+ nom.toLowerCase() +"\"";
					reponse = bdd.select(sql, 4);
					if(reponse.size() == 0) {
						return 4;
					} else {
						sql = "SELECT * FROM consommable WHERE reference_conso = \"" + reference.toLowerCase() + "\"";
						reponse = bdd.select(sql, 3);
						String[] ligneSplit = null;
						for (String ligne : reponse) {
							System.out.println(ligne);
							ligneSplit = ligne.split(";");
						}	
						int stock = Integer.parseInt(ligneSplit[1]);
						stock -= qte;
						if(stock < 0) {
							return 5;
						} else {
							if(seuil_alerte.alerteSeuil(ligneSplit[2], stock)) {
								sql = "UPDATE consommable SET stock = " + stock + "  WHERE reference_conso = \"" + reference.toLowerCase() + "\"";
								bdd.requeteSansReponse(sql);
								historique.retirer_histo(date, qte, batiment, numeroSalle, nom, prenom, reference);
								return 6;
							} else {
								sql = "UPDATE consommable SET stock = " + stock + "  WHERE reference_conso = \"" + reference.toLowerCase() + "\"";
								bdd.requeteSansReponse(sql);
								historique.retirer_histo(date, qte, batiment, numeroSalle, nom, prenom, reference);
								return 0;
							}
							
						}
						
						
					}
						
				}
			}
		}		
	}
	
	/**
	 * 
	 * @return <code>List</code> Renvoie la liste des types de consommable avec combien de consommable contient chaque type.
	 */
	public static List<String> afficher_conso() {
		connecteur bdd = new connecteur();
		String sql = "SELECT modele.modele, SUM(stock) FROM consommable INNER JOIN modele ON consommable.modele = modele.id_modele GROUP BY consommable.modele";
		List<String> reponse = bdd.select(sql, 2);
		List<String> finalList = new ArrayList<String>();
		for (String ligne : reponse) {
			String[] tempSplit = ligne.split(";");
			finalList.add(tempSplit[0] + " (" + tempSplit[1] + ")");
		}
		
		return finalList;
	}
	
	/**
	 * 
	 * @param type Le type de consommable.
	 * @return <code>List</code> Renvoie tout le stock d'un type de consommable
	 */
	public static List<String> all_conso_type(String type) {
		connecteur bdd = new connecteur();
		String sql = "SELECT consommable.reference_conso, consommable.stock FROM consommable INNER JOIN modele ON modele.id_modele = consommable.modele WHERE modele.modele =  \"" + type + "\"";
		List<String> reponse = bdd.select(sql, 2);
		List<String> finalList = new ArrayList<String>();
		for (String ligne : reponse) {
			String[] tempSplit = ligne.split(";");
			if(Integer.parseInt(tempSplit[1]) != 0) {
				finalList.add(tempSplit[0] + " (" + tempSplit[1] + ")");
			}
			
		}
		
		return finalList;
	}
}