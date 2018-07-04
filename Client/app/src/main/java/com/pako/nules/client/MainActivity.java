package com.pako.nules.client;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.pako.nules.client.Data.SettingsViewModel;

public class MainActivity extends LifecycleActivity implements
		AppCompatCallback,
		NavigationView.OnNavigationItemSelectedListener,
		MainFragment.OnFragmentInteractionListener,
		SettingsFragment.OnFragmentInteractionListener,
		FirstLaunchFragment.OnFragmentInteractionListener,
		SearchFragment.OnFragmentInteractionListener,
		MessageFragment.OnFragmentInteractionListener,
		ScheduleFragment.OnFragmentInteractionListener{

	private SettingsViewModel settingsViewModel;
	private AppCompatDelegate delegate;
	private NavigationView navigationView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		delegate = AppCompatDelegate.create(this, this);
		delegate.onCreate(savedInstanceState);
		delegate.setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		delegate.setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
				.setAction("Action", null).show());

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		toggle.syncState();

		navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
		getSupportFragmentManager().beginTransaction().replace(R.id.contentHolder, new MainFragment()).commit();

		settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);

		if (settingsViewModel.init(this)) {
			View hView =  navigationView.getHeaderView(0);
			TextView nav = (TextView) hView.findViewById(R.id.nav_header_name_text);
			String name = settingsViewModel.second_name.getValue();
			String secondname = settingsViewModel.name.getValue();
			nav.setText(name + " " + secondname);
			nav = (TextView) hView.findViewById(R.id.nav_header_group_text);
			String group = settingsViewModel.group.getValue();
			nav.setText(group);

			navigationView.setCheckedItem(R.id.nav_main);
			Fragment fragment = new MainFragment();
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.contentHolder, fragment).commit();
		} else {
			Fragment innerFragment = new FirstLaunchFragment();
			FragmentManager innerManager = getSupportFragmentManager();
			innerManager.beginTransaction().replace(R.id.contentHolder, innerFragment).commit();
		}

		drawer.addDrawerListener(new DrawerLayout.DrawerListener() {

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {}

			@Override
			public void onDrawerOpened(View drawerView) {
				Menu menu = navigationView.getMenu();
				int checked = -1;
				for (int i = 0; i < menu.size(); i++) {
					MenuItem item = menu.getItem(i);
					if (item.isChecked()) {
						checked = item.getItemId();
						break;
					}
				}
				switch (checked) {
					case R.id.nav_manage: {
						TextView text = (TextView) findViewById(R.id.settings_name_text);
						String name = text.getText().toString();
						text = (TextView) findViewById(R.id.settings_secondname_text);
						String second_name = text.getText().toString();
						Spinner spinner = (Spinner) findViewById(R.id.settings_group_spinner);
						String group = spinner.getSelectedItem().toString();
						CheckBox box = (CheckBox) findViewById(R.id.settings_alert_checkbox);
						Boolean notifications = box.isChecked();
						text = (TextView) findViewById(R.id.settings_key_text);
						String key = text.getText().toString();
						settingsViewModel.fill(name, second_name, group, key, notifications);

						View hView =  navigationView.getHeaderView(0);
						TextView nav = (TextView) hView.findViewById(R.id.nav_header_name_text);
						nav.setText(settingsViewModel.second_name.getValue() + " " + settingsViewModel.name.getValue());
						nav = (TextView) hView.findViewById(R.id.nav_header_group_text);
						nav.setText(settingsViewModel.group.getValue());

						if (group.equals("")) {
							drawer.closeDrawers();
							menu.findItem(checked).setChecked(false);
							Fragment fragment = new FirstLaunchFragment();
							FragmentManager fragmentManager = getSupportFragmentManager();
							fragmentManager.beginTransaction().replace(R.id.contentHolder, fragment).commit();
						}

						break;
					}
					default: {}
				}
			}

			@Override
			public void onDrawerClosed(View drawerView) {}

			@Override
			public void onDrawerStateChanged(int newState) {}
		});
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		Fragment fragment = null;
		int id = item.getItemId();

		switch (id) {
			case R.id.nav_main: {
				fragment = new MainFragment();
				break;
			}
			case R.id.nav_schedule: {
				fragment = new ScheduleFragment();
				break;
			}
			case R.id.nav_messages: {
				fragment = new MessageFragment();
				break;
			}
			case R.id.nav_search: {
				fragment = new SearchFragment();
				break;
			}
			case R.id.nav_manage: {
				fragment = new SettingsFragment();
				break;
			}
			case R.id.nav_docs: {
				Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.moodle.moodlemobile");
				if (launchIntent != null) {
					startActivity(launchIntent);
				}
				return false;
			}
			default:{}
		}

		if (settingsViewModel.group.getValue().equals("")) {
			fragment = new FirstLaunchFragment();
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.contentHolder, fragment).commit();
		}

		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.contentHolder, fragment).commit();

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	@Override
	public void onFragmentInteraction(Uri uri) {

	}

	@Override
	public void onSupportActionModeStarted(ActionMode actionMode) {

	}

	@Override
	public void onSupportActionModeFinished(ActionMode actionMode) {

	}

	@Nullable
	@Override
	public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
		return null;
	}

	public void settingsClick(View view) {
		navigationView.setCheckedItem(R.id.nav_manage);
		Fragment fragment = new SettingsFragment();
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.contentHolder, fragment).commit();
	}

	public void mainMessagesClick(View view) {
		navigationView.setCheckedItem(R.id.nav_messages);
		Fragment fragment = new MessageFragment();
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.contentHolder, fragment).commit();
	}

	public void mainFirstClick(View view) {
		Uri gmmIntentUri = Uri.parse("geo:50.3814597,30.4960151?q=50.3814597,30.4960151(НУБіП, Корпус 15)");
		Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
		mapIntent.setPackage("com.google.android.apps.maps");
		startActivity(mapIntent);
	}

	public void mainSecondClick(View view) {
		Uri gmmIntentUri = Uri.parse("geo:50.3807517,30.4946913?q=50.3807517,30.4946913(НУБіП, Корпус 11)");
		Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
		mapIntent.setPackage("com.google.android.apps.maps");
		startActivity(mapIntent);
	}
}
