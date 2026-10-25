import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationAttributeListener
        implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println(
            "[ApplicationAttributeListener] Attribute added: "
            + event.getName() + " = " + event.getValue()
        );
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        Object oldValue = event.getValue();
        Object newValue = event.getServletContext()
                               .getAttribute(event.getName());

        System.out.println(
            "[ApplicationAttributeListener] Attribute replaced: "
            + event.getName()
            + " | Old value: " + oldValue
            + " | New value: " + newValue
        );
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println(
            "[ApplicationAttributeListener] Attribute removed: "
            + event.getName() + " = " + event.getValue()
        );
    }
}