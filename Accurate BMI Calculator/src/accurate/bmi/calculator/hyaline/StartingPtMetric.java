package accurate.bmi.calculator.hyaline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartingPtMetric extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.startingptm);

		Button bImperical = (Button) findViewById(R.id.bUS);
		Button bBmi = (Button) findViewById(R.id.bBMI);
		Button bBf = (Button) findViewById(R.id.bBF);
		Button bFiN = (Button) findViewById(R.id.bFiN);
		Button bBmrTdee = (Button) findViewById(R.id.bBmrTdee);
		Button bWtH = (Button) findViewById(R.id.bWtH);
		
		bImperical.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(v.getContext(), StartingPoint.class);
				startActivityForResult(myIntent, 0);
				finish();
			}
		});
		
		bBmi.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(v.getContext(), BMImetric.class);
				startActivityForResult(myIntent, 0);
			}
		});
		
		bBf.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(v.getContext(), BodyfatMetric.class);
				startActivityForResult(myIntent, 0);
			}
		});
		bFiN.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(v.getContext(), FIOmetric.class);
				startActivityForResult(myIntent, 0);
			}
		});
		
		bBmrTdee.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(v.getContext(), BmrTdeeMetric.class);
				startActivityForResult(myIntent, 0);
			}
		});
		
		bWtH.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(v.getContext(), WaistToHipMetric.class);
				startActivityForResult(myIntent, 0);
			}
		});
		

	}

}
