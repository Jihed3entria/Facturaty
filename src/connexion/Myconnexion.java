package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
/**
 * Created by MBI on 10/02/2019.
 */
public class Myconnexion {
    Connection con;

    public Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion2", "root", "pass123");
        //here mabase is the database name;
        //root is the default username;
        //and pass123 is the password;


          /*  Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from article");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " em " + rs.getString(4));
            //con.close();
            return con;*/
        } catch (Exception e) {
            System.out.println(e);
        }

        return con;
    }
}
