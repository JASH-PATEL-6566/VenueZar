package booking;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import login_registration.RegisterOwnerServlet;
import utils.GetCookieValue;
import utils.GetUserName;

public class BookingsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        String cookie = request.getParameter("cookie");

        Connection conn = null;
        PreparedStatement stmt = null;
        String id = "";
        String response_string = "";
        String result = "";
        try {
            id = GetCookieValue.getValue(cookie, request);
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "root";
            String pass = "Viral@6566";
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/venuezar", user, pass);
            stmt = conn.prepareStatement("select * from bookingTable where ownerId = ?");
            stmt.setString(1, id);

            String venueName = "";
            String customerName = "";
            String customerEmail = "";
            String date = "";
            String slot = "";

            ResultSet set = stmt.executeQuery();

            while (set.next()) {
                String venue = set.getString("venueId");
                String customer = set.getString("customerId");
                date = set.getString("date");
                slot = set.getString("slot");

                PreparedStatement stmt_venueName = conn.prepareStatement("select * from venueTable where id = ?");
                stmt_venueName.setString(1, venue);
                ResultSet venueSet = stmt_venueName.executeQuery();

                //venue name
                if (venueSet.next()) {
                    venueName = venueSet.getString("name");
                }

                PreparedStatement stmt_customerName = conn.prepareStatement("select * from customerTable where customerId = ?");
                stmt_customerName.setString(1, customer);
                ResultSet customerSet = stmt_customerName.executeQuery();

                if (customerSet.next()) {
                    customerName = customerSet.getString("username");
                    customerEmail = customerSet.getString("email");
                }

                if (slot.equals("E")) {
                    slot = "Booking for evening time";
                } else if (slot.equals("M")) {
                    slot = "Booking for morning time";
                } else if (slot.equals("H")) {
                    slot = "Booking for all day";
                }

                String response_new = "<div class=\"venue_card\">\n"
                        + "<div class=\"img_container\">\n"
                        + "                        <img src=\"https://res.cloudinary.com/dreamlist/image/upload/v1681624096/VenueZar/demo_venue_ibiyou.jpg\" alt=\"demo_img\"/>\n"
                        + "</div>\n"
                        + "                    <div class=\"info_container\">\n"
                        + "                        <h2>" + venueName + "</h2>\n"
                        + "                        <span class=\"price\">\n"
                        + "                            <h3>" + customerName + "</h3>\n"
                        + "                        </span>\n"
                        + "                        <h4>" + date + "</h4>\n"
                        + "                        <span class=\"price\">\n"
                        + "                            <h4>" + customerEmail + "</h4>\n"
                        + "                        </span>\n"
                        + "                        <h5>" + slot + "</h5>\n"
                        + "                </div>"
                        + "                </div>";

                response_string = response_string.concat(response_new);
            }
            response.getWriter().write(response_string);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterOwnerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterOwnerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(GetUserName.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
