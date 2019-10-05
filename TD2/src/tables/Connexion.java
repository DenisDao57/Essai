package tables;
import java.sql.*;
public class Connexion{
    public static Connection creeConnexion() {
        String url = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/ramier2u_proa2";
        url += "?serverTimezone=Europe/Paris";
                String login = "ramier2u_appli";
                String pwd = "s10tpabo"; 
                Connection maConnexion = null;
                try {
                    maConnexion=DriverManager.getConnection(url,login,pwd);
                }catch (SQLException sqle) {
                    System.out.println("Erreur connexion" + sqle.getMessage());
                }
                return maConnexion;
    }
}