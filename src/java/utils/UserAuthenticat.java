
package utils;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserAuthenticat {
    public static boolean authenticat(String table,String inputPassword,String username) throws NoSuchAlgorithmException, SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "root";
            String pass = "Viral@6566";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/venuezar", user, pass);;
            PreparedStatement stmt = conn.prepareStatement("select * from "+table+" where username = ?");
            stmt.setString(1, username);
            ResultSet set = stmt.executeQuery();
            if(set.next()){
                String store = set.getString("password");
                boolean login = PasswordHasher.comparePasswords(inputPassword, store);
                return login;
            }
            else{
                return false;
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(UserAuthenticat.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
