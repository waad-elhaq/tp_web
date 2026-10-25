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
        System.out.println(
                "[SessionAttributeListener] Attribute replaced: "
                + event.getName()
                + " (old value: " + event.getValue() + ")"
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
