package org.ai.mobile.diabdiet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class InputProblem extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_problem);
        
        //Get button
        Button b = (Button) findViewById(R.id.form_btn_next);
        b.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				//Save input to external file
				
				//Go to solve screen
				Intent i = new Intent(InputProblem.this, SolveProblem.class);
				startActivity(i);
			}
		});
    }
}
