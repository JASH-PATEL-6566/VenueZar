package cookie;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectBasedOnCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       Cookie[] cookies = request.getCookies();
        System.out.println("call");
        boolean get = false;
        String id = "";
        
        if(cookies != null){
            for( Cookie c: cookies){
                String name = c.getName();
                if(name == "venuezar_id"){
                    get = true;
                    id = c.getValue();
                }
            }
        }
        
        if(get){
            response.sendRedirect("OwnerDashboard.html");
        }
        
        PrintWriter out = response.getWriter();
        out.write("hello");
    } 
}
