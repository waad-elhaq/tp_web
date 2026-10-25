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
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if ("edit".equals(action)) {
            String newUsername = request.getParameter("username");

            if (newUsername == null || newUsername.trim().isEmpty()) {
                response.sendRedirect("LoginServlet");
                return;
            }

            session.setAttribute("user", newUsername.trim());
            response.sendRedirect("LoginServlet");
            return;
        }

        if ("remove".equals(action)) {
            session.removeAttribute("user");
            session.invalidate();
            response.sendRedirect("login.html");
            return;
        }
        String username = request.getParameter("username");

        if (username == null || username.trim().isEmpty()) {
            response.sendRedirect("login.html");
            return;
        }

        session.setAttribute("user", username.trim());

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Login</title></head><body>");
        out.println("<h2>Welcome, " + escapeHtml(username.trim()) + "!</h2>");
        out.println("<p>You are now connected.</p>");
        out.println("<h3>Edit username</h3>");
        out.println("<form method='post' action='LoginServlet'>");
        out.println("<input type='hidden' name='action' value='edit'>");
        out.println("<input type='text' name='username' required>");
        out.println("<button type='submit'>Edit User</button>");
        out.println("</form>");

        out.println("<h3>Remove user attribute</h3>");
        out.println("<form method='post' action='LoginServlet'>");
        out.println("<input type='hidden' name='action' value='remove'>");
        out.println("<button type='submit'>Remove User</button>");
        out.println("</form>");
        out.println("<a href='items.html'>Go to items</a>");
        out.println("</body></html>");
    }
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);
        String username = session == null
                ? null : (String) session.getAttribute("user");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'>");
        out.println("<title>Login Session</title></head><body>");

        if (username == null) {
            out.println("<h2>No user attribute found</h2>");
            out.println("<p>The session may still exist, but it has no user attribute.</p>");
            out.println("<a href='login.html'>Login again</a>");
        } else {
            out.println("<h2>Welcome, " + escapeHtml(username) + "!</h2>");
            out.println("<p>You are connected.</p>");

            out.println("<h3>Edit username</h3>");
            out.println("<form method='post' action='LoginServlet'>");
            out.println("<input type='hidden' name='action' value='edit'>");
            out.println("<input type='text' name='username' required>");
            out.println("<button type='submit'>Edit User</button>");
            out.println("</form>");

            out.println("<h3>Remove user attribute</h3>");
            out.println("<form method='post' action='LoginServlet'>");
            out.println("<input type='hidden' name='action' value='remove'>");
            out.println("<button type='submit'>Remove User</button>");
            out.println("</form>");

            out.println("<p><a href='items.html'>Go to items</a></p>");
        }

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
