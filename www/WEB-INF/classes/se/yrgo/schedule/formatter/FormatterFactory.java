package se.yrgo.schedule.formatter;

/**
 * A factory to get a formatter. For testing not supported formats please use the testjsong.sh file.
 */
public class FormatterFactory {

    private static Formatter XML_FORMATTER = new XmlFormatter();
    private static Formatter JSON_FORMATTER = new JsonFormatter();

    /**
     * Returns a formatter for the given contentType
     * 
     * @param contentType The content type you want to format to (XML and JSON is the only ones allowed)
     * @return A Formatter of the correct type, depending on the provided
     *         contentType. Cannot handle null.
     */
    public static Formatter getFormatter(String contentType) {
        if (contentType.equalsIgnoreCase("xml")) {
            return XML_FORMATTER;
        } else if (contentType.equalsIgnoreCase("json")) {
            return JSON_FORMATTER;
        } else {
            throw new IllegalArgumentException("Format not supported");
        }
    }

}
