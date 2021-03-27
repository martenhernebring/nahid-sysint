package se.yrgo.schedule.formatter;

import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import se.yrgo.schedule.assignment.Assignment;
import se.yrgo.schedule.model.School;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * A class implementing the Formatter interface. Formats a List of Assignment to
 * XML.
 *
 */
public class XmlFormatter implements Formatter {
    private Document document;

    @Override
    public String format(List<Assignment> assignments) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            return "XML Error";
        }
        document = docBuilder.newDocument();

        if (assignments.size() > 0) {
            Element root = document.createElement("schedules");
            document.appendChild(root);
            for (Assignment assignment : assignments) {
                root.appendChild(createChildNode(assignment));
            }
        } else {
            Element root = document.createElement("not_found");
            document.appendChild(root);
            addChild("title", "No assignments found", root);
            addChild("body", "No assignment found for that date and/or substitute", root);
            addChild("footer", " - Try with a new date and/or substitute", root);
        }
        String xml;
        try {
            xml = transform();
        } catch (TransformerException e) {
            return "XML Error";
        }

        return xml;
    }

    private String transform() throws TransformerException {
        StringWriter text = new StringWriter();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(text);
        transformer.transform(source, result);
        return text.toString();
    }

    private Node createChildNode(Assignment assign) {
        Element schedule = document.createElement("schedule");
        schedule.setAttribute("date", assign.date());

        Element schoolElement = createElement(assign.school());
        schedule.appendChild(schoolElement);

        Element substituteElement = document.createElement("substitute");
        addChild("name", assign.teacher().getName(), substituteElement);
        schedule.appendChild(substituteElement);

        return schedule;
    }

    private Element createElement(School school) {
        Element schoolElement = document.createElement("school");
        addChild("name", school.getName(), schoolElement);
        addChild("address", school.getAddress(), schoolElement);
        return schoolElement;
    }

    private void addChild(String childMeta, String childData, Element parent) {
        Element childElement = document.createElement(childMeta);
        childElement.appendChild(document.createTextNode(childData));
        parent.appendChild(childElement);
    }
}