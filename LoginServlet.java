import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Map the servlet to "LoginServlet" to match the HTML form action
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Set the content type to HTML for the response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form data from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Initialize database connection
        DatabaseConnector dbConnector = new DatabaseConnector();
        boolean isAuthenticated = dbConnector.loginUser(username, password, role);

        // Check if login is successful
        if (isAuthenticated) {
            out.println("<h3>Login successful! Welcome, " + role + ".</h3>");
            response.sendRedirect("home.html"); // Redirect to the home page on successful login
        } else {
            out.println("<h3>Login failed. Incorrect credentials.</h3>");
            request.getRequestDispatcher("login.html").include(request, response); // Show login page again
        }

        out.close();
    }
}
