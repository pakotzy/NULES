package com.pako.nules.server;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.googlecode.objectify.Key;
import com.pako.nules.server.lib.SessionIdentifierGenerator;
import com.pako.nules.server.pojos.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.System;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * @author Pavlo Kotelnytskyi
 * @date 6/8/2017.
 */
public class ScheduleServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String param = req.getParameter("g");
		if (param == null || !param.equals("іуст")) {
			resp.getWriter().print("null");
			return;
		}

		Facility facility15 = new Facility(15L, "НУБіП, Корпус 15", 50.3814597, 30.4960151);
		Facility facility11 = new Facility(11L, "НУБіП, Корпус 15", 50.3807517, 30.4946913);

		Faculty faculty = new Faculty("іт", "Інформаційних технологій");

		Group group = new Group("іуст");

		Teacher teacher = new Teacher("Белла", new SessionIdentifierGenerator().nextSessionId(), faculty.id,
				"Белла", "Львівна", "Голуб");

		Schedule schedule = new Schedule("іуст", null, group.id, null);

		List<Subject> subjects = new ArrayList<>();
		subjects.add(new Subject(schedule.id, "25", "Об`єктно-орієнтоване моделювання",
				225, null, facility15));
		subjects.add(new Subject(schedule.id, "26", "Принципи розподіленого і мережевого програмування",
				317, null, facility11));
		subjects.add(new Subject(schedule.id, "35", "Інтелектуальні системи",
				213, null, facility15));
		subjects.add(new Subject(schedule.id, "36", "Безпека і надійність",
				317, null, facility11));
		subjects.add(new Subject(schedule.id, "37", "Безпека і надійність",
				317, null, facility11));
		subjects.add(new Subject(schedule.id, "41", "Принципи розподіленого і мережевого програмування",
				225, null, facility15));
		subjects.add(new Subject(schedule.id, "42", "Data mining",
				213, teacher, facility15));
		subjects.add(new Subject(schedule.id, "43", "Data mining",
				213, teacher, facility15));
		subjects.add(new Subject(schedule.id, "41", "Методологія і організація",
				214, null, facility15));
		subjects.add(new Subject(schedule.id, "42", "Методологія і організація",
				230, null, facility15));


		schedule.setSubjects(subjects);

		Gson gson = new Gson();

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(gson.toJson(schedule));
	}
}
