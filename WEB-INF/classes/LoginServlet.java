import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");

        if (username == null || username.trim().isEmpty()) {
            response.sendRedirect("login.html");
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", username.trim());

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Login</title></head><body>");
        out.println("<h2>Welcome, " + escapeHtml(username.trim()) + "!</h2>");
        out.println("<p>You are now connected.</p>");
        out.println("<a href='items.html'>Go to items</a>");
        out.println("</body></html>");
    }

    private String escapeHtml(String value) {
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
