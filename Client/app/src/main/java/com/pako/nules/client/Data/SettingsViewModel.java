package com.pako.nules.client.Data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.pako.nules.client.POJO.Settings;

/**
 * @author Pavlo Kotelnytskyi
 * @date 6/8/2017
 */

public class SettingsViewModel extends ViewModel {
	public LiveData<String> name = new MutableLiveData<>();
	public LiveData<String> second_name = new MutableLiveData<>();
	public LiveData<String> group = new MutableLiveData<>();
	public LiveData<String> key = new MutableLiveData<>();
	public LiveData<Boolean> notifications = new MutableLiveData<>();
	private Repository repository;
	private Settings settings = new Settings();

	public SettingsViewModel() {
		repository = Repository.getInstance();
	}

	public void fill(String name, String second_name, String group, String key, Boolean notifications) {
		MutableLiveData<String> data = new MutableLiveData<>();
		data.setValue(name);
		this.name = data;
		data = new MutableLiveData<>();
		data.setValue(second_name);
		this.second_name = data;
		data = new MutableLiveData<>();
		data.setValue(group);
		this.group = data;
		data = new MutableLiveData<>();
		data.setValue(key);
		this.key = data;
		setNotifications(notifications);

		settings = new Settings(name, second_name, group, key, notifications);
		repository.saveSettings(settings);
	}

	public boolean init(Context context) {
		if (group.getValue() != null) {
			return true;
		}
		repository.init(context);
		settings = repository.getSettings();
		String sgroup = settings.group;
		if (settings == null) {
			return false;
		} else {
			MutableLiveData<String> data = new MutableLiveData<>();
			data.setValue(settings.name);
			this.name = data;
			data = new MutableLiveData<>();
			data.setValue(settings.second_name);
			this.second_name = data;
			data = new MutableLiveData<>();
			data.setValue(settings.group);
			this.group = data;
			data = new MutableLiveData<>();
			data.setValue(settings.key);
			this.key = data;
			setNotifications(settings.notifications);
			return true;
		}
	}

	private void initBool() {
		MutableLiveData<Boolean> data = new MutableLiveData<>();
		data.setValue(false);
		this.notifications = data;
	}

	private void setNotifications(Boolean notifications) {
		MutableLiveData<Boolean> data = new MutableLiveData<>();
		data.setValue(notifications);
		this.notifications = data;
 	}
}
