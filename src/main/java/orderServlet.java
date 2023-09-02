import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/orderServlet")
public class orderServlet extends HttpServlet {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] services = request.getParameterValues("services");
        String phone_number = request.getParameter("phone_number");
        String location = request.getParameter("locationField");
        String totalCost = request.getParameter("totalCostHidden");
        String psw=request.getParameter("psw");

        try {
            Connection connection = MySqlConnection.initializedatabse();
            String sql = "insert into request(services, phone_number, location, total_cost, email) values(?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, String.join(", ", services));
            statement.setString(2, phone_number);
            statement.setString(3, location);
            statement.setBigDecimal(4, new BigDecimal(totalCost));
            statement.setString(5, psw);
            statement.executeUpdate();

            connection.close();

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

            PrintWriter writer = response.getWriter();
            writer.println("Order placed successfully!");
            response.sendRedirect("home.html");
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
