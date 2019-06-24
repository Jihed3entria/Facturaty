package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
/**
 * Created by JIHED on 10/02/2019.
 */
public class Myconnexion {
    Connection con;

    public Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/base", "root", "yourpassword");
        //here base is the database name;
        //root is the default username;
        } catch (Exception e) {
            System.out.println(e);
        }

        return con;
    }
}
