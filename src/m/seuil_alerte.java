package m;

import java.util.List;

import c.connecteur;

public class seuil_alerte {


	public static int definir_seuil(String categori, String seuil) {
		connecteur bdd = new connecteur();
		try {
			String sql  = "SELECT * FROM  modele WHERE modele.modele = \"" + categori.toLowerCase() + "\"";
			List<String> reponse = bdd.select(sql, 3);
			
			if(reponse.size() == 0) {
				return 2;
			} else {
				sql = "UPDATE modele SET seuil_alerte = " + Integer.parseInt(seuil) + "  WHERE 	modele = \"" + categori.toLowerCase() + "\"";
	            bdd.requeteSansReponse(sql);
	            return 0;			}
            
        } catch (Exception ex) {
        	return 1;
        }
		
	}
	
	public static Boolean alerteSeuil(String prm_modele, int stock) {
		connecteur bdd = new connecteur();
		String sql = "SELECT seuil_alerte FROM modele WHERE id_modele = " + prm_modele.toLowerCase();
		List<String> reponse = bdd.select(sql, 1);
		int seuil = Integer.parseInt(reponse.toString().substring(1, reponse.toString().length() -2));
		if(seuil >= stock) {
			return true;
		} else {
			return false;
		}
	}

}
