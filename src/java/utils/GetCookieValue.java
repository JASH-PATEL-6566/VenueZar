package utils;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import login_registration.RegisterOwnerServlet;

public class GetCookieValue {

    public static String getValue(String name, HttpServletRequest request) throws NoSuchAlgorithmException {
        Cookie[] cookies = request.getCookies();
        String cookieValue = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    cookieValue = cookie.getValue();
                    return cookieValue;
                }
            }
        }
        return "";
    }

    public static String getUserName(String tableName, String id, String field) throws NoSuchAlgorithmException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "root";
            String pass = "Viral@6566";
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/venuezar", user, pass);
            stmt = conn.prepareStatement("select * from ? where ? = ?");
            stmt.setString(1, tableName);
            stmt.setString(2, field);
            stmt.setString(3, id);

            ResultSet set = stmt.executeQuery();

            if (set.next()) {
                String name = set.getString("username");
                return name;
            } 
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterOwnerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterOwnerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}

