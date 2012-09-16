package accurate.bmi.calculator.hyaline;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

import accurate.bmi.calculator.hyaline.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class BMImetric extends Activity {

	String myBmiAm;
	TextView amBmi, bmiTxt;
	Button calculate;
	double bmiAm = 0;
	double intWeightKgs = 0;
	double intHeightCm = 0;
	EditText heightCm, weightKgs;
	ImageView bmiChart;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.bmimetric);
		findViewById(R.id.main).requestFocus();

		calculate = (Button) findViewById(R.id.bCalculate);

		weightKgs = (EditText) findViewById(R.id.etWeightKgs);
		heightCm = (EditText) findViewById(R.id.etHeightCm);

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

		bmiTxt = (TextView) findViewById(R.id.tvBmiText);

		calculate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View w) { // TODO
											// Auto-generated
											// method
											// stub

				String stringHeightCm = heightCm.getText().toString();
				String stringWeightKgs = weightKgs.getText().toString();

				double htCm = 0.5;
				double wtKgs = 0.5;

				// using cm and kgs
				try {

					htCm = Double.parseDouble(stringHeightCm);
					wtKgs = Double.parseDouble(stringWeightKgs);

				} catch (NumberFormatException nfe) {
					System.out.println("Could not parse " + nfe);
				}
				intHeightCm = htCm / 100;
				intWeightKgs = wtKgs;
				bmiAm = (double) Math.round(100 * intWeightKgs
						/ (intHeightCm * intHeightCm)) / 100;
				// rounding to .00

				// bmiAm = -1;
				// bmiTxt.setText("error");

				bmiTxt.setText("" + bmiAm);

				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(weightKgs.getWindowToken(), 0);

				if (bmiAm <= 18.5) { // underweight
					bmiTxt.setBackgroundResource(R.drawable.bmi_underw);
					// bmiChart.setImageResource(R.drawable.bmi_underw);
				} else if (bmiAm >= 18.51 && bmiAm <= 24.99) { // normal
					bmiTxt.setBackgroundResource(R.drawable.bmi_normal);
					// bmiChart.setImageResource(R.drawable.bmi_normal);
				} else if (bmiAm >= 25.0 && bmiAm <= 29.99) { // overweight
					bmiTxt.setBackgroundResource(R.drawable.bmi_overw);
					// bmiChart.setImageResource(R.drawable.bmi_overw);
				} else if (bmiAm >= 30.0 && bmiAm <= 34.99) { // obese class 1
					bmiTxt.setBackgroundResource(R.drawable.bmi_ocl1);
					// bmiChart.setImageResource(R.drawable.bmi_ocl1);
				} else if (bmiAm >= 35.0 && bmiAm <= 39.99) { // obese class 2
					bmiTxt.setBackgroundResource(R.drawable.bmi_ocl2);
					// bmiChart.setImageResource(R.drawable.bmi_ocl2);
				} else if (bmiAm >= 40.0) { // morbid obese
					bmiTxt.setBackgroundResource(R.drawable.bmi_mobese);
					// bmiChart.setImageResource(R.drawable.bmi_mobese);
				}
			}
		});

	}

}
