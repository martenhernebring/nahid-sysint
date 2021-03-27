package se.yrgo.schedule.assignment;

import java.util.ArrayList;
import java.util.List;

import se.yrgo.schedule.model.School;
import se.yrgo.schedule.model.Substitute;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An implementation of the Assignments interface
 */
public class DatabaseAssignments implements Assignments {

    private static final String SELECT_ALL = new StringBuilder("select day, name, school_name, address from schedule")
            .append(" join substitute on schedule.substitute_id=substitute.substitute_id")
            .append(" join school on schedule.school_id = school.school_id").toString();
    private static final String SELECT_WITH_SUBSTITUTE_ID = new StringBuilder(
            "select day, name, school_name, address from schedule")
                    .append(" join substitute on schedule.substitute_id=substitute.substitute_id")
                    .append(" join school on schedule.school_id = school.school_id WHERE substitute.substitute_id=")
                    .toString();

    private DBHelper db;

    /** A simple constructor that creates a DBHelper member variable */
    public DatabaseAssignments() {
        db = new DBHelper();
    }

    @Override
    public List<Assignment> all() throws AccessException {
        try {
            ResultSet resultSet = db.fetch(SELECT_ALL);
            List<Assignment> result = addAssignments(resultSet);
            return result;
        } catch (SQLException sqle) {
            throw new AccessException("Problem fetching all assignments", sqle);
        }
    }

    @Override
    public List<Assignment> forTeacher(String teacherId) throws AccessException {
        try {
            ResultSet resultSet = db.fetch(SELECT_WITH_SUBSTITUTE_ID + teacherId);
            List<Assignment> result = addAssignments(resultSet);
            return result;
        } catch (SQLException sqle) {
            throw new AccessException("Problem fetching all assignments", sqle);
        }
    }

    @Override
    public List<Assignment> at(String date) throws AccessException {
        try {
            ResultSet resultSet = db.fetch(SELECT_ALL + " where schedule.day = '" + date + " 08:00:00'");
            List<Assignment> result = addAssignments(resultSet);
            return result;
        } catch (SQLException sqle) {
            throw new AccessException("Problem fetching all assignments", sqle);
        }
    }

    @Override
    public List<Assignment> forTeacherAt(String teacherId, String date) throws AccessException {
        try {
            ResultSet resultSet = db
                    .fetch(SELECT_WITH_SUBSTITUTE_ID + teacherId + " and schedule.day='" + date + " 08:00:00'");
            List<Assignment> result = addAssignments(resultSet);
            return result;
        } catch (SQLException sqle) {
            throw new AccessException("Problem fetching all assignments", sqle);
        }
    }

    //SQLException are catched and AcessException is thrown instead.
    private List<Assignment> addAssignments(ResultSet rs) throws SQLException {
        List<Assignment> result = new ArrayList<>();
        while (rs.next()) {
            Substitute substitute = new Substitute(rs.getString("name"));
            String date = rs.getString("day");
            School school = new School(rs.getString("school_name"), rs.getString("address"));
            Assignment assignment = new Assignment(substitute, date, school);
            result.add(assignment);
        }
        return result;
    }
}
