package se.yrgo.schedule.formatter;

import java.util.List;

import se.yrgo.schedule.assignment.Assignment;
import org.json.*;

/**
 * A class implementing the Formatter interface. Formats a List of Assignment to
 * JSON.
 *
 */
public class JsonFormatter implements Formatter {

    @Override
    public String format(List<Assignment> assignments) {
        if (assignments.size() == 0) {
            return "[]";
        } else {
            JSONArray JSON = new JSONArray();
            for (Assignment assignment : assignments) {
                JSON.put(JSONAssignment(assignment));
            }
            return JSON.toString(2);
        }

    }

    private static JSONObject JSONAssignment(Assignment assign) {
        JSONObject JSONAssign = new JSONObject();

        JSONAssign.put("date", assign.date());

        JSONObject JSONSubstitute = new JSONObject();
        JSONSubstitute.put("name", assign.teacher().getName());
        JSONAssign.put("substitute", JSONSubstitute);

        JSONObject JSONSchool = new JSONObject();
        JSONSchool.put("school_name", assign.school().getName());
        JSONSchool.put("address", assign.school().getAddress());
        JSONAssign.put("school", JSONSchool);

        return JSONAssign;
    }

}