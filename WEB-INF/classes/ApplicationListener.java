import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;

//@WebListener
public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();

        context.setAttribute("appName", "ING1 Web Application");

        System.out.println("[ApplicationListener] Application started.");
        System.out.println("[ApplicationListener] App name: "
                + context.getAttribute("appName"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("[ApplicationListener] Application stopped.");
    }
}