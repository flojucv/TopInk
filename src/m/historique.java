package m;

import java.util.List;

import c.connecteur;

public class historique {
	
	/**
	 * Permet d'ajouter a l'historique quand un consommable est ajouter au stock
	 * 
	 * @param date La date du jour
	 * @param qte  Le nombre de consommable ajouter
	 * @param reference_conso La reference du consommable qui est ajouter 
	 */
	public static void ajouter_histo(String date, int qte, String reference_conso) {
		connecteur bdd = new connecteur();
		String sql = "INSERT INTO historique (date, qte, operation, reference_conso) VALUES(\"" + date + "\",\"" + qte + "\",\"Ajouter\",\"" + reference_conso + "\")";
		bdd.requeteSansReponse(sql);		
	}
	
	
	/**
	 * Permet d'ajouter a l'historique quand un consommable est ajouter au stock
	 * 
	 * @param date La date du jour.
	 * @param qte  Le nombre de consommable ajouter.
	 * @param Batiment Le batiment dans lequel se trouve l'imprimante.
	 * @param numeroSalle La salle dans lequel se trouve l'imprimante.
	 * @param nom Le nom de la personne qui retire un consommable.
	 * @param prenom Le prenom de la personne qui retire un consommable.
	 * @param reference_conso La reference du consommable qui est ajouter. 
	 */
	public static void retirer_histo(String date, int qte, String Batiment, String numeroSalle, String nom, String prenom, String reference_conso) {
		connecteur bdd = new connecteur();
		String sql = "INSERT INTO historique (date, qte, operation, id_salle, id_section, id_utilisateur, reference_impri, reference_conso) VALUES (\"" + date + "\"," + qte +", \"Supprimer\", (SELECT id_salle FROM salle WHERE batiment = \""+ Batiment.toUpperCase() +"\" AND designation = \"" + numeroSalle.toUpperCase() +"\"), (SELECT section.id_section FROM section INNER JOIN utilisateur ON utilisateur.id_section = section.id_section ), (SELECT id_utilisateur FROM utilisateur WHERE nom = \""+ nom.toLowerCase() +"\" AND prenom = \""+ prenom.toLowerCase() +"\"), (SELECT reference_impri FROM modele_imprimante WHERE id_salle = (SELECT id_salle FROM salle WHERE batiment = \""+ Batiment.toUpperCase() +"\" AND designation = \""+ numeroSalle.toUpperCase() +"\")), (SELECT reference_conso FROM consommable WHERE reference_conso = \""+ reference_conso.toLowerCase() +"\"));";
		bdd.requeteSansReponse(sql);
	};
	
	
	/**
	 * Permet de retourner la liste de l'historique
	 * 
	 * @return <code>List<\String></code> 
	 */
	public static List<String> afficher_histo() {
		connecteur bdd = new connecteur();
		String sql = "SELECT * FROM historique";
		List<String> reponse = bdd.select(sql, 9);
		return reponse;
	}
}
