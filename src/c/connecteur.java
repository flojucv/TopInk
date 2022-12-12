package c;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class connecteur {
	
	/**
	* Permet l'execution de requête sans réponse.<br/>
	* Liaison avec la base de donnée.
	*
	* @param requete  La requete qui sera executer dans la base de donne.
	* 
	* @return Ne renvois rien
	*/
	public void requeteSansReponse(String requete) {
		try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            // handle the error
        }
        Connection conn = null;
        try {
            conn =
               DriverManager.getConnection("jdbc:mysql://localhost/top_ink?" +
                                           "user=root&password=root");      
            
            Statement statement;
            statement = conn.createStatement();
            statement.executeUpdate(requete);
            conn.close();
        } catch (SQLException ex) {
        	
        }
		
	}
	
	/**
	* Permet l'execution de la requête select.<br/>
	* Liaison avec la base de donnée.
	*
	* @param requete  La requete qui sera executer dans la base de donne.
	* @param nombreColonne Le nombre de colonne qu'ai composez la réponse de la requete.
	* 
	* @return <code>List</code> Renvoie la réponse de la requête sous forme de liste de type string.
	*/
	public  List<String> select(String requete, int nombreColonne) {
		List<String> resultat = new ArrayList<String>();
		
		try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            // handle the error
        }
        Connection conn = null;
        try {
            conn =
               DriverManager.getConnection("jdbc:mysql://localhost/top_ink?" +
                                           "user=root&password=root");
            
            
            Statement statement;
            statement = conn.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(requete);
            while(resultSet.next()) {
            	String tempResultat = "";
            	for (int i = 1; i <= nombreColonne; i++) {
    				String tempVariable = resultSet.getString(i);
    				if(tempVariable != null) {
    					tempResultat += tempVariable+";";
    				}
    				
    			}
            	resultat.add(tempResultat);
            }
            conn.close();
        } catch (SQLException ex) {
        	
        }
		
		return resultat;
    	
    }
}
