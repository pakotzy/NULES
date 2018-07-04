package com.pako.nules.client.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.pako.nules.client.POJO.Settings;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * @author Pavlo Kotelnytskyi
 * @date 6/8/2017
 */

@Dao
public interface SettingsDAO {
	@Insert(onConflict = REPLACE)
	void save(Settings settings);
	@Query("SELECT * FROM Settings WHERE id = :id")
	Settings load(int id);
	@Query("SELECT CASE WHEN EXISTS (\n" +
			"    SELECT id\n" +
			"    FROM Settings\n" +
			"    WHERE id = :id\n" +
			")\n" +
			"THEN CAST(1 AS BIT)\n" +
			"ELSE CAST(0 AS BIT) END")
	Boolean hasSettings(int id);
}
