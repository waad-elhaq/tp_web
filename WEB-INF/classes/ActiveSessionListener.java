
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ActiveSessionListener implements HttpSessionListener {

    private static final String ATTRIBUTE_NAME = "activeSessions";

    private final AtomicInteger activeSessions = new AtomicInteger(0);

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        int count = activeSessions.incrementAndGet();

        ServletContext context = event.getSession()
                                      .getServletContext();

        context.setAttribute(ATTRIBUTE_NAME, count);

        System.out.println(
            "[ActiveSessionListener] Session created. Active sessions: "
            + count
        );
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        int count = activeSessions.decrementAndGet();

        ServletContext context = event.getSession()
                                      .getServletContext();

        context.setAttribute(ATTRIBUTE_NAME, count);

        System.out.println(
            "[ActiveSessionListener] Session destroyed. Active sessions: "
            + count
        );
    }
}
