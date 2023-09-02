

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
@WebServlet("/myservlet")
public class myservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 
	        PrintWriter out = response.getWriter();
	     
	        String name = request.getParameter("name");
	        
	        String email = request.getParameter("email");
	        String pass = request.getParameter("psw");
	         
	        try {
	           Connection con=MySqlConnection.initializedatabse();
	            PreparedStatement ps = con.prepareStatement("insert into user(name,email,password) values(?,?,?)");
	            ps.setString(1, name);
	        
	            ps.setString(2, email);
	            ps.setString(3, pass);
	            int i = ps.executeUpdate();
	             
	            if(i > 0) {
	                out.println("You have successfully signed in");
	                response.sendRedirect("home.html");
	            }
	         
	        }
	        catch(Exception se) {
	            se.printStackTrace();
	        }
	}

}
