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

public class AddVenueServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        //generate random id
        String venueId = UUID.randomUUID().toString().replace("-", "_");

        //get all the data from request
        String name = request.getParameter("name");
        String area = request.getParameter("area");
        String city = request.getParameter("city");
        String price = request.getParameter("price");
        String food_include = request.getParameter("food_include");
        String phone = request.getParameter("phone");
        String link = request.getParameter("location");
        
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            String ownerId = GetCookieValue.getValue("venuezar_id", request);
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "root";
            String pass = "Viral@6566";
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/venuezar", user, pass);
            stmt = conn.prepareStatement("INSERT INTO venueTable(id,name,area,city,price,food_include,phoneNumber,location_link,ownerId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, venueId);
            stmt.setString(2, name);
            stmt.setString(3, area);
            stmt.setString(4, city);
            stmt.setString(5, price);
            stmt.setString(6, food_include);
            stmt.setString(7, phone);
            stmt.setString(8, link);
            stmt.setString(9, ownerId);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                response.sendRedirect("OwnerDashboard.html");
            } else {
                try {
                    String res = AlertMessage.alertMessage("Something went wrong....... Please try again.", "AddVenueForm.html");
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
