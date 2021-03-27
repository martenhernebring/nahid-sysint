package se.yrgo.schedule.assignment;

/**
 * A factory to get all assignments for the query
 */

public class AssignmentsFactory {
    private AssignmentsFactory() {
    }

    /** Creates a assignments
     * @return Assigmnments based on query
     * */
    public static Assignments getAssignments() {
        return new DatabaseAssignments();
    }

}
