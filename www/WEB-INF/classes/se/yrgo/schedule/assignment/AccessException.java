package se.yrgo.schedule.assignment;

/** A simple exception class used for indicating problems accesssing the database. */
public class AccessException extends Exception {
    
    //Serializable class requirement
    private static final long serialVersionUID = 6980579797291615417L;

    /** Simple constructor for error message 
     * @param msg Error accessing database message 
     * */
    public AccessException(String msg) {
        super(msg);
    }

    /** Simple constructor for error messages and causes 
    * @param msg Error accessing database message
    * @param cause Error accessing database cause
    * */
    public AccessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
