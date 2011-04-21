package org.ai.mobile.diabdiet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DiabDiet extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button main_solve 	= (Button) findViewById(R.id.main_solve);
        Button main_edit	= (Button) findViewById(R.id.main_edit);
        
        OnClickListener list;
        list = new OnClickListener() {			
			public void onClick(View v) {
				//New intents
				Intent i = new Intent(DiabDiet.this, InputProblem.class);
				startActivity(i);
			}
		};
		
		main_solve.setOnClickListener(list);

    }
}