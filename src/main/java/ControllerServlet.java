import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controllerServlet")
public class ControllerServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get form data here
        String formData = request.getParameter("formData");

        // Forward the request to Servlet1
        request.getRequestDispatcher("/serlogin").forward(request, response);

        // Forward the request to Servlet2
        request.getRequestDispatcher("/orders").forward(request, response);
    }
}
