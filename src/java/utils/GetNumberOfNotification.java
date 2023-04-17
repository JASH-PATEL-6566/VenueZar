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

public class GetNumberOfNotification extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cookie = request.getParameter("cookie");
        Connection conn = null;
        PreparedStatement stmt = null;
        PrintWriter out = response.getWriter();
        try {
            String ownerId = GetCookieValue.getValue(cookie, request);
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "root";
            String pass = "Viral@6566";
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/venuezar", user, pass);
            stmt = conn.prepareStatement("select * from bookingTable where ownerId = ?");
            stmt.setString(1, ownerId);

            ResultSet set = stmt.executeQuery();

            int rowCount = 0;
            while (set.next()) {
                rowCount++;
            }
            
            String str_count = Integer.toString(rowCount);
            
            out.write(str_count);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(GetNumberOfNotification.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetNumberOfNotification.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetNumberOfNotification.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
