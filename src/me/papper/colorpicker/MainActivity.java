package me.papper.colorpicker;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	View view; // Used for setting the background color
	/** Begin final values for the shared preferences **/

	final String SHARED_PREFS_NAME = "Colors";
	final int SHARED_PREFS_MODE = 0; // Make the preferences private
	/*
	 * Return 255 for the RGB values if there is no existing preference, creating
	 * a white background
	 */
	final int SHARED_PREFS_DEF_VALUE = 255;
	final String RED_STRING = "red";
	final String GREEN_STRING = "green";
	final String BLUE_STRING = "blue";

	/** End final values for the shared preferences **/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/* Initialize the view for settings the background color */
		view = this.getWindow().getDecorView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onResume() {
		super.onResume();
		SharedPreferences data = getSharedPreferences(SHARED_PREFS_NAME,
				SHARED_PREFS_MODE);
		Colors.red.addFirst(data.getInt(RED_STRING, SHARED_PREFS_DEF_VALUE));
		Colors.green
				.addFirst(data.getInt(GREEN_STRING, SHARED_PREFS_DEF_VALUE));
		Colors.blue.addFirst(data.getInt(BLUE_STRING, SHARED_PREFS_DEF_VALUE));
		view.setBackgroundColor(Colors.returnColor());
	}

	@Override
	public void onPause() {
		super.onPause();
		SharedPreferences data = getSharedPreferences(SHARED_PREFS_NAME,
				SHARED_PREFS_MODE);
		SharedPreferences.Editor editor = data.edit();
		editor.putInt(RED_STRING, Colors.red.getFirst());
		editor.putInt(GREEN_STRING, Colors.green.getFirst());
		editor.putInt(BLUE_STRING, Colors.blue.getFirst());
		editor.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_details:
			ViewDetailsDialogFragment.newInstance().show(getFragmentManager(),
					"Details Fragment");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onBackPressed() {
		view.setBackgroundColor(Colors.undo());
	}

	/*
	 * The button is just transparent, so you're actually changing the
	 * background color
	 */
	public void onNewColorButtonClick(View v) {
		v.getRootView().setBackgroundColor(Colors.generateColor());
	}
}