package accurate.bmi.calculator.hyaline;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

import accurate.bmi.calculator.hyaline.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class BmrTdee extends Activity implements
		RadioGroup.OnCheckedChangeListener {

	TextView bmrTxt, tdeeTxt;
	Button calculate;
	Spinner spinner;
	double factor, tdee, bmr = 0;
	double intHeightIn = 0;
	double intWeightLbs = 0;
	int intAge = 0;
	static boolean boolGender = true;
	RadioButton genderMale;
	RadioButton genderFemale;
	RadioGroup mRadioGroup;

	static int genderChoice = -1;

	EditText heightFt, heightIn, weightLbs, age;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.bmrtdee);
		findViewById(R.id.main).requestFocus();

		calculate = (Button) findViewById(R.id.bCalculate);
		spinner = (Spinner) findViewById(R.id.sDActivity);

		genderMale = (RadioButton) findViewById(R.id.rbMale);
		genderFemale = (RadioButton) findViewById(R.id.rbFemale);
		mRadioGroup = (RadioGroup) findViewById(R.id.group1);

		mRadioGroup.setOnCheckedChangeListener(this);

		heightFt = (EditText) findViewById(R.id.etHeightFt);
		heightIn = (EditText) findViewById(R.id.etHeightIn);
		weightLbs = (EditText) findViewById(R.id.etWeightLbs);
		age = (EditText) findViewById(R.id.etAge);

		AdView adview = (AdView) findViewById(R.id.ad);
		AdRequest re = new AdRequest();
		// re.setTesting(true);
		adview.loadAd(re);

		bmrTxt = (TextView) findViewById(R.id.tvBMRTxt);
		tdeeTxt = (TextView) findViewById(R.id.tvTDEETxt);

		// spinner

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.activity_levels,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

			// spinner selection specifies activity level -- activity factor
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {
				if (position == 0) { // sedentary
					factor = 1.2;
				} else if (position == 1) { // lightly active
					factor = 1.375;
				} else if (position == 2) { // moderately active
					factor = 1.55;
				} else if (position == 3) { // very active
					factor = 1.725;
				} else if (position == 4) { // extra active
					factor = 1.9;
				}
			}
		});

		calculate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View w) { // TODO
											// Auto-generated
											// method
											// stub

				String stringHeightFt = heightFt.getText().toString();
				String stringHeightIn = heightIn.getText().toString();
				String stringWeightLbs = weightLbs.getText().toString();
				String stringAge = age.getText().toString();

				double htFt = 0.5;
				double htIn = 0.5;
				double wtLbs = 0.5;
				intAge = 0;

				// if female
				if (!boolGender) {
					try {

						htFt = Double.parseDouble(stringHeightFt);
						if (stringHeightIn.length() == 0) { // parsing height in
															// Inches
							htIn = 0;
						} else {
							htIn = Double.parseDouble(stringHeightIn);
						}

						wtLbs = Double.parseDouble(stringWeightLbs);
						intAge = Integer.parseInt(stringAge);

					} catch (NumberFormatException nfe) {
						System.out.println("Could not parse " + nfe);
					}

					calcFemale(htFt, htIn, wtLbs, intAge);

				} else if (boolGender) { // if male

					try {

						htFt = Double.parseDouble(stringHeightFt);
						if (stringHeightIn.length() == 0) { // parsing height in
															// Inches
							htIn = 0;
						} else {
							htIn = Double.parseDouble(stringHeightIn);
						}

						wtLbs = Double.parseDouble(stringWeightLbs);
						intAge = Integer.parseInt(stringAge);

					} catch (NumberFormatException nfe) {
						System.out.println("Could not parse " + nfe);
					}

					calcMale(htFt, htIn, wtLbs, intAge);

				} else {
					bmr = -1;
					bmrTxt.setText("error " + bmr);

				}

				tdee = (double) Math.round(100 * (bmr * factor)) / 100; // rounding
																		// tdee

				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(spinner.getWindowToken(), 0);

				// heightFt.setText("");
				// heightIn.setText("");
				// weightLbs.setText("");
				// age.setText("");
				bmrTxt.setText("Your BMR is " + bmr);
				tdeeTxt.setText("Your TDEE is " + tdee);

			}
		});

	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkedId) {
		switch (checkedId) {
		case R.id.rbMale:
			boolGender = true;
			break;
		case R.id.rbFemale:
			boolGender = false;
			break;
		}
	}

	public void calcFemale(double htFt, double htIn, double wtLbs, int intAge) {
		intHeightIn = 12 * htFt + htIn;
		bmr = (double) Math.round(100 * (655 + (4.35 * wtLbs)
				+ (4.7 * intHeightIn) - (4.7 * intAge))) / 100;
		// rounding to .0
	}

	public void calcMale(double htFt, double htIn, double wtLbs, int intAge) {
		intHeightIn = 12 * htFt + htIn;
		bmr = (double) Math.round(100 * (66 + (6.23 * wtLbs)
				+ (12.7 * intHeightIn) - (6.8 * intAge))) / 100;
		// rounding to .0

	}

}
