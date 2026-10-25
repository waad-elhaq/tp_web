import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RequestAttributeListener
        implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent event) {
        System.out.println(
            "[RequestAttributeListener] Attribute added: "
            + event.getName() + " = " + event.getValue()
        );
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent event) {
        Object oldValue = event.getValue();
        Object newValue = event.getServletRequest()
                               .getAttribute(event.getName());

        System.out.println(
            "[RequestAttributeListener] Attribute replaced: "
            + event.getName()
            + " | Old value: " + oldValue
            + " | New value: " + newValue
        );
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent event) {
        System.out.println(
            "[RequestAttributeListener] Attribute removed: "
            + event.getName() + " = " + event.getValue()
        );
    }
}