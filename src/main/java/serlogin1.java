

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






@WebServlet("/serlogin1")
public class serlogin1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 
	        PrintWriter out = response.getWriter();
	     
	        
	        String email = request.getParameter("email");
	       
	        String pass = request.getParameter("psw");
	        boolean status=false;
	        try {
	        	 Connection con=MySqlConnection.initializedatabse();
		         PreparedStatement ps = con.prepareStatement("select * from admlog where email=? and password=?");
	           
	            ps.setString(1, request.getParameter("email"));	      
	            ps.setString(2,request.getParameter("psw"));
	            System.out.println(ps.toString());
	            ResultSet rst=ps.executeQuery();
	           //  System.out.println(rst.next());
	           
	            if(rst.next()==true){
	           	   response.sendRedirect("admin.html");
	            }
	            else{        	               
	               response.sendRedirect("admlog.html");
	            }
	            ps.close();
	            con.close();
	        }
	        
	        catch(Exception se) {
	            se.printStackTrace();
	        }
	}

}
