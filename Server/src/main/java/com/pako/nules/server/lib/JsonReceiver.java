package com.pako.nules.server.lib;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.pako.nules.server.Test;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Pavlo Kotelnytskyi
 * @date 5/15/2017
 */

public class JsonReceiver {
	private static final Logger log = Logger.getLogger(Test.class.getName());

	public JSONObject convert(HttpServletRequest request) throws IOException {
		StringBuffer jb = new StringBuffer();
		String line;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) { /*report an error*/ }
		log.info(jb.toString());
		try {
			return new JSONObject(jb.toString());
		} catch (JSONException e) {
			// crash and burn
			throw new IOException("Error parsing JSON request string");
		}
	}
}
