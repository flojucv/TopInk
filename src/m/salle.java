package m;

import java.util.ArrayList;
import java.util.List;

import c.connecteur;

public class salle {

	public static void main(String[] args) {
		modif_salle("B", "01", "D", "111");

	}
	
	public static void ajout_salle(String Batiment, String numSalle) {
	    connecteur bdd = new connecteur();
	    String sql = "SELECT * FROM salle WHERE batiment = \"" + Batiment.toUpperCase() + "\" AND designation = \"" + numSalle.toUpperCase() + "\"";
	    List<String> reponse = bdd.select(sql, 4);
	    
	    if(reponse.size() == 0) {
		    sql = "INSERT INTO salle ( batiment, designation) VALUES  (\"" + Batiment.toUpperCase() + "\",\"" + numSalle.toUpperCase() + "\")";
		    bdd.requeteSansReponse(sql);
	    }
	    
	}
	
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
	
	public static void supprimer_salle(String batiment, String numSalle) {
		connecteur bdd = new connecteur();
		String sql = "SELECT id_historique FROM historique WHERE id_salle = (SELECT id_salle FROM salle WHERE batiment = \"" + batiment.toUpperCase() + "\" AND designation = \"" + numSalle.toUpperCase() + "\")" ;
		
		List<String> reponse = bdd.select(sql, 1);
		
		if(reponse.size() == 0) {
			sql = "DELETE FROM salle WHERE batiment = \"" + batiment.toUpperCase() + "\" AND designation = \"" + numSalle.toUpperCase() + "\"";
		} else {
			sql = "UPDATE salle SET visible = false  WHERE batiment = \"" + batiment.toUpperCase() + "\" AND designation = \"" + numSalle.toUpperCase() + "\"";
		}
		bdd.requeteSansReponse(sql);
	}
	
	public static void modif_salle(String newBatiment, String newNumSalle, String oldBatiment, String oldNumSalle) {
		connecteur bdd = new connecteur();
		
		String sql = "UPDATE salle SET batiment = \"" + newBatiment.toUpperCase() + "\", designation = \"" + newNumSalle.toUpperCase() + "\" WHERE batiment = \"" + oldBatiment.toUpperCase() +"\" AND designation = \"" + oldNumSalle.toUpperCase() + "\"";
		System.out.println(sql);
		bdd.requeteSansReponse(sql);
	}

}
