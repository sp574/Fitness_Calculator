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

public class WaistToHipMetric extends Activity implements
		RadioGroup.OnCheckedChangeListener {

	TextView wthRatioTxt, riskTxt;
	Button calculate;
	double ratio = 0;
	double dWaistCm = 0;
	double dHipCm = 0;
	RadioButton genderMale;
	RadioButton genderFemale;
	RadioGroup mRadioGroup;
	static boolean boolGender = true;

	EditText waistCm, hipCm;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wthipmetric);
		findViewById(R.id.main).requestFocus();

		calculate = (Button) findViewById(R.id.bCalculate);

		waistCm = (EditText) findViewById(R.id.etWaistCm);
		hipCm = (EditText) findViewById(R.id.etHipCm);

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

				String stringWaistCm = waistCm.getText().toString();
				String stringHipCm = hipCm.getText().toString();

				double wstCm = 0.5;
				double hpCm = 0.5;

				// if female
				if (!boolGender) {
					try {

						wstCm = Double.parseDouble(stringWaistCm);
						hpCm = Integer.parseInt(stringHipCm);

					} catch (NumberFormatException nfe) {
						System.out.println("Could not parse " + nfe);
					}

					ratio = (double) Math.round(100 * (wstCm / hpCm)) / 100;

					if (ratio <= 0.80) { // low risk
						riskTxt.setText("Low Health Risk");
					} else if (ratio > 0.80 && ratio <= 0.85) { // moderate
						riskTxt.setText("Moderate Health Risk");
					} else if (ratio > 0.85) { // high
						riskTxt.setText("High Health Risk");
					}

				} else if (boolGender) { // if male

					try {

						wstCm = Double.parseDouble(stringWaistCm);
						hpCm = Integer.parseInt(stringHipCm);

					} catch (NumberFormatException nfe) {
						System.out.println("Could not parse " + nfe);
					}

					ratio = (double) Math.round(100 * (wstCm / hpCm)) / 100;

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
				imm.hideSoftInputFromWindow(hipCm.getWindowToken(), 0);

			}
		});

		clearET();
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

	public void clearET() {

		waistCm.setOnTouchListener(new OnTouchListener() {
			InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				waistCm.setText("");
				mgr.showSoftInput(waistCm, InputMethodManager.SHOW_IMPLICIT);
				return false;
			}
		});
		hipCm.setOnTouchListener(new OnTouchListener() {
			InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				hipCm.setText("");
				mgr.showSoftInput(hipCm, InputMethodManager.SHOW_IMPLICIT);
				return false;
			}
		});
	}
}
