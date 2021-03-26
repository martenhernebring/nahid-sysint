package se.yrgo.schedule.formatter;

import java.util.List;

import se.yrgo.schedule.assignment.Assignment;

/**
 * A class implementing the Formatter interface. Formats a List of Assignment to
 * JSON.
 *
 */
public class JsonFormatter implements Formatter {

    @Override
    public String format(List<Assignment> assignments) {
        return "[ { \"some-key\": \"some-value\" } ]";
    }

}
