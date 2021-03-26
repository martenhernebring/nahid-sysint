package se.yrgo.schedule.formatter;

import java.util.List;

import se.yrgo.schedule.assignment.Assignment;

public interface Formatter {
    public String format(List<Assignment> assignments);
}
