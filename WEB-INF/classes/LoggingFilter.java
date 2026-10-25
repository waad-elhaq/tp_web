
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

//@WebFilter("/*")
public class LoggingFilter implements Filter {
/*
    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("LoggingFilter initialized");
    }
*/

    private String logPrefix;

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {

        logPrefix = filterConfig.getInitParameter("logPrefix");

        if (logPrefix == null) {
            logPrefix = "[LAB]";
        }

        System.out.println(logPrefix + " LoggingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.println(logPrefix + " Before servlet: " + httpRequest.getRequestURL());
        long startTime = System.nanoTime();
        try {
            chain.doFilter(request, response);
        } finally {
            long endTime = System.nanoTime();
            double executionTimeMs = (endTime - startTime) / 1_000_000.0;
            System.out.println(logPrefix + " After servlet: " + httpRequest.getRequestURL());
            System.out.printf("%s Request execution time: %.3f ms%n", logPrefix, executionTimeMs);
        }
    }


    @Override
    public void destroy() {
        System.out.println(logPrefix + "LoggingFilter destroyed");
    }
}
