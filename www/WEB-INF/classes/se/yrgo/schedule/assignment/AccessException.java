package se.yrgo.schedule.assignment;

public class AccessException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 6980579797291615417L;

    public AccessException(String msg) {
        super(msg);
    }

    public AccessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
