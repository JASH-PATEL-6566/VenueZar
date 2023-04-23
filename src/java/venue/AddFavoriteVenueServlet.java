package venue;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import login_registration.LoginOwnerServlet;
import login_registration.RegisterOwnerServlet;
import utils.AlertMessage;
import utils.GetCookieValue;
import utils.PasswordHasher;
import utils.UserAuthenticat;

public class AddFavoriteVenueServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        //generate random id
        //get all the data from request
        String venueId = request.getParameter("venueId");
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            String customerId = GetCookieValue.getValue("venuezar_customerId", request);
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "root";
            String pass = "Viral@6566";
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/venuezar", user, pass);
            stmt = conn.prepareStatement("select * from favorites where venueId = ? and customerId=?;");
            stmt.setString(1, venueId);
            stmt.setString(2, customerId);

            ResultSet set = stmt.executeQuery();

            if (set.next()) {
                // remove from favorite

                PreparedStatement removeFromFavorite = conn.prepareStatement("delete from favorites where venueId=? and customerId = ?;");
                removeFromFavorite.setString(1, venueId);
                removeFromFavorite.setString(2, customerId);

                int removeFav = removeFromFavorite.executeUpdate();

                if (!(removeFav > 0)) {
                    try {
                        String res = AlertMessage.alertMessage("Something went wrong....... Please try again.", "CustomerDashboard.html");
                        out.print(res);
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(RegisterOwnerServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                response.getWriter().write("success");
            } else {
                PreparedStatement addFavorite = conn.prepareStatement("INSERT INTO favorites VALUES (?, ?)");
                addFavorite.setString(1, venueId);
                addFavorite.setString(2, customerId);
                
                int addFav = addFavorite.executeUpdate();
                
                if (!(addFav > 0)) {
                    try {
                        String res = AlertMessage.alertMessage("Something went wrong....... Please try again.", "CustomerDashboard.html");
                        out.print(res);
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(RegisterOwnerServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                } 
                response.getWriter().write("success");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterOwnerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterOwnerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegisterOwnerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
