package com.pako.nules.client.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.pako.nules.client.POJO.Settings;

/**
 * @author Pavlo Kotelnytskyi
 * @date 6/8/2017
 */

@Database(version = 1, entities = {Settings.class})
public abstract class NulesDatabase extends RoomDatabase {
	private static NulesDatabase INSTANCE;

	public abstract SettingsDAO settingsDAO();

	public static NulesDatabase getDatabase(Context context) {
		if (INSTANCE == null) {
			INSTANCE = Room.databaseBuilder(context, NulesDatabase.class, "nules-database")
					.allowMainThreadQueries()
					.build();
		}
		return INSTANCE;
	}

	public static void destroyInstance() {
		INSTANCE = null;
	}
}
