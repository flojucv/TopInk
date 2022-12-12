package m;

import java.util.ArrayList;
import java.util.List;

import c.connecteur;

public class utilisateur {
	
	public static void ajouter_utilisateur(String prm_nom, String prm_prenom, String prm_section) {
		connecteur bdd = new connecteur();
	    String sql = "SELECT * FROM utilisateur WHERE nom = \"" + prm_nom.toLowerCase() + "\" AND prenom = \"" + prm_prenom.toLowerCase() + "\"";
	    List<String> reponse = bdd.select(sql, 5);
	    System.out.println(reponse.size());
	    if(reponse.size() == 0) {
		    sql = "INSERT INTO utilisateur ( nom, prenom, id_section ) SELECT  \"" + prm_nom.toLowerCase() + "\",\"" + prm_prenom.toLowerCase() + "\" id_section FROM section WHERE nom_section = \"" + prm_section.toLowerCase() + "\"";
		    bdd.requeteSansReponse(sql);
	    } else {
	    	sql = "UPDATE utilisateur SET visible = true, nom = \"" + prm_nom.toLowerCase() + "\", prenom = \"" + prm_prenom.toLowerCase() + "\", id_section = (SELECT id_section FROM section WHERE nom_section = \"" + prm_section.toUpperCase() + "\") WHERE nom = \"" + prm_nom.toLowerCase() + "\" AND prenom = \"" + prm_prenom.toLowerCase() + "\"";
	    	System.out.println(sql);
	    	bdd.requeteSansReponse(sql);
	    }
	}
	
	public static List<String> afficher_utilisateur() {
		connecteur bdd = new connecteur();
		String sql = "SELECT * FROM utilisateur WHERE visible = 1";
		List<String> reponse = bdd.select(sql, 5);
		List<String> ListFinal = new ArrayList<String>();
		
		for (String ligne : reponse) {
			System.out.println(ligne);
			String[] tempSplit = ligne.split(";");
			sql = "SELECT nom_section FROM section WHERE id_section = " + tempSplit[3];
			List<String> tempList = bdd.select(sql, 1);
			String tempSalle = tempList.toString().substring(1, tempList.toString().length()-2);
			ListFinal.add(tempSplit[0].toUpperCase() + " " + tempSplit[1].toUpperCase()  + " (" + tempSalle + ")");
		}
		return ListFinal;
	}
	
	public static void supprimer_utilisateur(String nom, String prenom) {
		connecteur bdd = new connecteur();
		String sql = "SELECT id_historique FROM historique WHERE (SELECT id_utilisateur FROM utilisateur WHERE nom = \"" + nom.toLowerCase() + "\" AND prenom = \"" + prenom.toLowerCase()+ "\")";

		List<String> reponse = bdd.select(sql, 1);
		if(reponse.size() == 0) {
			sql = "DELETE FROM utilisateur WHERE nom = \"" + nom.toLowerCase() + "\" AND prenom = \"" + prenom.toLowerCase()+ "\"";
		} else {
			sql = "UPDATE utilisateur SET visible = false  WHERE nom = \"" + nom.toLowerCase() + "\" AND prenom = \"" + prenom.toLowerCase()+ "\"";;
		}
		bdd.requeteSansReponse(sql);
	}
	
	public static void modifier_utilisateur(String nom, String prenom, String section) {
		connecteur bdd = new connecteur();

		String sql = "UPDATE utilisateur SET nom = \"" + nom.toLowerCase() + "\", prenom = \"" + prenom.toLowerCase() + "\", id_section = (SELECT id_section FROM section WHERE nom_section = \"" + section.toUpperCase() + "\")";
		bdd.requeteSansReponse(sql);
	}
}
