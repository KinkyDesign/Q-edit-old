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

    private static final int SESSION_SIZE = 5;
    private Stack<Session> stack = new Stack<Session>();

    public void clear() {
        stack.clear();
    }

    public synchronized int size() {
        return stack.size();
    }

    public synchronized Session get(int index) {
        return stack.get(index);
    }

    public synchronized Session remove(int index) {
        return stack.remove(index);
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

}
