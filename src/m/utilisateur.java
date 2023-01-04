package m;

import java.util.ArrayList;
import java.util.List;

import c.connecteur;

public class utilisateur {
	
	/**
	 * 
	 * Permet d'ajouter un utilisateur a la bdd.
	 * 
	 * @param prm_nom		Le nom de l'utilisateur.
	 * @param prm_prenom	Le prénom de l'utilisateur.
	 * @param prm_section	La section de l'utilisateur.
	 */
	public static void ajouter_utilisateur(String prm_nom, String prm_prenom, String prm_section) {
		connecteur bdd = new connecteur();
	    String sql = "SELECT * FROM utilisateur WHERE nom = \"" + prm_nom.toLowerCase() + "\" AND prenom = \"" + prm_prenom.toLowerCase() + "\"";
	    List<String> reponse = bdd.select(sql, 5);
	    if(reponse.size() == 0) {
		    sql = "INSERT INTO utilisateur ( nom, prenom, id_section ) SELECT  \"" + prm_nom.toLowerCase() + "\",\"" + prm_prenom.toLowerCase() + "\" id_section FROM section WHERE nom_section = \"" + prm_section.toLowerCase() + "\"";
		    bdd.requeteSansReponse(sql);
	    } else {
	    	sql = "UPDATE utilisateur SET visible = true, nom = \"" + prm_nom.toLowerCase() + "\", prenom = \"" + prm_prenom.toLowerCase() + "\", id_section = (SELECT id_section FROM section WHERE nom_section = \"" + prm_section.toUpperCase() + "\") WHERE nom = \"" + prm_nom.toLowerCase() + "\" AND prenom = \"" + prm_prenom.toLowerCase() + "\"";
	    	bdd.requeteSansReponse(sql);
	    }
	}
	
	
	/**
	 * 
	 * Permet de renvoyez tout les utilisateurs visibles.
	 * 
	 * @return <code>List</code> Renvoie une liste contenant tout les utilisateurs visibles.
	 */
	public static List<String> afficher_utilisateur() {
		connecteur bdd = new connecteur();
		String sql = "SELECT * FROM utilisateur WHERE visible = 1";
		List<String> reponse = bdd.select(sql, 5);
		List<String> ListFinal = new ArrayList<String>();
		
		for (String ligne : reponse) {
			String[] tempSplit = ligne.split(";");
			sql = "SELECT nom_section FROM section WHERE id_section = " + tempSplit[3];
			List<String> tempList = bdd.select(sql, 1);
			String tempSalle = tempList.toString().substring(1, tempList.toString().length()-2);
			ListFinal.add(tempSplit[0].toUpperCase() + " " + tempSplit[1].toUpperCase()  + " (" + tempSalle + ")");
		}
		return ListFinal;
	}
	
	
	/**
	 * 
	 * Permet de supprimer ou de cache un utilisateur.
	 * 
	 * @param nom		Le nom de l'utilisateur.
	 * @param prenom	Le prenom de l'utilisateur.
	 */
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
	
	
	/**
	 * 
	 * Permet de modifier un utilisateur dans la bdd.
	 * 
	 * @param nom		Le nom de l'utilisateur.
	 * @param prenom	Le prenom de l'utilisateur.
	 * @param section	La section de l'utilisateur.
	 */
	public static void modifier_utilisateur(String nom, String prenom, String section, String oldNom, String oldPrenom) {
		connecteur bdd = new connecteur();

		String sql = "UPDATE utilisateur SET nom = \"" + nom.toLowerCase() + "\", prenom = \"" + prenom.toLowerCase() + "\", id_section = (SELECT id_section FROM section WHERE nom_section = \"" + section.toUpperCase() + "\") WHERE nom = \"" + oldNom.toLowerCase() + "\" AND prenom = \"" + oldPrenom.toLowerCase() + "\"";
		bdd.requeteSansReponse(sql);
	}
}
