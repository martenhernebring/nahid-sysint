package se.yrgo.schedule.formatter;

import java.util.Collection;
import java.util.List;

import se.yrgo.schedule.assignment.Assignment;
import org.json.*;
import org.w3c.dom.Element;

/**
 * A class implementing the Formatter interface. Formats a List of Assignment to
 * JSON.
 *
 */
public class JsonFormatter implements Formatter {

    @Override
    public String format(List<Assignment> assignments) {
        JSONArray JSON = new JSONArray();
        if (assignments.size() > 0) {
            for (Assignment assignment : assignments) {
                JSON.put(JSONAssignment(assignment));
            }
        } else {
            JSON = notFound();
        }
        return JSON.toString(2);

    }

    private static JSONObject JSONAssignment(Assignment assign) {
        JSONObject JSONAssign = new JSONObject();

        JSONAssign.put("date", assign.date());

        JSONAssign.put("substitute", createSimple("name", assign.teacher().getName()));

        JSONObject JSONSchool = new JSONObject();
        JSONSchool.put("school_name", assign.school().getName());
        JSONSchool.put("address", assign.school().getAddress());
        JSONAssign.put("school", JSONSchool);

        return JSONAssign;
    }

    private static JSONObject createSimple(String meta, String data) {
        JSONObject JSONSimple = new JSONObject();
        JSONSimple.put(meta, data);
        return JSONSimple;
    }

    private static JSONArray notFound() {
        JSONArray JSONNotFound = new JSONArray();

        JSONNotFound.put(createSimple("title", "No assignments found"));
        JSONNotFound.put(createSimple("body", "No assignment found for that date and/or substitute"));
        JSONNotFound.put(createSimple("footer", " - Try with a new date and/or substitute"));

        return JSONNotFound;
    }

}