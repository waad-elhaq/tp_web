import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class SessionAttributeListener
        implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println(
                "[SessionAttributeListener] Attribute added: "
                + event.getName()
                + " = " + event.getValue()
        );
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        String oldValue = (String) event.getValue();
        String newValue = (String) event.getSession().getAttribute(event.getName());
        System.out.println(
                "[SessionAttributeListener] Attribute replaced: "
                + event.getName()
                + " | Old value: " + oldValue
                + " | New value: " + newValue
        );
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println(
                "[SessionAttributeListener] Attribute removed: "
                + event.getName()
                + " = " + event.getValue()
        );
    }
}
