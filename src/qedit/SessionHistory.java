package qedit;

import com.thoughtworks.xstream.XStream;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class SessionHistory {

    private static final int SESSION_SIZE = 3;
    Stack<Session> stack = new Stack<Session>();

    public void clear() {
        stack.clear();
    }

    public synchronized Session peek() throws EmptyStackException {
        return stack.peek();
    }

    public synchronized Session peekClone() throws EmptyStackException {
        return new Session(stack.peek());
    }

    public Session push(Session e) {
        if (!stack.contains(e)) {
            if (stack.size() == SESSION_SIZE) {
                stack.remove(0);
            }
            return stack.push(e);
        }
        return null;
    }

    @Override
    public String toString() {
        return new XStream().toXML(this);
    }

    public static void main(String... args) {
        SessionHistory sh = new SessionHistory();
        sh.push(new Session("1", "1", "1"));
        sh.push(new Session("2", "2", "2"));
        sh.push(new Session("3", "3", "3"));
        sh.push(new Session("4", "4", "4"));
        sh.push(new Session("4", "4", "4"));
        sh.push(new Session("4", "4", "4"));
        sh.push(new Session("5", "5", "5"));
        System.out.println(sh.stack.size());
    }
}
