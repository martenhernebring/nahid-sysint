package se.yrgo.schedule.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

import se.yrgo.schedule.assignment.AccessException;
import se.yrgo.schedule.assignment.Assignment;
import se.yrgo.schedule.assignment.Assignments;
import se.yrgo.schedule.assignment.AssignmentsFactory;
import se.yrgo.schedule.formatter.Formatter;
import se.yrgo.schedule.formatter.FormatterFactory;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * <p>
 * Listens to requests on localhost:8080/v1/ and accepts the following
 * parameters:
 * <ul>
 * <li>none - lists all schedules for all teachers</li>
 * <li>substitute_id - the ID for a substitute teacher you want to list the
 * schedult for</li>
 * <li>day - the day (YYYY-mm-dd) you want to see the schedule for</li>
 * </ul>
 * <p>
 * The substitute_id and day parameters can be combined or used alone.
 * </p>
 * <p>
 * Use the bash file compile_servlet_and_start_winstone.sh to automatically
 * compile and start the servlet.
 * </p>
 * <p>
 * Use the bash file clean.sh to automatically remove compiled files to save
 * space.
 * </p>
 * <p>
 * Use the various test files to test the error messages.
 * </p>
 */
public class ScheduleServlet extends HttpServlet {

    private PrintWriter out;
    
    // Serializable class requirement
    private static final long serialVersionUID = 3107939771013777557L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(UTF_8.name());
        ParamParser parser = new ParamParser(request);
        response.setContentType(parser.contentType());
        response.setCharacterEncoding(UTF_8.name());
        out = response.getWriter();
        try {
            List<Assignment> assignments = schedule(parser);
            if (assignments.size() <= 0) {
                //For testing no data found please use the test_bad_date_and_teacher.sh file
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
            Formatter formatter = FormatterFactory.getFormatter(parser.format());
            String result = formatter.format(assignments);
            if (!result.equals("XML Error")) {
                out.println(result);
            } else {
                xmlError(response);
            }  
        } catch (AccessException e) {
            accessError(response);
        } catch (IllegalArgumentException e) {
            formatError(response);
        }
        out.close();
    }
    
    private List<Assignment> schedule(ParamParser parser) throws AccessException {
        Assignments db = AssignmentsFactory.getAssignments();
        List<Assignment> assignments = new ArrayList<>();
        switch (parser.type()) {
        case ALL:
            assignments = db.all();
            break;
        case TEACHER_ID_AND_DAY:
            assignments = db.forTeacherAt(parser.teacherId(), parser.day());
            break;
        case DAY:
            assignments = db.at(parser.day());
            break;
        case TEACHER_ID:
            assignments = db.forTeacher(parser.teacherId());
        }
        return assignments;
    }

    //For testing not supported formats please use the testjsong.sh file.
    private void formatError(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("text/html");
        out.println("<html><head><title>Format error</title></head>");
        out.println("<body>Format missing or not supported");
        out.println(" - We support xml and json</body>");
        out.println("</html>");     
    }

    private void accessError(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType("text/html");
        out.println("<html><head><title>Problems with database</title></head>");
        out.println("<body>Error accessing servlet database");
        out.println(" - Please report to the administrator</body>");
        out.println("</html>");
        
    }

    private void xmlError(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType("text/html");
        out.println("<html><head><title>XML error</title></head>");
        out.println("<body>Could not create a XML-file");
        out.println(" - Please report to the administrator</body>");
        out.println("</html>");        
    }
}
