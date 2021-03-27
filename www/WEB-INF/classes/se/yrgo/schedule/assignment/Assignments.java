package se.yrgo.schedule.assignment;

import java.util.List;

/**
 * <p>
 * A small interface declaring the access methods to the data.
 * </p>
 */
public interface Assignments {
    /**
     * Returns all Assignments for all teachers for all dates
     * @return the Assigments for all teachers
     * @throws Exception from accesssing the database. Please contact the servlet administrator.
     */
    public List<Assignment> all() throws AccessException;

    /**
     * Returns the Assignmens for the given teacher
     * 
     * @param teacherId The id of the teacher
     * @return the Assigments for the given teacher
     * @throws Exception from accesssing the database. Please contact the servlet administrator.
     */
    public List<Assignment> forTeacher(String teacherId) throws AccessException;

    /**
     * Returns the assignments at a given date
     * 
     * @param date The date, as a String (YYYY-mm-ddd)
     * @return The Assignments at the given date
     * @throws Exception from accesssing the database. Please contact the servlet administrator.
     */
    public List<Assignment> at(String date) throws AccessException;

    /**
     * Returns the assignments for the given teacher at the given date
     * 
     * @param teacherId The id of the teacher
     * @param date      The date in question
     * @return The assignments for the given teacher at the given date
     * @throws Exception from accesssing the database. Please contact the servlet administrator.
     */
    public List<Assignment> forTeacherAt(String teacherId, String date) throws AccessException;
}
