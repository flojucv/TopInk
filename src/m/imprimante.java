package m;

import java.util.ArrayList;
import java.util.List;

import c.connecteur;

public class imprimante {
	
	/**
	 * 
	 * Permet de rendre visible ou d'ajouter une impirmante.
	 * 
	 * @param prm_reference La référence de l'imprimante.
	 * @param prm_modele	Le modéle de l'imprimante.
	 * @param prm_marque	La marque de l'imprimante.
	 * @param Batiment		Le batiment dans lequel l'imprimante se situe.
	 * @param salle			La salle dans laquel l'imprimante se situe.
	 */
	public static void ajout_impri(String prm_reference, String prm_modele, String prm_marque, String Batiment, String salle) {
	    connecteur bdd = new connecteur();
	    String sql = "SELECT * FROM modele_imprimante WHERE reference_impri = \"" + prm_reference.toLowerCase() + "\"";
	    List<String> reponse = bdd.select(sql, 5);
    	String modeleSansEspace = prm_modele.replace(" ", "_");
	    String marqueSansEspace = prm_marque.replace(" ", "_");
	    if(reponse.size() == 0) {
		    sql = "INSERT INTO modele_imprimante ( reference_impri, modele, marque, id_salle ) SELECT  \"" + prm_reference.toLowerCase() + "\",\"" + modeleSansEspace.toLowerCase() + "\",\"" + marqueSansEspace.toLowerCase() + "\", id_salle FROM salle WHERE batiment = \"" + Batiment.toUpperCase() + "\" AND designation = \"" + salle.toUpperCase() + "\"";
		    bdd.requeteSansReponse(sql);
	    } else {
	    	sql = "UPDATE modele_imprimante SET visible = true, modele = \"" + modeleSansEspace.toLowerCase() + "\", marque = \"" + marqueSansEspace.toLowerCase() + "\", id_salle = (SELECT id_salle FROM salle WHERE batiment = \"" + Batiment.toUpperCase() + "\" AND designation = \"" + salle.toUpperCase() + "\") WHERE reference_impri = \"" + prm_reference.toLowerCase() + "\"";
	    	bdd.requeteSansReponse(sql);
	    }
	    
	}
	
	/**
	 * Permet d'afficher tout les imprimantes visibles
	 * 
	 * @return <code>List</code> Renvoie tout les imprimantes qui sont visibles.
	 */
	public static List<String> afficher_imprimante() {
		connecteur bdd = new connecteur();
		String sql = "SELECT * FROM modele_imprimante WHERE visible = 1";
		List<String> reponse = bdd.select(sql, 5);
		List<String> ListFinal = new ArrayList<String>();
		
		for (String ligne : reponse) {
			
			String[] tempSplit = ligne.split(";");
			sql = "SELECT batiment, designation FROM salle WHERE id_salle = " + tempSplit[4];
			List<String> tempList = bdd.select(sql, 2);
			String tempSalle = tempList.toString().substring(1, tempList.toString().length()-1);
			String[] salle = tempSalle.split(";");
			ListFinal.add(tempSplit[0].toUpperCase() + " " + tempSplit[2].toUpperCase() + " " + tempSplit[1].toUpperCase() + " (" + salle[0] + salle[1] + ")");
		}
		return ListFinal;
	}
	
	/**
	 * Permet de supprimer ou de cacher une imprimante de la liste.
	 * 
	 * @param ref_impri	La référence de l'imprimante.
	 */
	public static void supprimer_impri(String ref_impri) {
		connecteur bdd = new connecteur();
		String sql = "SELECT id_historique FROM historique WHERE reference_impri = " + ref_impri.toLowerCase();
		
		List<String> reponse = bdd.select(sql, 1);
		
		if(reponse.size() == 0) {
			sql = "DELETE FROM modele_imprimante WHERE reference_impri = \"" + ref_impri.toLowerCase() + "\"";
		} else {
			sql = "UPDATE modele_imprimante SET visible = false  WHERE reference_impri = \"" + ref_impri.toLowerCase() + "\"";
		}
		bdd.requeteSansReponse(sql);
	}
	
	/**
	 * 
	 * Permet de modifier l'imprimante.
	 * 
	 * @param prm_reference	La référence de l'imprimante.
	 * @param prm_modele	Le modéle de l'imprimante.
	 * @param prm_marque	La marque de l'imprimante.
	 * @param Batiment		Le batiment dans lequel se trouve l'imprimante.
	 * @param salle			La salle dans laquel se trouve l'imprimante.
	 */
	public static void modifier_imprimante(String prm_reference, String prm_modele, String prm_marque, String Batiment, String salle) {
		connecteur bdd = new connecteur();
		String modeleSansEspace = prm_modele.replace(" ", "_");
		String marqueSansEspace = prm_marque.replace(" ", "_");
		
		String sql = "UPDATE modele_imprimante SET modele = \"" + modeleSansEspace.toLowerCase() + "\", marque = \"" + marqueSansEspace.toLowerCase() + "\", id_salle = (SELECT id_salle FROM salle WHERE batiment = \"" + Batiment.toUpperCase() + "\" AND designation = \"" + salle.toUpperCase() + "\") WHERE reference_impri = \"" + prm_reference.toLowerCase() + "\"";
		bdd.requeteSansReponse(sql);
	}
}