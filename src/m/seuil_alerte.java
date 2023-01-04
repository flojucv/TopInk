package m;

import java.util.List;

import c.connecteur;

public class seuil_alerte {

	/**
	 * 
	 * Permet de définir le seuil d'un des types de consommable
	 * 
	 * @param categori	Type de consommable.
	 * @param seuil		Seuil de consommable a définir.
	 * @return			<code>0</code> Retourne sans erreur.<br>
	 * 					<code>1</code> Retourne une erreur si le seuil n'ai pas un chiffre.<br>
	 * 					<code>2</code> Retourne une erreur si le type n'ai pas bon.
	 */
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
	
	
	/**
	 * 
	 * Permet de renvoyer true si le seuil est atteint ou supérieur au stock
	 * 
	 * @param prm_modele	Le type de consommable.
	 * @param stock			Le stock du consommable.
	 * @return	<code>true</code> Renvoie true si le seuil est atteint ou supérieur au stock.<br>
	 * 		 	<code>false</code> Renvoie false si le seuil n'ai pas atteint ou supérieur au stock.<br>
	 */
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
