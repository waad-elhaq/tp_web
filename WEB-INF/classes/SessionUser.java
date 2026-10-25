import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class SessionUser implements HttpSessionBindingListener {

    private final String username;

    public SessionUser(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println(
            "[SessionUser] User '" + username
            + "' was bound to session attribute: "
            + event.getName()
        );
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println(
            "[SessionUser] User '" + username
            + "' was unbound from session attribute: "
            + event.getName()
        );
    }
}
