package org.ai.diabdiet.mobile;

import org.ai.mobile.diabdiet.R;

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
        Button main_editk	= (Button) findViewById(R.id.main_editk);
        Button main_editm	= (Button) findViewById(R.id.main_editm);
        
        OnClickListener list;
        
        //Solve problem
        list = new OnClickListener() {			
			public void onClick(View v) {
				//New intents
				Intent i = new Intent(DiabDiet.this, InputProblem.class);
				startActivity(i);
			}
		};		
		main_solve.setOnClickListener(list);
		
		//Edit knowledge
		list = new OnClickListener() {
			public void onClick(View v) {
				//New intents
				Intent i = new Intent(DiabDiet.this, EditKnowledge.class);
				startActivity(i);
			}
		};
		main_editk.setOnClickListener(list);
		
		//Edit diet menu
		list = new OnClickListener() {
			public void onClick(View v) {
				//New intents
				Intent i = new Intent(DiabDiet.this, DietMenuList.class);
				startActivity(i);
			}
		};
		main_editm.setOnClickListener(list);

    }
}