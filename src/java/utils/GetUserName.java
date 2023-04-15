
package utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import login_registration.RegisterOwnerServlet;

public class GetUserName extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/plain");
        String tableName = request.getParameter("table");
        String field = request.getParameter("field");
        String cookie = request.getParameter("cookie");
        
     
        Connection conn = null;
        PreparedStatement stmt = null;
        String id = "";
        String name = "";
        
        
        try {
            id = GetCookieValue.getValue(cookie, request);
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "root";
            String pass = "Viral@6566";
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/venuezar", user, pass);
            stmt = conn.prepareStatement("select * from "+tableName+" where "+field+" = ?");
//            stmt = conn.prepareStatement("select * from "+tableName+" where ownerId = ?");
//            stmt.setString(1, tableName);
//            stmt.setString(2, field);
            stmt.setString(1, id);

            ResultSet set = stmt.executeQuery();

            if (set.next()) {
                name = set.getString("username");
                response.getWriter().write(name);
            } 
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterOwnerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterOwnerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(GetUserName.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
