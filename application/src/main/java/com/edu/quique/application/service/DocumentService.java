package com.edu.quique.application.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.domain.TeachingHour;
import com.edu.quique.application.domain.TimetableGroup;
import com.edu.quique.application.exceptions.ErrorUpdateXMLException;
import com.edu.quique.application.ports.in.services.DocumentServicePort;
import com.edu.quique.application.ports.in.services.TeacherServicePort;
import com.edu.quique.application.ports.in.services.TeachingHoursServicePort;
import com.edu.quique.application.ports.in.services.TimetableGroupServicePort;
import com.edu.quique.application.utils.DaysOfWeek;
import com.edu.quique.application.utils.TimeInterval;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static com.edu.quique.application.utils.AppConstants.*;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class DocumentService implements DocumentServicePort {

  private TeacherServicePort teacherService;
  private TeachingHoursServicePort teachingHoursService;
  private TimetableGroupServicePort timetableGroupService;

  public void updateXML(InputStream file) {

    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = null;
    Document doc = null;
    try {
      dBuilder = dbFactory.newDocumentBuilder();
      doc = dBuilder.parse(file);
    } catch (SAXException | IOException | ParserConfigurationException e) {
      throw new ErrorUpdateXMLException(e.getMessage());
    }
    NodeList nodeList = doc.getDocumentElement().getChildNodes();
    Map<String, Teacher> teachersMap = new HashMap<>();

    teachingHoursService.deleteAll();
    timetableGroupService.deleteAll();
    teacherService.deleteAllTeachers();

    processNodeList(nodeList, teachersMap);
  }

  private void processNodeList(NodeList nodeList, Map<String, Teacher> teachersMap) {
    IntStream.range(0, nodeList.getLength())
        .mapToObj(nodeList::item)
        .filter(node -> node.getNodeType() == Node.ELEMENT_NODE)
        .map(Element.class::cast)
        .forEach(elem -> processElement(elem, teachersMap));
  }

  private void processElement(Element elem, Map<String, Teacher> teachersMap) {
    NodeList nodeList = elem.getChildNodes();
    IntStream.range(0, nodeList.getLength())
        .mapToObj(nodeList::item)
        .filter(node2 -> node2.getNodeType() == Node.ELEMENT_NODE)
        .map(Element.class::cast)
        .forEach(elem2 -> processXmlElement(elem2, teachersMap));
  }

  private void processXmlElement(Element elem2, Map<String, Teacher> teachersMap) {
    switch (elem2.getTagName()) {
      case TEACHER -> processTeacherElement(elem2, teachersMap);
      case TEACHING_HOURS -> processTeachingHoursElement(elem2, teachersMap);
      case TIMETABLE_GROUP -> processTimetableGroupElement(elem2, teachersMap);
    }
  }

  private void processTeacherElement(Element elem2, Map<String, Teacher> teachersMap) {
    var teacher =
        Teacher.builder()
            .teacherId(elem2.getAttribute(DOCUMENT))
            .email(elem2.getAttribute(EMAIL))
            .name(elem2.getAttribute(NAME))
            .firstSurname(elem2.getAttribute(FIRST_SURNAME))
            .secondSurname(elem2.getAttribute(SECOND_SURNAME))
            .build();
    teachersMap.put(teacher.getTeacherId(), teacherService.save(teacher));
  }

  private void processTeachingHoursElement(Element elem2, Map<String, Teacher> teachersMap) {
    TeachingHour teachingHour =
        TeachingHour.builder()
            .timeInterval(
                TimeInterval.builder()
                    .startHour(LocalTime.parse(elem2.getAttribute(START_HOUR), FORMAT_TIME))
                    .endHour(LocalTime.parse(elem2.getAttribute(END_HOUR), FORMAT_TIME))
                    .build())
            .dayOfWeek(DaysOfWeek.getByDay(elem2.getAttribute(DAY_OF_WEEK)).getDay())
            .teacher(teachersMap.getOrDefault(elem2.getAttribute(DOCUMENT), null))
            .occupation(elem2.getAttribute(OCCUPATION))
            .build();
    teachingHoursService.save(teachingHour);
  }

  private void processTimetableGroupElement(Element elem2, Map<String, Teacher> teachersMap) {
    TimetableGroup timetableGroup =
        TimetableGroup.builder()
            .dayOfWeek(DaysOfWeek.getByDay(elem2.getAttribute(DAY_OF_WEEK)).getDay())
            .startHour(LocalTime.parse(elem2.getAttribute(START_HOUR), FORMAT_TIME))
            .endHour(LocalTime.parse(elem2.getAttribute(END_HOUR), FORMAT_TIME))
            .group(elem2.getAttribute(GROUP))
            .classroom(elem2.getAttribute(CLASSROOM))
            .content(elem2.getAttribute(CONTENT))
            .teacher(teachersMap.getOrDefault(elem2.getAttribute(TEACHER), null))
            .build();
    timetableGroupService.save(timetableGroup);
  }
}
