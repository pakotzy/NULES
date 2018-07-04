package com.pako.nules.server;

import com.googlecode.objectify.Key;
import com.pako.nules.server.db.Schedule;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * @author Pavlo Kotelnytskyi
 * @date 6/9/2017.
 */
public class Check extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("g");
		Schedule schedule = ofy().load().type(Schedule.class).parent(Key.create(System.class, 5629499534213120L)).id(param).now();
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		if (param != null && schedule != null) {
			resp.getWriter().print(schedule.id);
		} else {
			resp.getWriter().print("not found");
		}
	}
}
