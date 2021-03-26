package se.yrgo.schedule.assignment;

/**
 * A factory to get an assignment
 */

public class AssignmentsFactory {
    private AssignmentsFactory() {
    }

    public static Assignments getAssignments() {
        return new DatabaseAssignments();
    }

}
