package com.pako.nules.client.Data;

import android.content.Context;

import com.pako.nules.client.POJO.Settings;

/**
 * @author Pavlo Kotelnytskyi
 * @date 6/3/2017
 */

public class Repository {
	private static final String URL = "https://nules-appserver.appspot.com/";
	private static final Repository repository = new Repository();

	private static NulesDatabase nulesDatabase;

	private Repository(){}

	public static Repository getInstance() {
		return repository;
	}

	public void init(Context context) {
		nulesDatabase = nulesDatabase.getDatabase(context);
	}

	public Settings getSettings() {
		Settings settings = nulesDatabase.settingsDAO().load(0);
		return settings;
	}

	public void saveSettings(Settings settings) {
		nulesDatabase.settingsDAO().save(settings);
	}
}
