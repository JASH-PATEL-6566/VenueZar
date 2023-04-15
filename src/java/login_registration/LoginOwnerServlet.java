package login_registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.PasswordHasher;
import java.sql.*;
import utils.AlertMessage;
import utils.UserAuthenticat;
import javax.servlet.http.Cookie;

public class LoginOwnerServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        
        //get all the data from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try{
            String ownerId = UserAuthenticat.authenticat("ownerTable", password, username,"ownerId");
            if(ownerId != ""){
                Cookie venuezarCookie = new Cookie("venuezar_id", ownerId);
                response.addCookie(venuezarCookie);
                response.sendRedirect("OwnerDashboard.html");
            }
            else{
                String res = AlertMessage.alertMessage("Ivalid Username and Passowrd....... Please try again.","RegisterForOwner.html");
                out.print(res);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(LoginOwnerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginOwnerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  
}

