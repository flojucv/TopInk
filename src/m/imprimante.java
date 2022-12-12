package m;

import java.util.ArrayList;
import java.util.List;

import c.connecteur;

public class imprimante {

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
	
	public static void modifier_imprimante(String prm_reference, String prm_modele, String prm_marque, String Batiment, String salle) {
		connecteur bdd = new connecteur();
		String modeleSansEspace = prm_modele.replace(" ", "_");
		String marqueSansEspace = prm_marque.replace(" ", "_");
		
		String sql = "UPDATE modele_imprimante SET modele = \"" + modeleSansEspace.toLowerCase() + "\", marque = \"" + marqueSansEspace.toLowerCase() + "\", id_salle = (SELECT id_salle FROM salle WHERE batiment = \"" + Batiment.toUpperCase() + "\" AND designation = \"" + salle.toUpperCase() + "\") WHERE reference_impri = \"" + prm_reference.toLowerCase() + "\"";
		bdd.requeteSansReponse(sql);
	}
}