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

public class FIOmetric extends Activity implements
		RadioGroup.OnCheckedChangeListener {

	TextView bmrTxt, tdeeTxt, bfTxt, bfCategory;
	Button calculate;
	Spinner spinner;
	double factor, tdee, bmr = 0;
	double intHeightCm = 0;
	double intWeightKgs = 0;
	int intAge = 0;
	double bodyfat = 0;
	double intWaistCm = 0;
	double intNeckCm = 0;
	double intHipsCm = 0;
	static boolean boolGender = true;
	RadioButton genderMale;
	RadioButton genderFemale;
	RadioGroup mRadioGroup;

	String category = "";

	static int genderChoice = -1;

	EditText heightCm, weightKgs, age, waistCm, neckCm, hipsCm;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fiometric);
		findViewById(R.id.main).requestFocus();

		calculate = (Button) findViewById(R.id.bCalculate);
		spinner = (Spinner) findViewById(R.id.sDActivity);

		genderMale = (RadioButton) findViewById(R.id.rbMale);
		genderFemale = (RadioButton) findViewById(R.id.rbFemale);
		mRadioGroup = (RadioGroup) findViewById(R.id.group1);

		mRadioGroup.setOnCheckedChangeListener(this);

		heightCm = (EditText) findViewById(R.id.etHeightCm);
		weightKgs = (EditText) findViewById(R.id.etWeightKgs);
		waistCm = (EditText) findViewById(R.id.etWaistCm);
		neckCm = (EditText) findViewById(R.id.etNeckCm);
		hipsCm = (EditText) findViewById(R.id.etHipsCm);
		age = (EditText) findViewById(R.id.etAge);

		AdView adview = (AdView) findViewById(R.id.ad);
		AdRequest re = new AdRequest();
		// re.setTesting(true);
		adview.loadAd(re);

		// show keyboard
		/*
		 * InputMethodManager mgr = (InputMethodManager)
		 * getSystemService(Context.INPUT_METHOD_SERVICE);
		 * mgr.showSoftInput(mRadioGroup, InputMethodManager.SHOW_IMPLICIT);
		 */

		// amBmi = (TextView) findViewById(R.id.tvBmiResult);

		bmrTxt = (TextView) findViewById(R.id.tvBMRTxt);
		tdeeTxt = (TextView) findViewById(R.id.tvTDEETxt);
		bfTxt = (TextView) findViewById(R.id.tvBfTxt);
		bfCategory = (TextView) findViewById(R.id.tvBfCategory);

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

		/*
		 * genderMale.setOnClickListener(new View.OnClickListener() { public
		 * void onClick(View z) { boolGender = true; }
		 * 
		 * });
		 * 
		 * genderFemale.setOnClickListener(new View.OnClickListener() { public
		 * void onClick(View x) { boolGender = false; } });
		 */

		calculate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View w) { // TODO
											// Auto-generated
											// method
											// stub

				String stringHeightCm = heightCm.getText().toString();
				String stringWeightKgs = weightKgs.getText().toString();
				String stringAge = age.getText().toString();
				String stringWaistCm = waistCm.getText().toString();
				String stringNeckCm = neckCm.getText().toString();
				String stringHipsCm = hipsCm.getText().toString();

				double htCm = 0.5;
				double wtKgs = 0.5;
				double nckCm = 0.5;
				double wstCm = 0.5;
				double hpsCm = 0.5;

				intAge = 0;

				// if female
				if (!boolGender) {
					try {

						htCm = Double.parseDouble(stringHeightCm);

						wtKgs = Double.parseDouble(stringWeightKgs);
						intAge = Integer.parseInt(stringAge);
						nckCm = Double.parseDouble(stringNeckCm);
						wstCm = Double.parseDouble(stringWaistCm);
						hpsCm = Double.parseDouble(stringHipsCm);

					} catch (NumberFormatException nfe) {
						System.out.println("Could not parse " + nfe);
					}

					calcFemale(htCm, wtKgs, intAge, nckCm, wstCm, hpsCm);

				} else if (boolGender) { // if male

					try {

						htCm = Double.parseDouble(stringHeightCm);

						wtKgs = Double.parseDouble(stringWeightKgs);
						intAge = Integer.parseInt(stringAge);
						nckCm = Double.parseDouble(stringNeckCm);
						wstCm = Double.parseDouble(stringWaistCm);

					} catch (NumberFormatException nfe) {
						System.out.println("Could not parse " + nfe);
					}

					calcMale(htCm, wtKgs, intAge, nckCm, wstCm);

				} else {
					bmr = -1;
					bmrTxt.setText("error " + bmr);

				}

				tdee = (double) Math.round(100 * (bmr * factor)) / 100; // rounding
																		// tdee

				// heightFt.setText("");
				// heightIn.setText("");
				// weightLbs.setText("");
				// age.setText("");
				bmrTxt.setText("Your BMR is " + bmr);
				tdeeTxt.setText("Your TDEE is " + tdee);
				bfTxt.setText("Body Fat " + bodyfat + "%");
				bfCategory.setText("Body Fat Category: " + category);

				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(neckCm.getWindowToken(), 0);
				imm.hideSoftInputFromWindow(hipsCm.getWindowToken(), 0);

			}
		});

	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkedId) {
		switch (checkedId) {
		case R.id.rbMale:
			boolGender = true;
			hipsCm.setVisibility(View.GONE); // <<<<<<<<<<<<<<<<<<<<
			break;
		case R.id.rbFemale:
			boolGender = false;
			hipsCm.setVisibility(View.VISIBLE); // <<<<<<<<<<<<<<<<<<<<
			break;
		}
	}

	public void calcFemale(double htCm, double wtKgs, int intAge, double nckCm,
			double wstCm, double hpsCm) {
		intHeightCm = htCm;
		bmr = (double) Math.round(100 * (655 + (9.6 * wtKgs)
				+ (1.8 * intHeightCm) - (4.7 * intAge))) / 100;
		// rounding to .0

		bodyfat = (double) Math
				.round(10 * (495 / (1.29579 - .35004 * Math.log10(wstCm + hpsCm
						- nckCm) + .22100 * Math.log10(htCm)) - 450)) / 10;
		// rounding to .0

		if (bodyfat >= 9 && bodyfat <= 13.99) {
			category = "Essential Fat";
		} else if (bodyfat >= 14 && bodyfat <= 20.99) {
			category = "Athletic";
		} else if (bodyfat >= 21 && bodyfat <= 24.99) {
			category = "Fit";
		} else if (bodyfat >= 25 && bodyfat <= 31.99) {
			category = "Acceptable";
		} else if (bodyfat >= 32) {
			category = "Obese";
		} else {
			category = "Critical";
		}

	}

	public void calcMale(double htCm, double wtKgs, int intAge, double nckCm,
			double wstCm) {
		intHeightCm = htCm;
		bmr = (double) Math.round(100 * (66 + (13.7 * wtKgs)
				+ (5 * intHeightCm) - (6.8 * intAge))) / 100;
		// rounding to .0

		bodyfat = (double) Math.round(10 * (495 / (1.0324 - .19077 * Math
				.log10(wstCm - nckCm) + .15456 * Math.log10(htCm)) - 450)) / 10;
		// rounding to .0

		if (bodyfat >= 1 && bodyfat <= 5.99) {
			category = "Essential Fat";
		} else if (bodyfat >= 6 && bodyfat <= 13.99) {
			category = "Athletic";
		} else if (bodyfat >= 14 && bodyfat <= 17.99) {
			category = "Fit";
		} else if (bodyfat >= 18 && bodyfat <= 25.99) {
			category = "Acceptable";
		} else if (bodyfat >= 26) {
			category = "Obese";
		} else {
			category = "Critical";
		}

	}

}
