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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BodyfatImperical extends Activity {

	TextView bfTxt, bfCategory;
	Button calculate;
	double bodyfat = 0;
	double intHeightIn = 0;
	double intWeightLbs = 0;
	double intWaistIn = 0;
	double intNeckIn = 0;
	double intHipsIn = 0;
	EditText heightFt, heightIn, weightLbs, waistIn, neckIn, hipsIn;
	String category = "";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.bfimperical);
		findViewById(R.id.main).requestFocus();

		calculate = (Button) findViewById(R.id.bCalculate);

		heightFt = (EditText) findViewById(R.id.etHeightFt);
		heightIn = (EditText) findViewById(R.id.etHeightIn);
		weightLbs = (EditText) findViewById(R.id.etWeightLbs);
		waistIn = (EditText) findViewById(R.id.etWaistIn);
		neckIn = (EditText) findViewById(R.id.etNeckIn);
		hipsIn = (EditText) findViewById(R.id.etHipsIn);

		AdView adview = (AdView) findViewById(R.id.ad);
		AdRequest re = new AdRequest();
		// re.setTesting(true);
		adview.loadAd(re);

		// show keyboard
		/*
		 * InputMethodManager mgr = (InputMethodManager)
		 * getSystemService(Context.INPUT_METHOD_SERVICE);
		 * mgr.showSoftInput(heightFt, InputMethodManager.SHOW_IMPLICIT);
		 */

		// amBmi = (TextView) findViewById(R.id.tvBmiResult);

		bfTxt = (TextView) findViewById(R.id.tvBfTxt);
		bfCategory = (TextView) findViewById(R.id.tvBfCategory);

		calculate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View w) { // TODO
											// Auto-generated
											// method
											// stub

				String stringHeightFt = heightFt.getText().toString();
				String stringHeightIn = heightIn.getText().toString();
				String stringWaistIn = waistIn.getText().toString();
				String stringWeightLbs = weightLbs.getText().toString();
				String stringNeckIn = neckIn.getText().toString();
				String stringHipsIn = hipsIn.getText().toString();

				double htFt = 0.5;
				double htIn = 0.5;
				// double wtLbs = 0.5;
				double nckIn = 0.5;
				double wstIn = 0.5;
				double hpsIn = 0.5;

				// if female
				if (stringHeightFt.length() > 0 && stringWeightLbs.length() > 0
						&& stringWaistIn.length() > 0
						&& stringNeckIn.length() > 0
						&& stringHipsIn.length() > 0) {

					try {

						htFt = Double.parseDouble(stringHeightFt);
						if (stringHeightIn.length() == 0) {
							htIn = 0;
						} else {
							htIn = Double.parseDouble(stringHeightIn);
						}
						// wtLbs = Double.parseDouble(stringWeightLbs);
						htFt = Double.parseDouble(stringHeightFt);
						nckIn = Double.parseDouble(stringNeckIn);
						wstIn = Double.parseDouble(stringWaistIn);
						hpsIn = Double.parseDouble(stringHipsIn);

					} catch (NumberFormatException nfe) {
						System.out.println("Could not parse " + nfe);
					}
					// htIn = (stringHeightIn.equals("null") ? 0 : htIn);

					calcFemale(htFt, htIn, nckIn, wstIn, hpsIn);

				} else if (stringHeightFt.length() > 0
						&& stringWeightLbs.length() > 0
						&& stringWaistIn.length() > 0
						&& stringNeckIn.length() > 0
						&& stringHipsIn.length() == 0) { // if male

					try {

						htFt = Double.parseDouble(stringHeightFt);
						if (stringHeightIn.length() == 0) {
							htIn = 0;
						} else {
							htIn = Double.parseDouble(stringHeightIn);
						}
						// wtLbs = Double.parseDouble(stringWeightLbs);
						htFt = Double.parseDouble(stringHeightFt);
						nckIn = Double.parseDouble(stringNeckIn);
						wstIn = Double.parseDouble(stringWaistIn);
						hpsIn = Double.parseDouble(stringHipsIn);

					} catch (NumberFormatException nfe) {
						System.out.println("Could not parse " + nfe);
					}

					calcMale(htFt, htIn, nckIn, wstIn);

				} else {
					bodyfat = -1;
					bfTxt.setText("error " + bodyfat + "%");

				}

				bfTxt.setText("Body Fat " + bodyfat + "%");
				bfCategory.setText("Body Fat Category: " + category);

				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(neckIn.getWindowToken(), 0);
				imm.hideSoftInputFromWindow(hipsIn.getWindowToken(), 0);

			}
		});

	}

	public void calcFemale(double htFt, double htIn, double nckIn,
			double wstIn, double hpsIn) {
		intHeightIn = 12 * htFt + htIn;
		bodyfat = (double) Math.round(100 * (495 / (1.29579 - .35004 * Math
				.log10(2.54 * (wstIn + hpsIn - nckIn)) + .22100 * Math
				.log10(intHeightIn * 2.54)) - 450)) / 100;
		// rounding to .00

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

	public void calcMale(double htFt, double htIn, double nckIn, double wstIn) {
		intHeightIn = 12 * htFt + htIn;
		bodyfat = (double) Math.round(100 * (495 / (1.0324 - .19077 * Math
				.log10(2.54 * (wstIn - nckIn)) + .15456 * Math
				.log10(intHeightIn * 2.54)) - 450)) / 100;
		// rounding to .00

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
