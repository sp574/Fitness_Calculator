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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class WaistToHip extends Activity implements
		RadioGroup.OnCheckedChangeListener {

	TextView wthRatioTxt, riskTxt;
	Button calculate;
	double ratio = 0;
	double dWaistIn = 0;
	double dHipIn = 0;
	RadioButton genderMale;
	RadioButton genderFemale;
	RadioGroup mRadioGroup;
	static boolean boolGender = true;

	EditText waistIn, hipIn;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wthip);
		findViewById(R.id.main).requestFocus();

		calculate = (Button) findViewById(R.id.bCalculate);

		waistIn = (EditText) findViewById(R.id.etWaistIn);
		hipIn = (EditText) findViewById(R.id.etHipIn);

		riskTxt = (TextView) findViewById(R.id.tvRiskTxt);
		wthRatioTxt = (TextView) findViewById(R.id.tvWTHRatioTxt);

		genderMale = (RadioButton) findViewById(R.id.rbMale);
		genderFemale = (RadioButton) findViewById(R.id.rbFemale);
		mRadioGroup = (RadioGroup) findViewById(R.id.group1);
		mRadioGroup.setOnCheckedChangeListener(this);

		AdView adview = (AdView) findViewById(R.id.ad);
		AdRequest re = new AdRequest();
		// re.setTesting(true);
		adview.loadAd(re);

		calculate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View w) { // TODO
											// Auto-generated
											// method
											// stub

				String stringWaistIn = waistIn.getText().toString();
				String stringHipIn = hipIn.getText().toString();

				double wstIn = 0.5;
				double hpIn = 0.5;

				// if female
				if (!boolGender) {
					try {

						wstIn = Double.parseDouble(stringWaistIn);
						hpIn = Integer.parseInt(stringHipIn);

					} catch (NumberFormatException nfe) {
						System.out.println("Could not parse " + nfe);
					}

					ratio = (double) Math.round(100 * (wstIn / hpIn)) / 100;

					if (ratio <= 0.80) { // low risk
						riskTxt.setText("Low Health Risk");
					} else if (ratio > 0.80 && ratio <= 0.85) { // moderate
						riskTxt.setText("Moderate Health Risk");
					} else if (ratio > 0.85) { // high
						riskTxt.setText("High Health Risk");
					}

				} else if (boolGender) { // if male

					try {

						wstIn = Double.parseDouble(stringWaistIn);
						hpIn = Integer.parseInt(stringHipIn);

					} catch (NumberFormatException nfe) {
						System.out.println("Could not parse " + nfe);
					}

					ratio = (double) Math.round(100 * (wstIn / hpIn)) / 100;

					if (ratio <= 0.95) { // low risk
						riskTxt.setText("Low Health Risk");
					} else if (ratio > 0.95 && ratio <= 1.0) { // moderate
						riskTxt.setText("Moderate Health Risk");
					} else if (ratio > 1.0) { // high
						riskTxt.setText("High Health Risk");
					}

				} else {
					ratio = -1;
					wthRatioTxt.setText("error " + ratio);

				}

				wthRatioTxt.setText("Your W/H ratio: " + ratio);

				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(hipIn.getWindowToken(), 0);

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

}
