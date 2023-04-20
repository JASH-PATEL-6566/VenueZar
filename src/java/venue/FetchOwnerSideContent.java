
package venue;

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
import utils.GetCookieValue;
import utils.GetUserName;

public class FetchOwnerSideContent extends HttpServlet {
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        String field = request.getParameter("field");
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
            stmt = conn.prepareStatement("select * from venueTable where ownerId = ?");
//            stmt = conn.prepareStatement("select * from "+tableName+" where ownerId = ?");
//            stmt.setString(1, tableName);
//            stmt.setString(2, field);
            stmt.setString(1, id);

            ResultSet set = stmt.executeQuery();
//            set.next();
            while(set.next()) {
                String venueId = set.getString("id");
                String name = set.getString("name");
                String area = set.getString("area");
                String city = set.getString("city");
                String price = set.getString("price");
                String phone = set.getString("phoneNumber");
                String location = set.getString("location_link");
//                
//                if(food_include == "NULL"){
//                    food_include = "";
//                }
//                else{
//                    food_include = "food included";
//                }

                String response_new = "<div class=\"venue_card\">\n" +
"                    <div class=\"img_container\">\n" +
"                        <img src=\"https://res.cloudinary.com/dreamlist/image/upload/v1681624096/VenueZar/demo_venue_ibiyou.jpg\" alt=\"demo_img\"/>\n" +
"                    </div>\n" +
"                    <div class=\"info_container\">\n" +
"                        <h2>"+name+"</h2>\n" +
"                        <p>"+area+" , "+city+"</p>\n" +
"                        <span class=\"price\">\n" +
"                            <h3>"+price+" Rs.</h3>\n" +
"                            \n" +
"                        </span>\n" +
"                        <div class=\"absolute_btn\">\n" +
"                            <a href=\""+location+"\" target=\"_blank\">\n" +
"                                <span>\n" +
"                                    <img src=\"https://res.cloudinary.com/dreamlist/image/upload/v1681627121/VenueZar/1737383_gps_location_locationpin2_pin_icon_m8kj2a.svg\" alt=\"location\"/>\n" +
"                                </span>\n" +
"                            </a>\n" +
"                            <button class=\"span\" onclick=\"deleteVenue(event)\" venueId="+venueId+">\n" +
"                                <img src=\"https://res.cloudinary.com/dreamlist/image/upload/v1681628412/VenueZar/352303_delete_icon_vzzr5m.svg\" alt=\"location\"/>\n" +
"                            </button>\n" +
"                        </div>\n" +
"                        <p class=\"phone\"><img src=\"https://res.cloudinary.com/dreamlist/image/upload/v1681628746/VenueZar/352510_local_phone_icon_noxex3.svg\" alt=\"phone\"/> "+phone+"</p>\n" +
"                        <button>Edit</button>\n" +
"                    </div>\n" +
"                </div>"; 
                
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
