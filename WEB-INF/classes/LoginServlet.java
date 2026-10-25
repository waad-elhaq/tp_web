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

        if ("remove".equals(action)) {
            HttpSession session = request.getSession(false);

            if (session != null) {
                session.invalidate();
            }

            response.sendRedirect(
                    request.getContextPath() + "/login.html");
            return;
        }

        if ("edit".equals(action)) {
            String newUsername = request.getParameter("username");

            if (newUsername == null || newUsername.trim().isEmpty()) {
                response.sendRedirect(
                        request.getContextPath() + "/LoginServlet");
                return;
            }

            HttpSession session = request.getSession(false);

            if (session == null || !(session.getAttribute("user") instanceof SessionUser)) {
                response.sendRedirect(request.getContextPath() + "/login.html");
                return;
            }

            SessionUser updatedUser = new SessionUser(newUsername.trim());
            session.setAttribute("user", updatedUser);

            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }

        // Default action: login
        String username = request.getParameter("username");

        if (username == null || username.trim().isEmpty()) {
            response.sendRedirect(
                    request.getContextPath() + "/login.html");
            return;
        }

        HttpSession session = request.getSession();

        SessionUser sessionUser = new SessionUser(username.trim());
        session.setAttribute("user", sessionUser);

        response.sendRedirect(request.getContextPath() + "/LoginServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);
        SessionUser sessionUser = null;

        if (session != null) {
            Object userAttribute = session.getAttribute("user");

            if (userAttribute instanceof SessionUser) {
                sessionUser = (SessionUser) userAttribute;
            }     
        }

        String username = sessionUser == null ? null : sessionUser.getUsername();

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'>");
        out.println("<title>Login Session</title></head><body>");

        if (username == null) {
            out.println("<h2>No user is logged in</h2>");
            out.println("<p>Please log in to continue.</p>");
            out.println("<a href='login.html'>Login</a>");
        } else {
            out.println("<h2>Welcome, " + escapeHtml(username) + "!</h2>");
            out.println("<p>You are currently connected.</p>");

            out.println("<h3>Edit username</h3>");
            out.println("<form method='post' action='LoginServlet'>");
            out.println("<input type='hidden' name='action' value='edit'>");
            out.println("<input type='text' name='username' "
                    + "placeholder='New username' required>");
            out.println("<button type='submit'>Edit User</button>");
            out.println("</form>");

            out.println("<h3>End session</h3>");
            out.println("<form method='post' action='LoginServlet'>");
            out.println("<input type='hidden' name='action' value='remove'>");
            out.println("<button type='submit'>Kill Session</button>");
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
