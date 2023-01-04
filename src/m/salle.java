package m;

import java.util.ArrayList;
import java.util.List;

import c.connecteur;

public class salle {
	
	/**
	 * 
	 * Permet d'ajouter une salle dans la bdd.
	 * 
	 * @param Batiment	Le batiment de la salle.
	 * @param numSalle	Le numéro de la salle.
	 */
	public static void ajout_salle(String Batiment, String numSalle) {
	    connecteur bdd = new connecteur();
	    String sql = "SELECT * FROM salle WHERE batiment = \"" + Batiment.toUpperCase() + "\" AND designation = \"" + numSalle.toUpperCase() + "\"";
	    List<String> reponse = bdd.select(sql, 4);
	    
	    if(reponse.size() == 0) {
		    sql = "INSERT INTO salle ( batiment, designation) VALUES  (\"" + Batiment.toUpperCase() + "\",\"" + numSalle.toUpperCase() + "\")";
		    bdd.requeteSansReponse(sql);
	    }
	    
	}
	
	/**
	 * 
	 * Permet d'afficher les salles visibles
	 * 
	 * @return <code>List</code> Renvoie une liste contenant les salles visibles
	 */
	public static List<String> afficher_salle() {
		connecteur bdd = new connecteur();
		String sql = "SELECT * FROM salle WHERE visible = 1";
		List<String> reponse = bdd.select(sql, 4);
		List<String> ListFinal = new ArrayList<String>();
		
		for (String ligne : reponse) {
			
			String[] tempSplit = ligne.split(";");
			ListFinal.add(tempSplit[1].toUpperCase() + " " + tempSplit[0].toUpperCase());
		}
		return ListFinal;
	}
	
	/**
	 * 
	 * Permet de supprimer ou de cacher une salle.
	 * 
	 * @param batiment	Le batiment de la salle.
	 * @param numSalle	Le numéro de la salle.
	 */
	public static void supprimer_salle(String batiment, String numSalle) {
		connecteur bdd = new connecteur();

		String sql = "UPDATE salle SET visible = false  WHERE batiment = \"" + batiment.toUpperCase() + "\" AND designation = \"" + numSalle.toUpperCase() + "\"";
		System.out.println(sql);
		bdd.requeteSansReponse(sql);
	}
	
	
	/**
	 * 
	 * Permet de modifier une salle.
	 * 
	 * @param newBatiment	Le nouveau batiment dans lequel se trouve la salle.
	 * @param newNumSalle	Le nouveau numéro dans lequel se trouve la salle.
	 * @param oldBatiment	L'ancien batiment.
	 * @param oldNumSalle	L'ancien numére de la salle.
	 */
	public static void modif_salle(String newBatiment, String newNumSalle, String oldBatiment, String oldNumSalle) {
		connecteur bdd = new connecteur();
		
		String sql = "UPDATE salle SET batiment = \"" + newBatiment.toUpperCase() + "\", designation = \"" + newNumSalle.toUpperCase() + "\" WHERE batiment = \"" + oldBatiment.toUpperCase() +"\" AND designation = \"" + oldNumSalle.toUpperCase() + "\"";
		System.out.println(sql);
		bdd.requeteSansReponse(sql);
	}

}
