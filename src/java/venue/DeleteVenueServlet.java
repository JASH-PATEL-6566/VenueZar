package venue;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class DeleteVenueServlet extends HttpServlet {

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
            String ownerId = GetCookieValue.getValue("venuezar_id", request);
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "root";
            String pass = "Viral@6566";
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/venuezar", user, pass);
            stmt = conn.prepareStatement("delete from venueTable where id=?");
            stmt.setString(1, venueId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
//                response.getWriter().write("asdasd");

                PreparedStatement delete_booking = conn.prepareStatement("delete from bookingTable where venueId=?");
                delete_booking.setString(1, venueId);

                int bookingDeleted = delete_booking.executeUpdate();
                response.getWriter().write("success");

                response.sendRedirect("OwnerDashboard.html");
            } else {
                try {
                    String res = AlertMessage.alertMessage("Something went wrong....... Please try again.", "OwnerDashboard.html");
                    out.print(res);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(RegisterOwnerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
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
