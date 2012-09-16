package accurate.bmi.calculator.hyaline;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

import accurate.bmi.calculator.hyaline.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BodyfatMetric extends Activity {

	// String myBmiAm;
	TextView bfTxt, bfCategory;
	Button calculate;
	double bodyfat = 0;
	double intHeightCm = 0;
	double intWeightKgs = 0;
	double intWaistCm = 0;
	double intNeckCm = 0;
	double intHipsCm = 0;
	EditText heightCm, weightKgs, waistCm, neckCm, hipsCm;
	String category = "";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.bfmetric);
		findViewById(R.id.main).requestFocus();

		calculate = (Button) findViewById(R.id.bCalculate);

		heightCm = (EditText) findViewById(R.id.etHeightCm);
		weightKgs = (EditText) findViewById(R.id.etWeightKgs);
		waistCm = (EditText) findViewById(R.id.etWaistCm);
		neckCm = (EditText) findViewById(R.id.etNeckCm);
		hipsCm = (EditText) findViewById(R.id.etHipsCm);

		AdView adview = (AdView) findViewById(R.id.ad);
		AdRequest re = new AdRequest();
		// re.setTesting(true);
		adview.loadAd(re);

		// show keyboard
		/*
		 * InputMethodManager mgr = (InputMethodManager)
		 * getSystemService(Context.INPUT_METHOD_SERVICE);
		 * mgr.showSoftInput(heightCm, InputMethodManager.SHOW_IMPLICIT);
		 */

		// amBmi = (TextView) findViewById(R.id.tvBmiResult);

		bfTxt = (TextView) findViewById(R.id.tvBfTxt);
		bfCategory = (TextView) findViewById(R.id.tvBfCategory);

		calculate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View w) { // TODO
											// Auto-generated
											// method
											// stub

				String stringHeightCm = heightCm.getText().toString();
				String stringWaistCm = waistCm.getText().toString();
				String stringWeightKgs = weightKgs.getText().toString();
				String stringNeckCm = neckCm.getText().toString();
				String stringHipsCm = hipsCm.getText().toString();

				double htCm = 0.5;
				// double wtKgs = 0.5;
				double nckCm = 0.5;
				double wstCm = 0.5;
				double hpsCm = 0.5;

				// if female
				if (stringHeightCm.length() > 0 && stringWeightKgs.length() > 0
						&& stringWaistCm.length() > 0
						&& stringNeckCm.length() > 0
						&& stringHipsCm.length() > 0) {

					try {

						// wtKgs = Double.parseDouble(stringWeightKgs);
						htCm = Double.parseDouble(stringHeightCm);
						nckCm = Double.parseDouble(stringNeckCm);
						wstCm = Double.parseDouble(stringWaistCm);
						hpsCm = Double.parseDouble(stringHipsCm);

					} catch (NumberFormatException nfe) {
						System.out.println("Could not parse " + nfe);
					}
					// htIn = (stringHeightIn.equals("null") ? 0 : htIn);

					calcFemale(htCm, nckCm, wstCm, hpsCm);

				} else if (stringHeightCm.length() > 0
						&& stringWeightKgs.length() > 0
						&& stringWaistCm.length() > 0
						&& stringNeckCm.length() > 0
						&& stringHipsCm.length() == 0) { // if male

					try {

						htCm = Double.parseDouble(stringHeightCm);

						// wtKgs = Double.parseDouble(stringWeightKgs);
						nckCm = Double.parseDouble(stringNeckCm);
						wstCm = Double.parseDouble(stringWaistCm);
						hpsCm = Double.parseDouble(stringHipsCm);

					} catch (NumberFormatException nfe) {
						System.out.println("Could not parse " + nfe);
					}

					calcMale(htCm, nckCm, wstCm);

				} else {
					bodyfat = -1;
					bfTxt.setText("error " + bodyfat);

				}

				bfTxt.setText("Your bodyfat is " + bodyfat + "%");
				bfCategory.setText("Body Fat Category: " + category);

				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(neckCm.getWindowToken(), 0);
				imm.hideSoftInputFromWindow(hipsCm.getWindowToken(), 0);

			}
		});
	}

	public void calcFemale(double htCm, double nckCm, double wstCm, double hpsCm) {

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

	public void calcMale(double htCm, double nckCm, double wstCm) {

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
