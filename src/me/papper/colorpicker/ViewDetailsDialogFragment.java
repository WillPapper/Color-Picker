package me.papper.colorpicker;

import java.util.LinkedList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.NumberPicker;

/* Use a NumberPicker between 0 and 255, not an EditText */
public class ViewDetailsDialogFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final NumberPicker red = new NumberPicker(getActivity()
				.getApplicationContext());
		final NumberPicker green = new NumberPicker(getActivity()
				.getApplicationContext());
		final NumberPicker blue = new NumberPicker(getActivity()
				.getApplicationContext());
		initializeNumberPicker(red, Colors.red);
		initializeNumberPicker(green, Colors.green);
		initializeNumberPicker(blue, Colors.blue);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.argb_details);
		builder.setMessage(this.getString(R.string.alpha_details)
				+ Colors.ALPHA + "\n" + this.getString(R.string.red_details)
				+ Colors.red.getFirst() + "\n"
				+ this.getString(R.string.green_details)
				+ Colors.green.getFirst() + "\n"
				+ this.getString(R.string.blue_details)
				+ Colors.blue.getFirst());
		builder.setPositiveButton(R.string.ok_button,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Colors.red.addFirst(red.getValue());
						Colors.green.addFirst(green.getValue());
						Colors.blue.addFirst(blue.getValue());
					}
				});
		builder.setNegativeButton(R.string.cancel_button,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// Do nothing
					}
				});
		return builder.create();
	}

	private void initializeNumberPicker(NumberPicker n, LinkedList<Integer> l) {
		n.setMinValue(0);
		n.setMaxValue(255);
		n.setValue(l.getFirst());
		/*
		 * Since 0 and 255 create radically different colors, the selector wheel
		 * should not wrap
		 */
		n.setWrapSelectorWheel(false);
	}

	public static ViewDetailsDialogFragment newInstance() { // Prevents issues
															// arising from
															// MainActivity
															// being static
		return new ViewDetailsDialogFragment();
	}
}