import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        HttpServletRequest request =
                (HttpServletRequest) event.getServletRequest();

        System.out.println(
                "[RequestListener] Request started: "
                + request.getMethod() + " "
                + request.getRequestURI()
        );
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        HttpServletRequest request =
                (HttpServletRequest) event.getServletRequest();

        System.out.println(
                "[RequestListener] Request finished: "
                + request.getMethod() + " "
                + request.getRequestURI()
        );
    }
}
