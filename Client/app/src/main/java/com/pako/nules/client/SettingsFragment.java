package com.pako.nules.client;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.pako.nules.client.Data.SettingsViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends LifecycleFragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private SettingsViewModel settingsViewModel;

	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	public SettingsFragment() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment SettingsFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static SettingsFragment newInstance(String param1, String param2) {
		SettingsFragment fragment = new SettingsFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_settings, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		settingsViewModel = ViewModelProviders.of(getActivity()).get(SettingsViewModel.class);

		settingsViewModel.name.observe(this, s -> {
			TextView text = (TextView) getView().findViewById(R.id.settings_name_text);
			text.setText(settingsViewModel.name.getValue());
		});

		settingsViewModel.second_name.observe(this, s -> {
			TextView text = (TextView) getView().findViewById(R.id.settings_secondname_text);
			text.setText(settingsViewModel.second_name.getValue());
		});

		settingsViewModel.group.observe(this, s -> {
			Spinner spinner = (Spinner) getView().findViewById(R.id.settings_group_spinner);
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.groups, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setSelection(adapter.getPosition(settingsViewModel.group.getValue()));
		});

		settingsViewModel.notifications.observe(this, aBoolean -> {
			CheckBox box = (CheckBox) getView().findViewById(R.id.settings_alert_checkbox);
			box.setChecked(settingsViewModel.notifications.getValue());
		});

		settingsViewModel.key.observe(this, s -> {
			TextView text = (TextView) getView().findViewById(R.id.settings_key_text);
			text.setText(settingsViewModel.key.getValue());
		});
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}

	public interface OnHeadlineSelectedListener {
		public void onFragmentInteraction(Uri uri);
	}
}
