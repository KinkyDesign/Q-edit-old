package qedit.clients;

/**
 *
 * @author chung
 */
public class ClientException extends Exception {

    /**
     * Creates a new instance of <code>ClientException</code> without detail message.
     */
    public ClientException() {
    }


    /**
     * Constructs an instance of <code>ClientException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ClientException(String msg) {
        super(msg);
    }

    public ClientException(Throwable cause) {
        super(cause);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
