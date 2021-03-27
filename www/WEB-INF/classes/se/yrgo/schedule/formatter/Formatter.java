package se.yrgo.schedule.formatter;

import java.util.List;

import se.yrgo.schedule.assignment.Assignment;

/** Factory interface for different formats*/
public interface Formatter {
    /** Format used for the data returned from the Servlet
     * @param assignments List of teachers and dates to be presented.
     * @return Formatted object requested by the user.*/
    public String format(List<Assignment> assignments);
}
