package org.ai.diabdiet.mobile;

import org.ai.mobile.diabdiet.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SolveProblem extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.solve_problem);
        
        //Get say view
        TextView say = (TextView) findViewById(R.id.solve_say);
        say.setText("Hello there!");
        
        //Invoke solve operation
        
        //Display solved problem
        
	}
}
