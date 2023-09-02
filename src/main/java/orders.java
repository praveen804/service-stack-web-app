import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/orders")
public class orders extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load the database driver and establish connection
            Connection connection = MySqlConnection.initializedatabse();
            Statement requestStatement = connection.createStatement();
            Statement userStatement = connection.createStatement();

            ResultSet requestResultSet = requestStatement.executeQuery("SELECT services, phone_number, location, total_cost, email FROM request");
            ResultSet userResultSet = userStatement.executeQuery("SELECT name, email FROM user");

            // Start writing the HTML table
            out.println("<html><head><title>Orders</title></head><body>");
            out.println("<h1>Orders</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>Name</th><th>Phone number</th><th>Service</th><th>Location</th><th>Total cost</th></tr>");

            while (requestResultSet.next()) {
                String services = requestResultSet.getString("services");
                String phone = requestResultSet.getString("phone_number");
                String location = requestResultSet.getString("location");
                String cost = requestResultSet.getString("total_cost");
                String password = requestResultSet.getString("email");
                String name = "";

                // Iterate through the userResultSet to find the matching name
                while (userResultSet.next()) {
                    if (password.equals(userResultSet.getString("email"))) {
                        name = userResultSet.getString("name");
                        userResultSet.beforeFirst(); // Reset the userResultSet
                        break;
                    }
                }

                out.println("<tr><td>" + name + "</td><td>" + phone + "</td><td>" + services + "</td><td>" + location + "</td><td>$" + cost + "</td></tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

            // Close resources
            requestResultSet.close();
            userResultSet.close();
            requestStatement.close();
            userStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("An error occurred: " + e.getMessage());
        }
    }
}
