package com.pako.nules.server;

import com.google.appengine.repackaged.com.google.gson.*;
import com.google.common.reflect.TypeToken;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.pako.nules.server.db.*;
import com.pako.nules.server.db.System;
import com.pako.nules.server.lib.SessionIdentifierGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class Test extends HttpServlet {
	private static final Logger log = Logger.getLogger(Test.class.getName());
	private static final Long systemId = 5629499534213120L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
/*		JsonObject jsonRequest = new JsonReceiver().convert(request);
		log.info(jsonRequest.toString());

		String subjectString = jsonRequest.getString("subject");
		String teacherString = jsonRequest.getString("teacher");*/

		Key<System> systemKey = Key.create(System.class, systemId);

		Facility facility15 = new Facility(systemKey, 15L, "НУБіП, Корпус 15", 50.3814597, 30.4960151);
		Facility facility11 = new Facility(systemKey, 11L, "НУБіП, Корпус 15", 50.3807517, 30.4946913);

		Faculty faculty = new Faculty(systemKey, "іт", "Інформаційних технологій");
		Key<Faculty> facultyKey = Key.create(faculty);

		Group group = new Group(facultyKey, "іуст");

		Teacher teacher = new Teacher(systemKey, "Голуб", new SessionIdentifierGenerator().nextSessionId(), facultyKey,
				"Белла", "Львівна", "Голуб");

		Key<Group> groupKey = Key.create(group);

		Schedule schedule = new Schedule(systemKey,"іуст", null, groupKey, null);

		Ref<Facility> facility15Ref = Ref.create(Key.create(facility15));
		Ref<Facility> facility11Ref = Ref.create(Key.create(facility11));

		Ref<Teacher> teacherRef = Ref.create(Key.create(teacher));

		List<Subject> subjects = new ArrayList<>();
		subjects.add(new Subject(Key.create(schedule), "25", "Об`єктно-орієнтоване моделювання",
				225, null, facility15Ref));
		subjects.add(new Subject(Key.create(schedule), "26", "Принципи розподіленого і мережевого програмування",
				317, null, facility11Ref));
		subjects.add(new Subject(Key.create(schedule), "35", "Інтелектуальні системи",
				213, null, facility15Ref));
		subjects.add(new Subject(Key.create(schedule), "36", "Безпека і надійність",
				317, null, facility11Ref));
		subjects.add(new Subject(Key.create(schedule), "37", "Безпека і надійність",
				317, null, facility11Ref));
		subjects.add(new Subject(Key.create(schedule), "41", "Принципи розподіленого і мережевого програмування",
				225, null, facility15Ref));
		subjects.add(new Subject(Key.create(schedule), "42", "Data mining",
				213, teacherRef, facility15Ref));
		subjects.add(new Subject(Key.create(schedule), "43", "Data mining",
				213, teacherRef, facility15Ref));
		subjects.add(new Subject(Key.create(schedule), "41", "Методологія і організація",
				214, null, facility15Ref));
		subjects.add(new Subject(Key.create(schedule), "42", "Методологія і організація",
				230, null, facility15Ref));

		List<Ref<Subject>> subjectsRef = new ArrayList<>();
		for (Subject subject : subjects) {
			subjectsRef.add(Ref.create(Key.create(subject)));
			ofy().save().entity(subject);
		}
		schedule.setSubjects(subjectsRef);

		String inputString1 = "09-06-2017";
		String inputString2 = "09-06-2017";
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date1 = new Date();
		Date date2 = new Date();
		try {
			date1 = dateFormat.parse(inputString1);
			date2 = dateFormat.parse(inputString2);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Message message1 = new Message(systemKey, null, teacherRef, groupKey, null,
				"Попередній захист магістерської роботи",
				"З 7 по 9 червня в 15 корпусі університету відбудеться попередній захист магістерських робіт. З собою мати презентацію та підготовлений виступ.",
				date1, null);

		Message message2 = new Message(systemKey, null, teacherRef, groupKey, null,
				"Захист магістерської роботи",
				"З 14 по 16 червня в корпусі №15, аудиторія 230, відбудеться захист магістерських робіт. З собою мати підписаний диплом, залікову кніжку, презентацію та підготовлений виступ.",
				date2, null);

		ofy().save().entities(facility11, facility15, faculty, group, teacher, schedule, message1, message2).now();

		Faculty faculty1 = ofy().load().key(facultyKey).now();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.getWriter().println(faculty1.name);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}
}
