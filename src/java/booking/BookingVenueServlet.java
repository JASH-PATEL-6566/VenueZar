package booking;

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
import login_registration.RegisterOwnerServlet;
import utils.AlertMessage;
import utils.GetCookieValue;

public class BookingVenueServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String date = request.getParameter("date");
        String slot = request.getParameter("slot");
        String bookingId = UUID.randomUUID().toString().replace("-", "_");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            String venueId = GetCookieValue.getValue("owner", request);
            String ownerId = GetCookieValue.getValue("venue", request);
            String customerId = GetCookieValue.getValue("venuezar_customerId", request);
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "root";
            String pass = "Viral@6566";
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/venuezar", user, pass);
            stmt = conn.prepareStatement("INSERT INTO bookingTable VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, bookingId);
            stmt.setString(2, customerId);
            stmt.setString(3, venueId);
            stmt.setString(4, ownerId);
            stmt.setString(5, date);
            stmt.setString(6, slot);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                out.write(slot);
                Cookie cookie = new Cookie("owner", "");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                Cookie cookie2 = new Cookie("venue", "");
                cookie2.setMaxAge(0);
                response.addCookie(cookie2);
                response.sendRedirect("CustomerDashboard.html");
            } else {
                String res = AlertMessage.alertMessage("Booking of venue is faild....... Please try again.", "CustomerDashboard.html");
                out.print(res);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(BookingVenueServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookingVenueServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookingVenueServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
