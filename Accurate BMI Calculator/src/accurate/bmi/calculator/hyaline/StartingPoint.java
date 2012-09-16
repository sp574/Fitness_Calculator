package accurate.bmi.calculator.hyaline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartingPoint extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.startingpt);

		Button bMetric = (Button) findViewById(R.id.bMetric);
		Button bBmi = (Button) findViewById(R.id.bBMI);
		Button bBf = (Button) findViewById(R.id.bBF);
		Button bFiN = (Button) findViewById(R.id.bFiN);
		Button bBmrTdee = (Button) findViewById(R.id.bBmrTdee);
		Button bWtH = (Button) findViewById(R.id.bWtH);
		
		bMetric.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(v.getContext(), StartingPtMetric.class);
				startActivityForResult(myIntent, 0);
				finish();
			}
		});
		
		bBmi.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(v.getContext(), AccurateBMICalculatorActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});
		
		bBf.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(v.getContext(), BodyfatImperical.class);
				startActivityForResult(myIntent, 0);
			}
		});
		bFiN.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(v.getContext(), FourInOne.class);
				startActivityForResult(myIntent, 0);
			}
		});
		
		bBmrTdee.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(v.getContext(), BmrTdee.class);
				startActivityForResult(myIntent, 0);
			}
		});
		
		bWtH.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(v.getContext(), WaistToHip.class);
				startActivityForResult(myIntent, 0);
			}
		});
		

	}

}
