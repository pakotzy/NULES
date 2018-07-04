package com.pako.nules.server.db;

import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Pavlo Kotelnytskyi
 * @date 5/17/2017
 */

public class OfyHelper implements ServletContextListener {
	public void contextInitialized(ServletContextEvent event) {
		ObjectifyService.register(System.class);
		ObjectifyService.register(Facility.class);
		ObjectifyService.register(Faculty.class);
		ObjectifyService.register(Group.class);
		ObjectifyService.register(Teacher.class);
		ObjectifyService.register(Schedule.class);
		ObjectifyService.register(Subject.class);
		ObjectifyService.register(Message.class);
	}

	public void contextDestroyed(ServletContextEvent event) {
	}
}
