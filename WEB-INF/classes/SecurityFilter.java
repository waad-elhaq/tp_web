import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityFilter implements Filter {

    private String logPrefix;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logPrefix = filterConfig.getInitParameter("logPrefix");

        if (logPrefix == null) {
            logPrefix = "[LAB]";
        }

        System.out.println(logPrefix + "SecurityFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        System.out.println(logPrefix + " Before servlet: " + httpRequest.getRequestURL());
        HttpSession session = httpRequest.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(
                httpRequest.getContextPath() + "/login.html"
            );
        }
                System.out.println(logPrefix + " After servlet: " + httpRequest.getRequestURL());

    }

    @Override
    public void destroy() {
        System.out.println(logPrefix + "SecurityFilter destroyed");
    }
}
